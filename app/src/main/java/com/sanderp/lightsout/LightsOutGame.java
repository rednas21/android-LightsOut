package com.sanderp.lightsout;

import java.util.Random;

/**
 * Created by speerna on 3/4/15.
 */
public class LightsOutGame {

    private int[] buttonValues;
    private int numButtons;

    public LightsOutGame(int numButtons) {
        this.numButtons = numButtons;
        buttonValues = new int[numButtons];
    }

    public void newGame() {
        for(int button = 0; button < numButtons; button++)
            buttonValues[button] = randomizer();
    }

    private int randomizer() {

        Random rand = new Random();
        return rand.nextInt(2);
    }

    public void pressedButtonAtIndex(int button) {
        if(button == 0) {
            buttonValues[0] ^= 1;
            buttonValues[1] ^= 1;
        } else if(button == numButtons - 1) {
            buttonValues[numButtons - 2] ^= 1;
            buttonValues[numButtons - 1] ^= 1;
        } else {
            buttonValues[button - 1] ^= 1;
            buttonValues[button] ^= 1;
            buttonValues[button + 1] ^= 1;
        }
    }

    public int getButtonValue(int i) {
        return buttonValues[i];
    }

    public boolean gameOver() {
        for(int button = 0; button < numButtons; button++) {
            if(getButtonValue(button) >= 1) {
                return false;
            }
        }
        return true;
    }
}
