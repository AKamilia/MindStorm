import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;

public class Etalonnee {
	

	static int minRed; 
	static int maxRed;
	
	static int minGreen;
	static int maxGreen;
	
	static int minBlue;
	static int maxBlue;
	
	
	static int minRedd; 
	static int maxRedd;
	
	static int minGreenn;
	static int maxGreenn;
	
	static int minBluee;
	static int maxBluee;
	
	public static void Avancer(int x, int y, int z,int minr,int maxr,int ming, int maxg, int minb, int maxb,int minrr,int maxrr,int mingg, int maxgg, int minbb, int maxbb){
		ColorSensor cs = new ColorSensor(SensorPort.S1);
		Color color = cs.getColor();
	   while (((x>=minr)&&(x<=maxr)) && ((y>=ming)&&(y<=maxg)) &&((z>=minb)&&(z<=maxb))){
			
		     Motor.A.setSpeed(180);
		     Motor.C.setSpeed(180);
		     Motor.C.forward();
	         Motor.A.forward();
	         
				
				 color = cs.getColor();
					x=color.getRed();
				    y=color.getGreen();
					z=color.getBlue();
			 System.out.println(x+", "+y+", "+z);
			 System.out.println("Je suis mon parcours");}
	   
	   
	   if (((x>=minrr)&&(x<=maxrr)) && ((y>=mingg)&&(y<=maxgg)) &&((z>=minbb)&&(z<=maxbb)))
		{ 
		   Motor.A.stop();
		   Motor.C.stop();
		   System.out.println("Je suis arrivé");

		  }
	   else 
	   {
		   ChercherParcours(x,y,z,minr,maxr,ming,maxg,minb,maxb, minrr,maxrr ,mingg,  maxgg, minbb,  maxbb); 
	   }
		   }	   
	    
   
   
   public static void ChercherParcours(int x, int y, int z,int minr,int maxr,int ming, int maxg, int minb, int maxb,int minrr,int maxrr,int mingg, int maxgg, int minbb, int maxbb){
	    ColorSensor cs = new ColorSensor(SensorPort.S1);
		Color color = cs.getColor();
		  boolean droit=true;
		 
		  
		while (((((x<minr)||(x>maxr)) || ((y<ming)||(y>maxg)) ||((z<minb)||(z>maxb))))&& ((((x<minRedd)||(x>maxRedd)) || ((y<minGreenn)||(y>maxGreenn)) ||((z<minBluee)||(z>maxBluee))))){
			     Motor.C.setSpeed(150);
			     Motor.A.setSpeed(150);
			
			     if (droit){
			    	 
			    	 
			                Motor.C.forward();
			                Motor.A.stop();
			                
			                
		                droit=false;
		                System.out.println("droite");
		        		}
			     else{
			    	  
			    	    Motor.A.forward();
			    	    Motor.C.stop(); 
			    	    
			    	    
			    	    droit=true;
			    	    System.out.println("gauche");
			    	
			    	    
     			     }
        	
		    color = cs.getColor();
			
		         x=color.getRed();
		         y=color.getGreen();
			     z=color.getBlue();
			  
		}
		 
		
	     if(((x>=minrr)&&(x<=maxrr)) && ((y>=mingg)&&(y<=maxgg)) &&((z>=minbb)&&(z<=maxbb)))
			{ 
	  	   Motor.A.stop();
		   Motor.C.stop();
		   System.out.println("Je suis arrivé au point d'arrét");
	    	 
	    	}
		   else 
		   {
			   Avancer(x,y,z,minr,maxr,ming,maxg,minb,maxb, minrr, maxrr,mingg,  maxgg,  minbb,  maxbb);
		   }
			
	}
   
   
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
	Button.waitForAnyPress();}
   
   
   
   
   
public static void finParcours(){	
	   
	   ColorSensor cs = new ColorSensor(SensorPort.S1);
	   Color color = cs.getColor();
	 
	 Button.waitForAnyPress();
	
	 minRedd=color.getRed(); 
	 maxRedd=color.getRed();
	
	 minGreenn=color.getGreen(); 
	 maxGreenn=color.getGreen();
	
	 minBluee=color.getBlue(); 
	 maxBluee=color.getBlue();
	
	for (int i = 0; i < 5; i++) {
		 
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
		     finParcours();
		
		   ColorSensor cs = new ColorSensor(SensorPort.S1);
		   Color color = cs.getColor();
		
	    color = cs.getColor();
		int r=color.getRed();
		int g=color.getGreen();
		int b=color.getBlue();
		
	
		System.out.println(r+", "+g+", "+b);

	Avancer(r,g,b,minRed,maxRed,minGreen,maxGreen,minBlue,maxBlue,minRedd,maxRedd,minGreenn,maxGreenn,minBluee,maxBluee);
	}
	
	}