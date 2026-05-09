package org.allbinary.game.input;

import javax.microedition.lcdui.Canvas;

import android.view.KeyEvent;

public class AndroidNumberKeyToJ2MECanvasNumberKey
{
    //protected final LogUtil logUtil = LogUtil.getInstance();


    private static final int[] androidtoJavaMicroEditionKeyMap = new int[17];

    public static void init()
    {
        AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap[KeyEvent.KEYCODE_0] = Canvas.KEY_NUM0;
        AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap[KeyEvent.KEYCODE_1] = Canvas.KEY_NUM1;
        AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap[KeyEvent.KEYCODE_2] = Canvas.KEY_NUM2;
        AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap[KeyEvent.KEYCODE_3] = Canvas.KEY_NUM3;
        AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap[KeyEvent.KEYCODE_4] = Canvas.KEY_NUM4;
        AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap[KeyEvent.KEYCODE_5] = Canvas.KEY_NUM5;
        AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap[KeyEvent.KEYCODE_6] = Canvas.KEY_NUM6;
        AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap[KeyEvent.KEYCODE_7] = Canvas.KEY_NUM7;
        AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap[KeyEvent.KEYCODE_8] = Canvas.KEY_NUM8;
        AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap[KeyEvent.KEYCODE_9] = Canvas.KEY_NUM9;
    }

    private AndroidNumberKeyToJ2MECanvasNumberKey()
    {
    }

    //TWB - Leave all keys intact except for the number keys 
    //since Canvas number keys are used instead of
    public static int getKey(int key)
    {
        // this.logUtil.putF("Canvas Left: " + Canvas.LEFT +
        // "== " + KeyEvent.KEYCODE_DPAD_LEFT + "==" +
        // KeyEvent.KEYCODE_SOFT_LEFT + " key: " + key, "AndroidToJ2MEKey", // "getKey");

        if (key < AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap.length)
        {
            int value = AndroidNumberKeyToJ2MECanvasNumberKey.androidtoJavaMicroEditionKeyMap[key];
            if (value != 0)
            {
                return value;
            }
        }

        // AndroidToJ2MEKey androidToJ2MEKey = new AndroidToJ2MEKey(key, key);
        // hashtable.put(androidToJ2MEKey, key);
        return key;
        // return 0;
    }

}
