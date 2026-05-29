# Práctica Final - Chinchón - Jorge González Burgos - 1ºDAM

## Normas del Juego

El objetivo del juego es ser el jugador con menos puntos al final de la partida, formando combinaciones de cartas o sacar un chinchón.

Se juega con la baraja española, quitando los 8 y 9. Pueden jugar de 2 a 5 personas (también con IA).

Cada jugador empieza con 7 cartas. En su turno puede robar del mazo (boca abajo) o del descarte (boca arriba). Después de robar, descarta una carta de su mano y, si puede, decide si cerrar ronda. Para cerrar, la carta sobrante debe valer 5 o menos. Las combinaciones válidas son:

- **Iguales:** mínimo tres cartas del mismo número.
- **Escalera:** mínimo tres cartas consecutivas del mismo palo (el 7 continúa con el 10, pero el 12 no continúa con el 1).
- **Chinchón:** siete cartas consecutivas del mismo palo — victoria inmediata.

Al cerrar ronda, el resto de jugadores suman a su puntuación el valor de sus cartas no combinadas. El jugador que cierra con 7 cartas combinadas descuenta 10 puntos. El jugador que alcance o supere la puntuación máxima es eliminado. La partida termina cuando solo queda un jugador o alguien saca chinchón.

---

## Salida por consola

### Menú principal
```
----------------------------------
             Chinchón             
Jorge González Burgos - 1ºDAM
----------------------------------
--------------------------------
- 1. Un jugador ----------------
- 2. Dos jugadores (sin IA) ----
- 3. Dos jugadores (con IA) ----
- 4. Tres jugadores (sin IA) ---
- 5. Tres jugadores (con IA) ---
- 6. Cuatro jugadores (sin IA) -
- 7. Cuatro jugadores (con IA) -
- 8. Cinco jugadores (sin IA) --
- 9. Salir ---------------------
```

### Ejemplo de turno
```
===== Turno 1 =====

--- Turno de Jorge ---
Carta en el descarte: 5 📀
[0] 1 📀
[1] 3 🗡️
[2] 4 📀
[3] 7 🍷
[4] 10 🍾
[5] 11 📀
[6] 12 🗡️
¿Robas del (1)MAZO o del (2)DESCARTE (5 📀)?
```

### Resultados de ronda
```
===== RESULTADOS DE LA RONDA =====
Jorge cierra la ronda (+3 puntos). Puntuación: 3
IA suma 28 puntos por cartas no combinadas. Puntuación total: 28
Puntuación límite: 100
==================================
```

---

## Estructura del proyecto

```
ChinchonJorgeGonzalezBurgos/
├── src/
│   ├── app/                    ← lógica de partida y entrada por consola
│   │   ├── ConsoleInput.java
│   │   ├── IAMatch.java
│   │   ├── IMatch.java
│   │   ├── Main.java
│   │   ├── Match.java
│   │   ├── Menu.java
│   │   └── NoIAMatch.java
│   └── resources/              ← modelo de datos del juego
│       ├── Card.java
│       ├── Deck.java
│       ├── IA.java
│       ├── IPlayer.java
│       ├── Number.java
│       ├── Player.java
│       └── Suit.java
├── test/
│   └── resourceTest/
│       └── PlayerTest.java     ← pruebas unitarias JUnit 5
├── assets/
│   ├── diagramaChinchon.drawio.png
│   └── pruebasUnitarias.png
└── README.md
```
---

## **`app`** — Responsable de toda la lógica de ejecución de la aplicación, interacción con el usuario y control general de las partidas.

### **Main**:
Clase principal del programa y punto de entrada de la aplicación. Su responsabilidad es mínima y está completamente desacoplada de la lógica del juego: únicamente crea una instancia de **Menu** e inicia el flujo principal de ejecución. Esto permite mantener separada la inicialización de la aplicación del resto de componentes del sistema.

---

### **Menu**:
Gestiona toda la interfaz de usuario basada en consola.

Funciones principales:

