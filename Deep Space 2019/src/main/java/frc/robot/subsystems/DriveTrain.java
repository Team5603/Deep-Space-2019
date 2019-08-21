/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Spark;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import Util.Utilities;


/**
 * Add your docs here.
 */



public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  
 
  
  private DifferentialDrive m_dd;

  //private CANSparkMax m_leftsparkMback;
  //private CANSparkMax m_leftsparkMfront;
  //private CANSparkMax m_rightsparkMfront;
  //private CANSparkMax m_rightsparkMfront;
  //private CANSparkMax m_rightsparkMback;
  
  private Spark m_pwmRight1;
  private Spark m_pwmRight2;
  private Spark m_pwmLeft1;
  private Spark m_pwmLeft2;

  CANEncoder m_EncoderLB;
  CANEncoder m_EncoderRB;
  CANEncoder m_EncoderLF;
  CANEncoder m_EncoderRF;

  public DriveTrain(){
  
    SpeedControllerGroup leftSide;
    SpeedControllerGroup rightSide;
    
    //m_leftsparkMback = new CANSparkMax(RobotMap.leftmotorback, MotorType.kBrushless);
    //m_leftsparkMfront = new CANSparkMax(RobotMap.leftmotorfront, MotorType.kBrushless);
    //m_rightsparkMback = new CANSparkMax(RobotMap.rightmotorback, MotorType.kBrushless);
    //m_rightsparkMfront = new CANSparkMax(RobotMap.rightmotorfront, MotorType.kBrushless);
    
    m_pwmRight1 = new Spark(0);
    m_pwmRight2 = new Spark(1);
    m_pwmLeft1 = new Spark(2);
    m_pwmLeft2 = new Spark(3);
    //m_rightsparkMback = new CANSparkMax(11, MotorType.kBrushless);
    //m_rightsparkMfront = new CANSparkMax(11, MotorType.kBrushless);
     

    //leftSide = new SpeedControllerGroup(m_leftsparkMback, m_leftsparkMfront);
    leftSide = new SpeedControllerGroup(m_pwmLeft1,m_pwmLeft2);
    rightSide = new SpeedControllerGroup(m_pwmRight1, m_pwmRight2);

    //m_leftsparkMback.follow(m_leftsparkMfront);  // Not following now.  
    //m_rightsparkMfront.follow(m_rightsparkMback);
    

    //m_EncoderLB = m_leftsparkMback.getEncoder();
    //m_EncoderLF = m_leftsparkMfront.getEncoder();
    //m_EncoderRB = m_rightsparkMback.getEncoder();
    //m_EncoderRF = m_rightsparkMfront.getEncoder();
    
    //
    //m_dd = new DifferentialDrive(m_leftsparkMfront, m_rightsparkMfront);
    m_dd = new DifferentialDrive(leftSide, rightSide);
    //m_dd= new DifferentialDrive(leftSide, rightSide);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TankDrive());
  }



  public void TankDrive(double leftpower, double rightpower){
    int toPowerFactor = 2;
    double leftFinalPower;
    double rightFinalPower;
    try {
     // m_dd.tankDrive(leftpower, rightpower, true);
     
    leftFinalPower = Utilities.scalePower(leftpower, .75, .3);
    rightFinalPower = Utilities.scalePower(rightpower, .75, .3);
    leftFinalPower = Utilities.prunePower(Utilities.toPower(leftpower, toPowerFactor), .16);
    rightFinalPower = Utilities.prunePower(Utilities.toPower(rightpower, toPowerFactor), .16);
    SmartDashboard.putNumber("Left Joystick:", leftpower );
    SmartDashboard.putNumber("Right Joystick:", rightpower);
    SmartDashboard.putNumber("Left Motor:", leftFinalPower );
    SmartDashboard.putNumber("Right Motor:", rightFinalPower);
    m_dd.tankDrive(leftFinalPower, rightFinalPower, false);
    }
    catch (Exception ex) {
      System.out.println("TankDrive:"+ex.getMessage());
    }


  }


  /*
  public double GetPostion(String whatMotor){
    // send in RF RB LF LB to represent what motor was sent in
    double returnValue = 0;
    try {
      switch (whatMotor){
        case "LF":
          returnValue = m_EncoderLF.getPosition();
          break;
        case "LB":
         returnValue = m_EncoderLB.getPosition();
          break;
        case "RF":
          returnValue = m_EncoderRF.getPosition();
          break;
        case "RB":
        returnValue = m_EncoderRB.getPosition();
          break;
      }
    } catch (Exception ex) {
      System.out.println("GetPos:"+ex.getMessage());
    }
    return returnValue;
  }
  */
}

