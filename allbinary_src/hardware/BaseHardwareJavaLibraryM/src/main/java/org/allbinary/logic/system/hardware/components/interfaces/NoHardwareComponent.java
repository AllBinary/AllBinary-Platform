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
package org.allbinary.logic.system.hardware.components.interfaces;

import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class NoHardwareComponent implements HardwareComponentInterface {
    
    private static final NoHardwareComponent instance = new NoHardwareComponent();

    /**
     * @return the instance
     */
    public static NoHardwareComponent getInstance() {
        return instance;
    }
    
    @Override
    public boolean compareTo(HardwareComponentInterface componentInterface) {
        if(this == componentInterface) {
            return true;
        }
        return false;
    }
    
    public String toString() {
        return StringUtil.getInstance().NULL_STRING;
    }
    
}
