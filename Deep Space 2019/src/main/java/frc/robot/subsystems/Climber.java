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
import edu.wpi.first.wpilibj.Timer;
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
  
  private Timer m_climbTimer;
  
  //CHANGE THE FOLLOWING TWO VALUES ONLY!!!!
  private static double FrontClimbTime = .35; // portion of second to be 'on'
  private static double BackClimbTime = .7; // portion of second to be 'on'
  //CHANGE THE ABOVE TWO VALUES ONLY!!!!!

  private static double FrontClimbOnSegment = FrontClimbTime/5; // we are splitting time into 5 chuncks to smooth out process
  private static double FrontClimbOffSegment = (1 - FrontClimbTime)/5; 
  private static double BackClimbOnSegment = BackClimbTime/5; // we are splitting time into 5 chuncks to smooth out process
  private static double BackClimbOffSegment = (1 - BackClimbTime)/5; 

  private boolean m_curFrontStateOn=false;
  private boolean m_curBackStateOn=false;
  private double m_curFrontTimer=0;
  private double m_curBackTimer=0;

  public Climber() {
    m_DSBACKClimb = new DoubleSolenoid(1, 2, 3);
    m_DSFRONTClimb = new DoubleSolenoid(1, 0, 1);
    m_climbTimer = new Timer();
    m_climbTimer.start();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ClimberDefault());
  }

  

  public void BACKClimb() {
    //check state
    if (m_curBackStateOn) {
      // do we keep it on?
      if (m_climbTimer.get()-m_curBackTimer>=BackClimbOnSegment) {
        // time to change to 'off'
        m_curBackStateOn = false; // time to turn 'off'
        m_curBackTimer = m_climbTimer.get();
        m_DSBACKClimb.set(DoubleSolenoid.Value.kOff);
      } else {
        // keep it on
        m_DSBACKClimb.set(DoubleSolenoid.Value.kForward);
      }
    }
    else
    {
      // it's off, so do we swtich to on?
      if (m_climbTimer.get()-m_curBackTimer>=BackClimbOffSegment) {
        // time to change to 'on'
        m_curBackStateOn = true; // time to turn on
        m_curBackTimer = m_climbTimer.get();
        m_DSBACKClimb.set(DoubleSolenoid.Value.kForward);
      } else {
        // keep it off
        m_DSBACKClimb.set(DoubleSolenoid.Value.kOff);
      }
    }
  }

  public void BACKDescend(){
    m_DSBACKClimb.set(DoubleSolenoid.Value.kReverse);
  }
  public void BACKOff() {
    m_DSBACKClimb.set(DoubleSolenoid.Value.kOff);
  }

  public void FRONTClimb() {
    //check state
    if (m_curFrontStateOn) {
      // do we keep it on?
      if (m_climbTimer.get()-m_curFrontTimer>=FrontClimbOnSegment) {
        // time to change to 'off'
        m_curFrontStateOn = false; // time to turn 'off'
        m_curFrontTimer = m_climbTimer.get();
        m_DSFRONTClimb.set(DoubleSolenoid.Value.kOff);
      } else {
        // keep it off
        m_DSFRONTClimb.set(DoubleSolenoid.Value.kForward);
      }
    }
    else
    {
      // it's off, so do we swtich to on?
      if (m_climbTimer.get()-m_curFrontTimer>=FrontClimbOffSegment) {
        // time to change to 'on'
        m_curFrontStateOn = true; // time to turn on
        m_curFrontTimer = m_climbTimer.get();
        m_DSFRONTClimb.set(DoubleSolenoid.Value.kForward);
      } else {
        // keep it off
        m_DSFRONTClimb.set(DoubleSolenoid.Value.kOff);
      }
    }
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
