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
package org.allbinary.logic.communication.log.config.type;

import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;

/**
 *
 * @author user
 */
public class LogConfigTypeFactory
{

    private static final LogConfigTypeFactory instance = new LogConfigTypeFactory();

    /**
     * @return the instance
     */
    public static LogConfigTypeFactory getInstance()
    {
        return instance;
    }

    private final String NO_DESCRIPTION = "No Description";

    public final LogConfigType INIT_SERVER = new LogConfigType("Init Server", NO_DESCRIPTION);
    public final LogConfigType LOBBY_SERVER = new LogConfigType("Lobby Server", NO_DESCRIPTION);
    public final LogConfigType GAME_SERVER = new LogConfigType("Game Server", NO_DESCRIPTION);
    public final LogConfigType GAME_SIMULATOR = new LogConfigType("Game Simulator", NO_DESCRIPTION);
    public final LogConfigType CUSTOM_TYPES = new LogConfigType("Custom Types", NO_DESCRIPTION);

    private LogConfigTypeFactory()
    {
        LogConfigTypes.LOGGING.add(this.INIT_SERVER);
        //LogConfigTypes.LOGGING.add(this.GAME_SIMULATOR);
    }
}
