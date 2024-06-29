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

import java.awt.Canvas;

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
        throw new RuntimeException();
    }
    
    public void addCommands(Canvas canvas) {
    }
}
