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

 
  private DoubleSolenoid m_DSBACKClimb;
  private DoubleSolenoid m_DSFRONTClimb;
  public Climber() {
    m_DSBACKClimb = new DoubleSolenoid(1, 2, 3);
    m_DSFRONTClimb = new DoubleSolenoid(1, 0, 1);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ClimberDefault());
  }

  

  public void BACKClimb() {
    m_DSBACKClimb.set(DoubleSolenoid.Value.kForward);
  }
  public void BACKDescend(){
    m_DSBACKClimb.set(DoubleSolenoid.Value.kReverse);
  }
  public void BACKOff() {
    m_DSBACKClimb.set(DoubleSolenoid.Value.kOff);
  }

  public void FRONTClimb() {
    m_DSFRONTClimb.set(DoubleSolenoid.Value.kForward);
  }
  public void FRONTDescend(){
    m_DSFRONTClimb.set(DoubleSolenoid.Value.kReverse);
  }
  public void FRONTOff() {
    m_DSFRONTClimb.set(DoubleSolenoid.Value.kOff);
  }

  public void Break(){
    m_DSBACKClimb.set(DoubleSolenoid.Value.kOff);
    m_DSFRONTClimb.set(DoubleSolenoid.Value.kOff);
  }
}
