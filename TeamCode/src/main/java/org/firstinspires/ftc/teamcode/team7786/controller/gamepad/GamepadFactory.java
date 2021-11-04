/*
package org.firstinspires.ftc.teamcode.team7786.controller.gamepad;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadFactory {
    String[] standardIds, variableIds;
    GamepadEx.VariableInputTypes[] variableTypes;
    GamepadEx.StandardButtonTypes[] standardTypes;
    int index;
    Gamepad gamepad;

    StandardButton a, b, x, y, left_bumper, right_bumper, dpad_up, dpad_down,
            dpad_left, dpad_right, left_stick_button, right_stick_button;
    StandardButton right_trigger, left_trigger, left_stick_x, left_stick_y, right_stick_x,
            right_stick_y;

    public GamepadFactory(String[] standardIds, String[] variableIds, GamepadEx.VariableInputTypes[] variableTypes, GamepadEx.StandardButtonTypes[] standardTypes) {
        this.standardTypes = standardTypes;
        this.variableTypes = variableTypes;
        this.standardIds = standardIds;
        this.variableIds = variableIds;



        if(!(standardIds.length == standardTypes.length)){
            throw new Exception("Length of standardIds and standardTypes do not match");
        }
        if(!(variableIds.length == variableTypes.length)){
            throw new Exception("Length of variableIds and variableTypes do not match");
        }


    }

    public void gen(){
        switch(standardTypes[findIndex("a", standardIds)]){
            case TOGGLE_BUTTON:
                a = new StandardButton(){
                    boolean state = gamepad.a;
                    private ButtonCore button;
                    public boolean state(){

                    }
                    public ButtonCore buttonCore() {
                        if (button == null){
                            button = new ButtonCore(this);
                        }
                        return button;
                    }
                };
        }
    }

    private int findIndex(String id, String[] field){
        int a;
        for(int i = 0; i< field.length; i++) {
            if (field[i].equals(id)) {
                return i;
            }
        }
        return 50000000;
    }
}
*/