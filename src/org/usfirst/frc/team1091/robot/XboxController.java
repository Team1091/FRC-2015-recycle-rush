package org.usfirst.frc.team1091.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class XboxController {
	static final int numAxisTypes = 6;
	static final int numButtonTypes = 10;
	static final double acc = 0.066;
	private double speed = 0;
	static final double deadZone = 0.1;

	DriverStation driverStation;

	public XboxController(int port) {
		driverStation = DriverStation.getInstance();
		// super(port, numAxisTypes, numButtonTypes);
		// TODO Auto-generated constructor stub
	}

	// 0 left lr
	// 1 left ud
	// 2 lt
	// 3 rt
	// 4 right ud
	// 5 right lr
	public double getLeft() {
		return velocity(driverStation.getStickAxis(0, 1)) * -1;
	}

	public double getRight() {
		// TODO Auto-generated method stub
		return driverStation.getStickAxis(0, 5) * -1;
	}

	private double velocity(double tilt) {

		if (Math.abs(tilt) >= deadZone) {
			speed = speed + (tilt * acc);
			if (speed > 1)
				return 1;
			else if (speed < -1)
				return -1;
			else
				return speed;
		} else {
			if (Math.abs(speed) > deadZone) {
				speed *= 0.8;
				return speed;
			} else
				return 0;
		}
	}
}
