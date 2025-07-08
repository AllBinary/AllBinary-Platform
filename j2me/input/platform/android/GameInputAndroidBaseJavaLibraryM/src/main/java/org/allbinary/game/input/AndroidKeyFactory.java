package org.allbinary.game.input;

import android.view.KeyEvent;

import org.allbinary.android.ActivityFractureUtil;
import org.allbinary.string.CommonPhoneStrings;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class AndroidKeyFactory extends ActivityFractureUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public final AndroidGameKey UNKNOWN = new AndroidGameKey(KeyEvent.KEYCODE_UNKNOWN, "UNKNOWN");

     //Added in API level 16 
     /*
     KEYCODE_ASSIST
     KEYCODE_EISU 
     KEYCODE_HENKAN
     KEYCODE_KANA 
     KEYCODE_KATAKANA_HIRAGANA 
     KEYCODE_MUHENKAN 
     KEYCODE_RO  
     KEYCODE_YEN 
     KEYCODE_ZENKAKU_HANKAKU 
      * 
      */

     //Added in API level 15
     /*
     KEYCODE_CALCULATOR 
     KEYCODE_CALENDAR 
     KEYCODE_CONTACTS 
      * KEYCODE_MUSIC
      */

     //Added in API level 14
     /*
     KEYCODE_3D_MODE 
     KEYCODE_LANGUAGE_SWITCH 
     KEYCODE_MANNER_MODE 
      * 
      */

    //Added in API level 12
     /*
     KEYCODE_BUTTON_1 
     KEYCODE_BUTTON_10 
     KEYCODE_BUTTON_11 
     KEYCODE_BUTTON_12 
     KEYCODE_BUTTON_13 
     KEYCODE_BUTTON_14 
     KEYCODE_BUTTON_15 
     KEYCODE_BUTTON_16 
     KEYCODE_BUTTON_2 
     KEYCODE_BUTTON_3 
     KEYCODE_BUTTON_4 
     KEYCODE_BUTTON_5 
     KEYCODE_BUTTON_6 
     KEYCODE_BUTTON_7 
     KEYCODE_BUTTON_8 
     KEYCODE_BUTTON_9 
      * 
      */
     
    //Added in API level 11
    /*
     KEYCODE_F1 
     KEYCODE_F10 
     KEYCODE_F11 
     KEYCODE_F12 
     KEYCODE_F2 
     KEYCODE_F3 
     KEYCODE_F4 
     KEYCODE_F5 
     KEYCODE_F6 
     KEYCODE_F7 
     KEYCODE_F8 
     KEYCODE_F9 

     FLAG_FALLBACK
     
     KEYCODE_APP_SWITCH 
     KEYCODE_AVR_INPUT 
     KEYCODE_AVR_POWER 
     KEYCODE_BOOKMARK 
     KEYCODE_BREAK 
     KEYCODE_CAPS_LOCK 
     KEYCODE_CAPTIONS 
     KEYCODE_CHANNEL_DOWN 
     KEYCODE_CHANNEL_UP 
     KEYCODE_CTRL_LEFT 
     KEYCODE_CTRL_RIGHT 
     KEYCODE_DVR 
     KEYCODE_ESCAPE 
     KEYCODE_FORWARD 
     KEYCODE_FORWARD_DEL 
     KEYCODE_FUNCTION 
     KEYCODE_GUIDE 
     KEYCODE_INFO 
     KEYCODE_INSERT 
     KEYCODE_MEDIA_CLOSE 
     KEYCODE_MEDIA_EJECT 
     KEYCODE_MEDIA_PAUSE 
     KEYCODE_MEDIA_PLAY 
     KEYCODE_MEDIA_RECORD 
     KEYCODE_META_LEFT 
     KEYCODE_META_RIGHT 
     KEYCODE_MOVE_END 
     KEYCODE_MOVE_HOME 

     KEYCODE_NUMPAD_0 
     KEYCODE_NUMPAD_1 
     KEYCODE_NUMPAD_2 
     KEYCODE_NUMPAD_3 
     KEYCODE_NUMPAD_4 
     KEYCODE_NUMPAD_5 
     KEYCODE_NUMPAD_6 
     KEYCODE_NUMPAD_7 
     KEYCODE_NUMPAD_8 
     KEYCODE_NUMPAD_9 
     KEYCODE_NUMPAD_ADD 
     KEYCODE_NUMPAD_COMMA 
     KEYCODE_NUMPAD_DIVIDE 
     KEYCODE_NUMPAD_DOT 
     KEYCODE_NUMPAD_ENTER 
     KEYCODE_NUMPAD_EQUALS 
     KEYCODE_NUMPAD_LEFT_PAREN 
     KEYCODE_NUMPAD_MULTIPLY 
     KEYCODE_NUMPAD_RIGHT_PAREN 
     KEYCODE_NUMPAD_SUBTRACT 
     KEYCODE_NUM_LOCK 

     KEYCODE_PROG_BLUE 
     KEYCODE_PROG_GREEN 
     KEYCODE_PROG_RED 
     KEYCODE_PROG_YELLOW 
     KEYCODE_SCROLL_LOCK 
     KEYCODE_SETTINGS 
     KEYCODE_STB_INPUT 
     KEYCODE_STB_POWER 
     KEYCODE_SYSRQ 
     KEYCODE_TV 
     KEYCODE_TV_INPUT 
     KEYCODE_TV_POWER 
     KEYCODE_VOLUME_MUTE 
     KEYCODE_WINDOW 
     KEYCODE_ZOOM_IN 
     KEYCODE_ZOOM_OUT 
     META_ALT_MASK 
     META_CAPS_LOCK_ON 
     META_CTRL_LEFT_ON 
     META_CTRL_MASK 
     META_CTRL_ON 
     META_CTRL_RIGHT_ON 
     META_FUNCTION_ON 
     META_META_LEFT_ON 
     META_META_MASK 
     META_META_ON 
     META_META_RIGHT_ON 
     META_NUM_LOCK_ON 
     META_SCROLL_LOCK_ON      
     * META_SHIFT_MASK
     */

     //Added in API level 9
     /*
     KEYCODE_BUTTON_A 
     KEYCODE_BUTTON_B 
     KEYCODE_BUTTON_C 
     KEYCODE_BUTTON_L1 
     KEYCODE_BUTTON_L2 
     KEYCODE_BUTTON_MODE 
     KEYCODE_BUTTON_R1 
     KEYCODE_BUTTON_R2 
     KEYCODE_BUTTON_SELECT 
     KEYCODE_BUTTON_START 
     KEYCODE_BUTTON_THUMBL 
     KEYCODE_BUTTON_THUMBR 
     KEYCODE_BUTTON_X 
     KEYCODE_BUTTON_Y 
     KEYCODE_BUTTON_Z 

     KEYCODE_PAGE_DOWN 
     KEYCODE_PAGE_UP 
     KEYCODE_PICTSYMBOLS 
      * KEYCODE_SWITCH_CHARSET
      */

   //Added in API level 5
     /*
     FLAG_CANCELED
     FLAG_CANCELED_LONG_PRESS 
     FLAG_LONG_PRESS 
     FLAG_TRACKING 
     FLAG_VIRTUAL_HARD_KEY 
      * 
      */
     
   //Added in API level 3 
     /*
     FLAG_EDITOR_ACTION 
     FLAG_FROM_SYSTEM 
     FLAG_KEEP_TOUCH_MODE 
     FLAG_SOFT_KEYBOARD 

     KEYCODE_MEDIA_FAST_FORWARD 
     KEYCODE_MEDIA_NEXT 
     KEYCODE_MUTE 

     KEYCODE_MEDIA_PLAY_PAUSE 
     KEYCODE_MEDIA_PREVIOUS 
     KEYCODE_MEDIA_REWIND 
     KEYCODE_MEDIA_STOP 

      */
     
    //Added in API level 1
    /*
     * 
     ACTION_DOWN 
     ACTION_MULTIPLE 
     ACTION_UP
      
     FLAG_WOKE_HERE 
     
     KEYCODE_0 
     KEYCODE_1 
     KEYCODE_2 
     KEYCODE_3 
     KEYCODE_4 
     KEYCODE_5 
     KEYCODE_6 
     KEYCODE_7 
     KEYCODE_8 
     KEYCODE_9 
     KEYCODE_A 
     KEYCODE_ALT_LEFT 
     KEYCODE_ALT_RIGHT 
     KEYCODE_APOSTROPHE       
     KEYCODE_AT 
     KEYCODE_B 
     KEYCODE_BACK 
     KEYCODE_BACKSLASH 
     KEYCODE_C 
     KEYCODE_CALL 
     KEYCODE_CAMERA 
     KEYCODE_CLEAR 
     KEYCODE_COMMA 
     KEYCODE_D 
     KEYCODE_DEL 
     KEYCODE_DPAD_CENTER 
     KEYCODE_DPAD_DOWN 
     KEYCODE_DPAD_LEFT 
     KEYCODE_DPAD_RIGHT 
     KEYCODE_DPAD_UP 
     KEYCODE_E 
     KEYCODE_ENDCALL 
     KEYCODE_ENTER 
     KEYCODE_ENVELOPE 
     KEYCODE_EQUALS 
     KEYCODE_EXPLORER 
     KEYCODE_F 
     KEYCODE_FOCUS 
     KEYCODE_G 
     KEYCODE_GRAVE 
     KEYCODE_H 
     KEYCODE_HEADSETHOOK 
     KEYCODE_HOME 
     KEYCODE_I 
     KEYCODE_J 
     KEYCODE_K 
     KEYCODE_L 
     KEYCODE_LEFT_BRACKET 
     KEYCODE_M 
     KEYCODE_MENU 
     KEYCODE_MINUS 
     KEYCODE_N 
     KEYCODE_NOTIFICATION 
     KEYCODE_NUM 
     KEYCODE_O 
     KEYCODE_P 
     KEYCODE_PERIOD      
     KEYCODE_PLUS 
     KEYCODE_POUND 
     KEYCODE_POWER 
     KEYCODE_Q 
     KEYCODE_R 
     KEYCODE_RIGHT_BRACKET 
     KEYCODE_S
     KEYCODE_SEARCH 
     KEYCODE_SEMICOLON 
     KEYCODE_SHIFT_LEFT 
     KEYCODE_SHIFT_RIGHT 
     KEYCODE_SLASH 
     KEYCODE_SOFT_LEFT 
     KEYCODE_SOFT_RIGHT 
     KEYCODE_SPACE 
     KEYCODE_STAR 
     KEYCODE_SYM 
     KEYCODE_T 
     KEYCODE_TAB 
     KEYCODE_U      
     KEYCODE_UNKNOWN 
     KEYCODE_V 
     KEYCODE_VOLUME_DOWN 
     KEYCODE_VOLUME_UP 
     KEYCODE_W
     KEYCODE_X 
     KEYCODE_Y 
     KEYCODE_Z 
     MAX_KEYCODE 
     META_ALT_LEFT_ON 
     META_ALT_ON 
     META_ALT_RIGHT_ON 
     META_SHIFT_LEFT_ON
     META_SHIFT_ON 
    META_SHIFT_RIGHT_ON 
    META_SYM_ON      
     */
  
    /*
     * API 9 Game Input
	 */    
    public AndroidGameKey KEYCODE_BUTTON_L1 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_R1 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_THUMBR = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_THUMBL = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_START = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_MODE = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_B = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_A = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_X = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_Y = UNKNOWN;

    public AndroidGameKey KEYCODE_BUTTON_L2 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_R2 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_SELECT = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_C = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_Z = UNKNOWN;
    
    /* 
     * API 12 Game Input
	 */
    public AndroidGameKey KEYCODE_BUTTON_1 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_10 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_11 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_12 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_13 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_14 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_15 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_16 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_2 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_3 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_4 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_5 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_6 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_7 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_8 = UNKNOWN;
    public AndroidGameKey KEYCODE_BUTTON_9 = UNKNOWN;

    public final AndroidGameKey SOFT_LEFT = new AndroidGameKey(KeyEvent.KEYCODE_SOFT_LEFT, "SOFT_LEFT");

    public final AndroidGameKey SOFT_RIGHT = new AndroidGameKey(KeyEvent.KEYCODE_SOFT_RIGHT, "SOFT_RIGHT");

    public final AndroidGameKey HOME = new AndroidGameKey(KeyEvent.KEYCODE_HOME, "HOME");

    public final AndroidGameKey BACK = new AndroidGameKey(KeyEvent.KEYCODE_BACK, "BACK");

    public final AndroidGameKey CALL = new AndroidGameKey(KeyEvent.KEYCODE_CALL, "CALL");

    public final AndroidGameKey ENDCALL = new AndroidGameKey(KeyEvent.KEYCODE_ENDCALL, "ENDCALL");

    public final AndroidGameKey ZERO = new AndroidGameKey(KeyEvent.KEYCODE_0, CommonPhoneStrings
            .getInstance().ZERO);
    public final AndroidGameKey ONE = new AndroidGameKey(KeyEvent.KEYCODE_1, CommonPhoneStrings
            .getInstance().ONE);
    public final AndroidGameKey TWO = new AndroidGameKey(KeyEvent.KEYCODE_2, CommonPhoneStrings
            .getInstance().TWO);
    public final AndroidGameKey THREE = new AndroidGameKey(KeyEvent.KEYCODE_3, CommonPhoneStrings
            .getInstance().THREE);
    public final AndroidGameKey FOUR = new AndroidGameKey(KeyEvent.KEYCODE_4, CommonPhoneStrings
            .getInstance().FOUR);
    public final AndroidGameKey FIVE = new AndroidGameKey(KeyEvent.KEYCODE_5, CommonPhoneStrings
            .getInstance().FIVE);
    public final AndroidGameKey SIX = new AndroidGameKey(KeyEvent.KEYCODE_6, CommonPhoneStrings
            .getInstance().SIX);
    public final AndroidGameKey SEVEN = new AndroidGameKey(KeyEvent.KEYCODE_7, CommonPhoneStrings
            .getInstance().SEVEN);
    public final AndroidGameKey EIGHT = new AndroidGameKey(KeyEvent.KEYCODE_8, CommonPhoneStrings
            .getInstance().EIGHT);
    public final AndroidGameKey NINE = new AndroidGameKey(KeyEvent.KEYCODE_9, CommonPhoneStrings
            .getInstance().NINE);

    public final AndroidGameKey STAR = new AndroidGameKey(KeyEvent.KEYCODE_STAR, CommonPhoneStrings
            .getInstance().STAR);

    public final AndroidGameKey POUND = new AndroidGameKey(KeyEvent.KEYCODE_POUND, CommonPhoneStrings
            .getInstance().POUND);

    public final AndroidGameKey DPAD_UP = new AndroidGameKey(KeyEvent.KEYCODE_DPAD_UP, "UP");

    public final AndroidGameKey DPAD_DOWN = new AndroidGameKey(KeyEvent.KEYCODE_DPAD_DOWN, "DOWN");

    public final AndroidGameKey DPAD_LEFT = new AndroidGameKey(KeyEvent.KEYCODE_DPAD_LEFT, "LEFT");

    public final AndroidGameKey DPAD_RIGHT = new AndroidGameKey(KeyEvent.KEYCODE_DPAD_RIGHT, "RIGHT");

    public final AndroidGameKey DPAD_CENTER = new AndroidGameKey(KeyEvent.KEYCODE_DPAD_CENTER, "CENTER");

    public final AndroidGameKey VOLUME_UP = new AndroidGameKey(KeyEvent.KEYCODE_VOLUME_UP, "VOLUME_UP");

    public final AndroidGameKey VOLUME_DOWN = new AndroidGameKey(KeyEvent.KEYCODE_VOLUME_DOWN,
            "VOLUME_DOWN");

    public final AndroidGameKey POWER = new AndroidGameKey(KeyEvent.KEYCODE_POWER, "POWER");

    public final AndroidGameKey CAMERA = new AndroidGameKey(KeyEvent.KEYCODE_CAMERA, "CAMERA");

    public final AndroidGameKey CLEAR = new AndroidGameKey(KeyEvent.KEYCODE_CLEAR, "CLEAR");

    public final AndroidGameKey A = new AndroidGameKey(KeyEvent.KEYCODE_A, "A");
    public final AndroidGameKey B = new AndroidGameKey(KeyEvent.KEYCODE_B, "B");
    public final AndroidGameKey C = new AndroidGameKey(KeyEvent.KEYCODE_C, "C");
    public final AndroidGameKey D = new AndroidGameKey(KeyEvent.KEYCODE_D, "D");
    public final AndroidGameKey E = new AndroidGameKey(KeyEvent.KEYCODE_E, "E");
    public final AndroidGameKey F = new AndroidGameKey(KeyEvent.KEYCODE_F, "F");
    public final AndroidGameKey G = new AndroidGameKey(KeyEvent.KEYCODE_G, "G");
    public final AndroidGameKey H = new AndroidGameKey(KeyEvent.KEYCODE_H, "H");
    public final AndroidGameKey I = new AndroidGameKey(KeyEvent.KEYCODE_I, "I");
    public final AndroidGameKey J = new AndroidGameKey(KeyEvent.KEYCODE_J, "J");
    public final AndroidGameKey K = new AndroidGameKey(KeyEvent.KEYCODE_K, "K");
    public final AndroidGameKey L = new AndroidGameKey(KeyEvent.KEYCODE_L, "L");
    public final AndroidGameKey M = new AndroidGameKey(KeyEvent.KEYCODE_M, "M");
    public final AndroidGameKey N = new AndroidGameKey(KeyEvent.KEYCODE_N, "N");
    public final AndroidGameKey O = new AndroidGameKey(KeyEvent.KEYCODE_O, "O");
    public final AndroidGameKey P = new AndroidGameKey(KeyEvent.KEYCODE_P, "P");
    public final AndroidGameKey Q = new AndroidGameKey(KeyEvent.KEYCODE_Q, "Q");
    public final AndroidGameKey R = new AndroidGameKey(KeyEvent.KEYCODE_R, "R");
    public final AndroidGameKey S = new AndroidGameKey(KeyEvent.KEYCODE_S, "S");
    public final AndroidGameKey T = new AndroidGameKey(KeyEvent.KEYCODE_T, "T");
    public final AndroidGameKey U = new AndroidGameKey(KeyEvent.KEYCODE_U, "U");
    public final AndroidGameKey V = new AndroidGameKey(KeyEvent.KEYCODE_V, "V");
    public final AndroidGameKey W = new AndroidGameKey(KeyEvent.KEYCODE_W, "W");
    public final AndroidGameKey X = new AndroidGameKey(KeyEvent.KEYCODE_X, "X");
    public final AndroidGameKey Y = new AndroidGameKey(KeyEvent.KEYCODE_Y, "Y");
    public final AndroidGameKey Z = new AndroidGameKey(KeyEvent.KEYCODE_Z, "Z");

    public final AndroidGameKey COMMA = new AndroidGameKey(KeyEvent.KEYCODE_COMMA, "COMMA");

    public final AndroidGameKey PERIOD = new AndroidGameKey(KeyEvent.KEYCODE_PERIOD,
            AbPathData.getInstance().EXTENSION_SEP);

    public final AndroidGameKey ALT_LEFT = new AndroidGameKey(KeyEvent.KEYCODE_ALT_LEFT, "ALT_LEFT");

    public final AndroidGameKey ALT_RIGHT = new AndroidGameKey(KeyEvent.KEYCODE_ALT_RIGHT, "ALT_RIGHT");

    public final AndroidGameKey SHIFT_LEFT = new AndroidGameKey(KeyEvent.KEYCODE_SHIFT_LEFT, "SHIFT_LEFT");

    public final AndroidGameKey SHIFT_RIGHT = new AndroidGameKey(KeyEvent.KEYCODE_SHIFT_RIGHT,
            "SHIFT_RIGHT");

    public final AndroidGameKey TAB = new AndroidGameKey(KeyEvent.KEYCODE_TAB, "TAB");

    public final AndroidGameKey SPACE = new AndroidGameKey(KeyEvent.KEYCODE_SPACE, "SPACE");

    public final AndroidGameKey SYM = new AndroidGameKey(KeyEvent.KEYCODE_SYM, "SYM");

    public final AndroidGameKey EXPLORER = new AndroidGameKey(KeyEvent.KEYCODE_EXPLORER, "EXPLORER");

    public final AndroidGameKey ENVELOPE = new AndroidGameKey(KeyEvent.KEYCODE_ENVELOPE, "ENVELOPE");

    public final AndroidGameKey ENTER = new AndroidGameKey(KeyEvent.KEYCODE_ENTER, "ENTER");

    public final AndroidGameKey DEL = new AndroidGameKey(KeyEvent.KEYCODE_DEL, "DEL");

    public final AndroidGameKey GRAVE = new AndroidGameKey(KeyEvent.KEYCODE_GRAVE, "GRAVE");

    public final AndroidGameKey MINUS = new AndroidGameKey(KeyEvent.KEYCODE_MINUS, "MINUS");

    public final AndroidGameKey EQUALS = new AndroidGameKey(KeyEvent.KEYCODE_EQUALS, "EQUALS");

    public final AndroidGameKey LEFT_BRACKET = new AndroidGameKey(KeyEvent.KEYCODE_LEFT_BRACKET,
            "LEFT_BRACKET");

    public final AndroidGameKey RIGHT_BRACKET = new AndroidGameKey(KeyEvent.KEYCODE_RIGHT_BRACKET,
            "RIGHT_BRACKET");

    public final AndroidGameKey BACKSLASH = new AndroidGameKey(KeyEvent.KEYCODE_BACKSLASH, "BACKSLASH");

    public final AndroidGameKey SEMICOLON = new AndroidGameKey(KeyEvent.KEYCODE_SEMICOLON, "SEMICOLON");

    public final AndroidGameKey APOSTROPHE = new AndroidGameKey(KeyEvent.KEYCODE_APOSTROPHE, "APOSTROPHE");

    public final AndroidGameKey SLASH = new AndroidGameKey(KeyEvent.KEYCODE_SLASH, "SLASH");

    public final AndroidGameKey AT = new AndroidGameKey(KeyEvent.KEYCODE_AT, "AT");

    public final AndroidGameKey NUM = new AndroidGameKey(KeyEvent.KEYCODE_NUM, "NUM");

    public final AndroidGameKey HEADSETHOOK = new AndroidGameKey(KeyEvent.KEYCODE_HEADSETHOOK,
            "HEADSETHOOK");

    public final AndroidGameKey FOCUS = new AndroidGameKey(KeyEvent.KEYCODE_FOCUS, "FOCUS");

    public final AndroidGameKey PLUS = new AndroidGameKey(KeyEvent.KEYCODE_PLUS, "PLUS");

    public final AndroidGameKey MENU = new AndroidGameKey(KeyEvent.KEYCODE_MENU, "MENU");

    public final AndroidGameKey NOTIFICATION = new AndroidGameKey(KeyEvent.KEYCODE_NOTIFICATION,
            "NOTIFICATION");

    public final AndroidGameKey SEARCH = new AndroidGameKey(KeyEvent.KEYCODE_SEARCH, "SEARCH");

    public void init()
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put("Start/End", this, commonStrings.INIT);
    }

    public boolean isSubmission(Input input)
    {
        if (input == ENTER)
        {
            return true;
        } else if (input == DPAD_CENTER)
        {
            return true;
        }
        return false;
    }

    public boolean isDelete(Input input)
    {
        return false;
    }

    //In android delete is backspace
    public boolean isBackSpace(Input input)
    {
        if (input == DEL)
        {
            return true;
        }
        return false;
    }

    public boolean isLeft(Input input)
    {
        if (input == DPAD_LEFT)
        {
            return true;
        }

        return false;
    }

    public boolean isRight(Input input)
    {
        if (input == DPAD_RIGHT)
        {
            return true;
        }

        return false;
    }

    public boolean isUp(Input input)
    {
        if (input == DPAD_UP)
        {
            return true;
        }
        return false;
    }

    public boolean isDown(Input input)
    {
        if (input == DPAD_DOWN)
        {
            return true;
        }

        return false;
    }
    
    public boolean isEnter(Input input)
    {
        if (input == DPAD_CENTER)
        {
            return true;
        }
        else
            if (input == ENTER)
            {
                return true;
            }
            else
                if (input == CALL)
                {
                    return true;
                }
            
        return false;
    }
}
