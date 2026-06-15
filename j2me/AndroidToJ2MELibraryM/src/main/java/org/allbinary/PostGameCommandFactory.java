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
package org.allbinary;

import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.feature.MultiPlayerGameFeatureFactory;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.logic.system.os.GenericOperatingSystem;
import org.allbinary.logic.system.os.OperatingSystemFactory;

/**
 *
 * @author User
 */
public class PostGameCommandFactory {
    
    private static final PostGameCommandFactory instance = new PostGameCommandFactory();


    /**
     * @return the instance
     */
    public static PostGameCommandFactory getInstance() {
        return PostGameCommandFactory.instance;
    }
    
    public void addCommands(MyCanvas canvas) {
        
        final GenericOperatingSystem operatingSystemInterface = OperatingSystemFactory.getInstance().getOperatingSystemInstance();
        final GameCommandsFactory gameCommandsFactory = GameCommandsFactory.getInstance();
        if(!operatingSystemInterface.isOverScan()) {
            canvas.addCommand(gameCommandsFactory.TOGGLE_KEYBOARD);
        }

        MultiPlayerGameFeatureFactory.getInstance().addCommands(canvas);        
    }
}
