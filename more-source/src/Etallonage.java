import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;


public class Etallonage {

	public static void main(String[] args) {
		
		ColorSensor cs = new ColorSensor(SensorPort.S1);
		 
		
		Color color = cs.getColor();
		 Button.waitForAnyPress();
		
		int minRed=color.getRed(); 
		int maxRed=color.getRed();
		
		int minGreen=color.getGreen(); 
		int maxGreen=color.getGreen();
		
		int minBlue=color.getBlue(); 
		int maxBlue=color.getBlue();
		
		for (int i = 0; i < 5; i++) {
			 
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
		Button.waitForAnyPress();
		
	    color = cs.getColor();
		int r=color.getRed();
		int g=color.getGreen();
		int b=color.getBlue();
		
	
		System.out.println(r+", "+g+", "+b);

		
while (((r>=minRed)&&(r<=maxRed)) && ((g>=minGreen)&&(g<=maxGreen)) &&((b>=minBlue)&&(b<=maxBlue))){
			
	     Motor.A.setSpeed(180);
	     Motor.C.setSpeed(180);
		 	Motor.A.forward();
			Motor.C.forward();
			
			 color = cs.getColor();
				r=color.getRed();
			    g=color.getGreen();
				b=color.getBlue();
				
			
				System.out.println(r+", "+g+", "+b);
				
			
			
			}

            Motor.A.stop();
            Motor.C.stop();
				
			System.out.println("Oups fall risk");

		Button.waitForAnyPress();}
		  
		
	   }			