* Mostrar el menú principal de opciones.
* Solicitar el modo de juego.
* Pedir nombres de jugadores.
* Validar entradas del usuario.
* Delegar la creación de partidas mediante la factoría Match.createMatch().

El menú se ejecuta mediante un bucle continuo hasta que el usuario decide salir de la aplicación.

---

### **Match**:
Clase abstracta principal del proyecto y núcleo de toda la lógica del juego. Implementa el flujo completo de una partida:

* Configuración inicial.
* Reparto de cartas.
* Gestión de rondas.
* Turnos secuenciales.
* Control del descarte.
* Evaluación de cierres.
* Cálculo de puntuaciones.
* Eliminación de jugadores.

También incluye el método factoría: `createMatch()` que permite generar dinámicamente distintos tipos de partidas sin acoplar el menú a implementaciones concretas.
Esta clase actúa además como base del patrón de diseño Builder, definiendo la estructura fija del algoritmo principal del juego.

--- 

### **IAMatch**:
Subclase concreta de Match. Especializa la configuración inicial de la partida añadiendo automáticamente una instancia de IA a la lista de jugadores.
Su objetivo es habilitar modos de juego contra la máquina sin modificar la lógica principal heredada desde Match.

---

### **NoIAMatch**:
Subclase concreta de Match orientada al multijugador local. A diferencia de IAMatch, únicamente añade jugadores humanos reales a la partida.
Toda la lógica general del juego se reutiliza directamente desde la superclase.

---

### **IMatch**:
Interfaz que define el contrato obligatorio que debe cumplir cualquier tipo de partida. Declara métodos esenciales como:

* start()
* distributeCards()
* playNewRound()
* playTurn()
* showRoundResults()
* eliminatePlayer()

Gracias a esta abstracción, el sistema trabaja desacoplado de implementaciones concretas y favorece la extensibilidad futura del proyecto.

---

### **ConsoleInput**:
Clase utilitaria encargada de centralizar toda la lectura de datos por consola. Está implementada mediante el patrón Singleton para garantizar la existencia de una única instancia compartida de Scanner(System.in) durante toda la ejecución del programa.
Sus responsabilidades incluyen:

* Validación segura de entradas.
* Gestión de excepciones de lectura.
* Conversión de tipos primitivos.
* Evitar fugas o conflictos en System.in.

Permite leer y validar:

* int
* double
* float
* byte
* char
* String
* boolean

---

## **`resources`** — Contiene todo el modelo de dominio del juego y las estructuras de datos principales. Representa las entidades reales del Chinchón y encapsula la lógica interna asociada a las cartas, jugadores y combinaciones.

### **Player**:
Representa a un jugador humano de la partida. Gestiona:

* La mano de cartas.
* La puntuación acumulada.
* El robo y descarte.
* La validación de combinaciones.
* El cálculo de puntuaciones sobrantes.

Incluye además la parte algorítmica más compleja del proyecto mediante un sistema de búsqueda recursiva con Backtracking, encargado de encontrar la combinación óptima de cartas que minimiza la puntuación restante.

---

### **IA**:
Subclase de Player que representa un jugador controlado automáticamente por la máquina. Su nombre queda fijado internamente como: `IA`

La lógica de su turno se ejecuta automáticamente desde Match, realizando:

* Robo automático de cartas.
* Selección de descartes.
* Evaluación de cierres.

Su comportamiento sigue una estrategia básica automatizada.

--- 

### **IPlayer**:
Interfaz que define las capacidades y operaciones obligatorias de cualquier jugador del sistema. Incluye métodos como:

* draw()
* discard()
* canClose()
* calculateCloseScore()
* getLeftoverScore()

Gracias a esta interfaz, tanto Player como IA pueden tratarse de forma polimórfica dentro de las colecciones de jugadores.

---

### **Card**:
Clase que representa una carta individual de la baraja. Cada carta está compuesta por:

* Un número (Number)
* Un palo (Suit)

Actúa como un objeto de valor (Value Object) inmutable dentro del sistema.

---

