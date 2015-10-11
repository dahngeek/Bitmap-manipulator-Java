import java.io.*;

public class BMPImageHandler {
	public static void main(String args[]) throws Exception {
		String archivo;
		String orden = args[0];
		archivo = args[1];
		String[] archivoenArr = new String[1];
		archivoenArr[0] = archivo;
		switch (orden) {
			case "-core" :
				System.out.println("Generando los Basics");
				BMPBasic.soloBasico(archivoenArr);
				break;
			case "-rotate":
				System.out.println("Generando Rotaciones");
				BMPRotations rot = new BMPRotations(archivo);
				break;
			default:
				System.out.println("Error! sintaxis incorrecta.");
				break;
		}
	}
}