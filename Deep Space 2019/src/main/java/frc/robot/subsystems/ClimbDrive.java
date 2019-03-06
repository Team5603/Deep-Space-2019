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

  public ClimbDrive(){
    m_motor1 = new WPI_VictorSPX(RobotMap.WheelyBar1);
    m_motor2 = new WPI_VictorSPX(RobotMap.WheelyBar2);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ClimbDriver());
  }
  public void DriveClimb(double speed){
    m_motor1.set(ControlMode.PercentOutput, speed);
    m_motor2.set(ControlMode.PercentOutput, speed);

  }

}
