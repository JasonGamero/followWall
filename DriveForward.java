package followWall;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;

class DriveForward implements Behavior {

	private boolean suppressed = false;
	private RegulatedMotor leftMotor;
	private RegulatedMotor rightMotor;
	private RobotController controller;
	
	public DriveForward(RobotController controller, RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		this.controller = controller;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}

	public boolean takeControl() {
			/*if ((Button.waitForAnyPress() & Button.ID_ESCAPE) != 0)
	        {
	            Button.LEDPattern(0);
	            System.exit(1);
	        }*/
		return true;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		
		leftMotor.forward();
		rightMotor.forward();
		
		while (!suppressed) {
			Thread.yield();
		}

		controller.endAction();
	}
}

