package org.firstinspires.ftc.teamcode.team7786.opmodes.gamepadconfigs;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.team7786.controller.gamepad.GamepadPlus;
import org.firstinspires.ftc.teamcode.team7786.controller.gamepad.StandardButton;
import org.firstinspires.ftc.teamcode.team7786.controller.gamepad.ToggleButton;
import org.firstinspires.ftc.teamcode.team7786.controller.gamepad.VariableInput;
import org.firstinspires.ftc.teamcode.team7786.controller.gamepad.VariableInputButton;
import org.firstinspires.ftc.teamcode.team7786.controller.gamepad.VariableInputToggleButton;
public class SampleConfig {
    public GamepadPlus gamepad;


    /**
     *
     * @param gamepad the gamepad, ususally gamepad1
     */
    public SampleConfig(Gamepad gamepad){
        this.gamepad = new GamepadPlus(gamepad);
    }

    /*
    then you define each button that will be used by calling the factory method from
    the gamepad plus class like this I recommend grouping them by button type for readability
     */

    StandardButton a = gamepad.getAButton();
    StandardButton x = gamepad.getXButton();
    StandardButton dpadUp = gamepad.getDpad_upButton();
    StandardButton dpadDown = gamepad.getDpad_DownButton();

    ToggleButton b = gamepad.getBButtonToggled();

    VariableInput leftStickX = gamepad.getLeftStickX();
    VariableInput leftStickY = gamepad.getLeftStickY();

    VariableInput rightStickX = gamepad.getRightStickX();
    VariableInput rightStickY = gamepad.getRightStickY();

    VariableInputButton rightTrigger = gamepad.getRightTriggerButton(0.5);


    /*
    from here you will make a method for each button, trying to keep in line with the
    method names given in the gamepad class even if this violates your naming conventions
    this will allow the improved gamepad to be almost a drop in replacement for the standard gamepad
     */

    //Standard buttons
    public boolean a(){
        return a.pressed();
    }
    public boolean x(){
        return x.pressed();
    }
    public boolean dpad_up(){
        return dpadUp.pressed();
    }
    public boolean dpad_down(){
        return dpadDown.pressed();
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
