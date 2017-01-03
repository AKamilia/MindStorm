package com.diderot;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.ColorSensor.Color;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Waypoint;

public class Program {
	static ColorSensor cs;
	static DifferentialPilot pilot;
	static OdometryPoseProvider opp;
	
	Program() {
        cs = new ColorSensor(SensorPort.S1);
        pilot = new DifferentialPilot(2.25f, 5.5f, Motor.A, Motor.C);
        opp = new OdometryPoseProvider(pilot);
	}
	
    public static ColorSensor getSensor() {
        return cs;
    }
    
	protected static Couleur getSensorColor() {
		int r = getSensor().getColor().getRed();
        int g = getSensor().getColor().getGreen();
        int b = getSensor().getColor().getBlue();

        return new Couleur(r, g, b);
	}

    public static void attente() {
        System.out.println("Merci d'appuyer sur un bouton pour continuer !");
        Button.waitForAnyPress();
    }

   public static Waypoint action_and_save(int rotation) {
        pilot.rotate(rotation);
        pilot.travel(5);
        System.out.println(opp.getPose().getX() + ", " + opp.getPose().getY());
        return new Waypoint(opp.getPose().getX(), opp.getPose().getY());
    }

    static boolean insideInterval(Couleur c, CouleurInterval ci) {
        boolean condition_1 = (c.getRed()   >= ci.getMinRed())   && (c.getRed()   <= ci.getMaxRed());
        boolean condition_2 = (c.getGreen() >= ci.getMinGreen()) && (c.getGreen() <= ci.getMaxGreen());
        boolean condition_3 = (c.getBlue()  >= ci.getMinBlue())  && (c.getBlue()  <= ci.getMaxBlue());

        return condition_1 && condition_2 && condition_3;
    }
    
	 public static CouleurInterval etalonnage() {
	        Color color = getSensor().getColor();
	        CouleurInterval color_i = new CouleurInterval();

	        attente();

	        color_i.setMinRed(color.getRed());
	        color_i.setMaxRed(color.getRed());
	        color_i.setMinGreen(color.getGreen());
	        color_i.setMaxGreen(color.getGreen());
	        color_i.setMinBlue(color.getBlue());
	        color_i.setMaxBlue(color.getBlue());

	        attente();

	        for (int i = 0; i < 200; i++) {
	            int r = getSensor().getColor().getRed();
	            int g = getSensor().getColor().getGreen();
	            int b = getSensor().getColor().getBlue();

	            System.out.println(r + ", " + g + ", " + b);

	            if (r <= color_i.getMinRed())   { color_i.setMinRed(r);   }
	            if (r >  color_i.getMaxRed())   { color_i.setMaxRed(r);   }
	            if (g <= color_i.getMinGreen()) { color_i.setMinGreen(g); }
	            if (g >  color_i.getMaxGreen()) { color_i.setMaxGreen(g); }
	            if (b <= color_i.getMinBlue())  { color_i.setMinBlue(b);  }
	            if (b >  color_i.getMaxBlue())  { color_i.setMaxBlue(b);  }
	        }

	        System.out.println(color_i.getMinRed() + ", " + color_i.getMinGreen() + " , " + color_i.getMinBlue());
	        System.out.println(color_i.getMaxRed() + ", " + color_i.getMaxGreen() + " , " + color_i.getMaxBlue());

	        return color_i;
	    }
}
