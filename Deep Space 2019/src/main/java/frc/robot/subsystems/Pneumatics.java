/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;



/**
 * Add your docs here.
 */
public class Pneumatics extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Compressor m_compressor;
  private DoubleSolenoid m_DSClimb;
  private DoubleSolenoid m_DSDetachHatch;
  private DoubleSolenoid m_DSBreak;

  public Pneumatics() {
    m_compressor = new Compressor(RobotMap.PCM);
    m_compressor.setClosedLoopControl(true);
    m_DSClimb = new DoubleSolenoid(1, 0, 1);
    m_DSDetachHatch = new DoubleSolenoid(1, 2, 3);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(new Descend());
  
  }

  public void Climb() {
    m_DSClimb.set(DoubleSolenoid.Value.kForward);
  }
  public void Descend(){
    m_DSClimb.set(DoubleSolenoid.Value.kReverse);
  }
}