/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.game.displayable.canvas;

/*//JSNI Expose so JSNI can access this class *** &#10;
globalThis.org = globalThis.org || {}; &#10;
globalThis.org.allbinary = globalThis.org.allbinary || {}; &#10;
globalThis.org.allbinary.game = globalThis.org.allbinary.game || {}; &#10;
globalThis.org.allbinary.game.displayable = globalThis.org.allbinary.game.displayable || {}; &#10;
globalThis.org.allbinary.game.displayable.canvas = globalThis.org.allbinary.game.displayable.canvas || {}; &#10;
globalThis.org.allbinary.game.displayable.canvas.NullWaitGameRunnable = NullWaitGameRunnable; &#10;
console.log('Exported NullWaitGameRunnable as globalThis'); &#10;
*/
public class NullWaitGameRunnable extends GameRunnable {
    
    private static final GameRunnable instance = new NullWaitGameRunnable();

    public static GameRunnable getInstance()
    {
        return NullWaitGameRunnable.instance;
    }
    
}
