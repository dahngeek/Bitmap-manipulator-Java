import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class Prueba{
	public static void main(String[] args)throws Exception{	
	String file;
	int[] x;
	int[][] red;
	int[][] green;
	int[][] blue;

		
		file = args [0];
		x = new int[54];
		FileInputStream in = new FileInputStream(new File(args[0]));
		for(int c = 0; c < 54; c++){
			x[c] = in.read();
			System.out.println(x[c]);
		}
		int width = x[18] * 1 + x[19] * 256 + x[20]*65536 + x[21] * 16777216;
		int height = x[22] * 1 + x[23] * 256 + x[24]*65536 + x[25] * 16777216;
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
									//		salida.write(0); igual para cambiar los balores abjajo 
									//	}

									}

									//x[30]=8;
									//x[31]=0; lastimosamente no funciono muy bien daba imagen dañada
									//x[32]=0;
									//x[33]=0;


									for(int e = 0; e < red.length; e++){
										for(int y = 0; y < red[e].length; y++){
											int k = (int) (red[e][y]*0.2126 + 0.7152* green[e][y] + 0.0722 *blue[e][y]); //k es la constante de gris 
											salida.write(k);
											salida.write(k);//en teoria es solo una salida porque a 8bits es solo escribe una pero como esta en 24 es asi para que salga
											salida.write(k);//no estoy seguro que funcione como debe de ser
											
										}
									}
									for(int e = 0; e < 2; e++){
										salida.write(0);
									}
									salida.close();}}
									//para el kernel si podes usar esto me avisas porque tengo la ide de como hacerlo mas facil
									//creo que solo voy a poder de 1-2 y despues de 8 pm en adelante trabajar mañana
									//cualquier cosa estoy en mi telefono