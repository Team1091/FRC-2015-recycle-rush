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
	boolean swag = false;
	boolean first = true;
	long time;
	Solenoid s1 = new Solenoid(0, 0);
	Solenoid s2 = new Solenoid(0, 1);

	public void yolo420SwagDrive(XboxController xboxController) {
		if (xboxController == null) {
			throw new NullPointerException("No controller found!");
		}

		if (xboxController.isButtonDown(xboxController.start))
			arcadeDrive(0, -.25, false);
		else if (xboxController.isButtonDown(xboxController.back))
			arcadeDrive(0, .2, false);

		else if (xboxController.isButtonDown(xboxController.y))
			arcadeDrive(.15, 0, false);
		else if (xboxController.isButtonDown(xboxController.x))
			arcadeDrive(-.15, 0, false);
		else
			arcadeDrive(xboxController.forward(), xboxController.turn(), false);

	}

	public void pneumatics(XboxController xboxController) {

		if (xboxController.isButtonDown(xboxController.rb)) {
			if (allow) {
				if (clossed) {
					s1.set(true);
					s2.set(false);
					clossed = false;
					allow = false;
				}

				else {
					s1.set(false);
					s2.set(true);
					clossed = true;
					allow = false;
				}
			}
		}
		else
			allow = true;
	}
	
//	public void swagMatics(XboxController xboxController){
//		if (xboxController.isButtonDown(xboxController.r3))
//			swag = true;
//			
//		while (swag) {
//			if (first){ 
//				time = System.currentTimeMillis();
//				first = false;
//			}
//			if (System.currentTimeMillis() - startTime < 1000) {
//	yolo420SwagDrive.drive(.6, 0);
//}
//		}
//	}

	public void autoSwagDrive(double l, double r) {
		tankDrive(l, r);
	}
}
