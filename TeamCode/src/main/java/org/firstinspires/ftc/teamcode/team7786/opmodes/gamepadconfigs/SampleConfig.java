package org.firstinspires.ftc.teamcode.team7786.opmodes.gamepadconfigs;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.team7786.controller.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.team7786.controller.gamepad.StandardButton;
import org.firstinspires.ftc.teamcode.team7786.controller.gamepad.ToggleButton;
import org.firstinspires.ftc.teamcode.team7786.controller.gamepad.VariableInput;
import org.firstinspires.ftc.teamcode.team7786.controller.gamepad.VariableInputButton;

public class SampleConfig {
    GamepadEx gamepadEx;
    StandardButton a, x , dpadUp, dpadDown;
    ToggleButton b;
    VariableInput leftStickX, leftStickY, rightStickX, rightStickY;
    VariableInputButton rightTrigger;

    /**
     *
     * @param gamepad the gamepad, usually gamepad1 or gamepad2
     */
    public SampleConfig(Gamepad gamepad){
        this.gamepadEx = new GamepadEx(gamepad);

        a = gamepadEx.getAButton();
        x = gamepadEx.getXButton();
        dpadUp = gamepadEx.getDpad_upButton();
        dpadDown = gamepadEx.getDpad_DownButton();

        b = gamepadEx.getBButtonToggled();

        leftStickX = gamepadEx.getLeftStickX();
        leftStickY = gamepadEx.getLeftStickY();

        rightStickX = gamepadEx.getRightStickX();
        rightStickY = gamepadEx.getRightStickY();

        rightTrigger = gamepadEx.getRightTriggerButton(0.5);
    }

    /*
    then you define each button that will be used by calling the factory method from
    the gamepad plus class like this I recommend grouping them by button type for readability
     */





    /*
    from here you will make a method for each button, trying to keep in line with the
    method names given in the gamepad class even if this violates your naming conventions
    this will allow the improved gamepad to be almost a drop in replacement for the standard gamepad
     */

    //Standard buttons
    public boolean a(){
        return a.state();
    }
    public boolean x(){
        return x.state();
    }
    public boolean dpad_up(){
        return dpadUp.state();
    }
    public boolean dpad_down(){
        return dpadDown.state();
    }

    //Toggle Button
    public boolean b(){
        return b.getState();
    }

    //Variable Inputs
    public float left_stick_x(){
        return leftStickX.getPosition();
    }
    public float left_stick_y() {
        return leftStickY.getPosition();
    }
    public float right_stick_x(){
        return rightStickX.getPosition();
    }
    public float right_stick_y(){
        return rightStickY.getPosition();
    }
    public boolean right_trigger(){
        return rightTrigger.pressed();
    }
}
