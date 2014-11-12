package followWall;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class ParallelWall implements Behavior {

	private boolean supressed;
	private SensorThread sensorThread;
	private RobotController controller;
	private RegulatedMotor leftMotor, rightMotor;
	
	private static final float MAX_DISTANCE = (float) 0.09;
	private static final float CONTROL_DISTANCE = (float) 0.14;
	private static final float ERROR = (float) 0.01;
	
	public ParallelWall(RobotController controller, SensorThread sensorThread, RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		this.controller = controller;
		this.sensorThread = sensorThread;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}
	
	private boolean isWallDetected() {

		if ((sensorThread.getSonarDistance1() < MAX_DISTANCE && sensorThread.getSonarDistance2() < CONTROL_DISTANCE
				|| sensorThread.getSonarDistance2() < MAX_DISTANCE && sensorThread.getSonarDistance1() < CONTROL_DISTANCE) 
				&& Math.abs(sensorThread.getSonarDistance1()- sensorThread.getSonarDistance2())> ERROR) {
			return true;
		}
		
		else {
			return false;
		}
	}

	public boolean takeControl() {
		return isWallDetected();
	}

	public void suppress() {
		supressed = true;
	}

	public void action() {
		
		//try to follow near the wall
			
		if(sensorThread.getSonarDistance1() < sensorThread.getSonarDistance2()){
			while(sensorThread.getSonarDistance2()-sensorThread.getSonarDistance1() > ERROR){
				rightMotor.rotate(1, true);
				leftMotor.rotate(-1);
				
			}
		}else if(sensorThread.getSonarDistance2() < sensorThread.getSonarDistance1()){
			while(sensorThread.getSonarDistance1()- sensorThread.getSonarDistance2()> ERROR){
				leftMotor.rotate(1, true);
				rightMotor.rotate(-1);
			}
		}
		
		while(rightMotor.isMoving() || leftMotor.isMoving()) {
			if(supressed) {
				controller.endAction();
				return;
			}
			
			Thread.yield();
		}
		
		controller.endAction();
	}

}
