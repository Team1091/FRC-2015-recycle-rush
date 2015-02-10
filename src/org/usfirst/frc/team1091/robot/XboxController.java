package org.usfirst.frc.team1091.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class XboxController {

	static final double sensitivity = 0.075;
	static final double deadzone = 0.2;
	static final double holdZone = 0.15;
	private double speed;
	private double tSpeed;
	DriverStation driverStation;
	byte a = 1; // good
	byte b = 2; //The mad one
	byte x = 3; //the sad one
	byte y = 4;
	byte lb = 5;
	byte rb = 6;
	byte back = 7;
	byte start = 8;
	byte l3 = 9;
	byte r3 = 10;

	public XboxController(int port) {
		driverStation = DriverStation.getInstance();
		// super(port, numAxisTypes, numButtonTypes);

		// Joystick
	}

	// 0 left lr
	// 1 left ud
	// 2 lt
	// 3 rt
	// 4 right lr
	// 5 right ud
	public double forward() {
		return velocity(driverStation.getStickAxis(0, 1)) / 1.9 * -1;
	}

	public double turn() {
		return dead(driverStation.getStickAxis(0, 0)) / 1.2 * -1;
	}

	public double elevatorLift() {
		double leftStick = driverStation.getStickAxis(0, 3);

		double rightStick = driverStation.getStickAxis(0, 2);

		double totalStick = (leftStick - rightStick);

		return totalStick;
	}

	private double velocity(double tilt) {

		if (Math.abs(tilt) > deadzone) { // forward
			if (tilt < 0) {
				speed = tilt * tilt * -.75;
			}
			if (tilt > 0) {// backward
				speed = tilt * tilt * .35;
			}
		}
		if (Math.abs(tilt) < deadzone)
			speed = 0;

		return speed;
	}

	private double dead(double tilt) {
		if (Math.abs(tilt) >= deadzone) {
			if (tilt > 0)
				tSpeed = (tilt * tilt) / 2.0;

			if (tilt < 0)
				tSpeed = (tilt * tilt) / -2.0;

			return tSpeed;
		} else
			return 0;
	}

	public boolean isButtonDown(byte button) {

		return driverStation.getStickButton(0, button);
	}

	// public void testButtons() {
	// int buttons = driverStation.getStickButtonCount(0);
	//
	// String output = "";
	// for (byte i = 1; i <= buttons; i++) {
	//
	// output += " " + i + ":";
	// if (driverStation.getStickButton(0, i)) {
	// output += "true";
	// } else {1222waaaaaaaz
	// output += "false";
	// }
	//
	// }
	// throw new RuntimeException(output);
	//
	// }
}
