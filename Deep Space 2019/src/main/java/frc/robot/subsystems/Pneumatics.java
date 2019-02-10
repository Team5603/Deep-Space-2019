/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Pneumat;

/**
 * Add your docs here.
 */
public class Pneumatics extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Compressor m_compressor;
  private Solenoid m_Sole1;
  private Solenoid m_Sole2;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Pneumat());
  
  }

  public Pneumatics(){
    m_Sole1 = new Solenoid(2);
    m_Sole2 = new Solenoid(3);
    m_compressor = new Compressor(RobotMap.Compressor);
    

  }
}
