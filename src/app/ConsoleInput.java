package app;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase utilitaria Singleton para la lectura de datos desde la consola.
 * Proporciona métodos para leer distintos tipos de datos con validación de entrada.
 */
public class ConsoleInput {
	private static ConsoleInput instance;
	private Scanner keyboard;
	
	private ConsoleInput () {
		this.keyboard=new Scanner(System.in);
	}
	
	/**
	 * Devuelve la única instancia de ConsoleInput (Singleton).
	 * @return instancia única de ConsoleInput
	 */
	public static ConsoleInput getInstance() {
		if (instance == null) {
			instance = new ConsoleInput();
		}
		return instance;
	}
	
	/**
	 * Limpia el buffer del teclado.
	 */
	private void cleanInput() { 
		keyboard.nextLine();
	}
	
	/**
	 * Lee y devuelve un entero introducido por el usuario.
	 * Repite la lectura hasta que el valor sea válido.
	 * @return entero introducido por el usuario
	 */
	public int readInt() { 
		int value=0;
		boolean error;
		
		do {
			try {
				value = keyboard.nextInt();
				error = false;
			}catch (InputMismatchException e) {
				System.err.printf("Error: Introduce un número entero válido entre %d y %d\n",Integer.MIN_VALUE,Integer.MAX_VALUE);
				error = true;
			}finally {
				cleanInput();
			}
		}while (error);
		return value;
	}
	
