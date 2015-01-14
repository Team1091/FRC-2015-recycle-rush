package org.usfirst.frc.team1091.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class XboxController {
	static final int numAxisTypes=6;
	static final int numButtonTypes=10;
	DriverStation driverStation;
	public XboxController(int port) {
		driverStation= DriverStation.getInstance();				
		//super(port, numAxisTypes, numButtonTypes);
		// TODO Auto-generated constructor stub
	}
		// 0 left lr
		// 1 left ud
		// 2 lt
		// 3 rt
		// 4 right ud
		// 5 right lr
	public double getLeft() {
		// TODO Auto-generated method stub
		return driverStation.getStickAxis(0, 1)*-1;
	}

	public double getRight() {
		// TODO Auto-generated method stub
		return driverStation.getStickAxis(0, 5)*-1;
	}

}
