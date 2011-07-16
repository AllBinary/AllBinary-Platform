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
package abbot.tester;

import java.util.Map;

/** Provides read/write of local-specific mappings for virtual keycode-based
    KeyStrokes to characters and vice versa.  
*/
public interface KeyStrokeMapProvider {
    /** Returns a map for the current locale which translates an Integer
     * virtual keycode (VK_XXX) into a the Character it produces.  May not
     * necessarily map all keycode/modifier combinations.
     */
    Map loadCharacterMap(); 

    /** Returns a map for the current locale which translates a Character into
     * a keycode-based KeyStroke.  Where multiple keycodes may produce the
     * same Character output, the simplest keystroke is used.
     */
    Map loadKeyStrokeMap();
}