	/**
	 * Lee y devuelve un entero estrictamente menor que el límite superior indicado.
	 * @param upperBound límite superior exclusivo
	 * @return entero menor que upperBound
	 */
	public int readIntLessThan(int upperBound) { 
		int value;
		
		do {
			value = readInt();
			if (value >= upperBound) {
				System.err.println("Error: El número debe ser menor que "+upperBound);
			}
		}while (value>=upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un entero menor o igual que el límite superior indicado.
	 * @param upperBound límite superior inclusivo
	 * @return entero menor o igual que upperBound
	 */
	public int readIntLessOrEqualThan(int upperBound) {
		int value;
		
		do {
			value = readInt();
			if (value > upperBound) {
				System.err.println("Error: El número debe ser menor o igual que "+upperBound);
			}
		}while (value > upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un entero estrictamente mayor que el límite inferior indicado.
	 * @param lowerBound límite inferior exclusivo
	 * @return entero mayor que lowerBound
	 */
	public int readIntGreaterThan(int lowerBound) {
		int value;
		
		do {
			value = readInt();
			if (value <= lowerBound) {
				System.err.println("Error: El número debe ser mayor que "+lowerBound);
			}
		}while (value <= lowerBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un entero mayor o igual que el límite inferior indicado.
	 * @param lowerBound límite inferior inclusivo
	 * @return entero mayor o igual que lowerBound
	 */
	public int readIntGreaterOrEqualThan(int lowerBound) {
		int value;
		
		do {
			value = readInt();
			if (value < lowerBound) {
				System.err.println("Error: El número debe ser mayor o igual que "+lowerBound);
			}
		}while (value < lowerBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un entero dentro del rango [lowerBound, upperBound].
	 * Si lowerBound es mayor que upperBound, los intercambia automáticamente.
	 * @param lowerBound límite inferior inclusivo
	 * @param upperBound límite superior inclusivo
	 * @return entero dentro del rango indicado
	 */
	public int readIntInRange (int lowerBound, int upperBound) {
		int value;
		
		if (lowerBound > upperBound) { 
			int aux = lowerBound;
			lowerBound = upperBound;
			upperBound = aux;
		}		
		
		do {
			value = readInt();
			if (value <lowerBound || value >upperBound) {
				System.err.printf("Error: El número debe estar entre %d y %d\n",lowerBound,upperBound);
			}
		}while (value < lowerBound || value > upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un número decimal (double) introducido por el usuario.
	 * Repite la lectura hasta que el valor sea válido.
	 * @return double introducido por el usuario
	 */
	public double readDouble() {
		double value=0;
		boolean error;
		
		do {
			try {
				value = keyboard.nextDouble();
				error = false;
			}catch (InputMismatchException e) {
				System.err.println("Error: Introduce un número decimal.");
				error = true;
			}finally {
				cleanInput();
			}
		}while (error);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un double estrictamente menor que el límite superior indicado.
	 * @param upperBound límite superior exclusivo
	 * @return double menor que upperBound
	 */
	public double readDoubleLessThan(double upperBound) {
		double value;
		
		do {
			value = readDouble();
			if (value >=upperBound) {
				System.err.println("Error: El número debe ser menor que "+upperBound);
			}
		}while (value>=upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un double menor o igual que el límite superior indicado.
	 * @param upperBound límite superior inclusivo
	 * @return double menor o igual que upperBound
	 */
	public double readDoubleLessOrEqualThan(double upperBound) {
		double value;
		
		do {
			value = readDouble();
			if (value >upperBound) {
				System.err.println("Error: El número debe ser menor o igual que "+upperBound);
			}
		}while (value>upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un double estrictamente mayor que el límite inferior indicado.
	 * @param lowerBound límite inferior exclusivo
	 * @return double mayor que lowerBound
	 */
	public double readDoubleGreaterThan(double lowerBound) {
		double value;
		
		do {
			value=readDouble();
			if (value <=lowerBound) {
				System.err.println("Error: El número debe ser mayor que "+lowerBound);
			}
		}while (value<=lowerBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un double mayor o igual que el límite inferior indicado.
	 * @param lowerBound límite inferior inclusivo
	 * @return double mayor o igual que lowerBound
	 */
	public double readDoubleGreaterOrEqualThan(double lowerBound) {
		double value;
		
		do {
			value = readDouble();
			if (value < lowerBound) {
				System.err.println("Error: El número debe ser mayor o igual que "+lowerBound);
			}
		}while (value<lowerBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un double dentro del rango [lowerBound, upperBound].
	 * Si lowerBound es mayor que upperBound, los intercambia automáticamente.
	 * @param lowerBound límite inferior inclusivo
	 * @param upperBound límite superior inclusivo
	 * @return double dentro del rango indicado
	 */
	public double readDoubleInRange(double lowerBound, double upperBound) {
		double value;
		
		if (lowerBound > upperBound) {  
			double aux = lowerBound;
			lowerBound = upperBound;
			upperBound = aux;
		}
		
		do {
			value=readDouble();
			if (value <lowerBound || value> upperBound) {
				System.err.printf("Error: El número debe estar entre %f y %f\n",lowerBound, upperBound);
			}
		}while (value<lowerBound || value>upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un número decimal (float) introducido por el usuario.
	 * Repite la lectura hasta que el valor sea válido.
	 * @return float introducido por el usuario
	 */
	public float readFloat() {
		float value=0;
		boolean error;
		
		do {
			try {
				value = keyboard.nextFloat();
				error = false;
			}catch (InputMismatchException e) {
				System.err.println("Error: Introduce un número decimal.");
				error = true;
			}finally {
				cleanInput();
			}
		}while (error);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un float estrictamente menor que el límite superior indicado.
	 * @param upperBound límite superior exclusivo
	 * @return float menor que upperBound
	 */
	public float readFloatLessThan(float upperBound) {
		float value;
		
		do {
			value = readFloat();
			if (value >=upperBound) {
				System.err.println("Error: El número debe ser menor que "+upperBound);
			}
		}while (value>=upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un float menor o igual que el límite superior indicado.
	 * @param upperBound límite superior inclusivo
	 * @return float menor o igual que upperBound
	 */
	public float readFloatLessOrEqualThan(float upperBound) {
		float value;
		
		do {
			value = readFloat();
			if (value >upperBound) {
				System.err.println("Error: El número debe ser menor o igual que "+upperBound);
			}
		}while (value>upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un float estrictamente mayor que el límite inferior indicado.
	 * @param lowerBound límite inferior exclusivo
	 * @return float mayor que lowerBound
	 */
	public float readFloatGreaterThan(float lowerBound) {
		float value;
		
		do {
			value=readFloat();
			if (value <=lowerBound) {
				System.err.println("Error: El número debe ser mayor que "+lowerBound);
			}
		}while (value<=lowerBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un float mayor o igual que el límite inferior indicado.
	 * @param lowerBound límite inferior inclusivo
	 * @return float mayor o igual que lowerBound
	 */
	public float readFloatGreaterOrEqualThan(float lowerBound) {
		float value;
		
		do {
			value = readFloat();
			if (value < lowerBound) {
				System.err.println("Error: El número debe ser mayor o igual que "+lowerBound);
			}
		}while (value<lowerBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un float dentro del rango [lowerBound, upperBound].
	 * Si lowerBound es mayor que upperBound, los intercambia automáticamente.
	 * @param lowerBound límite inferior inclusivo
	 * @param upperBound límite superior inclusivo
	 * @return float dentro del rango indicado
	 */
	public float readFloatInRange(float lowerBound, float upperBound) {
		float value;
		
		if (lowerBound > upperBound) { 
			float aux = lowerBound;
			lowerBound = upperBound;
			upperBound = aux;
		}
		
		do {
			value=readFloat();
			if (value <lowerBound || value> upperBound) {
				System.err.printf("Error: El número debe estar entre %f y %f\n",lowerBound, upperBound);
			}
		}while (value<lowerBound || value>upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un byte introducido por el usuario.
	 * Repite la lectura hasta que el valor sea válido.
	 * @return byte introducido por el usuario
	 */
	public byte readByte() {
		byte value=0;
		boolean error;
		
		do {
			try {
				value = keyboard.nextByte();
				error = false;
			}catch (InputMismatchException e) {
				System.err.println("Error: Introduce un número decimal.");
				error = true;
			}finally {
				cleanInput();
			}
		}while (error);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un byte estrictamente menor que el límite superior indicado.
	 * @param upperBound límite superior exclusivo
	 * @return byte menor que upperBound
	 */
	public byte readByteLessThan(byte upperBound) {
		byte value;
		
		do {
			value = readByte();
			if (value >=upperBound) {
				System.err.println("Error: El número debe ser menor que "+upperBound);
			}
		}while (value>=upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un byte menor o igual que el límite superior indicado.
	 * @param upperBound límite superior inclusivo
	 * @return byte menor o igual que upperBound
	 */
	public byte readByteLessOrEqualThan(byte upperBound) {
		byte value;
		
		do {
			value = readByte();
			if (value >upperBound) {
				System.err.println("Error: El número debe ser menor o igual que "+upperBound);
			}
		}while (value>upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un byte estrictamente mayor que el límite inferior indicado.
	 * @param lowerBound límite inferior exclusivo
	 * @return byte mayor que lowerBound
	 */
	public byte readByteGreaterThan(byte lowerBound) {
		byte value;
		
		do {
			value=readByte();
			if (value <=lowerBound) {
				System.err.println("Error: El número debe ser mayor que "+lowerBound);
			}
		}while (value<=lowerBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un byte mayor o igual que el límite inferior indicado.
	 * @param lowerBound límite inferior inclusivo
	 * @return byte mayor o igual que lowerBound
	 */
	public byte readByteGreaterOrEqualThan(byte lowerBound) {
		byte value;
		
		do {
			value = readByte();
			if (value < lowerBound) {
				System.err.println("Error: El número debe ser mayor o igual que "+lowerBound);
			}
		}while (value<lowerBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un byte dentro del rango [lowerBound, upperBound].
	 * Si lowerBound es mayor que upperBound, los intercambia automáticamente.
	 * @param lowerBound límite inferior inclusivo
	 * @param upperBound límite superior inclusivo
	 * @return byte dentro del rango indicado
	 */
	public byte readByteInRange(byte lowerBound, byte upperBound) {
		byte value;
		
		if (lowerBound > upperBound) {  
			byte aux = lowerBound;
			lowerBound = upperBound;
			upperBound = aux;
		}
		
		do {
			value=readByte();
			if (value <lowerBound || value> upperBound) {
				System.err.printf("Error: El número debe estar entre %f y %f\n",lowerBound, upperBound);
			}
		}while (value<lowerBound || value>upperBound);
		
		return value;
	}
	
	/**
	 * Lee y devuelve un único carácter introducido por el usuario.
	 * Repite la lectura si el usuario introduce más de un carácter.
	 * @return carácter introducido por el usuario
	 */
	public char readChar() {
		String input;
		
		do {
			input=keyboard.nextLine();
			if(input.length()!=1) {
				System.err.println("Error: Introduce un solo carácter");
			}
		}while(input.length()!=1);
		
		return input.charAt(0);
	}
	
	/**
	 * Lee y devuelve una cadena de caracteres introducida por el usuario.
	 * @return cadena introducida por el usuario
	 */
	public String readString() {
		return keyboard.nextLine();
	}
	
	/**
	 * Lee y devuelve una cadena de caracteres de como máximo maxLength caracteres.
	 * Repite la lectura si la cadena supera la longitud máxima.
	 * @param maxLength longitud máxima permitida (debe ser al menos 1)
	 * @return cadena introducida por el usuario con longitud <= maxLength
	 * @throws IllegalArgumentException si maxLength es menor que 1
	 */
	public String readString(int maxLength) {
		String input;
		if(maxLength <1) {
			throw new IllegalArgumentException("La longitud de la cadena debe ser al menos 1");
		}
		
		do {
			input=keyboard.nextLine();
			if(input.length()>maxLength) {
				System.err.println("Error: Máximo de caracteres permitidos = "+maxLength);
			}
		}while(input.length()>maxLength);
		
		return input;
	}
	
	/**
	 * Lee un carácter y lo interpreta como un valor booleano.
	 * Devuelve true si coincide con affirmativeValue, false si coincide con negativeValue.
	 * La comparación no distingue mayúsculas de minúsculas.
	 * @param affirmativeValue carácter que representa la afirmación
	 * @param negativeValue carácter que representa la negación
	 * @return true si el usuario introduce affirmativeValue, false si introduce negativeValue
	 */
	public boolean readBooleanUsingChar(char affirmativeValue, char negativeValue) {
		char input;
		char aff = Character.toLowerCase(affirmativeValue);
		char neg = Character.toLowerCase(negativeValue);
		
		do {
			input= Character.toLowerCase(readChar());
			if(input != aff && input != neg) {
				System.err.printf("Error: Por favor, introduce '%c' para afirmar y '%c' para negar\n",affirmativeValue,negativeValue);
			}
		}while(input!=Character.toLowerCase(affirmativeValue) && input!=Character.toLowerCase(negativeValue));
		
		return input == Character.toLowerCase(affirmativeValue);
	}
	
	/**
	 * Muestra un mensaje por la salida estándar seguido de un salto de línea.
	 * @param message mensaje a mostrar
	 */
	public void showMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * Muestra un mensaje formateado por la salida estándar.
	 * @param format cadena de formato al estilo de printf
	 * @param args argumentos a insertar en el formato
	 */
	public void showFormattedMessage(String format, Object... args) {
		System.out.printf(format,args);
	}
	
	
	
}