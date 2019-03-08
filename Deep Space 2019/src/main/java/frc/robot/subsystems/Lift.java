/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.UpDawg;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX m_lifter = new WPI_TalonSRX(RobotMap.LiftyBar);
  //double liftmultiplier = .5;
  private static final double RAISE_MULTIPLIER = .50;
	private static final double LOWER_MULTIPLIER = .1;
  private static final double MAINTAIN_POWER = .1;
  private boolean m_maintain = false;
  CANEncoder m_LiftEncodVal;
  
public Lift(){
  m_lifter.configReverseSoftLimitThreshold(-2305);
  m_lifter.configReverseSoftLimitEnable(true);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new UpDawg());
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
    double liftPowerFinal;

    SmartDashboard.putNumber("LiftSentPower", LiftPower);
    //m_lifter.set(LiftPower *liftmultiplier );
    if (LiftPower == 0) {
      if (m_maintain) {
        liftPowerFinal = MAINTAIN_POWER;
      } else {
        liftPowerFinal = 0;
      }
    } else {
      if (LiftPower<0)
        liftPowerFinal = -LiftPower*RAISE_MULTIPLIER;
      else  
        liftPowerFinal = -LiftPower*LOWER_MULTIPLIER;
    }
    SmartDashboard.putNumber("Lift Motor Power", liftPowerFinal);
    m_lifter.set(ControlMode.PercentOutput, liftPowerFinal);
  }
  public double GetEncoder(String otherthing){
    double returnValue = 0;
    switch (otherthing){
      case "Yes":
        returnValue = m_LiftEncodVal.getPosition();
        break;
    }
    return returnValue;
  }

  public void SetMaintain(boolean DoMaintain){
    m_maintain = DoMaintain;
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
