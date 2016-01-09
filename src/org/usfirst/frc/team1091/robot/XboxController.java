package org.usfirst.frc.team1091.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class XboxController {

	static final double sensitivity = 0.075;
	static final double deadzone = 0.2;
	static final double holdZone = 0.15;
	private double speed;
	private double tSpeed;
	private double totalStick;
	DriverStation driverStation;
	byte a = 1;
	byte b = 2;
	byte x = 3;
	byte y = 4;
	byte lb = 5;
	byte rb = 6;
	byte back = 7;
	byte start = 8;
	byte l3 = 9;
	byte r3 = 10;

	public XboxController(int port) {
		driverStation = DriverStation.getInstance();
	}

	public double forward() {
		return velocity(driverStation.getStickAxis(0, 1)) / 1.4 * -1;
	}

	public double turn() {
		return dead(driverStation.getStickAxis(0, 0)) / .6 * -1;
	}

	public double elevatorLift(int controller) {
		if (!isButtonDown(controller, r3)) {

			double leftStick = driverStation.getStickAxis(controller, 3) * -1;

			double rightStick = driverStation.getStickAxis(controller, 2) * -1;

			totalStick = (leftStick - rightStick);
		}

		else
			totalStick = .01;

		return totalStick;

	}

	private double velocity(double tilt) {

		if (Math.abs(tilt) > deadzone) { // forward
			if (tilt < 0) {
				speed = tilt * tilt * -.8; //if the robot isnot good set this to -.7
			}
			if (tilt > 0) {// backward
				speed = tilt * tilt * 1;
			}
		}
		if (Math.abs(tilt) < deadzone)
			speed = 0;

		return speed;
	}

	private double dead(double tilt) {
		if (Math.abs(tilt) >= deadzone) {
			if (tilt > 0)
				tSpeed = (tilt * tilt) / 2.25;

			if (tilt < 0)
				tSpeed = (tilt * tilt) / -2.25;

			return tSpeed;
		} else
			return 0;
	}

	public boolean isButtonDown(int port, byte button) {
		return driverStation.getStickButton(port, button);
	}
}
