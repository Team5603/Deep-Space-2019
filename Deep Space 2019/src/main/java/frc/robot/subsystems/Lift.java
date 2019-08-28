/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;



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
	private static final double LOWER_MULTIPLIER = .2;
  private static final double MAINTAIN_POWER = .1;
  //private static final double LIFT_CLIMB_POWER = .5;
  double encPOSLIFT = 0;
  
  
public Lift(){

  //Break Mode
  m_lifter.setNeutralMode(NeutralMode.Brake); 

  //Inverted or Not...
  m_lifter.setInverted(false);
  m_lifter.setSensorPhase(false);
 
  m_lifter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
  m_lifter.setSelectedSensorPosition(0,0,0);
  m_lifter.configNominalOutputForward(0,30);
  m_lifter.configNominalOutputReverse(0, 30);
  m_lifter.configPeakOutputForward(1, 30); //motor currently going in reverse when lift goes up
  m_lifter.configPeakOutputReverse(-1, 30);

  m_lifter.configAllowableClosedloopError(0, 0, 30);

  m_lifter.config_kP(5, 0, 30);
  m_lifter.config_kI(0, 0, 30);
  m_lifter.config_kD(1, 0, 30);
  m_lifter.config_kF(0, 0, 30);
  m_lifter.configReverseSoftLimitThreshold(-4500);
  m_lifter.configReverseSoftLimitEnable(false);
  m_lifter.configForwardSoftLimitThreshold(0);
  m_lifter.configForwardSoftLimitEnable(false);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new UpDawg());
  }
  
  
  public void Lifter(Double LiftPower) {
    double liftPowerFinal;

    SmartDashboard.putNumber("LiftSentPower", LiftPower);
    //m_lifter.set(LiftPower *liftmultiplier );
    if (LiftPower == 0) {
      //liftPowerFinal = MAINTAIN_POWER;
      m_lifter.set(ControlMode.Position, encPOSLIFT);
      //System.out.println("lift Maintain-out:" + (int)(m_lifter.getMotorOutputPercent()*100)+"%\tpos:"+GetEncoder()+"\terr:"+m_lifter.getClosedLoopError(0)+"\ttrg:"+encPOSLIFT);
    } else {
      if (LiftPower<0)
        liftPowerFinal = LiftPower*RAISE_MULTIPLIER;
      else  
        liftPowerFinal = LiftPower*LOWER_MULTIPLIER;
      
      encPOSLIFT=GetEncoder();
      m_lifter.set(ControlMode.PercentOutput, liftPowerFinal);
      //System.out.println("Lift Joystick:" + (int)(m_lifter.getMotorOutputPercent()*100)+"%\tpos:"+GetEncoder()+"\tLift Final:"+liftPowerFinal);

    }
    SmartDashboard.putNumber("Lift Motor Power", m_lifter.getMotorOutputPercent());
   
  }

  public int GetEncoder(){
    return m_lifter.getSelectedSensorPosition(0);
  }

  public void resetPID() {
    encPOSLIFT=GetEncoder();
  }

  public void Stop() {
      m_lifter.set(ControlMode.PercentOutput, 0);
  }
  
}
