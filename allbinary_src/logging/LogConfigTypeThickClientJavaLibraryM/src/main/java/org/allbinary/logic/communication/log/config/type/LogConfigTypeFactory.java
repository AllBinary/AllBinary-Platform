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
   
   public final LogConfigType FILE = new LogConfigType("File",NO_DESCRIPTION);
   public final LogConfigType FILEERROR = new LogConfigType("File Error",NO_DESCRIPTION);

   public final LogConfigType IDLOGGING = new LogConfigType("ID Logging",NO_DESCRIPTION);
   
   public final LogConfigType REPLACE = new LogConfigType("Replace", NO_DESCRIPTION);
   public final LogConfigType REPLACE_INFO = new LogConfigType("Replace Info", NO_DESCRIPTION);
   public final LogConfigType REPLACEERROR = new LogConfigType("Replace Error", NO_DESCRIPTION);   
   
}
