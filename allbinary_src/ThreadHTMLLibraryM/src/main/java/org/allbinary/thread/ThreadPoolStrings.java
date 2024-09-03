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
package org.allbinary.thread;

/**
 *
 * @author User
 */
public class ThreadPoolStrings {

    private static final ThreadPoolStrings instance = new ThreadPoolStrings();
    
    /**
     * @return the instance
     */
    public static ThreadPoolStrings getInstance() {
        return instance;
    }
    
    public final String ADD_TASK = "addTask";
    public final String START_TASK = "Started Task: ";
    public final String COMPLETE_TASK = "Completed Task: ";

}
