package com.diderot;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Traveler implements Runnable {
	Pilot pilot;
	
    public Traveler(Pilot pilot){
    	this.pilot = pilot;
    }

	@Override
	public void run() {
    	try {
			pilot.forward();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  }