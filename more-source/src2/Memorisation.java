package com.company;

import lejos.nxt.*;
import lejos.nxt.ColorSensor.Color;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.*;
import java.io.*;
import java.util.Vector;

public class Memorisation {
    static ColorSensor cs;
    static int acceleration = 0;
    static DifferentialPilot pilot;
    static OdometryPoseProvider opp;
    static Vector<Waypoint> points;

    public static ColorSensor getSensor() {
        return cs;
    }

    public static void setAcceleration(int a) {
        acceleration = a;
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

        for (int i = 0; i < 500; i++) {
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

    public static void attente() {
        System.out.println("Merci d'appuyer sur un bouton pour continuer !");
        Button.waitForAnyPress();
    }

    public static void action_and_save(int rotation, Writer sw_out) {
        pilot.rotate(rotation);
        pilot.travel(5);
        System.out.println(opp.getPose().getX() + " " + opp.getPose().getY());
        
        try {
			sw_out.write(opp.getPose().getX() + " " + opp.getPose().getY() + "\n");
			sw_out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static boolean insideInterval(Couleur c, CouleurInterval ci) {
        boolean condition_1 = (c.getRed()   >= ci.getMinRed())   && (c.getRed()   <= ci.getMaxRed());
        boolean condition_2 = (c.getGreen() >= ci.getMinGreen()) && (c.getGreen() <= ci.getMaxGreen());
        boolean condition_3 = (c.getBlue()  >= ci.getMinBlue())  && (c.getBlue()  <= ci.getMaxBlue());

        return condition_1 && condition_2 && condition_3;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("ETALONNAGE.txt");

        cs    = new ColorSensor(SensorPort.S1);
        pilot = new DifferentialPilot(2.25f, 5.5f, Motor.A, Motor.C);
        opp   = new OdometryPoseProvider(pilot);

        CouleurInterval color_p  = new CouleurInterval();
        CouleurInterval color_f  = new CouleurInterval();
        CouleurInterval color_fp = new CouleurInterval();

        if (file.exists()) {
            System.out.println("exists !");
            InputStream is = new FileInputStream(file);
            BufferedReader sr = new BufferedReader(new InputStreamReader(is));

            color_p.minFromString(sr.readLine());
            color_p.maxFromString(sr.readLine());

            color_f.minFromString(sr.readLine());
            color_f.maxFromString(sr.readLine());

            color_fp.minFromString(sr.readLine());
            color_fp.maxFromString(sr.readLine());
            
            sr.close();
        } else {
            System.out.println("NOT exists !");

            OutputStream os = new FileOutputStream(file);
            Writer sw = new OutputStreamWriter(os);

            color_p  = etalonnage(); attente();
            color_f  = etalonnage(); attente();
            color_fp = etalonnage(); attente();

            sw.write(color_p.minToString()  + "\n");
            sw.write(color_p.maxToString()  + "\n");
            sw.write(color_f.minToString()  + "\n");
            sw.write(color_f.maxToString()  + "\n");
            sw.write(color_fp.minToString() + "\n");
            sw.write(color_fp.maxToString() + "\n");
            sw.flush();
            sw.close();
        }

        File file_out = new File("OUTPOINTS.txt");
        Writer sw_out = new OutputStreamWriter(new FileOutputStream(file_out));

        int angle = 1;
        while (true) {
            int r = getSensor().getColor().getRed();
            int g = getSensor().getColor().getGreen();
            int b = getSensor().getColor().getBlue();

            Couleur color = new Couleur(r, g, b);

            if (insideInterval(color, color_p)) {
                action_and_save(0, sw_out); angle = -1 * angle;
            } else {
                action_and_save(10 * angle, sw_out);
            }

            if (insideInterval(color, color_f)) { pilot.stop(); }
            if (insideInterval(color, color_fp)) {
            	sw_out.close();
            	break;
        	}
        }
    }
}