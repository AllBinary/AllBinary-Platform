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
package org.allbinary.game.input.mapping.event;

import org.allbinary.logic.util.event.AllBinaryEventObject;

/**
 *
 * @author User
 */
public class NullInputMappingEventListener implements InputMappingEventListenerInterface {
    
    public static final NullInputMappingEventListener NULL_INPUT_MAPPING_EVENT_LISTENER = new NullInputMappingEventListener();
    
    @Override
    public void onInputMappingEvent(final InputMappingEvent inputMappingEvent) throws Exception {
        
    }
    
    @Override
    public void onEvent(final AllBinaryEventObject eventObject) {
        
    }

}
