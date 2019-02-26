/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.UpDawg;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX m_lifter = new WPI_TalonSRX(RobotMap.LiftyBar);
  double liftmultiplier = .5;
  private static final double MAINTAIN_POWER = .35;
  private boolean m_maintain = false;

  private DigitalInput liftEOTUp;
  private DigitalInput liftEOTDown;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new UpDawg());
  }

  public boolean AtEndOfTravelUp() {
    return (liftEOTUp.get()!=RobotMap.LIFT_END_OF_TRAVEL_DOWN_DS);
  }

  public boolean AtEndOfTravelDown() {
    return (liftEOTDown.get()!=RobotMap.LIFT_END_OF_TRAVEL_DOWN_DS);
  }

  public void TurnOffMaintain() {
    m_maintain = false;
  }

  public void TurnOnMaintain() {
    m_maintain = true;
  }

  public boolean GetMaintain() {
    return m_maintain;
  }

  public void Lifter(Double LiftPower) {
    m_lifter.set(LiftPower *liftmultiplier );
      if (m_maintain)
        m_lifter.set(ControlMode.PercentOutput, MAINTAIN_POWER);
      else 
        m_lifter.set(ControlMode.PercentOutput, 0);
  }

  public void Maintain() {
    if (m_maintain)
        m_lifter.set(ControlMode.PercentOutput, MAINTAIN_POWER);
      else 
        m_lifter.set(ControlMode.PercentOutput, 0);
  }

  public void Stop() {
      m_lifter.set(ControlMode.PercentOutput, 0);
  }
}
