package com.company;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

import java.io.*;

public class Memorisationn {
    static ColorSensor cs;
    static int acceleration = 0;

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

        for (int i = 0; i < 300; i++) {
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

    public static void action(int acceleration_1, int acceleration_2) {
        Motor.A.setSpeed(acceleration_1);
        Motor.C.setSpeed(acceleration_2);
        Motor.A.forward();
        Motor.C.forward();
    }

    public static boolean insideInterval(Couleur c, CouleurInterval ci) {
        boolean condition_1 = (c.getRed()   >= ci.getMinRed())   && (c.getRed()   <= ci.getMaxRed());
        boolean condition_2 = (c.getGreen() >= ci.getMinGreen()) && (c.getGreen() <= ci.getMaxGreen());
        boolean condition_3 = (c.getBlue()  >= ci.getMinBlue())  && (c.getBlue()  <= ci.getMaxBlue());

        return condition_1 && condition_2 && condition_3;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("ETALONNAGE.txt");

        OutputStream os = new FileOutputStream(file);
        Writer sw = new OutputStreamWriter(os);

        InputStream is = new FileInputStream(file);
        BufferedReader sr = new BufferedReader(new InputStreamReader(is));

        if (true) {
            cs = new ColorSensor(SensorPort.S1);

            CouleurInterval color_p  = new CouleurInterval();
            CouleurInterval color_f  = new CouleurInterval();
            CouleurInterval color_fp = new CouleurInterval();

            if (file.exists()) {
                color_p.minFromString(sr.readLine());
                color_p.maxFromString(sr.readLine());

                color_f.minFromString(sr.readLine());
                color_f.maxFromString(sr.readLine());

                color_fp.minFromString(sr.readLine());
                color_fp.maxFromString(sr.readLine());
            } else {
                file.createNewFile();

                color_p  = etalonnage(); attente();
                color_f  = etalonnage(); attente();
                color_fp = etalonnage(); attente();

                sw.write(color_p.minToString()  + "\n");
                sw.write(color_p.maxToString()  + "\n");
                sw.write(color_f.minToString()  + "\n");
                sw.write(color_f.maxToString()  + "\n");
                sw.write(color_fp.minToString() + "\n");
                sw.write(color_fp.maxToString() + "\n");
                sw.close();
            }


            while (true) {
                int r = getSensor().getColor().getRed();
                int g = getSensor().getColor().getGreen();
                int b = getSensor().getColor().getBlue();

                Couleur color = new Couleur(r, g, b);

                if (insideInterval(color, color_p)) {
                    action(180 + acceleration, 150);
                    setAcceleration(acceleration + 3);
                } else {
                    setAcceleration(0);
                }

                if (insideInterval(color, color_f)) {
                    action(150, 180 + acceleration);
                    setAcceleration(acceleration + 3);
                }

                if (insideInterval(color, color_fp)) {
                    break;
                }
            }
        }
    }
}
