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
package org.allbinary.logic.communication.log.config.type;

/**
 *
 * @author User
 */
public class LogConfigTypeFactory {
    
    private static final LogConfigTypeFactory instance = new LogConfigTypeFactory();

    /**
     * @return the instance
     */
    public static LogConfigTypeFactory getInstance() {
        return instance;
    }
    
   private final String NO_DESCRIPTION = "No Description";
   
   public final LogConfigType OS = new LogConfigType("Operating System",NO_DESCRIPTION);
   public final LogConfigType FACTORYERROR = new LogConfigType("Factory Error",NO_DESCRIPTION);
   
   private LogConfigTypeFactory() {
       LogConfigTypes.LOGGING.add(OS);
       LogConfigTypes.LOGGING.add(FACTORYERROR);
   }

}
