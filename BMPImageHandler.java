import java.io.*;

public class BMPImageHandler {
	public static void main(String args[]) throws Exception {
		String archivo;
		String orden = args[0];
		archivo = args[1];
		String[] archivoenArr = new String[2];
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
			case "-resize":
				System.out.println("Cambiando tamaño a la imagen");
				BMPResize resize = new BMPResize(archivo);
				break;
			case "-grayscale":
				System.out.println("Generando Imagen Grayscale");
				BMPGrayscale.escalaGrises(archivoenArr);
				break;
			case "-rle":
				System.out.println("Generando Imagen RLE");
				BMPRunLengthEncoding rle = new BMPRunLengthEncoding(archivo);
				break;
			case "-kernel" :
				System.out.println("Generando imagen con filtro Kernel");
				archivoenArr[1] = args[2];
				BMPRunKernelFilter.kernel(archivoenArr);
				break;
			default:
				System.out.println("Error! sintaxis incorrecta.");
				break;
		}
	}
}