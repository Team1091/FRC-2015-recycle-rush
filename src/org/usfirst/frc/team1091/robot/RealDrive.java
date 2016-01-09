package org.usfirst.frc.team1091.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;

public class RealDrive extends RobotDrive {

	public RealDrive(int leftMotorChannel, int rightMotorChannel) {
		super(leftMotorChannel, rightMotorChannel);
	}

	boolean down = true;
	boolean signal = true;
	boolean allow = true;
	boolean clossed = false;
	boolean allow1 = true;
	boolean clossed1 = false;
	boolean swag = false;
	boolean first = true;
	long time;
	Solenoid clawSolenoid1 = new Solenoid(0, 0);
	Solenoid clawSolenoid2 = new Solenoid(0, 1);
	Solenoid brakeSolenoid1 = new Solenoid(0, 2);
	Solenoid brakeSolenoid2 = new Solenoid(0, 3);

	public void yolo420SwagDrive(XboxController xboxController) {
		if (xboxController == null)
			throw new NullPointerException("No controller found!");

		if (xboxController.isButtonDown(0, xboxController.start))
			arcadeDrive(0, -.5, false);
		else if (xboxController.isButtonDown(0, xboxController.back))
			arcadeDrive(0, .4, false);

		else if (xboxController.isButtonDown(0, xboxController.y))
			arcadeDrive(.30, 0, false);
		else if (xboxController.isButtonDown(0, xboxController.x))
			arcadeDrive(-.30, 0, false);
		else
			arcadeDrive(xboxController.forward(), xboxController.turn(), false);

	}

	public void pneumatics(XboxController xboxController, int controller) {

		if (xboxController.isButtonDown(controller, xboxController.rb)) {
			if (allow) {
				if (clossed) {
					clawSolenoid1.set(true);
					clawSolenoid2.set(false);
					clossed = false;
					allow = false;
				}

				else {
					clawSolenoid1.set(false);
					clawSolenoid2.set(true);
					clossed = true;
					allow = false;
				}
			}
		} else
			allow = true;
	}

	public void autoBrake(boolean good) {

		if (good) {
			brakeSolenoid1.set(true);
			brakeSolenoid2.set(false);
		}

		else {
			brakeSolenoid1.set(false);
			brakeSolenoid2.set(true);
		}
	}

	public void close() {
		clawSolenoid1.set(false);
		clawSolenoid2.set(true);
	}

	public void open() {
		clawSolenoid1.set(true);
		clawSolenoid2.set(false);
	}

	public void autoDrive(double l, double r) {
		tankDrive(l, r);
	}
}