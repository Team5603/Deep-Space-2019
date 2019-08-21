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
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ElbowJoystick;



/**
 * Add your docs here.
 */
public class Elbow extends Subsystem {
  private TalonSRX elbowMotorTalon;

  private boolean m_BuckMaintain = false;
  private boolean m_maintain = false;

  //private DigitalInput elbowEndUp;
  //private DigitalInput elbowEndDown;

  private static final double RAISE_MULTIPLIER = .30;
	private static final double LOWER_MULTIPLIER = .2; // was .07 3/8/19 12:49pm
	private static final double MAINTAIN_POWER = .09;
	private static final double ELBOW_CLIMB_POWER = .5;
	
  private static final double HIGH_POW = 1.0;
  private static final double LOW_POW = -1.0;

  int encELBOW = 0;
  int encposELBOWOrig = 0;
  int encposLIFTOrig = 0;
 
  //private static final double raiselowerPower = 0;

  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Elbow(){  
     // Set the default command for a subsystem here.
    
    elbowMotorTalon = new TalonSRX(RobotMap.ELBOW_MOTOR_1);
   
    //Break Mode
    elbowMotorTalon.setNeutralMode(NeutralMode.Brake); 

     //Inverted or Not...
		elbowMotorTalon.setInverted(true);
		elbowMotorTalon.setSensorPhase(false);
    //Encoder
    elbowMotorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    elbowMotorTalon.setSelectedSensorPosition(0,0,0);
    elbowMotorTalon.configNominalOutputForward(0,30);
    elbowMotorTalon.configNominalOutputReverse(0, 30);
    elbowMotorTalon.configPeakOutputForward(1, 30); //motor currently going in reverse when lift goes up
    elbowMotorTalon.configPeakOutputReverse(-1, 30);

    elbowMotorTalon.configAllowableClosedloopError(0, 0, 30);

    elbowMotorTalon.config_kP(5, 0, 30);
    elbowMotorTalon.config_kI(0, 0, 30);
    elbowMotorTalon.config_kD(1, 0, 30);
    elbowMotorTalon.config_kF(0, 0, 30);
    elbowMotorTalon.configReverseSoftLimitThreshold(-100);
    elbowMotorTalon.configReverseSoftLimitEnable(false);
    elbowMotorTalon.configForwardSoftLimitThreshold(2600);
    elbowMotorTalon.configForwardSoftLimitEnable(false);

    
  }
   
  public void Raise_Lower(Double Speed){
    SmartDashboard.putNumber("ElbowSentPower", Speed);
    double elbowFinalPower=0;
    if (Speed == 0) {
      //elbowFinalPower = MAINTAIN_POWER;
      //elbowMotorTalon.set(ControlMode.Position, encELBOW);
      //System.out.println("Elbow Maintain-out:" + (int)(elbowMotorTalon.getMotorOutputPercent()*100)+"%\tpos:"+GetEncoder()+"\terr:"+elbowMotorTalon.getClosedLoopError(0)+"\ttrg:"+encELBOW);
      encELBOW = getElbowMaintPos(encposELBOWOrig, Robot.kLift.GetEncoder(), encposLIFTOrig);
      elbowMotorTalon.set(ControlMode.Position,encELBOW);
      //System.out.println("Elbow MaintainRelative-out:" + (int)(elbowMotorTalon.getMotorOutputPercent()*100)+"%\tpos:"+GetEncoder()+"\terr:"+elbowMotorTalon.getClosedLoopError(0)+"\ttrg:"+encELBOW+"\tOrigElbow:"+encposELBOWOrig+"\tLiftOrig:"+encposLIFTOrig);

    }else{
      if (Speed>0)
        elbowFinalPower = Speed*LOWER_MULTIPLIER;
        //elbowMotorTalon.set(ControlMode.PercentOutput, -Speed*LOWER_MULTIPLIER);
      else
        elbowFinalPower = Speed*RAISE_MULTIPLIER;
        //elbowMotorTalon.set(ControlMode.PercentOutput, -Speed*RAISE_MULTIPLIER);
      encELBOW = GetEncoder();
      encposELBOWOrig = encELBOW;
      encposLIFTOrig = Robot.kLift.GetEncoder();
      elbowMotorTalon.set(ControlMode.PercentOutput, elbowFinalPower);
      //System.out.println("Elbow Joystick:" + (int)(elbowMotorTalon.getMotorOutputPercent()*100)+"%\tpos:"+GetEncoder()+"\tFinal Power:"+elbowFinalPower);

    }
    SmartDashboard.putNumber("ElbowMotorPower", elbowMotorTalon.getMotorOutputPercent());


  }
  
  private int getElbowMaintPos(int ElbowMaintOrig, int CurLiftPos, int LiftPosOrig){
    int LowLiftPos=0;
    int HighLifPos=-4888;
    int HighBucketPos=-20;
    int LowBucketPos=2600;
    int LiftElbowRatioLow=930;
    int LiftElbowRatioHigh=2378;
    
    //double ratio = (double)((LiftElbowRatioHigh-LiftElbowRatioLow)/(HighLifPos-LowLiftPos));
    double ratio = -0.2962;

    int ElbowMaint;
    ElbowMaint = (int)((CurLiftPos-LiftPosOrig)*ratio)+ElbowMaintOrig;
    //System.out.println("GetElbowMaintPost:"+"\trat:"+ ratio + "\tcurpos:"+CurLiftPos+"\tElbowMaint:"+ElbowMaint+"\tOrigElbow:"+ElbowMaintOrig+"\tLiftOrig:"+LiftPosOrig);
    if (ElbowMaint> LowBucketPos)       ElbowMaint = LowBucketPos;
    if (ElbowMaint< HighBucketPos)      ElbowMaint = HighBucketPos;

    return ElbowMaint;
  }

  public void SetMaintain(boolean DoMaintain){
     m_BuckMaintain = DoMaintain;
  }
  
  public int GetEncoder(){
    return elbowMotorTalon.getSelectedSensorPosition(0);
  }

  public void resetPID() {
    encELBOW = GetEncoder();
    encposELBOWOrig = encELBOW;
    encposLIFTOrig = Robot.kLift.GetEncoder();


  }
 

  protected void initDefaultCommand() {
    setDefaultCommand(new ElbowJoystick());
  }

  public void Stop() {
  }

  public boolean GetMaintain() {
	  return m_maintain;
  }

}
