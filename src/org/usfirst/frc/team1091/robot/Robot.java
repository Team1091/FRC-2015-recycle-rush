package org.usfirst.frc.team1091.robot;

import com.ni.vision.NIVision;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Sendable;

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
	XboxController xboxcontroler;
	DigitalInput topLimitSwitch;
	DigitalInput lowerLimitSwitch;
	edu.wpi.first.wpilibj.Timer autoClock;
	Compressor compressor;
	Relay underRelay;
				//the bit
	static final int maxController = 0;
	long startTime;
	long matchTime;
	private boolean first = true;
	Sendable distance;
	boolean bDown;
	boolean moving;
	boolean on;
	boolean down;
	double elevatorLift = 0;


	// CameraServer server;

	public Robot() {
		yolo420SwagDrive = new RealDrive(0, 1);
		yolo420SwagDrive.setExpiration(0.1);
		xboxController = new XboxController(0);
		elevatorMotor = new Victor(2);
		topLimitSwitch = new DigitalInput(0);
		lowerLimitSwitch = new DigitalInput(1);

		compressor = new Compressor(1);

		underRelay = new Relay(0);
		// server = CameraServer.getInstance();
		// server.setQuality(50);
		// server.startAutomaticCapture("cam0");

	}

	boolean exampleBool;
	{
		exampleBool = isDisabled();
		exampleBool = isEnabled();

		exampleBool = isAutonomous();
		exampleBool = isOperatorControl();
		exampleBool = isTest();

		while (isOperatorControl() && isEnabled()) {
		}

		exampleBool = DriverStation.getInstance().isDisabled();

	}

	public void autonomous() {

		while (isAutonomous() && isEnabled()) {
			

			yolo420SwagDrive.setSafetyEnabled(false);

			if (first) {

				startTime = System.currentTimeMillis();

				first = false;

			}

			long currentTime = System.currentTimeMillis() - startTime;
//			if (currentTime < 3500) {
//			drive.close();
//			drive.autoBrake(true);
//			elevatorMotor.set(-.9);
//		} else if (currentTime < 4500) {
//			drive.autoBrake(false);
//			drive.autoDrive(.65, -.65);
//			elevatorMotor.set(0);
//		} else if (currentTime < 5250 + 300) {
//			drive.drive(.4, 0);
//		} else if (currentTime < 6700) {
//			drive.autoDrive(-.25, .4);
			if (currentTime < 2500) {
				yolo420SwagDrive.open();
				yolo420SwagDrive.drive(.4, 0);
			} else if (currentTime < 3000) {
				yolo420SwagDrive.drive(.2, 0);
			} else if (currentTime < 4500) {
				yolo420SwagDrive.drive(0, 0);
			} else if (currentTime < 11200+2600) {
				// elevatorMotor.set(-.6);
			} else if (currentTime < 12200+2600) {
				// drive.open();
				yolo420SwagDrive.drive(0, 0);
				elevatorMotor.set(0);
			} else
				yolo420SwagDrive.drive(0, 0);
		}

		if (isAutonomous() && !isEnabled())
			first = true;

	}

	public void operatorControl() {


		yolo420SwagDrive.setSafetyEnabled(true);

		while (isOperatorControl() && isEnabled()) {

			if (moving == false)
				elevatorLift = xboxController.elevatorLift(maxController);

			if (!lowerLimitSwitch.get())
				elevatorLift = Math.min(0, elevatorLift);

			if (!topLimitSwitch.get())
				elevatorLift = Math.max(elevatorLift, 0);

			elevatorMotor.set(elevatorLift);
			if (Math.abs(elevatorLift) > 0)
				yolo420SwagDrive.autoBrake(true);
			else
				yolo420SwagDrive.autoBrake(false);
			yolo420SwagDrive.yolo420SwagDrive(xboxController);
			yolo420SwagDrive.pneumatics(xboxController, maxController);

			Timer.delay(0.005); // wait for a motor update time

		}
	}
}
