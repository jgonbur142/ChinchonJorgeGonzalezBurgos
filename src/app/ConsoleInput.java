package app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInput {
	private Scanner keyboard;
	
	public ConsoleInput (Scanner keyboard) {
		this.keyboard=keyboard;
	}
	
	private void cleanInput() { //limpia el teclado
		keyboard.nextLine();
	}
	
	public int readInt() { //devuelve un int introducido por el usuario
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
	
	public int readIntLessThan(int upperBound) { // devuelve un int inferior al parametro
		int value;
		
		do {
			value = readInt();
			if (value >= upperBound) {
				System.err.println("Error: El número debe ser menor que "+upperBound);
			}
		}while (value>=upperBound);
		
		return value;
	}
	
	public int readIntLessOrEqualThan(int upperBound) {// devuelve un int inferior o igual al parametro
		int value;
		
		do {
			value = readInt();
			if (value > upperBound) {
				System.err.println("Error: El número debe ser menor o igual que "+upperBound);
			}
		}while (value > upperBound);
		
		return value;
	}
	
	public int readIntGreaterThan(int lowerBound) {// devuelve un int superior al parametro
		int value;
		
		do {
			value = readInt();
			if (value <= lowerBound) {
				System.err.println("Error: El número debe ser mayor que "+lowerBound);
			}
		}while (value <= lowerBound);
		
		return value;
	}
	
	public int readIntGreaterOrEqualThan(int lowerBound) {// devuelve un int superior o igual al parametro
		int value;
		
		do {
			value = readInt();
			if (value < lowerBound) {
				System.err.println("Error: El número debe ser mayor o igual que "+lowerBound);
			}
		}while (value < lowerBound);
		
		return value;
	}
	
	public int readIntInRange (int lowerBound, int upperBound) {// devuelve un int cuyo valor esta en el rango [ lowerBound, upperBound ]
		int value;
		
		if (lowerBound > upperBound) {  //si el usuario introduce primero un numero mayor que el segundo:
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
	
	public double readDouble() {// devuelve un double introducido por el usuario
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
	
	public double readDoubleLessThan(double upperBound) {// devuelve un double inferior al parametro
		double value;
		
		do {
			value = readDouble();
			if (value >=upperBound) {
				System.err.println("Error: El número debe ser menor que "+upperBound);
			}
		}while (value>=upperBound);
		
		return value;
	}
	
	public double readDoubleLessOrEqualThan(double upperBound) {// devuelve un double inferior o igual al parametro
		double value;
		
		do {
			value = readDouble();
			if (value >upperBound) {
				System.err.println("Error: El número debe ser menor o igual que "+upperBound);
			}
		}while (value>upperBound);
		
		return value;
	}
	
	public double readDoubleGreaterThan(double lowerBound) {// devuelve un double superior al parametro
		double value;
		
		do {
			value=readDouble();
			if (value <=lowerBound) {
				System.err.println("Error: El número debe ser mayor que "+lowerBound);
			}
		}while (value<=lowerBound);
		
		return value;
	}
	
	public double readDoubleGreaterOrEqualThan(double lowerBound) {// devuelve un double superior o igual al parametro
		double value;
		
		do {
			value = readDouble();
			if (value < lowerBound) {
				System.err.println("Error: El número debe ser mayor o igual que "+lowerBound);
			}
		}while (value<lowerBound);
		
		return value;
	}
	
	public double readDoubleInRange(double lowerBound, double upperBound) {// devuelve un double cuyo valor esta en el rango [ lowerBound, upperBound ]
		double value;
		
		if (lowerBound > upperBound) {  //si el usuario introduce primero un numero mayor que el segundo:
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
	
	public float readFloat() {// devuelve un float introducido por el usuario
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
	
	public float readFloatLessThan(float upperBound) {// devuelve un float inferior al parametro
		float value;
		
		do {
			value = readFloat();
			if (value >=upperBound) {
				System.err.println("Error: El número debe ser menor que "+upperBound);
			}
		}while (value>=upperBound);
		
		return value;
	}
	
	public float readFloatLessOrEqualThan(float upperBound) {// devuelve un float inferior o igual al parametro
		float value;
		
		do {
			value = readFloat();
			if (value >upperBound) {
				System.err.println("Error: El número debe ser menor o igual que "+upperBound);
			}
		}while (value>upperBound);
		
		return value;
	}
	
	public float readFloatGreaterThan(float lowerBound) {// devuelve un float superior al parametro
		float value;
		
		do {
			value=readFloat();
			if (value <=lowerBound) {
				System.err.println("Error: El número debe ser mayor que "+lowerBound);
			}
		}while (value<=lowerBound);
		
		return value;
	}
	
	public float readFloatGreaterOrEqualThan(float lowerBound) {// devuelve un float superior o igual al parametro
		float value;
		
		do {
			value = readFloat();
			if (value < lowerBound) {
				System.err.println("Error: El número debe ser mayor o igual que "+lowerBound);
			}
		}while (value<lowerBound);
		
		return value;
	}
	
	public float readFloatInRange(float lowerBound, float upperBound) {// devuelve un double cuyo valor esta en el rango [ lowerBound, upperBound ]
		float value;
		
		if (lowerBound > upperBound) {  //si el usuario introduce primero un numero mayor que el segundo:
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
	
	public byte readByte() {// devuelve un byte introducido por el usuario
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
	
	public byte readByteLessThan(byte upperBound) {// devuelve un byte inferior al parametro
		byte value;
		
		do {
			value = readByte();
			if (value >=upperBound) {
				System.err.println("Error: El número debe ser menor que "+upperBound);
			}
		}while (value>=upperBound);
		
		return value;
	}
	
	public byte readByteLessOrEqualThan(byte upperBound) {// devuelve un byte inferior o igual al parametro
		byte value;
		
		do {
			value = readByte();
			if (value >upperBound) {
				System.err.println("Error: El número debe ser menor o igual que "+upperBound);
			}
		}while (value>upperBound);
		
		return value;
	}
	
	public float readByteGreaterThan(byte lowerBound) {// devuelve un byte superior al parametro
		byte value;
		
		do {
			value=readByte();
			if (value <=lowerBound) {
				System.err.println("Error: El número debe ser mayor que "+lowerBound);
			}
		}while (value<=lowerBound);
		
		return value;
	}
	
	public byte readByteGreaterOrEqualThan(byte lowerBound) {// devuelve un byte superior o igual al parametro
		byte value;
		
		do {
			value = readByte();
			if (value < lowerBound) {
				System.err.println("Error: El número debe ser mayor o igual que "+lowerBound);
			}
		}while (value<lowerBound);
		
		return value;
	}
	
	public byte readByteInRange(byte lowerBound, byte upperBound) {// devuelve un byte cuyo valor esta en el rango [ lowerBound, upperBound ]
		byte value;
		
		if (lowerBound > upperBound) {  //si el usuario introduce primero un numero mayor que el segundo:
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
	
	public char readChar() {// devuelve un caracter introducido por el usuario
		String input;
		
		do {
			input=keyboard.nextLine();
			if(input.length()!=1) {
				System.err.println("Error: Introduce un solo carácter");
			}
		}while(input.length()!=1);
		
		return input.charAt(0);
	}
	
	public String readString() {// devuelve una cadena de caracteres
		return keyboard.nextLine();
	}
	
	public String readString(int maxLength) {// devuelve una cadena de caracteres de maximo maxLength caracteres
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
	
	public boolean readBooleanUsingChar(char affirmativeValue, char negativeValue) {// devuelve un booleano de forma que: [coincide con affirmativeValue -> true | coincide con negativeValue -> false]
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
	
	public void showMessage(String message) {
		System.out.println(message);
	}
	
	
	
}