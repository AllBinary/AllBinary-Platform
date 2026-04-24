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

import org.allbinary.layer.LayerManager;
import org.allbinary.layer.LayerManagerNoDebug;

/**
 *
 * @author User
 */
public class GameInputLayerManager extends LayerManager {

    public GameInputLayerManager() {
        super(LayerManagerNoDebug.getInstance());
        //super(LayerManagerLogging.getInstance());
    }

}