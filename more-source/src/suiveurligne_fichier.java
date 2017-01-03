import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;




public class suiveurligne {

	/**
	 * @param args
	 */
	static boolean parcours=true;
	/*intervales parcours*/
	static int minRed; 
	static int maxRed;
	
	static int minGreen;
	static int maxGreen;
	
	static int minBlue;
	static int maxBlue;
	
	/*intervales fond parcours*/
	static int minRedd; 
	static int maxRedd;
	
	static int minGreenn;
	static int maxGreenn;
	
	static int minBluee;
	static int maxBluee;

	/*intervales fin*/
	static int minRedf; 
	static int maxRedf;
	
	static int minGreenf;
	static int maxGreenf;
	
	static int minBluef;
	static int maxBluef;
	
	 public static void  etalonnnageParcours(){	
		   
		   ColorSensor cs = new ColorSensor(SensorPort.S1);
		   Color color = cs.getColor();
		 
		 Button.waitForAnyPress();
		
		 minRed=color.getRed(); 
		 maxRed=color.getRed();
		
		 minGreen=color.getGreen(); 
		 maxGreen=color.getGreen();
		
		 minBlue=color.getBlue(); 
		 maxBlue=color.getBlue();
		 
		 System.out.println("Appuiyer pour demarrer etalonnage");
		 Button.waitForAnyPress();
		
		for (int i = 0; i < 300; i++) {
			 
			Color colorr = cs.getColor();
		      int r=colorr.getRed();
			  int g=colorr.getGreen();
			  int b=colorr.getBlue();
			  
			 System.out.println(r+", "+g+", "+b);
				 
			  
			  if (r<=minRed){
				  minRed=r;
			  }
			  if(r>maxRed){
				  maxRed=r;
			  }
			
			  
			  if (g<=minGreen){
				  minGreen=g;
			  }
			  
			  if(g>maxGreen){
				  maxGreen=g;
			  }
			  
			  if (b<=minBlue){
				  minBlue=b;
			  }
			  
			  if(b>maxBlue){
				  maxBlue=b;
			  }
				
					    }
		
		System.out.println("min parcours= "+minRed+", "+minGreen+" , "+minBlue);
		
		
		System.out.println("max parcours= "+maxRed+", "+maxGreen+" , "+maxBlue);
		Button.waitForAnyPress();}
	   
	   
	   
	   
	   
	public static void fondParcours(){	
		   
		 ColorSensor cs = new ColorSensor(SensorPort.S1);
		 Color color = cs.getColor();
		 
		 Button.waitForAnyPress();
		
		
		
		 System.out.println("Appuyez pour demarrer etalonnage");
		 Button.waitForAnyPress();
		 
		 
		 
		 try {

				File file = new File("couleurRGB.txt");
				// if file doesnt exists, then create it
				
				
				if (!file.exists()) {
					file.createNewFile();
				}

				FileOutputStream fw = new FileOutputStream (file);
				BufferedOutputStream bw = new BufferedOutputStream(fw);
		        for(int i=0;i<300;i++){
			  
		        	Color colorr = cs.getColor();
		    		
					  int r=colorr.getRed();
					  int g=colorr.getGreen();
					  int b=colorr.getBlue();
					  
		        	
					fw.write(r);
					fw.write(g);
					fw.write(b);
		        }
				    bw.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
	      
		 
		 }
	
	
	
	
	 public static void  etalonnnagefinParcours(){	
		   
		   ColorSensor cs = new ColorSensor(SensorPort.S1);
		   Color color = cs.getColor();
		 
		 Button.waitForAnyPress();
		
		 minRedf=color.getRed(); 
		 maxRedf=color.getRed();
		
		 minGreenf=color.getGreen(); 
		 maxGreenf=color.getGreen();
		
		 minBluef=color.getBlue(); 
		 maxBluef=color.getBlue();
		 
		 System.out.println("Appuiyer pour demarrer etalonnage");
		 Button.waitForAnyPress();
		
		for (int i = 0; i < 200; i++) {
			 
			Color colorr = cs.getColor();
		
			  int r=colorr.getRed();
			  int g=colorr.getGreen();
			  int b=colorr.getBlue();
			  
				System.out.println(r+", "+g+", "+b);
				 
			  
			  if (r<=minRedf){
				  minRedf=r;
			  }
			  if(r>maxRedf){
				  maxRedf=r;
			  }
			
			  
			  if (g<=minGreenf){
				  minGreenf=g;
			  }
			  
			  if(g>maxGreenf){
				  maxGreenf=g;
			  }
			  
			  if (b<=minBluef){
				  minBluef=b;
			  }
			  
			  if(b>maxBluef){
				  maxBluef=b;
			  }
				
					    }
		
		System.out.println("min parcours= "+minRedf+", "+minGreenf+" , "+minBluef);
		System.out.println("max parcours= "+maxRedf+", "+maxGreenf+" , "+maxBluef);
		Button.waitForAnyPress();}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		etalonnnageParcours();
		fondParcours();
		etalonnnagefinParcours();
		
		
		int acceleration=0;
		
	    	while(parcours)
		{  ColorSensor cs = new ColorSensor(SensorPort.S1);
		   Color colorr = cs.getColor();
		   int r=colorr.getRed();
		   int g=colorr.getGreen();
		   int b=colorr.getBlue();
		  
		  System.out.println("red= "+r+", green= "+g+" , blue="+b);
		  
		  //il est dans le parcours
		  while (((r>=minRed)&&(r<=maxRed)) && ((g>=minGreen)&&(g<=maxGreen)) && ((b>=minBlue)&&(b<=maxBlue)))
		  
		  {		  
				Motor.A.setSpeed(180+acceleration);
				Motor.C.setSpeed(150);
				Motor.A.forward();
				Motor.C.forward();
				colorr = cs.getColor();
				r=colorr.getRed();
				g=colorr.getGreen();
			    b=colorr.getBlue();
				acceleration+=3;
		  }
		  
		    acceleration=0;
		  
		  //il est sur le fond du parcou
		  while((((r>=minRedd)&&(r<=maxRedd)) && ((g>=minGreenn)&&(g<=maxGreenn)) && ((b>=minBluee)&&(b<=maxBluee)))) 
			  {
				    Motor.A.setSpeed(150);
					Motor.C.setSpeed(180+acceleration);
					Motor.A.forward();
					Motor.C.forward();
					colorr = cs.getColor();
					r=colorr.getRed();
					g=colorr.getGreen();
					b=colorr.getBlue();
					acceleration+=3;
			  }
		  
		    
		  
		  if((((r>=minRedf)&&(r<=maxRedf)) && ((g>=minGreenf)&&(g<=maxGreenf)) && ((b>=minBluef)&&(b<=maxBluef)))) 
		  {
			  System.out.println("arrivé");
			  Motor.A.stop();
			  Motor.C.stop();
			  parcours=false;
		
		  }
		  

		  }	
			} 

	}

