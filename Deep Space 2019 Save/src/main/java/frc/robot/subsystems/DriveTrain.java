/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Spark;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


/**
 * Add your docs here.
 */



public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  
 
  
  private DifferentialDrive m_dd;

  private CANSparkMax m_leftsparkMback;
  private CANSparkMax m_leftsparkMfront;
  private CANSparkMax m_rightsparkMfront;
  private CANSparkMax m_rightsparkMback;
  CANEncoder m_EncoderLB;
  CANEncoder m_EncoderRB;
  CANEncoder m_EncoderLF;
  CANEncoder m_EncoderRF;

  public DriveTrain(){
  

    m_leftsparkMback = new CANSparkMax(RobotMap.leftmotorback, MotorType.kBrushless);
    m_leftsparkMfront = new CANSparkMax(RobotMap.leftmotorfront, MotorType.kBrushless);
    m_rightsparkMback = new CANSparkMax(RobotMap.rightmotorback, MotorType.kBrushless);
    m_rightsparkMfront = new CANSparkMax(RobotMap.rightmotorfront, MotorType.kBrushless);
      

    m_leftsparkMback.follow(m_leftsparkMfront);
    m_rightsparkMback.follow(m_rightsparkMfront);

    m_EncoderLB = m_leftsparkMback.getEncoder();
    m_EncoderLF = m_leftsparkMfront.getEncoder();
    m_EncoderRB = m_rightsparkMback.getEncoder();
    m_EncoderRF = m_rightsparkMfront.getEncoder();
    
    m_dd = new DifferentialDrive(m_leftsparkMfront, m_rightsparkMfront);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TankDrive());
  }



  public void TankDrive(double leftpower, double rightpower){
    m_dd.tankDrive(leftpower, rightpower, true);

  }


  public double GetPostion(String whatMotor){
    // send in RF RB LF LB to represent what motor was sent in
    double returnValue = 0;
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
    return returnValue;
  }
}

