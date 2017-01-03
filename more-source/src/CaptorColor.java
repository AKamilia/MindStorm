import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;

public class CaptorColor {
	public static void main(String argv[]) {
		ColorSensor cs = new ColorSensor(SensorPort.S1);

		for (int i = 0; i < 25; i++) {
			Color color = cs.getColor();
			System.out.println("Color = " + cs.getColorID() + " " + color.getColor() + "(" + color.getRed() + ","
					+ color.getGreen() + "," + color.getBlue() + ") " + color.getColor());
			Button.waitForAnyPress();
		}
	}
}
