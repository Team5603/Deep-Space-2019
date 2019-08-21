/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ClimbDrive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.ExampleSubsystem;

import frc.robot.subsystems.IntakeOutake;
import frc.robot.subsystems.Lift;
//import frc.robot.subsystems.LineSensors;
import frc.robot.subsystems.Pneumatics;
//import frc.robot.subsystems.SlideDrive;
import frc.robot.subsystems.UptownFunk;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;
  public static final DriveTrain sDriveTrain = new DriveTrain();
  //public static final SlideDrive sChaCha  = new SlideDrive();
  public static final Climber sClimber = new Climber();

  //public static final LineSensors sLineSens = new LineSensors();
  //public static final Pneumatics sPneu = new Pneumatics();
  public static final Elbow kElbow = new Elbow();
  public Compressor m_compressor = new Compressor(RobotMap.PCM);
  public static final IntakeOutake sInNOut = new IntakeOutake();
  public static final ClimbDrive sCD = new ClimbDrive();
  public static final UptownFunk kFunk = new UptownFunk();
  public static final Lift kLift = new Lift();
  double joystickAxis;
  double deadband = 0.02;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() 
  {
    m_oi = new OI();
    m_compressor.setClosedLoopControl(true);

    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);

  }
  
  private void UpdateSmartDashboard() {
    SmartDashboard.putNumber("Lift Encoder:", kLift.GetEncoder());  
    SmartDashboard.putNumber("Elbow Encoder:", kElbow.GetEncoder());  
    //SmartDashboard.putNumber("LF:", sDriveTrain.GetPostion("LF"));  
    //SmartDashboard.putNumber("LB:", sDriveTrain.GetPostion("LB"));  
    //SmartDashboard.putNumber("RF:", sDriveTrain.GetPostion("RF"));  
    //SmartDashboard.putNumber("RB:", sDriveTrain.GetPostion("RB"));  
  }
    
    
  

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    UpdateSmartDashboard();
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    UpdateSmartDashboard();
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    Robot.kLift.resetPID();
    Robot.kElbow.resetPID();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    UpdateSmartDashboard();
    Scheduler.getInstance().run();
    
    

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
