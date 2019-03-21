/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CLimbExtend extends Command {
  char m_WhichOne;
  public CLimbExtend(char FrontBack) {
    m_WhichOne = FrontBack;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.sClimber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    switch(m_WhichOne) {
      case 'F':
        Robot.sClimber.FRONTClimb();
        Robot.sClimber.BACKOff();
        break;
      case 'B':
        Robot.sClimber.BACKClimb();
        Robot.sClimber.FRONTOff();
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
    end();
  }
}
