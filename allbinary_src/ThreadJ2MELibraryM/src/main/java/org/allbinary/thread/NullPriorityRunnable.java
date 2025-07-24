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
package org.allbinary.thread;

/**
 *
 * @author User
 */
public class NullPriorityRunnable implements PriorityRunnable {

    public int getPriority() {
        return 0;
    }

    public boolean isDone() {
        return true;
    }

    public void reset() {

    }

    @Override
    public void run() {

    }

}
