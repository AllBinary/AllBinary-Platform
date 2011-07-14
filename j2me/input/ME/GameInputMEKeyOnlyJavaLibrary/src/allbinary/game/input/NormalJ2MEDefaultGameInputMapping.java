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
package allbinary.game.input;

import allbinary.game.input.mapping.InputToGameKeyMapping;
import allbinary.game.input.mapping.PersistentInputMapping;

public class NormalJ2MEDefaultGameInputMapping extends PersistentInputMapping
{
    public NormalJ2MEDefaultGameInputMapping()
    {
        super(PersistentInputMapping.DEFAULT_SAVE_NAME);

        InputToGameKeyMapping inputToGameKeyMapping = this.getInputMapping();

        // Also up
        inputToGameKeyMapping.add(GameKey.UP, GameKey.KEY_NUM2);
        // Also left
        inputToGameKeyMapping.add(GameKey.LEFT, GameKey.KEY_NUM4);
        // Also right
        inputToGameKeyMapping.add(GameKey.RIGHT, GameKey.KEY_NUM6);
        // Also down
        inputToGameKeyMapping.add(GameKey.DOWN, GameKey.KEY_NUM8);

        inputToGameKeyMapping.add(GameKey.KEY_NUM1, GameKey.GAME_A);
        inputToGameKeyMapping.add(GameKey.KEY_NUM3, GameKey.GAME_B);
        inputToGameKeyMapping.add(GameKey.KEY_NUM7, GameKey.GAME_C);
        inputToGameKeyMapping.add(GameKey.KEY_NUM9, GameKey.GAME_D);

        /*
        inputToGameKeyMapping.add(GameKey.KEY_NUM1, GameKey.KEYBOARD_Y);
        inputToGameKeyMapping.add(GameKey.KEY_NUM2, GameKey.KEYBOARD_U);
        inputToGameKeyMapping.add(GameKey.KEY_NUM3, GameKey.KEYBOARD_I);
        inputToGameKeyMapping.add(GameKey.KEY_NUM4, GameKey.KEYBOARD_H);
        inputToGameKeyMapping.add(GameKey.KEY_NUM5, GameKey.KEYBOARD_J);
        inputToGameKeyMapping.add(GameKey.KEY_NUM6, GameKey.KEYBOARD_K);
        inputToGameKeyMapping.add(GameKey.KEY_NUM7, GameKey.KEYBOARD_N);
        inputToGameKeyMapping.add(GameKey.KEY_NUM8, GameKey.KEYBOARD_M);

        inputToGameKeyMapping.add(GameKey.KEY_NUM9, GameKey.KEYBOARD_LESS);
        inputToGameKeyMapping.add(GameKey.KEY_STAR, GameKey.KEYBOARD_SPACE);
        inputToGameKeyMapping.add(GameKey.KEY_NUM0, GameKey.KEYBOARD_INSERT);
        inputToGameKeyMapping.add(GameKey.KEY_POUND, GameKey.KEYBOARD_DELETE);
         */

        inputToGameKeyMapping.add(GameKey.LEVEL_DOWN, GameKey.LEVEL_DOWN);
        inputToGameKeyMapping.add(GameKey.LEVEL_UP, GameKey.LEVEL_UP);

        inputToGameKeyMapping.add(GameKey.UP, GameKey.UP);

        inputToGameKeyMapping.add(GameKey.DOWN, GameKey.DOWN);

        inputToGameKeyMapping.add(GameKey.LEFT, GameKey.LEFT);

        inputToGameKeyMapping.add(GameKey.RIGHT, GameKey.RIGHT);

        inputToGameKeyMapping.add(GameKey.KEY_POUND, GameKey.KEY_POUND);

        inputToGameKeyMapping.add(GameKey.KEY_STAR, GameKey.KEY_STAR);

        inputToGameKeyMapping.add(GameKey.KEY_NUM1, GameKey.KEY_NUM1);

        inputToGameKeyMapping.add(GameKey.KEY_NUM3, GameKey.KEY_NUM3);

        inputToGameKeyMapping.add(GameKey.KEY_NUM5, GameKey.KEY_NUM5);

        inputToGameKeyMapping.add(GameKey.KEY_NUM7, GameKey.KEY_NUM7);

        inputToGameKeyMapping.add(GameKey.KEY_NUM9, GameKey.KEY_NUM9);

        inputToGameKeyMapping.add(GameKey.KEY_NUM0, GameKey.KEY_NUM0);

        inputToGameKeyMapping.add(GameKey.KEY_NUM1, GameKey.FIRE);

        // Keyboard mappings - Not part of hashtable to array optimization
        /*
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_Q.getKey(),
         * GameKey..getInstance(GameKey.KEY_NUM4));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_W.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM5));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_E.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM6));
         * //GameKeyMapping.hashtable.put(GameKey.KEYBOARD_A.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM7));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_S.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM8));
         * //GameKeyMapping.hashtable.put(GameKey.KEYBOARD_D.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM9));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_Z.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_STAR));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_X.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM0));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_C.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_POUND));
         */

        /*
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_I.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM1));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_O.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM2));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_P.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM3));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_K.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM4));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_L.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM5));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_SEMICOLON.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM6));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_LESS.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM7));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_GREATER.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM8));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_SLASH.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM9));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_INSERT.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_STAR));
         * GameKeyMapping.hashtable.put(GameKey.KEYBOARD_DELETE.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_NUM0));
         * //GameKeyMapping.hashtable.put(GameKey.KEYBOARD_ALT.getKey(),
         * GameKeyMapping.getInstance(GameKey.KEY_POUND));
         */
    }
}
