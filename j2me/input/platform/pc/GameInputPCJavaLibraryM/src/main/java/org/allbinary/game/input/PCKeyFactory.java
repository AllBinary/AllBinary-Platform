/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.game.input;

import java.awt.event.KeyEvent;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.string.CommonPhoneStrings;

public class PCKeyFactory
{
    private static final PCKeyFactory SINGLETON = new PCKeyFactory();

    public static final PCKeyFactory getInstance()
    {
        return SINGLETON;
    }

    public final PCGameKey UNKNOWN = new PCGameKey(KeyEvent.VK_UNDEFINED, "UNKNOWN");
    public final PCGameKey ENTER = new PCGameKey(5, "Enter");
    public final PCGameKey A = new PCGameKey(KeyEvent.VK_A, "A");
    public final PCGameKey B = new PCGameKey(KeyEvent.VK_B, "B");
    public final PCGameKey C = new PCGameKey(KeyEvent.VK_C, "C");
    public final PCGameKey D = new PCGameKey(KeyEvent.VK_D, "D");
    public final PCGameKey E = new PCGameKey(KeyEvent.VK_E, "E");
    public final PCGameKey F = new PCGameKey(KeyEvent.VK_F, "F");
    public final PCGameKey G = new PCGameKey(KeyEvent.VK_G, "G");
    public final PCGameKey H = new PCGameKey(KeyEvent.VK_H, "H");
    public final PCGameKey I = new PCGameKey(KeyEvent.VK_I, "I");
    public final PCGameKey J = new PCGameKey(KeyEvent.VK_J, "J");
    public final PCGameKey K = new PCGameKey(KeyEvent.VK_K, "K");
    public final PCGameKey L = new PCGameKey(KeyEvent.VK_L, "L");
    public final PCGameKey M = new PCGameKey(KeyEvent.VK_M, "M");
    public final PCGameKey N = new PCGameKey(KeyEvent.VK_N, "N");
    public final PCGameKey O = new PCGameKey(KeyEvent.VK_O, "O");
    public final PCGameKey P = new PCGameKey(KeyEvent.VK_P, "P");
    public final PCGameKey Q = new PCGameKey(KeyEvent.VK_Q, "Q");
    public final PCGameKey R = new PCGameKey(KeyEvent.VK_R, "R");
    public final PCGameKey S = new PCGameKey(KeyEvent.VK_S, "S");
    public final PCGameKey T = new PCGameKey(KeyEvent.VK_T, "T");
    public final PCGameKey U = new PCGameKey(KeyEvent.VK_U, "U");
    public final PCGameKey V = new PCGameKey(KeyEvent.VK_V, "V");
    public final PCGameKey W = new PCGameKey(KeyEvent.VK_W, "W");
    public final PCGameKey X = new PCGameKey(KeyEvent.VK_X, "X");
    public final PCGameKey Y = new PCGameKey(KeyEvent.VK_Y, "Y");
    public final PCGameKey Z = new PCGameKey(KeyEvent.VK_Z, "Z");
    public final PCGameKey a = new PCGameKey(97, "a");
    public final PCGameKey b = new PCGameKey(98, "b");
    public final PCGameKey c = new PCGameKey(99, "c");
    public final PCGameKey d = new PCGameKey(100, "d");
    public final PCGameKey e = new PCGameKey(101, "e");
    public final PCGameKey f = new PCGameKey(102, "f");
    public final PCGameKey g = new PCGameKey(103, "g");
    public final PCGameKey h = new PCGameKey(104, "h");
    public final PCGameKey i = new PCGameKey(105, "i");
    public final PCGameKey j = new PCGameKey(106, "j");
    public final PCGameKey k = new PCGameKey(107, "k");
    public final PCGameKey l = new PCGameKey(108, "l");
    public final PCGameKey m = new PCGameKey(109, "m");
    public final PCGameKey n = new PCGameKey(110, "n");
    public final PCGameKey o = new PCGameKey(111, "o");
    public final PCGameKey p = new PCGameKey(112, "p");
    public final PCGameKey q = new PCGameKey(113, "q");
    public final PCGameKey r = new PCGameKey(114, "r");
    public final PCGameKey s = new PCGameKey(115, "s");
    public final PCGameKey t = new PCGameKey(116, "t");
    public final PCGameKey u = new PCGameKey(117, "u");
    public final PCGameKey v = new PCGameKey(118, "v");
    public final PCGameKey w = new PCGameKey(119, "w");
    public final PCGameKey x = new PCGameKey(120, "x");
    public final PCGameKey y = new PCGameKey(121, "y");
    public final PCGameKey z = new PCGameKey(122, "z");
    
