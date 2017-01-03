package com.diderot;

import lejos.nxt.*;
import lejos.nxt.ColorSensor.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.*;
import java.io.*;
import java.util.Vector;

public class Main extends Program {
    static ColorSensor cs;
    static int acceleration = 0;
    static DifferentialPilot pilot;
    static OdometryPoseProvider opp;
    static Vector<Waypoint> points;

    public static void main(String[] args) throws IOException {
    	new Program_1();
    }
}
