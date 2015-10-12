import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class BMPRunKernelFilter{
	public static void kernel(String[] args)throws Exception{	
	String file;
	int[] x;
	int[][] red;
	int[][] green;
	int[][] blue;

	double[][] redK;
	double[][] greenK;
	double[][] blueK;

		
		file = args [1];
		x = new int[54];
		System.out.println(args[0]);
		Scanner kernel = new Scanner(new File(args[0]));
		
		//Cargamos los multiplicadores
		double op1 = Double.parseDouble(kernel.next());
		double op2 = Double.parseDouble(kernel.next());
		double op3 = Double.parseDouble(kernel.next());
		double op4 = Double.parseDouble(kernel.next());
		double opMain = Double.parseDouble(kernel.next());
		double op6 = Double.parseDouble(kernel.next());
		double op7 = Double.parseDouble(kernel.next());
		double op8 = Double.parseDouble(kernel.next());
		double op9 = Double.parseDouble(kernel.next());

		System.out.println(op1 + " # " + op2 + " # " + op3 + " # " + op4 + " # " + " # " + opMain + " # " + op6 + " # " + op7 + " # " + op8 + " # " + op9);

		FileInputStream in = new FileInputStream(new File(args[1]));
		for(int c = 0; c < 54; c++){
			x[c] = in.read();
		}
		int width = x[18] * 1 + x[19] * 256 + x[20]*65536 + x[21] * 16777216;
		int height = x[22] * 1 + x[23] * 256 + x[24]*65536 + x[25] * 16777216;
		red = new int[height][width];
		green = new int[height][width];
		blue = new int[height][width];

		redK = new double[height][width];
		greenK = new double[height][width];
		blueK = new double[height][width];
		for(int e = 0; e < height; e++){
			for(int y = 0; y < width; y++){
				blue[e][y] = in.read();
				green[e][y] = in.read();
				red[e][y] = in.read();
				
			}
		}
		in.close();
		
		for(int e = 0; e < red.length; e++){
										for(int y = 0; y < red[e].length; y++){
											blueK[e][y] = blue[e-1][y-1]*op1+blue[e-1][y]*op2+blue[e-1][y+1]*op3+blue[e][y-1]*op4+blue[e][y]*opMain+blue[e][y+1]*op6+blue[e+1][y-1]*op7+blue[e+1][y]*op8+blue[e+1][y+1]*op9;
											greenK[e][y] = green[e-1][y-1]*op1+green[e-1][y]*op2+green[e-1][y+1]*op3+green[e][y-1]*op4+green[e][y]*opMain+green[e][y+1]*op6+green[e+1][y-1]*op7+green[e+1][y]*op8+green[e+1][y+1]*op9;
											redK[e][y] = red[e-1][y-1]*op1+red[e-1][y]*op2+red[e-1][y+1]*op3+red[e][y-1]*op4+red[e][y]*opMain+red[e][y+1]*op6+red[e+1][y-1]*op7+red[e+1][y]*op8+red[e+1][y+1]*op9;
											
										}
									}

		FileOutputStream salida = new FileOutputStream(file.substring(0,file.length()-4)+"Kernel.bmp");
									for(int e = 0; e < 54; e++){	
										salida.write(x[e]);
									}
									for(int e = 0; e < red.length; e++){
										for(int y = 0; y < red[e].length; y++){
											salida.write((int)blueK[e][y]);
											salida.write((int)greenK[e][y]);
											salida.write((int)redK[e][y]);
											
										}
									}
									for(int e = 0; e < 2; e++){
										salida.write(0);
									}
									salida.close();
	
		}
	}
