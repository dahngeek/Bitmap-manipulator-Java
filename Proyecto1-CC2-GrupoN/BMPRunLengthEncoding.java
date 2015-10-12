import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.lang.*;

public class BMPRunLengthEncoding {
	private String archivo;
	int[][] arrDatos;
	//int[][] greenPixeles;
	//int[][] bluePixeles;
	int width;
	int height;
	int[] x;
	int[] tabladecolores;
	String file;
	LinkedList<Integer> elementos = new LinkedList<Integer>();
	int lastdato = new Integer(0);
	int cantidad = new Integer(0);
	boolean newitem = true;

	public BMPRunLengthEncoding(String archivo) throws Exception {
		this.archivo = archivo;
		String[] archivoenArr = new String[1];
		archivoenArr[0] = this.archivo;
		BMPGrayscale.escalaGrises(archivoenArr);
		x = new int[54];
		FileInputStream in = new FileInputStream(new File(archivo.substring(0,archivo.length()-4)+"Grayscale.bmp"));

		//Obtenemos Header
		for(int c = 0; c < 54; c++){
			x[c] = in.read();
		}

		//Tamaño original
		//System.out.println(x[18]);
		//System.out.println(x[19]);
		this.width = x[18] * 1 + x[19] * 256 + x[20]*65536 + x[21] * 16777216;
		//System.out.println(this.width);
		//System.out.println(x[22]);
		//System.out.println(x[23]);
		this.height = x[22] * 1 + x[23] * 256 + x[24]*65536 + x[25] * 16777216;
		//System.out.println(this.height);
		int compresion = x[30] * 1 + x[31] * 256 + x[32]*65536 + x[33] * 16777216;
		System.out.println("Compresion? :");
		System.out.println(compresion);

		//Read ColorTable
		tabladecolores = new int[1024];
		for (int i = 0; i < 1024; i++) {
			tabladecolores[i] = in.read();
		}
		
		arrDatos = new int[height][width];
		for(int e = 0; e < height; e++){
			for(int y = 0; y < width; y++){
				arrDatos[e][y] = in.read();
			}
		}
		in.close();
		//System.out.println("Traaaiiiii");
		for(int e = 0; e < arrDatos.length; e++){
			for(int y = 0; y < arrDatos[e].length; y++){
				//System.out.println(".");
				if ((y == 0 && e == 0) || newitem) {
					cantidad = 1;
					lastdato = arrDatos[e][y];
					newitem = false;
					//System.out.println("Iniciamos");
				} else {
					if (lastdato == arrDatos[e][y]) {
						cantidad = cantidad+1;
					} else {
						
						elementos.addFirst(cantidad);
						elementos.addFirst(lastdato);
						cantidad = 0;
						lastdato = 0;
						newitem = true;
					}
				}
			}
		}
		file = archivo;
		//LLAMAMOS A LAS Funciones
		this.encode();
	}
	private void encode() throws Exception{
		FileOutputStream salida = new FileOutputStream(file.substring(0,file.length()-4)+"RLE.bmp");
		//Nuevo Tamaño
		//x[19] = (this.width/2)/256;
		//x[18] = (this.width/2)%256;
		
		//this.width = x[18] * 1 + x[19] * 256 + x[20]*65536 + x[21] * 16777216;
		//System.out.println(this.width);

		//Modificamos para RLE-8 Encoding
		x[30] = 1;
		int compresion = x[30] * 1 + x[31] * 256 + x[32]*65536 + x[33] * 16777216;
		System.out.println("Compresion? :");
		System.out.println(compresion);
		
		int bitaje = x[28] * 1 + x[29]*256;
		System.out.println("Bits :D :");
		System.out.println(bitaje);
		
		//Inprimimos headers
		for(int e = 0; e < 54; e++){	
			salida.write(x[e]);
		}
		//x[19] = this.width/256;
		//x[18] = this.width%256;

		//Imprimiemos Tabla de colores
		for (int f = 0;f < 1024 ; f++) {
			salida.write(tabladecolores[f]);
		}


		//imprimimos los pixeles
		while (!elementos.isEmpty()) {
			salida.write(elementos.removeLast());
		}

		salida.write(0);
		salida.write(1);
		salida.close();
	}
}