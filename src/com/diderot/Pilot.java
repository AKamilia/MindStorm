package com.diderot;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class Pilot {
	DifferentialPilot pilot;
	volatile boolean flag;
	
	Pilot() {
		this.pilot = new DifferentialPilot(2.25f, 5.5f, Motor.A, Motor.C);
		this.flag = true;
	}
	
    synchronized void forward() throws InterruptedException {
		pilot.travel(0.5, true);
	}
	
    synchronized void rotate(int d) throws InterruptedException {
		pilot.rotate(20, true);
	}

	public void stop() {
		pilot.stop();
	}
}
