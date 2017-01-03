package com.diderot;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;
public class suiveurligne {

	/**
	 * @param args
	 */
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
		
		for (int i = 0; i < 10; i++) {
			 
			Color colorr = cs.getColor();
		
			  int r=colorr.getRed();
			  int g=colorr.getGreen();
			  int b=colorr.getBlue();
			  
				System.out.println(r+", "+g+", "+b);
				 Button.waitForAnyPress();
			  
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
		
		System.out.println("min= "+minRed+", "+minGreen+" , "+minBlue);
		System.out.println("max= "+maxRed+", "+maxGreen+" , "+maxBlue);
		Button.waitForAnyPress();}
	
	public static void fondParcours(){	
		   
		   ColorSensor cs = new ColorSensor(SensorPort.S1);
		   Color color = cs.getColor();
		 
		 Button.waitForAnyPress();
		
		 minRedd=color.getRed(); 
		 maxRedd=color.getRed();
		
		 minGreenn=color.getGreen(); 
		 maxGreenn=color.getGreen();
		
		 minBluee=color.getBlue(); 
		 maxBluee=color.getBlue();
		
		for (int i = 0; i < 10; i++) {
			 
			Color colorr = cs.getColor();
		
			  int r=colorr.getRed();
			  int g=colorr.getGreen();
			  int b=colorr.getBlue();
			  
				System.out.println(r+", "+g+", "+b);
				 Button.waitForAnyPress();
			  
			  if (r<=minRedd){
				  minRedd=r;
			  }
			  if(r>maxRedd){
				  maxRedd=r;
			  }
			
			  
			  if (g<=minGreenn){
				  minGreenn=g;
			  }
			  
			  if(g>maxGreenn){
				  maxGreenn=g;
			  }
			  
			  if (b<=minBluee){
				  minBluee=b;
			  }
			  
			  if(b>maxBluee){
				  maxBluee=b;
			  }
				
					    }
		
		System.out.println("min= "+minRedd+", "+minGreenn+" , "+minBluee);
		System.out.println("max= "+maxRedd+", "+maxGreenn+" , "+maxBluee);
		Button.waitForAnyPress();}
	
	
	public static void main(String[] args) {

		etalonnnageParcours();
		fondParcours();
		
		
		for (int i=0 ; i<1500; i++)
		{ ColorSensor cs = new ColorSensor(SensorPort.S1);
			Color colorr = cs.getColor();
		
		  int r=colorr.getRed();
		  int g=colorr.getGreen();
		  int b=colorr.getBlue();
		  
		  System.out.println("red= "+r+", green= "+g+" , blue="+b);
			
		  if (((r>=minRed)&&(r<=maxRed)) && ((g>=minGreen)&&(g<=maxGreen)) && ((b>=minBlue)&&(b<=maxBlue)))
		  
		  {		  
				Motor.A.setSpeed(200);
				Motor.C.setSpeed(175);
				Motor.A.forward();
				Motor.C.forward();
		  }
		  else {
			  if ((((r>=minRedd)&&(r<=maxRedd)) && ((g>=minGreenn)&&(g<=maxGreenn)) && ((b>=minBluee)&&(b<=maxBluee)))) 
			  {
				  Motor.A.setSpeed(175);
					Motor.C.setSpeed(200);
					Motor.A.forward();
					Motor.C.forward();
			  }
			  else {
				  System.out.println("la valeur est fausse");
			  }
		  }
			   
			
	}
		  Motor.A.stop();
		  Motor.C.stop();
	}
}