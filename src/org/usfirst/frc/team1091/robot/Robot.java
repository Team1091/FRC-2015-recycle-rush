package org.usfirst.frc.team1091.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it 
 * contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    RealDrive yolo420SwagDrive;  // class that handles basic drive operations
    XboxController xboxController;  // set to ID 1 in DriverStation
    
    
    public Robot() {
    	yolo420SwagDrive = new RealDrive(0, 1);
    	yolo420SwagDrive.setExpiration(0.1);
        xboxController = new XboxController(0);
    }

    
    /**
     * Runs the motors with tank steering.
     */
    public void operatorControl() {
    	yolo420SwagDrive.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	yolo420SwagDrive.yolo420SwagDrive(xboxController);
            Timer.delay(0.005);		// wait for a motor update time
        }
    }
}
