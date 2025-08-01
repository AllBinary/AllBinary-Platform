/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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

import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class NullPlayerGameInputComposite implements PlayerGameInputCompositeInterface {
    
    public static final NullPlayerGameInputComposite NULL_PLAYER_GAME_INPUT_COMPOSITE = new NullPlayerGameInputComposite();
    
    @Override
    public String getName() {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    @Override
    public void processInput(AllBinaryLayerManager layerManager) throws Exception {
        
    }
   
    @Override
    public void initInputProcessors() {
        
    }

    @Override
    public PlayerGameInput getPlayerGameInput() {
        return NoPlayerGameInput.getInstance();
    }
        
}
