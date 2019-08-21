/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;



/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  
  //motors and stuff
  
  //DRIVE TRAIN
  public static final int leftmotorfront = 13;
  public static final int leftmotorback = 33;
  public static final int rightmotorfront = 11;
  public static final int rightmotorback = 14;
  public static final int FunkMotor = 20; // front climber extension
  
  //OTHER MOTORS
  public static final int WheelyBar1 = 21;
  public static final int WheelyBar2 = 22;
  public static final int LiftyBar = 23;
  public static final int AntiOutTake = 24;
  public static final int Elbow = 25;


  
  //OP joystick stuf
  public static int OPjoystick = 0;
  public static int updawg = 1; //leftstick
  public static int rYStick = 5; //rightstick
  public static int ClimbDriveForward = 3; //Right Trigger
  public static int ClimbDriveReverse = 2; // left trigger
  public static int Intake = 1;
  public static int Outtake = 2;
  public static int FrontExtend =3;  // X Button
  public static int BackExtend = 4;
  public static int FunkButton = 8;
  //public static int ElbowMainOff = 10;
  //public static int DesendButton = 6;
  //public static int AsendButton = 5;
  
  //Drive Joystick
  public static int LeftStick = 1;
  public static int RightStick = 2;
  public static int DriveAxis = 1;
  //public static int SlideAxis = 0;

  /*sensors (we didnt use this stuf)
  public static final int linesensorFL = 0;
  public static final int linesensorFLM = 1;
  public static final int linesensorFM = 2;
  public static final int linesensorFRM = 3;
  public static final int linesensorFR = 4;
  public static final int linesensorBL = 5;
  public static final int linesensorBLM = 6;
  public static final int linesensorBM = 7;
  public static final int linesensorBRM = 8;
  public static final int linesensorBR = 9;
*/
  //Pnematic Stuf
  public static final int PCM = 1;
  
  //Elbow motors
  public static final int ELBOW_MOTOR_1 = 25;
  

 
  



}

