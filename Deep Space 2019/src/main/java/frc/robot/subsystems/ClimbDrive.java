/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbDriver;

/**
 * Add your docs here.
 */
public class ClimbDrive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_VictorSPX m_motor1;
  private WPI_VictorSPX m_motor2;
  private boolean m_CDMaintain;
  private double m_CDMaintainValue= .15;

  public ClimbDrive(){
    m_motor1 = new WPI_VictorSPX(RobotMap.WheelyBar1);
    m_motor2 = new WPI_VictorSPX(RobotMap.WheelyBar2);
    m_CDMaintain = false;
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ClimbDriver());
  }
  public void DriveClimb(double speed){
    double FinalSpeed;
    FinalSpeed = speed;

    // Turn on or off maintain depending on what's sent. > 0 turn on, < 0 turn off, is 0, leave alone
    if (speed > 0)
      m_CDMaintain=true;
    else
      if (speed < 0)
      m_CDMaintain=false;
    
    if (speed==0 && m_CDMaintain)
      FinalSpeed= m_CDMaintainValue;
      
    m_motor1.set(ControlMode.PercentOutput, FinalSpeed);
    m_motor2.set(ControlMode.PercentOutput, FinalSpeed);
 }

}
