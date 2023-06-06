package org.allbinary.game.input;

import android.view.KeyEvent;

public class AndroidAPI12KeyFactory extends AndroidAPI9KeyFactory
{
    private static final AndroidAPI12KeyFactory SINGLETON = new AndroidAPI12KeyFactory();

    private AndroidAPI12KeyFactory()
    {
        /*
    	*/
		KEYCODE_BUTTON_1 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_1,
				"BUTTON 1");
		KEYCODE_BUTTON_10 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_10,
				"BUTTON 10");
		KEYCODE_BUTTON_11 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_11,
				"BUTTON 11");
		KEYCODE_BUTTON_12 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_12,
				"BUTTON 12");
		KEYCODE_BUTTON_13 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_13,
				"BUTTON 13");
		KEYCODE_BUTTON_14 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_14,
				"BUTTON 14");
		KEYCODE_BUTTON_15 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_15,
				"BUTTON 15");
		KEYCODE_BUTTON_16 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_16,
				"BUTTON 16");
		KEYCODE_BUTTON_2 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_2,
				"BUTTON 2");
		KEYCODE_BUTTON_3 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_3,
				"BUTTON 3");
		KEYCODE_BUTTON_4 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_4,
				"BUTTON 4");
		KEYCODE_BUTTON_5 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_5,
				"BUTTON 5");
		KEYCODE_BUTTON_6 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_6,
				"BUTTON 6");
		KEYCODE_BUTTON_7 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_7,
				"BUTTON 7");
		KEYCODE_BUTTON_8 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_8,
				"BUTTON 8");
		KEYCODE_BUTTON_9 = new AndroidGameKey(KeyEvent.KEYCODE_BUTTON_9,
				"BUTTON 9");
    }
    
    public static final AndroidAPI12KeyFactory getAPI12Instance()
    {
        return SINGLETON;
    }

}
