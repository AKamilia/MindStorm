package com.diderot;

public class Program_1 extends Program {
	Program_1() {
		super();
		CouleurInterval color_0  = etalonnage(); attente();
		CouleurInterval color_1  = etalonnage(); attente();
		new Rotator(color_0, color_1);
	}
}
