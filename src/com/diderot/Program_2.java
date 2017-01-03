package com.diderot;

class DataColorInterval {
	int min_r;
	int min_b;
	int min_g;
	int max_r;
	int max_b;
	int max_g;
	String name;

	DataColorInterval(String name, int min_r, int min_g, int min_b, int max_r, int max_g, int max_b) {
		this.min_r = min_r;
		this.min_b = min_b;
		this.min_g = min_g;
		
		this.max_r = max_r;
		this.max_b = max_b;
		this.max_g = max_g;
		
		this.name = name;
		
	}
}

public class Program_2 extends Program {
	private Couleur currentColor;
	Program_2() throws InterruptedException {
		super();
		
		DataColorInterval[] data = new DataColorInterval[]{
			new DataColorInterval("Noir" , 0, 0, 0, 119, 119, 119),
			new DataColorInterval("Blanc", 217, 217, 217, 255, 255, 255),
			new DataColorInterval("Rouge", 139, 35, 35, 255, 64, 64),
			new DataColorInterval("Jaune", 139, 90, 0, 255, 165, 0)
		};
		
		while(true) {
			wait();
			currentColor = getSensorColor();
			
			for(DataColorInterval record : data) {
				boolean c_1 = (currentColor.getRed()   >= record.min_r) && (currentColor.getRed()   <= record.max_r);
				boolean c_2 = (currentColor.getGreen() >= record.min_g) && (currentColor.getGreen() <= record.max_g);
				boolean c_3 = (currentColor.getBlue()  >= record.min_b) && (currentColor.getBlue()  <= record.max_b);
				
				if (c_1 && c_2 && c_3) {
					System.out.println(record.name);
				}	
			}
		}
	}
	
}
