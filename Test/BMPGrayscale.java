import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class BMPGrayscale{
	public static void main(String[] args)throws Exception{	
	String file;
	int[] x;
	int[] efe;
	int[][] red;
	int[][] green;
	int[][] blue;

		
		file = args [0];
		x = new int[54];
		FileInputStream in = new FileInputStream(new File(args[0]));
		FileInputStream colortable = new FileInputStream(new File("tabla.bmp"));

		//Leer encabezado
		for(int c = 0; c < 54; c++){
			x[c] = in.read();
			//System.out.print(c);
			//System.out.print(" = ");
			//System.out.print(x[c]);
			//System.out.print("\n");
		}
		
		/* 
			leamos el color table:
		*/
			efe = new int[1024];
			for(int c = 0; c < 1024; c++){
			efe[c] = colortable.read();

			//System.out.print(c);
			//System.out.print(" = ");
			//System.out.print(x[c]);
			//System.out.print("\n");
		}
		colortable.close();
		/* 
			==================================================================

						OK, VAMOS A DESPLEGAR INFO DE NUESTRA IMAGEN SOLO PARA DEBUG

			==================================================================

		*/
		int tamanio = x[2] * 1 + x[3] * 256 + x[4]*65536 + x[5] * 16777216;
		System.out.println("Tamaño en Bytes:");
		System.out.println(tamanio);

		int offset = x[10] * 1 + x[11] * 256 + x[12]*65536 + x[13] * 16777216;
		System.out.println("Offset del array:");
		System.out.println(offset);

		int header = x[14] * 1 + x[15] * 256 + x[16]*65536 + x[17] * 16777216;
		System.out.println("Bytes que encontramos en la cabezera:");
		System.out.println(header);

		int width = x[18] * 1 + x[19] * 256 + x[20]*65536 + x[21] * 16777216;
		System.out.println("Ancho:");
		System.out.println(width);

		int height = x[22] * 1 + x[23] * 256 + x[24]*65536 + x[25] * 16777216;
		System.out.println("Alto:");
		System.out.println(height);

		int planos = x[26] * 1 + x[27]*256;
		System.out.println("Planos:");
		System.out.println(planos);

		int bitaje = x[28] * 1 + x[29]*256;
		System.out.println("Bits :D :");
		System.out.println(bitaje);

		int compresion = x[30] * 1 + x[31] * 256 + x[32]*65536 + x[33] * 16777216;
		System.out.println("Compresion? :");
		System.out.println(compresion);

		int raw = x[34] * 1 + x[35] * 256 + x[36]*65536 + x[37] * 16777216;
		System.out.println("Tamaño de Raw Bitmap data :");
		System.out.println(raw);

		int plata = x[46] * 1 + x[47] * 256 + x[48]*65536 + x[49] * 16777216;
		System.out.println("Colores en la paleta :");
		System.out.println(plata);

		int important = x[50] * 1 + x[51] * 256 + x[52]*65536 + x[53] * 16777216;
		System.out.println("Colores Importantes :");
		System.out.println(important);

		/* 
			==========================================================================
						COOOL, VAMOS A CAMBIARLE EL BITTAJEEEEE
			==========================================================================
		*/

		x[28] = 8;
		bitaje = x[28] * 1 + x[29]*256;
		System.out.println("Bits :");
		System.out.println(bitaje);

		/* 
			==========================================================================
						COOOL, VAMOS A CAMBIARLE EL OFFFFSSEEEETTTTTT
			==========================================================================
		*/

		x[10] = 54;
		x[11] = 4;
		offset = x[10] * 1 + x[11]*256;
		System.out.println("offset :D :");
		System.out.println(offset);
		// LO DE SIEMPRE.. COPIAR LOS BYTES

		red = new int[height][width];
		green = new int[height][width];
		blue = new int[height][width];
		for(int e = 0; e < height; e++){
			for(int y = 0; y < width; y++){
				blue[e][y] = in.read();
				green[e][y] = in.read();
				red[e][y] = in.read();
			}
		}
		in.close();

									FileOutputStream salida = new FileOutputStream("grayscale" + file);
									for(int e = 0; e < 54; e++){	
									//	if (e != 30 || e !=31 || e !=32 || e !=33 ){ trate de cambiar el header a 8bits porque en esas posiciones estan los bits
											salida.write(x[e]);
									//	}else{
									//		salida.write(0); igual para cambiar los valores abjajo 
									//	}
											
											//System.out.print(e);
											//System.out.print(" => ");
											//System.out.print(efe[e]);
											//System.out.print("\n");
											
									}
									/* for(int e = 0; e < efe.length; e++){	
									//	if (e != 30 || e !=31 || e !=32 || e !=33 ){ trate de cambiar el header a 8bits porque en esas posiciones estan los bits
											salida.write(efe[e]);
									//	}else{
									//		salida.write(0); igual para cambiar los valores abjajo 
									//	}
											
											//System.out.print(e);
											//System.out.print(" => ");
											//System.out.print(efe[e]);
											//System.out.print("\n");
											
									}
									*/
									for (int ii=0; ii <= 256; ii++) {
										salida.write(ii);
										salida.write(ii);
										salida.write(ii);
										salida.write(0);
									}
									//x[30]=8;
									//x[31]=0; lastimosamente no funciono muy bien daba imagen dañada
									//x[32]=0;
									//x[33]=0;


									for(int e = 0; e < red.length; e++){
										for(int y = 0; y < red[e].length; y++){
											int k = (int) (red[e][y]*0.2126 + 0.7152* green[e][y] + 0.0722 *blue[e][y]); //k es la constante de gris 
											salida.write(k);
											//salida.write(k);//en teoria es solo una salida porque a 8bits es solo escribe una pero como esta en 24 es asi para que salga
											//salida.write(k);//no estoy seguro que funcione como debe de ser
											
										}
									}
									for(int e = 0; e < 2; e++){
										salida.write(0);
									}
									salida.close();}}
									//para el kernel si podes usar esto me avisas porque tengo la ide de como hacerlo mas facil
									//creo que solo voy a poder de 1-2 y despues de 8 pm en adelante trabajar mañana
									//cualquier cosa estoy en mi telefono