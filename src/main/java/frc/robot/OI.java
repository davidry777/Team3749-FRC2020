/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
  // controller used for main controls (xbox controller)
  private XboxController ctrl;
  private JoystickButton[] buttons;
  private POVButton[] dpad;

  // how many buttons in the controller
  private final int BUTTON_RANGE = 10;
  /**
   * constructor OI connects the controller and buttons to the different commands
   */
  public OI ()
  {
    ctrl = new XboxController(0);

    // indexes start at 1 for buttons
    buttons = new JoystickButton[BUTTON_RANGE+1];
    dpad = new POVButton[4];
    for (int i = 1; i <= BUTTON_RANGE; i ++)
      buttons[i] = new JoystickButton(ctrl, i);
    for (int i = 0; i < 4; i ++)
      dpad[i] = new POVButton(ctrl, i * 90);
    
    /**
     * BUTTON MAP KEY:
     * 1 = A
     * 2 = B
     * 3 = X
     * 4 = Y
     * 5 = left bumper
     * 6 = right bumper
     * 7 = select
     * 8 = menu
     * 9 = left stick click
     * 10 = right stick click
     */
    
    if(Robot.getMap().getSys("wheelio") != 0)
    {
      // right bumper - init the timer start, and shoot
      buttons[6].whenPressed(new StartUnload());
      buttons[6].whenReleased(new Unload());
      
      // left bumper - toggle the intake
      buttons[5].toggleWhenPressed(new Intake());
    }

  



}