### **Deck**:
Representa la baraja completa del juego. Se encarga de:

* Generar las 40 cartas válidas.
* Mantener el mazo de robo.
* Barajar aleatoriamente las cartas.
* Controlar las cartas restantes.

La inicialización de la baraja se realiza mediante: `fillDeck()` y el mezclado mediante: `shuffle()`

---

### **Number**:
Enum fuertemente tipado que representa los números válidos de la baraja española utilizada en el proyecto. Incluye:

* UNO a SIETE
* SOTA
* CABALLO
* REY

Cada valor almacena:

* Su valor lógico.
* Su puntuación.
* Su posición ordinal para validar escaleras.

El proyecto elimina explícitamente los números 8 y 9 siguiendo las reglas establecidas del Chinchón.

---

### **Suit**:
Enum que representa los cuatro palos de la baraja española:

* COINS
* SWORD
* CUP
* CLUBS

Cada palo incluye información adicional utilizada para mejorar la visualización en consola:

* Emoji representativo.
* Código ANSI de color.
* Nombre textual.

--- 

### Diagrama de Clases UML

![Diagrama de Clases UML](assets/diagramaChinchon.drawio.png)

---

## Patrones de Diseño

### 1. Singleton — `ConsoleInput`

`ConsoleInput` implementa el patrón Singleton para garantizar que exista una única instancia que gestiona toda la entrada por consola durante la ejecución del programa. Esto evita tener múltiples `Scanner` abiertos sobre `System.in`, lo que podría provocar comportamientos inesperados.

**Fragmento de código:**
```java
// ConsoleInput.java
public class ConsoleInput {
    private static ConsoleInput instance;

    private ConsoleInput() { /* constructor privado */ }

    public static ConsoleInput getInstance() {
        if (instance == null) {
            instance = new ConsoleInput();
        }
        return instance;
    }
}
```

Toda la aplicación (Menu, Match) obtiene la consola con `ConsoleInput.getInstance()`, garantizando acceso centralizado y sin duplicidades.

---

### 2. Factory — `Match.createMatch(int option)`

El método `createMatch()` en `Match` actúa como factoría: recibe la opción elegida por el usuario en el menú y devuelve la instancia concreta de `IMatch` correspondiente (`IAMatch` o `NoIAMatch`), sin que el cliente (`Menu`) necesite conocer los constructores específicos.

**Fragmento de código:**
```java
// Match.java
public IMatch createMatch(int option) {
    switch (option) {
        case 1 -> { return new IAMatch(console.readString()); }
        case 2 -> { return new NoIAMatch(name1, name2); }
        case 3 -> { return new IAMatch(name1, name2); }
        // ...
        default -> { return null; }
    }
}
```

Desacopla la creación de objetos de su uso. `Menu` solo llama a `createMatch()` y trabaja con la interfaz `IMatch`, sin depender de las clases concretas.

---

## Flujo de la partida

```
Main.main()
  └─ Menu.startGame()
       └─ Match.createMatch(option)      ← factoría: devuelve IAMatch o NoIAMatch
            └─ IMatch.start()
                 ├─ configureMaxScore()  ← el usuario fija la puntuación límite
                 └─ bucle mientras queden jugadores y no haya chinchón
                      ├─ playNewRound()
                      │    ├─ distributeCards()   ← nueva baraja, reparto de 7 cartas, descarte inicial
                      │    └─ bucle de turnos
                      │         └─ playTurn(player, turn)
                      │              ├─ [humano] playTurnPerson()
                      │              │    ├─ muestra descarte y mano
                      │              │    ├─ roba del mazo o del descarte
                      │              │    ├─ descarta una carta
                      │              │    └─ si turn > 1 y canClose() → ofrece cerrar
                      │              └─ [IA] playTurnIA()
                      │                   ├─ roba siempre del mazo
                      │                   └─ descarta aleatoriamente
                      ├─ si alguien cierra → showRoundResults()
                      │    ├─ puntuación del que cierra (calculateCloseScore)
                      │    │    ├─ Chinchón  → Integer.MIN_VALUE (victoria inmediata)
                      │    │    ├─ 7 combinadas → -10 puntos
                      │    │    └─ 6 combinadas → valor de la carta sobrante
                      │    └─ resto de jugadores → getLeftoverScore() (recursividad)
                      └─ eliminatePlayer()  ← elimina a quien alcanza la puntuación límite
```

