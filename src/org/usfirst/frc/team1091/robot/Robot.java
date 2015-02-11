package org.usfirst.frc.team1091.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SensorBase;
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
	long matchTime;
	private boolean first = true;
	private boolean second = true;
	boolean bDown;
	boolean moving;
	boolean on;
	boolean down;
	double elevatorLift = 0;
	Compressor compressor;
	Relay underRelay;

	public Robot() {
		yolo420SwagDrive = new RealDrive(0, 1);
		yolo420SwagDrive.setExpiration(0.1);
		xboxController = new XboxController(0);
		elevatorMotor = new Victor(2);
		topLimitSwitch = new DigitalInput(0);
		lowerLimitSwitch = new DigitalInput(1);

		compressor = new Compressor(1);

		underRelay = new Relay(0);

		new SensorBase() {
		};

	}

	public void autonomous() {
		while (isAutonomous() && isEnabled()) {

			yolo420SwagDrive.setSafetyEnabled(false);

			if (first) {

				startTime = System.currentTimeMillis();

				first = false;

			}

			long currentTime = System.currentTimeMillis() - startTime;

			if (currentTime < 1000) {
				yolo420SwagDrive.drive(.2, 0);
			} else if (currentTime < 2000) {
				yolo420SwagDrive.autoSwagDrive(0, 0);
				elevatorMotor.set(.75);
			} else if (currentTime < 13200) {
				yolo420SwagDrive.autoSwagDrive(-.25, .2);
				elevatorMotor.set(.19);
			} else if (currentTime < 14700) {
				yolo420SwagDrive.autoSwagDrive(.7, -.7);
				elevatorMotor.set(.0);
			} else if (currentTime < 15000) {
				yolo420SwagDrive.autoSwagDrive(0, 0);
				elevatorMotor.set(0);
			}
		}
		while (isAutonomous() && !isEnabled())
			first = true;
	}

	public void operatorControl() {
		yolo420SwagDrive.setSafetyEnabled(true);

		while (isOperatorControl() && isEnabled()) {

			if (xboxController.isButtonDown(xboxController.b)) {

				if (second) {
					startTime = System.currentTimeMillis();
					second = false;
				}
			} else
				second = true;

			if (System.currentTimeMillis() - startTime < 1000) {
				elevatorLift = .8;
				moving = true;
			}
			if (System.currentTimeMillis() - startTime > 1000) {
				elevatorLift = 0;
				moving = false;
			}

			if (moving == false)
				elevatorLift = xboxController.elevatorLift();

			if (!topLimitSwitch.get())
				elevatorLift = Math.min(0, elevatorLift);

			if (!lowerLimitSwitch.get())
				elevatorLift = Math.max(elevatorLift, 0);

			if (xboxController.isButtonDown(xboxController.a))
				elevatorLift = 0.18;

			elevatorMotor.set(elevatorLift);

			yolo420SwagDrive.yolo420SwagDrive(xboxController);
			yolo420SwagDrive.pneumatics(xboxController);
			// yolo420SwagDrive.pneumatics(xboxController);
			if (System.currentTimeMillis() - matchTime > 140000)

				Timer.delay(0.005); // wait for a motor update time

		}
	}
}
