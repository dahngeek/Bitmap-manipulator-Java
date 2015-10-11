import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class BMPBasic{
	public static void soloBasico(String[] args)throws Exception{	
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
		int a =1;
		while (a<=4){
					if (a==1){
									FileOutputStream salida = new FileOutputStream(file.substring(0,file.length()-4)+"Red.bmp");
									for(int e = 0; e < 54; e++){	
										salida.write(x[e]);
									}
									for(int e = 0; e < red.length; e++){
										for(int y = 0; y < red[e].length; y++){
											salida.write(0);
											salida.write(0);
											salida.write(red[e][y]);
											
										}
									}
									for(int e = 0; e < 2; e++){
										salida.write(0);
									}
									salida.close();
									a++;

								}if(a==2){
													
											
											
										FileOutputStream salida = new FileOutputStream(file.substring(0,file.length()-4)+"Green.bmp");
													for(int e = 0; e < 54; e++){	
														salida.write(x[e]);
													}
													for(int e = 0; e < green.length; e++){
														for(int y = 0; y < green[e].length; y++){
															salida.write(0);
															salida.write(green[e][y]);
															salida.write(0);
															
														}
													}
													for(int e = 0; e < 2; e++){
														salida.write(0);
													}
													salida.close();
													a++;
												}if(a==3){
														FileOutputStream salida = new FileOutputStream(file.substring(0,file.length()-4)+"Blue.bmp");
														for(int e = 0; e < 54; e++){	
															salida.write(x[e]);
														}
														for(int e = 0; e < blue.length; e++){
															for(int y = 0; y < blue[e].length; y++){
																salida.write(blue[e][y]);
																salida.write(0);
																salida.write(0);
																
															}
														}
														for(int e = 0; e < 2; e++){
															salida.write(0);
														}
														salida.close();
														a++;
													} if (a==4){
																FileOutputStream salida = new FileOutputStream(file.substring(0,file.length()-4)+"Sepia.bmp");
																for(int e = 0; e < 54; e++){	
																	salida.write(x[e]);
																}
																for(int e = 0; e < blue.length; e++){
																	for(int y = 0; y < blue[e].length; y++){
																		int bs = (int)(.272 * red[e][y] + .534 * green[e][y] + .131 * blue[e][y]);
																		int gs = (int)(.349 * red[e][y] + .686 * green[e][y] + .168 * blue[e][y]);
																		int rs = (int)(.393 * red[e][y] + .769 * green[e][y] + .189 * blue[e][y]);
																		if(bs > 255){
																			bs = 255;}
																		if(rs > 255){
																			rs = 255;}
																		if(gs > 255){
																			gs = 255;}
																		salida.write(bs);
																		salida.write(gs);
																		salida.write(rs);
																		
																	}
																}
																for(int e = 0; e < 2; e++){
																	salida.write(0);
																}
																salida.close();
																a++;
															}





			}
	
		}
	}