La ejecución del juego comienza en **Main**, que inicia el menú principal gestionado por Menu. Desde allí, el usuario selecciona el modo de juego y `Match.createMatch()`  actúa como factoría para generar dinámicamente una partida del tipo **IAMatch** o **NoIAMatch**. Una vez creada, el método `start()` controla todo el ciclo de vida de la partida: configura la puntuación límite, ejecuta rondas sucesivas y mantiene el juego activo mientras queden jugadores sin eliminar y no se produzca un Chinchón. En cada ronda se genera una nueva baraja, se reparten las cartas y comienza un bucle de turnos donde cada jugador roba y descarta cartas. Los jugadores humanos interactúan mediante consola, mientras que la IA realiza acciones automáticas. Cuando un jugador decide cerrar, el sistema calcula las puntuaciones utilizando algoritmos de validación y backtracking para encontrar las mejores combinaciones posibles. Finalmente, los jugadores que superan el límite de puntos son eliminados hasta que solo queda un ganador.

--- 

#### Lógica de combinaciones (recursividad en `Player`)

El método `findBestCombination()` busca de forma recursiva la combinación en la mano más conveniente, dejando solas las cartas de menor valor. Para cada tamaño posible de subconjunto (de mayor a menor) genera todos los subconjuntos posibles y comprueba si forman una escalera válida (`isStraight`) o un grupo de iguales válido (`isSameGroup`). Cuando encuentra una combinación válida, retira esas cartas y recursa con el resto. El resultado final son las cartas sobrantes de menor valor posible.

---

## Pruebas Unitarias

Las pruebas están implementadas en `test/resourceTest/PlayerTest.java` (fuera de `src`) con JUnit 5, y comprueban los métodos `isChinchon()`, `canClose()`, `calculateCloseScore()` y `getLeftoverScore()` de la clase `Player`.

### Enfoque: caja negra con apoyo de caja blanca

Las pruebas se han diseñado principalmente con **enfoque de caja negra**, ya que cada caso de prueba se ha elegido a partir del comportamiento esperado del método, no de cómo está implementado internamente:

- Se han identificado las distintas situaciones posibles: chinchón, combinación parcial, sin combinaciones, sobrante válido, sobrante inválido…
- Se han cubierto los **valores límite** clave: el umbral de valor 5 para el sobrante (`canClose`), los 7 valores consecutivos exactos (`isChinchon`), y la diferencia entre devolver `-10` y devolver el valor de la sobrante (`calculateCloseScore`).
- Se ha comprobado también el **camino de error**, verificando que `calculateCloseScore` lanza `IllegalStateException` cuando el jugador no puede cerrar.

Sin embargo, el conocimiento del código interno ha sido útil como **apoyo de caja blanca** en dos aspectos concretos:

- Saber que `isChinchon()` ordena las cartas por **ordinal del enum** (no por valor numérico) ha permitido diseñar el caso `SEIS → SOTA`, que tiene valores 6 y 10 pero ordinales 5 y 7, dejando un hueco que rompe la escalera. Sin conocer ese detalle interno, ese caso límite sería difícil de identificar solo con caja negra.
- Saber que `calculateCloseScore()` usa `Integer.MIN_VALUE` como señal interna de chinchón ha permitido verificar ese valor concreto en lugar de limitarse a comprobar que no lanza excepción.

### Tabla de pruebas por método

