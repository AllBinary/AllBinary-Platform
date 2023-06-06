package org.allbinary.game.input;

import android.view.KeyEvent;

public class AndroidAPI9KeyFactory extends AndroidKeyFactory
{
    private static final AndroidAPI9KeyFactory SINGLETON = new AndroidAPI9KeyFactory();

    protected AndroidAPI9KeyFactory()
    {
        /*
    	*/
    	KEYCODE_BUTTON_L1 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_L1, "BUTTON L1");
    	KEYCODE_BUTTON_R1 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_R1, "BUTTON R1");
		KEYCODE_BUTTON_THUMBR = new AndroidGameKey(
				KeyEvent.KEYCODE_BUTTON_THUMBR, "BUTTON THUMBR");
		KEYCODE_BUTTON_THUMBL = new AndroidGameKey(
				KeyEvent.KEYCODE_BUTTON_THUMBL, "BUTTON THUMBL");
		KEYCODE_BUTTON_START = new AndroidGameKey(
				KeyEvent.KEYCODE_BUTTON_START, "BUTTON START");
		KEYCODE_BUTTON_MODE = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_MODE,
				"BUTTON MODE");
		KEYCODE_BUTTON_B = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_B,
				"BUTTON B");
		KEYCODE_BUTTON_A = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_A,
				"BUTTON A");
		KEYCODE_BUTTON_X = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_X,
				"BUTTON X");
		KEYCODE_BUTTON_Y = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_Y,
				"BUTTON Y");
		
	    KEYCODE_BUTTON_L2 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_L2, "BUTTON L2");
	    KEYCODE_BUTTON_R2 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_R2, "BUTTON R2");
	    KEYCODE_BUTTON_SELECT = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_SELECT, "BUTTON SELECT");
	    KEYCODE_BUTTON_C = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_C, "BUTTON C");
	    KEYCODE_BUTTON_Z = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_Z, "BUTTON Z");
		
    }
    
    public static final AndroidAPI9KeyFactory getInstance()
    {
        return SINGLETON;
    }

}
