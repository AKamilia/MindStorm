package com.diderot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import lejos.robotics.navigation.Waypoint;

public class Program_4 extends Program {
	Program_4() throws IOException {
		super();
		
	    File file = new File("ETALONNAGE.txt");

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
        OutputStream os_out = new FileOutputStream(file_out);
        Writer sw_out = new OutputStreamWriter(os_out);

        for (int i = 0; i < 10; i++) {
            Waypoint wp = action_and_save(i);
            sw_out.write(wp.getX() + ", " + wp.getY() + "\n");
        }

        sw_out.flush();
        sw_out.close();
        
        CouleurInterval[] config = new CouleurInterval[3];
        
        config[0] = etalonnage(); attente();
        config[1] = etalonnage(); attente();
        config[2] = etalonnage(); attente();

        Couleur color;        
/*        while (true) {
            color = getSensorColor();
            System.out.println("Début Boucle");

            if (insideInterval(color, color_p)) {
                action(0);
                System.out.println("Couleur Parcourt");
            } else {
            	if (insideInterval(color, config[0])) {
            		action(0);
            		System.out.println("Couleur Config 1");
            		while (insideInterval(getSensorColor(), config[0])) {
            			action(0);
            			System.out.println("Couleur Config 1");
            		}
            		
            		color = getSensorColor();
            		
            		if (insideInterval(color, config[1])) {
            			action(0);
            			System.out.println("Couleur Config 2");
            			
                		while (insideInterval(getSensorColor(), config[1])) {
                			action(0);
                			System.out.println("Couleur Config 2");
                		}
                		
            			color = getSensorColor();
            			if (insideInterval(color, config[2])) {
            				action(0);
            				System.out.println("Couleur Config 3");
            				
                    		while (insideInterval(getSensorColor(), config[2])) {
                    			action(0);
                    			System.out.println("Couleur Config 3");
                    		}
                    		
                    		action(45);
            			}
            		}
            	}
            }
        }*/
	}
}
