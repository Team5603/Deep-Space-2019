/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
//import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.SDrive;

/**
 * Add your docs here.
 */
public class SlideDrive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_VictorSPX m_slider = new WPI_VictorSPX(RobotMap.SlideMotor);  
  
  double slidemultiplier = .5;
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new SDrive());
  }


  public void ChaCha (double slidePower) {
    m_slider.set(slidemultiplier * -slidePower);

  }
}