    public final PCGameKey ZERO = new PCGameKey(KeyEvent.VK_0, CommonPhoneStrings.getInstance().ZERO);
    public final PCGameKey ONE = new PCGameKey(KeyEvent.VK_1, CommonPhoneStrings.getInstance().ONE);
    public final PCGameKey TWO = new PCGameKey(KeyEvent.VK_2, CommonPhoneStrings.getInstance().TWO);
    public final PCGameKey THREE = new PCGameKey(KeyEvent.VK_3,
        CommonPhoneStrings.getInstance().THREE);
    public final PCGameKey FOUR = new PCGameKey(KeyEvent.VK_4, CommonPhoneStrings.getInstance().FOUR);
    public final PCGameKey FIVE = new PCGameKey(KeyEvent.VK_5, CommonPhoneStrings.getInstance().FIVE);
    public final PCGameKey SIX = new PCGameKey(KeyEvent.VK_6, CommonPhoneStrings.getInstance().SIX);
    public final PCGameKey SEVEN = new PCGameKey(KeyEvent.VK_7,
        CommonPhoneStrings.getInstance().SEVEN);
    public final PCGameKey EIGHT = new PCGameKey(KeyEvent.VK_8,
        CommonPhoneStrings.getInstance().EIGHT);
    public final PCGameKey NINE = new PCGameKey(KeyEvent.VK_9, CommonPhoneStrings.getInstance().NINE);
    public final PCGameKey DPAD_UP = new PCGameKey(KeyEvent.VK_UP, CommonPhoneStrings.getInstance().UP);
    public final PCGameKey DPAD_DOWN = new PCGameKey(KeyEvent.VK_DOWN, CommonPhoneStrings.getInstance().DOWN);
    public final PCGameKey DPAD_LEFT = new PCGameKey(KeyEvent.VK_LEFT, CommonPhoneStrings.getInstance().LEFT);
    public final PCGameKey DPAD_RIGHT = new PCGameKey(KeyEvent.VK_RIGHT, CommonPhoneStrings.getInstance().RIGHT);
    public final PCGameKey DPAD_UP2 = DPAD_UP;
    public final PCGameKey DPAD_DOWN2 = DPAD_DOWN;
    public final PCGameKey DPAD_LEFT2 = DPAD_LEFT;
    public final PCGameKey DPAD_RIGHT2 = DPAD_RIGHT;
//    public final PCGameKey DPAD_UP2 = new PCGameKey(1, "UP");
//    public final PCGameKey DPAD_DOWN2 = new PCGameKey(2, "DOWN");
//    public final PCGameKey DPAD_LEFT2 = new PCGameKey(3, "LEFT");
//    public final PCGameKey DPAD_RIGHT2 = new PCGameKey(4, "RIGHT");
//    public final PCGameKey DPAD_UP2 = new PCGameKey(-1, "UP");
//    public final PCGameKey DPAD_DOWN2 = new PCGameKey(-2, "DOWN");
//    public final PCGameKey DPAD_LEFT2 = new PCGameKey(-3, "LEFT");
//    public final PCGameKey DPAD_RIGHT2 = new PCGameKey(-4, "RIGHT");
    //public final PCGameKey DPAD_CENTER = new PCGameKey(KeyEvent.VK_CENTER,
    //      "CENTER");
    public final PCGameKey COMMA = new PCGameKey(KeyEvent.VK_COMMA, "COMMA");
    public final PCGameKey SPACE = new PCGameKey(KeyEvent.VK_SPACE, "SPACE");
    public final PCGameKey DEL = new PCGameKey(KeyEvent.VK_DELETE, "DEL");
    //public final PCGameKey POUND = new PCGameKey(KeyEvent.VK_NUMBER_SIGN,
    //      CommonPhoneStrings.getInstance().POUND);
    public final PCGameKey STAR = new PCGameKey(42,
        CommonPhoneStrings.getInstance().STAR);
    public final PCGameKey QUESTION = new PCGameKey(47, "?");
    public final PCGameKey PERIOD = new PCGameKey(KeyEvent.VK_PERIOD,
        CommonSeps.getInstance().PERIOD);
    public final PCGameKey ESCAPE = new PCGameKey(KeyEvent.VK_ESCAPE, "Esc");
    
    public final PCGameKey BACK_SPACE = new PCGameKey(KeyEvent.VK_BACK_SPACE, "Backspace");

    //KeyEvent.VK_BACK_SPACE = ?
    //public PCGameKey BACK_SPACE = new PCGameKey(,
    //      AbPathData.EXTENSION_SEP);

    public boolean isSubmission(Input input)
    {
        if (input == ENTER)
        {
            return true;
        }
        return false;
    }

    public boolean isDelete(Input input)
    {
        if (input == DEL)
        {
            return true;
        }
        return false;
    }

    public boolean isBackSpace(Input input)
    {
        if (input == BACK_SPACE) {
            return true;
        }
        return false;
    }

    public boolean isUp(Input input)
    {
        if (input == DPAD_UP || input == DPAD_UP2)
        {
            return true;
        }
        return false;
    }

    public boolean isDown(Input input)
    {
        if (input == DPAD_DOWN || input == DPAD_DOWN2)
        {
            return true;
        }

        return false;
    }

    public boolean isLeft(Input input)
    {
        if (input == DPAD_LEFT || input == DPAD_LEFT2)
        {
            return true;
        }
        return false;
    }

    public boolean isRight(Input input)
    {
        if (input == DPAD_RIGHT || input == DPAD_RIGHT2)
        {
            return true;
        }

        return false;
    }

    public boolean isEnter(Input input)
    {
        if (input == ENTER)
        {
            return true;
        }

        return false;
    }
}
