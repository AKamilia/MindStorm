package com.diderot;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;

public class Rotator {
	private static ColorSensor cs;
	private int index = 1;
	private int v = 1;
	private DifferentialPilot pilot;

	Rotator(CouleurInterval color_0, CouleurInterval color_1) {
		cs = new ColorSensor(SensorPort.S1, Color.WHITE);
	    this.pilot = new DifferentialPilot(2.25f, 5.5f, Motor.A, Motor.C);

		while(true) {
			if (insideInterval(getSensorColor(), color_0)) {
				System.out.println("Couleur parcourt");
				pilot.forward();
			} else if (insideInterval(getSensorColor(), color_1)) {
				System.out.println("Couleur arrêt");
				pilot.stop();
				break;
			} else {
				System.out.println("Couleur autre");
				while(true) {
					v = v * -1;
					pilot.stop();
					pilot.rotate(index++ * v);
					
					if (insideInterval(getSensorColor(), color_0) || insideInterval(getSensorColor(), color_1)){
						index = 1;
						break;
					}
 				}
			}
		}
	}
	
    static boolean insideInterval(Couleur c, CouleurInterval ci) {
        boolean condition_1 = (c.getRed()   >= ci.getMinRed())   && (c.getRed()   <= ci.getMaxRed());
        boolean condition_2 = (c.getGreen() >= ci.getMinGreen()) && (c.getGreen() <= ci.getMaxGreen());
        boolean condition_3 = (c.getBlue()  >= ci.getMinBlue())  && (c.getBlue()  <= ci.getMaxBlue());

        return condition_1 && condition_2 && condition_3;
    }
	
	protected static Couleur getSensorColor() {
		Color c = cs.getColor();
		
		int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        return new Couleur(r, g, b);
	}

}
