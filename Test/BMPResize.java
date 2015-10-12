import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BMPRotations {
	private String archivo;
	int[][] redPixeles;
	int[][] greenPixeles;
	int[][] bluePixeles;
	int width;
	int height;
	int[] x;
	String file;
	public BMPResize(String archivo) throws Exception {
		this.archivo = archivo;
		x = new int[54];
		FileInputStream in = new FileInputStream(new File(archivo));
		for(int c = 0; c < 54; c++){
			x[c] = in.read();
		}
		this.width = x[18] * 1 + x[19] * 256 + x[20]*65536 + x[21] * 16777216;
		//System.out.println(this.width);
		this.height = x[22] * 1 + x[23] * 256 + x[24]*65536 + x[25] * 16777216;
		//System.out.println(this.height);
		redPixeles = new int[height][width];
		greenPixeles = new int[height][width];
		bluePixeles = new int[height][width];
		for(int e = 0; e < height; e++){
			for(int y = 0; y < width; y++){
				bluePixeles[e][y] = in.read();
				greenPixeles[e][y] = in.read();
				redPixeles[e][y] = in.read();
				
			}
		}
		in.close();
		file = archivo;
		//LLAMAMOS A LAS Funciones
		this.hRotation();
		this.vRotation();
	}
	private void hRotation() throws Exception{
		FileOutputStream salida = new FileOutputStream(file.substring(0,file.length()-4)+"HRotation.bmp");
		//Inprimimos headers
		for(int e = 0; e < 54; e++){	
			salida.write(x[e]);
		}
		//imprimimos los pixeles
		for(int e =  redPixeles.length-1; e >= 0; e--){
			for(int y = 0; y < redPixeles[e].length; y++){
				salida.write(bluePixeles[e][y]);
				salida.write(greenPixeles[e][y]);
				salida.write(redPixeles[e][y]);
			}
		}
		for(int e = 0; e < 2; e++){
			salida.write(0);
		}
		salida.close();
	}
	private void vRotation() throws Exception{
		FileOutputStream salida = new FileOutputStream(file.substring(0,file.length()-4)+"VRotation.bmp");
		//Inprimimos headers
		for(int e = 0; e < 54; e++){	
			salida.write(x[e]);
		}
		//imprimimos los pixeles
		for(int e =  0; e < redPixeles.length; e++){
			for(int y = redPixeles[e].length -1 ; y >= 0; y--){
				salida.write(bluePixeles[e][y]);
				salida.write(greenPixeles[e][y]);
				salida.write(redPixeles[e][y]);
			}
		}
		for(int e = 0; e < 2; e++){
			salida.write(0);
		}
		salida.close();
	}
}