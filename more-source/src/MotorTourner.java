import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;

public class MotorTourner {

	public static void main(String[] args) {
		LCD.drawString("Programme 1", 0, 0);
		Button.waitForAnyPress();
		LCD.clear();
		
		Motor.A.forward();//pour aller vers l'avant avec le moteur A
		Motor.C.forward();// pour aller vers l'avant avec le moteur B
		
		LCD.drawString("FORWARD", 0, 0);
		Button.waitForAnyPress();
		LCD.drawString("BACKWARD", 0, 0);
		
		Motor.A.backward();// pour aller vers l'arriere avec le moteur A 
        Motor.C.backward();// pour aller vers l'arriere avec le moteur B
       
        
		Button.waitForAnyPress();
		Motor.A.stop();
		Motor.C.stop();

	}
}