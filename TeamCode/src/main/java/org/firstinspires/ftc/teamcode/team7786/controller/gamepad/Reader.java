package org.firstinspires.ftc.teamcode.team7786.controller.gamepad;

import java.util.Scanner;
import java.io.File;

public class Reader{
    public void parseMain(){
        File file = new File("test/sampleConfig.cfg");
        Scanner scan;
        try{
            scan = new Scanner(file);
            while(scan.hasNextLine()){
                parse(scan.nextLine());
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    private  void parse(String in){
        try{
            int index = in.indexOf("-");
            String id = in.substring(0, index);
            String type = in.substring(index+1);
            System.out.println(id + "  " + type);
            parseCore(id, type);
        }
        catch(Exception e){}
    }

    private void parseCore(String button, String value){

    }

    /*
    possible ideas for this

    1. use a map to match each button to its type which can be passed as a value from an enum class
    from there the factory class can use fancy interface magic bs to make it work
     */
    public void standardParseCore(String button, String value){
        GamepadEx.StandardButtonTypes standardType;

        switch(button.toLowerCase()){
            case"a":
                standardType = standardTypeGetter(value);
                break;
            case"b":
                 standardType = standardTypeGetter(value);
                break;
            case"x":
                standardType = standardTypeGetter(value);
                break;

            case"y":
                standardType = standardTypeGetter(value);
                break;
            case"dpad_up":
                standardType = standardTypeGetter(value);
                break;
            case"dpad_left":
                standardType = standardTypeGetter(value);
                break;
            case"dpad_right":
                standardType = standardTypeGetter(value);
                break;
            case"dpad_down":
                standardType = standardTypeGetter(value);
                break;
            case"left_bumper":
                standardType = standardTypeGetter(value);
                break;
            case"right_bumper":
                standardType = standardTypeGetter(value);
                break;
            case"left_stick_button":
                standardType = standardTypeGetter(value);
                break;
            case"right_stick_button":
                standardType = standardTypeGetter(value);
                break;



        }
    }
    public void variableParserCore(String button, String value){
        GamepadEx.VariableInputTypes variableType;
        switch(button.toLowerCase()){
            case"left_trigger":
                variableType = variableTypeGetter(value);
                break;
            case"right_trigger":
                variableType = variableTypeGetter(value);
                break;
            case"left_stick_x":
                variableType = variableTypeGetter(value);
                break;
            case"left_stick_y":
                variableType = variableTypeGetter(value);
                break;
            case"right_stick_x":
                variableType = variableTypeGetter(value);
                break;
            case"right_stick_y":
                variableType = variableTypeGetter(value);
                break;
        }
    }
    private static GamepadEx.StandardButtonTypes standardTypeGetter(String in){
        in = in.toLowerCase();
        switch(in){
            case("standard"):
                return GamepadEx.StandardButtonTypes.STANDARD_BUTTON;
                
            case("toggle"):
                return GamepadEx.StandardButtonTypes.TOGGLE_BUTTON;
                
            default:
                return null;
        }
        
    }
    private static GamepadEx.VariableInputTypes variableTypeGetter(String in){
        in = in.toLowerCase();
        switch(in){
            case("variable_input_standard"):
                return GamepadEx.VariableInputTypes.VARIABLE_INPUT_STANDARD;
            case("variable_input_standard_button"):
                return GamepadEx.VariableInputTypes.VARIABLE_INPUT_STANDARD_BUTTON;
            case("variable_input_toggle_button"):
                return GamepadEx.VariableInputTypes.VARIABLE_INPUT_TOGGLE_BUTTON;
            default:
                return null;

        }
    }
}
