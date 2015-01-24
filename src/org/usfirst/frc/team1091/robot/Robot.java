package org.usfirst.frc.team1091.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */

public class Robot extends SampleRobot {

	private SpeedController elevatorMotor; // Lifter motor
	RealDrive yolo420SwagDrive; // class that handles basic drive operations
	XboxController xboxController; // set to ID 1 in DriverStation
	DigitalInput topLimitSwitch;
	DigitalInput lowerLimitSwitch;
	edu.wpi.first.wpilibj.Timer autoClock;
	long startTime;
	private boolean first = true;
	private boolean second = true;
	boolean bDown;

	public Robot() {
		yolo420SwagDrive = new RealDrive(0, 1);
		yolo420SwagDrive.setExpiration(0.1);
		xboxController = new XboxController(0);

		elevatorMotor = new Victor(2);
		topLimitSwitch = new DigitalInput(0);
		lowerLimitSwitch = new DigitalInput(1);
	}

	public void autonomous() {
		while (isAutonomous() && isEnabled()) {
			yolo420SwagDrive.setSafetyEnabled(false);
			if (first) {
				startTime = System.currentTimeMillis();
				first = false;
			}
			if (System.currentTimeMillis() - startTime < 3000) {
				yolo420SwagDrive.drive(.3, 0);
			} else if (System.currentTimeMillis() - startTime < 3500) {
				yolo420SwagDrive.drive(0, 0);
				elevatorMotor.set(.75);
			} else if (System.currentTimeMillis() - startTime < 5100) {
				yolo420SwagDrive.drive(-.3, 0);
				elevatorMotor.set(0);
			} else if (System.currentTimeMillis() - startTime < 15000) {
				yolo420SwagDrive.drive(0, 0);
			}
		}
	}

	public void operatorControl() {
		yolo420SwagDrive.setSafetyEnabled(true);

		while (isOperatorControl() && isEnabled()) {

			double elevatorLift = xboxController.elevatorLift();

			if (xboxController.isButtonDown(xboxController.a)) {

				elevatorLift = 0.1;
			}

			else {
				if (xboxController.isButtonDown(xboxController.b)) {

					if (second) {

						startTime = System.currentTimeMillis();

						second = false;
					}
					if (System.currentTimeMillis() - startTime < 1000)
						elevatorLift = .75;
					if (System.currentTimeMillis() - startTime > 1000)
						elevatorLift = 0;

				} else
					second = true;

				if (System.currentTimeMillis() - startTime < 1000)
					elevatorLift = .75;

			}

			if (!topLimitSwitch.get())
				elevatorLift = Math.min(0, elevatorLift);

			if (!lowerLimitSwitch.get())
				elevatorLift = Math.max(elevatorLift, 0);

			elevatorMotor.set(elevatorLift);

			yolo420SwagDrive.yolo420SwagDrive(xboxController);
			Timer.delay(0.005); // wait for a motor update time

		}
	}
}
