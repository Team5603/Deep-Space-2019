/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Climb;
import frc.robot.commands.IntakeOutakeJoystick;
import frc.robot.subsystems.IntakeOutake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
 
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  
  Joystick m_OPstick = new Joystick(RobotMap.OPjoystick);
  Joystick m_leftJoystick = new Joystick(RobotMap.LeftStick);
  Joystick m_rightJoystick = new Joystick(RobotMap.RightStick);

  public Button climberButton;
  public Button IntakeButton;
  public JoystickButton OuttakeButton;
  
  public OI() {
    climberButton = new JoystickButton(m_OPstick, RobotMap.ClimbButton);
    IntakeButton = new JoystickButton(m_OPstick, RobotMap.Intake);
    OuttakeButton = new JoystickButton(m_OPstick, RobotMap.Outtake);
    


    climberButton.whileHeld(new Climb());
    IntakeButton.whileHeld(new IntakeOutakeJoystick());
    OuttakeButton.whileHeld(new IntakeOutakeJoystick());
    
    
  }
  public double getLeftDrive() {
    return -m_leftJoystick.getRawAxis(RobotMap.DriveAxis);

  }

  public double getRightDrive() {
    return -m_rightJoystick.getRawAxis(RobotMap.DriveAxis);

  }

  public double getSlider() {
    return m_rightJoystick.getRawAxis(RobotMap.SlideAxis);

  }
  

 
  public double getLiftPower(){
    double lift;
    

    lift = m_OPstick.getRawAxis(RobotMap.updawg);
    return lift;
    

   
  }
  
  public double getElbow() 
  {
    double elbowValue = 0;
      elbowValue = m_OPstick.getRawAxis(RobotMap.rYStick);
    
    return elbowValue;
  }

  public double ClimbMotorDrive() {
    double drivePower = 0;

    if (m_OPstick.getRawAxis(RobotMap.ClimbDriveForward)>0.05) {
      drivePower =  m_OPstick.getRawAxis(RobotMap.ClimbDriveForward);
    }
    
    if (drivePower==0) {
      if (m_OPstick.getRawAxis(RobotMap.ClimbDriveReverse)>0.05) {
        drivePower = -m_OPstick.getRawAxis(RobotMap.ClimbDriveReverse);
      }
    }

    return drivePower;
  }

  
    
    
  
  
}
  
  

