/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;



import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimberDefault extends Command {
  public ClimberDefault() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.sClimber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    int POVvalue = Robot.m_oi.getPOV();
    switch (POVvalue) {
      case 0:
        Robot.sClimber.BACKClimb();
        Robot.sClimber.FRONTClimb();
        break;
      case 90:
        Robot.sClimber.BACKDescend();
        Robot.sClimber.FRONTOff();
        break;
      case 180:
        Robot.sClimber.BACKDescend();
        Robot.sClimber.FRONTDescend();
        break;
      case 270:
        Robot.sClimber.BACKOff();
        Robot.sClimber.FRONTDescend();
        break;
      default:
        Robot.sClimber.Break();
        break;
    }

    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.sClimber.Break();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
