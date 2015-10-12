import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BMPResize {
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

		//Tamaño original
		System.out.println(x[18]);
		System.out.println(x[19]);
		this.width = x[18] * 1 + x[19] * 256 + x[20]*65536 + x[21] * 16777216;
		System.out.println(this.width);
		System.out.println(x[22]);
		System.out.println(x[23]);
		this.height = x[22] * 1 + x[23] * 256 + x[24]*65536 + x[25] * 16777216;
		System.out.println(this.height);
		
		


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
		this.thin();
		this.flat();
	}
	private void thin() throws Exception{
		FileOutputStream salida = new FileOutputStream(file.substring(0,file.length()-4)+"Thin.bmp");
		//Nuevo Tamaño
		x[19] = (this.width/2)/256;
		x[18] = (this.width/2)%256;
		
		//this.width = x[18] * 1 + x[19] * 256 + x[20]*65536 + x[21] * 16777216;
		//System.out.println(this.width);
		//Inprimimos headers
		for(int e = 0; e < 54; e++){	
			salida.write(x[e]);
		}
		x[19] = this.width/256;
		x[18] = this.width%256;

		//imprimimos los pixeles
		for(int e = 0; e < redPixeles.length; e++){
			for(int y = 0; y < redPixeles[e].length; y++){
				if (y%2==0) {
					salida.write(bluePixeles[e][y]);
					salida.write(greenPixeles[e][y]);
					salida.write(redPixeles[e][y]);
				}
			}
		}
		for(int e = 0; e < 2; e++){
			salida.write(0);
		}
		salida.close();
	}
	private void flat() throws Exception{
		FileOutputStream salida = new FileOutputStream(file.substring(0,file.length()-4)+"Flat.bmp");
		//Inprimimos headers
		x[23] = (this.height/2)/256;
		x[22] = (this.height/2)%256;
		//this.height = x[22] * 1 + x[23] * 256 + x[24]*65536 + x[25] * 16777216;
		//System.out.println(this.height);

		for(int e = 0; e < 54; e++){	
			salida.write(x[e]);
		}
		//imprimimos los pixeles
		for(int e =  0; e < redPixeles.length; e++){
			for(int y = 0; y < redPixeles[e].length; y++){
				if (e%2==0) {
					salida.write(bluePixeles[e][y]);
					salida.write(greenPixeles[e][y]);
					salida.write(redPixeles[e][y]);
				}
			}
		}
		for(int e = 0; e < 2; e++){
			salida.write(0);
		}
		salida.close();
	}
}