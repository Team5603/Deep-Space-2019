/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimberDefault;


/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

 
  private DoubleSolenoid m_DSBWClimb;
  private DoubleSolenoid m_DSFWClimb;
  public Climber() {
    m_DSBWClimb = new DoubleSolenoid(1, 0, 1);
    m_DSFWClimb = new DoubleSolenoid(1, 2, 3);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ClimberDefault());
  }

  

  public void BWClimb() {
    m_DSBWClimb.set(DoubleSolenoid.Value.kForward);
  }
  public void BWDescend(){
    m_DSBWClimb.set(DoubleSolenoid.Value.kReverse);
  }
  public void Break(){
    m_DSBWClimb.set(DoubleSolenoid.Value.kOff);
    m_DSFWClimb.set(DoubleSolenoid.Value.kOff);
  }
}
