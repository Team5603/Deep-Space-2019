/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import Util.JoystickMap;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.commands.Climb;
import frc.robot.commands.Outake;
import frc.robot.commands.CLimbExtend;
import frc.robot.commands.FunkyTown;
import frc.robot.commands.Intake;





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

  private static final double GAMEPAD_DEADBAND = 0.02;

  public Joystick opPad;

  //public Button climberButton;
  public Button IntakeButton;
  public Button OuttakeButton;
  public Button FrontExtend;
  public Button BackExtend;
  public Button FunkButton;
  //public Button hRButton;
  //public Button TurnEMainOffButton;
  //public Button Timber;
  //public Button Ascension;
  //public Button ForwardClimberButton;
  //public Button ReverseClimberButton;

  public OI() {
    //climberButton = new JoystickButton(m_OPstick, RobotMap.ClimbButton);
    //ForwardClimberButton = new JoystickButton(m_OPstick, RobotMap.ClimbDriveForward);
    //ReverseClimberButton = new JoystickButton(m_OPstick, RobotMap.ClimbDriveReverse);
    IntakeButton = new JoystickButton(m_OPstick, RobotMap.Intake);
    OuttakeButton = new JoystickButton(m_OPstick, RobotMap.Outtake);
    FrontExtend = new JoystickButton(m_OPstick, RobotMap.FrontExtend);
    BackExtend = new JoystickButton(m_OPstick, RobotMap.BackExtend);
    FunkButton = new JoystickButton(m_OPstick, RobotMap.FunkButton);
    //hRButton = new JoystickButton(m_OPstick, RobotMap.HRButton);
    //TurnEMainOffButton = new JoystickButton(m_OPstick, RobotMap.ElbowMainOff);
    
    
    //climberButton.whileHeld(new Climb());
    OuttakeButton.whileHeld(new Outake());
    IntakeButton.whileHeld(new Intake());
// put in commands for front and back extend
    FrontExtend.whileHeld(new CLimbExtend('F'));
    BackExtend.whileHeld(new CLimbExtend('B'));
    FunkButton.whileHeld(new FunkyTown());
    //TurnEMainOffButton.whenPressed(new TurnOffElbowMaintain());
    
  }
  public double getLeftDrive() {
    double LeftPower = 0;
    LeftPower = stickDeadband(-m_leftJoystick.getRawAxis(RobotMap.DriveAxis), GAMEPAD_DEADBAND, 0.0);
    return LeftPower;
  }

  public double getRightDrive() {
    double RightPower = 0;
    RightPower= stickDeadband(-m_rightJoystick.getRawAxis(RobotMap.DriveAxis), GAMEPAD_DEADBAND, 0.0);
    return RightPower;
  }

  
  
  private static double stickDeadband(double value, double deadband, double center) {
   if (value < (center + deadband) && value > (center - deadband))
      return center; 
   else 
      return value;
  }
 
  public double getLiftPower(){
    double lift;
    

    lift = stickDeadband(m_OPstick.getRawAxis(RobotMap.updawg), GAMEPAD_DEADBAND, 0.0);
    return lift;
    
  }

  public int getPOV(){
    return m_OPstick.getPOV();
  }

  public boolean GoodFeels() {
    return m_OPstick.getPOV() == 0;
  }

  public double getLiftValue() {
      double liftValue = 0;
      liftValue = stickDeadband(opPad.getRawAxis(JoystickMap.gamepad.LEFT_Y), GAMEPAD_DEADBAND, 0.0);
  
      return liftValue;
  }
  
  public double getElbow() 
  {
    double elbowValue = 0;
      elbowValue = stickDeadband(m_OPstick.getRawAxis(RobotMap.rYStick), GAMEPAD_DEADBAND, 0.0);
    
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



    
    

    
  

  
    
    
  
  

  
  

