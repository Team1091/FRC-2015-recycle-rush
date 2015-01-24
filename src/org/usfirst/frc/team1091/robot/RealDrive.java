package org.usfirst.frc.team1091.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class RealDrive extends RobotDrive {

	public RealDrive(int leftMotorChannel, int rightMotorChannel) {
		super(leftMotorChannel, rightMotorChannel);
		// TODO Auto-generated constructor stub
	}

	public void yolo420SwagDrive(XboxController xboxController) {
		if (xboxController == null) {
			throw new NullPointerException("No controller found!");
		}

		arcadeDrive(xboxController.forward(), xboxController.turn(), false);
	}

}
