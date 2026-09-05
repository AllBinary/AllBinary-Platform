/*
 * AllBinary Open License Version 1
 * Copyright (c) 2026 AllBinary
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

/**
 *
 * @author User
 */
public class ABKeyEventWrapper {
    
    private static final ABKeyEventWrapper instance = new ABKeyEventWrapper();

    /**
     * @return the instance
     */
    public static ABKeyEventWrapper getInstance() {
        return instance;
    }
    
    public final int VK_ENTER = KeyEvent.VK_ENTER;
    public final int VK_BACK_SPACE = KeyEvent.VK_BACK_SPACE;
    public final int VK_TAB = KeyEvent.VK_TAB;
    public final int VK_CANCEL = KeyEvent.VK_CANCEL;
    public final int VK_CLEAR = KeyEvent.VK_CLEAR;
    public final int VK_SHIFT = KeyEvent.VK_SHIFT;
    public final int VK_CONTROL = KeyEvent.VK_CONTROL;
    public final int VK_ALT = KeyEvent.VK_ALT;
    public final int VK_PAUSE = KeyEvent.VK_PAUSE;
    public final int VK_CAPS_LOCK = KeyEvent.VK_CAPS_LOCK;
    public final int VK_ESCAPE = KeyEvent.VK_ESCAPE;
    public final int VK_SPACE = KeyEvent.VK_SPACE;
    public final int VK_PAGE_UP = KeyEvent.VK_PAGE_UP;
    public final int VK_PAGE_DOWN = KeyEvent.VK_PAGE_DOWN;
    public final int VK_END = KeyEvent.VK_END;
    public final int VK_HOME = KeyEvent.VK_HOME;
    public final int VK_LEFT = KeyEvent.VK_LEFT;
    public final int VK_UP = KeyEvent.VK_UP;
    public final int VK_RIGHT = KeyEvent.VK_RIGHT;
    public final int VK_DOWN = KeyEvent.VK_DOWN;
    public final int VK_COMMA = KeyEvent.VK_COMMA;
    public final int VK_MINUS = KeyEvent.VK_MINUS;
    public final int VK_PERIOD = KeyEvent.VK_PERIOD;
    public final int VK_SLASH = KeyEvent.VK_SLASH;
    public final int VK_0 = KeyEvent.VK_0;
    public final int VK_1 = KeyEvent.VK_1;
    public final int VK_2 = KeyEvent.VK_2;
    public final int VK_3 = KeyEvent.VK_3;
    public final int VK_4 = KeyEvent.VK_4;
    public final int VK_5 = KeyEvent.VK_5;
    public final int VK_6 = KeyEvent.VK_6;
    public final int VK_7 = KeyEvent.VK_7;
    public final int VK_8 = KeyEvent.VK_8;
    public final int VK_9 = KeyEvent.VK_9;
    public final int VK_SEMICOLON = KeyEvent.VK_SEMICOLON;
    public final int VK_EQUALS = KeyEvent.VK_EQUALS;
    public final int VK_A = KeyEvent.VK_A;
    public final int VK_B = KeyEvent.VK_B;
    public final int VK_C = KeyEvent.VK_C;
    public final int VK_D = KeyEvent.VK_D;
    public final int VK_E = KeyEvent.VK_E;
    public final int VK_F = KeyEvent.VK_F;
    public final int VK_G = KeyEvent.VK_G;
    public final int VK_H = KeyEvent.VK_H;
    public final int VK_I = KeyEvent.VK_I;
    public final int VK_J = KeyEvent.VK_J;
    public final int VK_K = KeyEvent.VK_K;
    public final int VK_L = KeyEvent.VK_L;
    public final int VK_M = KeyEvent.VK_M;
    public final int VK_N = KeyEvent.VK_N;
    public final int VK_O = KeyEvent.VK_O;
    public final int VK_P = KeyEvent.VK_P;
    public final int VK_Q = KeyEvent.VK_Q;
    public final int VK_R = KeyEvent.VK_R;
    public final int VK_S = KeyEvent.VK_S;
    public final int VK_T = KeyEvent.VK_T;
    public final int VK_U = KeyEvent.VK_U;
    public final int VK_V = KeyEvent.VK_V;
    public final int VK_W = KeyEvent.VK_W;
    public final int VK_X = KeyEvent.VK_X;
    public final int VK_Y = KeyEvent.VK_Y;
    public final int VK_Z = KeyEvent.VK_Z;
    public final int VK_OPEN_BRACKET = KeyEvent.VK_OPEN_BRACKET;
    public final int VK_BACK_SLASH = KeyEvent.VK_BACK_SLASH;
    public final int VK_CLOSE_BRACKET = KeyEvent.VK_CLOSE_BRACKET;
    public final int VK_NUMPAD0 = KeyEvent.VK_NUMPAD0;
    public final int VK_NUMPAD1 = KeyEvent.VK_NUMPAD1;
    public final int VK_NUMPAD2 = KeyEvent.VK_NUMPAD2;
    public final int VK_NUMPAD3 = KeyEvent.VK_NUMPAD3;
    public final int VK_NUMPAD4 = KeyEvent.VK_NUMPAD4;
    public final int VK_NUMPAD5 = KeyEvent.VK_NUMPAD5;
    public final int VK_NUMPAD6 = KeyEvent.VK_NUMPAD6;
    public final int VK_NUMPAD7 = KeyEvent.VK_NUMPAD7;
    public final int VK_NUMPAD8 = KeyEvent.VK_NUMPAD8;
    public final int VK_NUMPAD9 = KeyEvent.VK_NUMPAD9;
    public final int VK_MULTIPLY = KeyEvent.VK_MULTIPLY;
    public final int VK_ADD = KeyEvent.VK_ADD;
    public final int VK_SEPARATER = KeyEvent.VK_SEPARATER;
    public final int VK_SEPARATOR = KeyEvent.VK_SEPARATOR;
    public final int VK_SUBTRACT = KeyEvent.VK_SUBTRACT;
    public final int VK_DECIMAL = KeyEvent.VK_DECIMAL;
    public final int VK_DIVIDE = KeyEvent.VK_DIVIDE;
    public final int VK_DELETE = KeyEvent.VK_DELETE;
    public final int VK_NUM_LOCK = KeyEvent.VK_NUM_LOCK;
    public final int VK_SCROLL_LOCK = KeyEvent.VK_SCROLL_LOCK;
    public final int VK_F1 = KeyEvent.VK_F1;
    public final int VK_F2 = KeyEvent.VK_F2;
    public final int VK_F3 = KeyEvent.VK_F3;
    public final int VK_F4 = KeyEvent.VK_F4;
    public final int VK_F5 = KeyEvent.VK_F5;
    public final int VK_F6 = KeyEvent.VK_F6;
    public final int VK_F7 = KeyEvent.VK_F7;
    public final int VK_F8 = KeyEvent.VK_F8;
    public final int VK_F9 = KeyEvent.VK_F9;
    public final int VK_F10 = KeyEvent.VK_F10;
    public final int VK_F11 = KeyEvent.VK_F11;
    public final int VK_F12 = KeyEvent.VK_F12;
    public final int VK_F13 = KeyEvent.VK_F13;
    public final int VK_F14 = KeyEvent.VK_F14;
    public final int VK_F15 = KeyEvent.VK_F15;
    public final int VK_F16 = KeyEvent.VK_F16;
    public final int VK_F17 = KeyEvent.VK_F17;
    public final int VK_F18 = KeyEvent.VK_F18;
    public final int VK_F19 = KeyEvent.VK_F19;
    public final int VK_F20 = KeyEvent.VK_F20;
    public final int VK_F21 = KeyEvent.VK_F21;
    public final int VK_F22 = KeyEvent.VK_F22;
    public final int VK_F23 = KeyEvent.VK_F23;
    public final int VK_F24 = KeyEvent.VK_F24;
    public final int VK_PRINTSCREEN = KeyEvent.VK_PRINTSCREEN;
    public final int VK_INSERT = KeyEvent.VK_INSERT;
    public final int VK_HELP = KeyEvent.VK_HELP;
    public final int VK_META = KeyEvent.VK_META;
    public final int VK_BACK_QUOTE = KeyEvent.VK_BACK_QUOTE;
    public final int VK_QUOTE = KeyEvent.VK_QUOTE;
    public final int VK_KP_UP = KeyEvent.VK_KP_UP;
    public final int VK_KP_DOWN = KeyEvent.VK_KP_DOWN;
    public final int VK_KP_LEFT = KeyEvent.VK_KP_LEFT;
    public final int VK_KP_RIGHT = KeyEvent.VK_KP_RIGHT;
    public final int VK_DEAD_GRAVE = KeyEvent.VK_DEAD_GRAVE;
    public final int VK_DEAD_ACUTE = KeyEvent.VK_DEAD_ACUTE;
    public final int VK_DEAD_CIRCUMFLEX = KeyEvent.VK_DEAD_CIRCUMFLEX;
    public final int VK_DEAD_TILDE = KeyEvent.VK_DEAD_TILDE;
    public final int VK_DEAD_MACRON = KeyEvent.VK_DEAD_MACRON;
    public final int VK_DEAD_BREVE = KeyEvent.VK_DEAD_BREVE;
    public final int VK_DEAD_ABOVEDOT = KeyEvent.VK_DEAD_ABOVEDOT;
    public final int VK_DEAD_DIAERESIS = KeyEvent.VK_DEAD_DIAERESIS;
    public final int VK_DEAD_ABOVERING = KeyEvent.VK_DEAD_ABOVERING;
    public final int VK_DEAD_DOUBLEACUTE = KeyEvent.VK_DEAD_DOUBLEACUTE;
    public final int VK_DEAD_CARON = KeyEvent.VK_DEAD_CARON;
    public final int VK_DEAD_CEDILLA = KeyEvent.VK_DEAD_CEDILLA;
    public final int VK_DEAD_OGONEK = KeyEvent.VK_DEAD_OGONEK;
    public final int VK_DEAD_IOTA = KeyEvent.VK_DEAD_IOTA;
    public final int VK_DEAD_VOICED_SOUND = KeyEvent.VK_DEAD_VOICED_SOUND;
    public final int VK_DEAD_SEMIVOICED_SOUND = KeyEvent.VK_DEAD_SEMIVOICED_SOUND;
    public final int VK_AMPERSAND = KeyEvent.VK_AMPERSAND;
    public final int VK_ASTERISK = KeyEvent.VK_ASTERISK;
    public final int VK_QUOTEDBL = KeyEvent.VK_QUOTEDBL;
    public final int VK_LESS = KeyEvent.VK_LESS;
    public final int VK_GREATER = KeyEvent.VK_GREATER;
    public final int VK_BRACELEFT = KeyEvent.VK_BRACELEFT;
    public final int VK_BRACERIGHT = KeyEvent.VK_BRACERIGHT;
    public final int VK_AT = KeyEvent.VK_AT;
    public final int VK_COLON = KeyEvent.VK_COLON;
    public final int VK_CIRCUMFLEX = KeyEvent.VK_CIRCUMFLEX;
    public final int VK_DOLLAR = KeyEvent.VK_DOLLAR;
    public final int VK_EURO_SIGN = KeyEvent.VK_EURO_SIGN;
    public final int VK_EXCLAMATION_MARK = KeyEvent.VK_EXCLAMATION_MARK;
    public final int VK_INVERTED_EXCLAMATION_MARK = KeyEvent.VK_INVERTED_EXCLAMATION_MARK;
    public final int VK_LEFT_PARENTHESIS = KeyEvent.VK_LEFT_PARENTHESIS;
    public final int VK_NUMBER_SIGN = KeyEvent.VK_NUMBER_SIGN;
    public final int VK_PLUS = KeyEvent.VK_PLUS;
    public final int VK_RIGHT_PARENTHESIS = KeyEvent.VK_RIGHT_PARENTHESIS;
    public final int VK_UNDERSCORE = KeyEvent.VK_UNDERSCORE;
    public final int VK_WINDOWS = KeyEvent.VK_WINDOWS;
    public final int VK_CONTEXT_MENU = KeyEvent.VK_CONTEXT_MENU;
    public final int VK_FINAL = KeyEvent.VK_FINAL;
    public final int VK_CONVERT = KeyEvent.VK_CONVERT;
    public final int VK_NONCONVERT = KeyEvent.VK_NONCONVERT;
    public final int VK_ACCEPT = KeyEvent.VK_ACCEPT;
    public final int VK_MODECHANGE = KeyEvent.VK_MODECHANGE;
    public final int VK_KANA = KeyEvent.VK_KANA;
    public final int VK_KANJI = KeyEvent.VK_KANJI;
    public final int VK_ALPHANUMERIC = KeyEvent.VK_ALPHANUMERIC;
    public final int VK_KATAKANA = KeyEvent.VK_KATAKANA;
    public final int VK_HIRAGANA = KeyEvent.VK_HIRAGANA;
    public final int VK_FULL_WIDTH = KeyEvent.VK_FULL_WIDTH;
    public final int VK_HALF_WIDTH = KeyEvent.VK_HALF_WIDTH;
    public final int VK_ROMAN_CHARACTERS = KeyEvent.VK_ROMAN_CHARACTERS;
    public final int VK_ALL_CANDIDATES = KeyEvent.VK_ALL_CANDIDATES;
    public final int VK_PREVIOUS_CANDIDATE = KeyEvent.VK_PREVIOUS_CANDIDATE;
    public final int VK_CODE_INPUT = KeyEvent.VK_CODE_INPUT;
    public final int VK_JAPANESE_KATAKANA = KeyEvent.VK_JAPANESE_KATAKANA;
    public final int VK_JAPANESE_HIRAGANA = KeyEvent.VK_JAPANESE_HIRAGANA;
    public final int VK_JAPANESE_ROMAN = KeyEvent.VK_JAPANESE_ROMAN;
    public final int VK_KANA_LOCK = KeyEvent.VK_KANA_LOCK;
    public final int VK_INPUT_METHOD_ON_OFF = KeyEvent.VK_INPUT_METHOD_ON_OFF;
    public final int VK_CUT = KeyEvent.VK_CUT;
    public final int VK_COPY = KeyEvent.VK_COPY;
    public final int VK_PASTE = KeyEvent.VK_PASTE;
    public final int VK_UNDO = KeyEvent.VK_UNDO;
    public final int VK_AGAIN = KeyEvent.VK_AGAIN;
    public final int VK_FIND = KeyEvent.VK_FIND;
    public final int VK_PROPS = KeyEvent.VK_PROPS;
    public final int VK_STOP = KeyEvent.VK_STOP;
    public final int VK_COMPOSE = KeyEvent.VK_COMPOSE;
    public final int VK_ALT_GRAPH = KeyEvent.VK_ALT_GRAPH;
    public final int VK_BEGIN = KeyEvent.VK_BEGIN;
    public final int VK_UNDEFINED = KeyEvent.VK_UNDEFINED;
    
}