| Método | Prueba | Tipo | Descripción |
|---|---|---|---|
| `isChinchon()` | `isChinchon_sevenConsecutiveSameSuit_returnTrue` | Caja negra | 7 cartas consecutivas del mismo palo → `true` |
| `isChinchon()` | `isChinchon_sevenConsecutiveDifferentSuit_returnFalse` | Caja negra | 7 cartas consecutivas pero palo distinto → `false` |
| `isChinchon()` | `isChinchon_sevenNotConsecutiveSameSuit_returnFalse` | Caja blanca | Mismo palo, salto de ordinal SEIS→SOTA → `false` |
| `isChinchon()` | `isChinchon_lessThanSevenCards_returnFalse` | Caja negra | Menos de 7 cartas → `false` |
| `canClose()` | `canClose_chinchon_returnTrue` | Caja negra | Chinchón siempre puede cerrar → `true` |
| `canClose()` | `canClose_sixCombinatedLeftLessOrEqualThanFive_returnTrue` | Caja negra | Sobrante ≤ 5 → `true` (valor límite) |
| `canClose()` | `canClose_sixCombinatedLeftGreaterThanFive_returnFalse` | Caja negra | Sobrante > 5 → `false` (valor límite) |
| `canClose()` | `canClose_twoLeft_returnFalse` | Caja negra | Más de 1 sobrante → `false` |
| `calculateCloseScore()` | `calculateCloseScore_chinchon_returnMinValue` | Caja blanca | Chinchón devuelve `Integer.MIN_VALUE` (señal interna) |
| `calculateCloseScore()` | `calculateCloseScore_sevenCombinated_returnMinusTen` | Caja negra | 7 combinadas, sin sobrante → `-10` |
| `calculateCloseScore()` | `calculateCloseScore_oneLeft_returnLeftValue` | Caja negra | 1 sobrante de valor 2 → devuelve `2` |
| `calculateCloseScore()` | `calculateCloseScore_cannotClose_throwllegalStateException` | Caja negra | Sin combinaciones → lanza `IllegalStateException` |
| `getLeftoverScore()` | `getLeftoverScore_allCombinated_returnZero` | Caja negra | Todo combinado → `0` |
| `getLeftoverScore()` | `getLeftoverScore_noCombinations_returnSumAll` | Caja negra | Sin combinaciones → suma total de cartas |
| `getLeftoverScore()` | `getLeftoverScore_partialCombination_returnLeftSum` | Caja negra | Combinación parcial → suma de sobrantes |

En resumen, el diseño de los casos es de **caja negra** (qué debe hacer el método), pero el conocimiento del código ha refinado algunos casos límite que de otro modo podrían haberse pasado por alto.

### Capturas de las pruebas

![Pruebas unitarias JUnit](assets/pruebasUnitarias.png)

---

## JavaDoc

El proyecto incorpora una documentación técnica completa mediante JavaDoc en todas las clases, interfaces y métodos públicos de los paquetes app y resources. El objetivo no es únicamente describir qué hace cada componente, sino también documentar de forma precisa su comportamiento interno, sus responsabilidades dentro de la arquitectura y las condiciones necesarias para su correcto funcionamiento. Cada clase incluye una descripción general de su propósito dentro del sistema, mientras que los métodos documentan detalladamente sus parámetros de entrada `@param`, valores de retorno `@return`, excepciones controladas `@throws` y, en los casos más complejos, el funcionamiento del algoritmo implementado.

La documentación resulta especialmente importante en métodos críticos relacionados con la lógica del juego y el algoritmo de backtracking. Por ejemplo, `Player.findBestCombination()` explica el proceso recursivo utilizado para explorar combinaciones válidas de cartas y minimizar la puntuación sobrante; `Match.createMatch()` documenta el comportamiento de la factoría y las variantes de partida disponibles; e `IPlayer.calculateCloseScore()` especifica todos los posibles escenarios de cierre de ronda, incluyendo la señalización especial de Chinchón mediante `Integer.MIN_VALUE`, la bonificación de -10 puntos y el lanzamiento de excepciones cuando un jugador intenta cerrar sin cumplir las condiciones reglamentarias. Gracias a esta documentación, el código resulta mucho más mantenible, escalable y sencillo de comprender para futuros desarrolladores o revisores del proyecto.
