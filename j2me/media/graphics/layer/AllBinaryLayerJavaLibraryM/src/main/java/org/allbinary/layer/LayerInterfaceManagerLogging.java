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

package org.allbinary.layer;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author User
 */
public class LayerInterfaceManagerLogging extends LayerInterfaceManagerLoggingBase {
    
    private static final LayerInterfaceManagerLogging instance = new LayerInterfaceManagerLogging();

    /**
     * @return the instance
     */
    public static LayerInterfaceManagerLogging getInstance() {
        return instance;
    }
    
    private final StringBuilder stringBuilder = new StringBuilder();
    
    private final String APPEND = "append";
    private final String APPEND_ = "append: ";
    private final String _AT_ = " at: ";
    private final String REMOVE_ = "Remove: ";
    private final String REMOVE = "remove";

    public void append(final AllBinaryLayer layerInterface) throws Exception
    {
        stringBuilder.delete(0, stringBuilder.length());
        LogUtil.put(LogFactory.getInstance(stringBuilder.append(APPEND_).append(layerInterface).toString(), this, APPEND));
    }
    
    public void append(final AllBinaryLayer layerInterface, final int index) {
        stringBuilder.delete(0, stringBuilder.length());
        LogUtil.put(LogFactory.getInstance(stringBuilder.append(APPEND_).append(layerInterface).append(_AT_).append(index).toString(), this, APPEND));
    }

    public void remove(final AllBinaryLayer layerInterface) {
        stringBuilder.delete(0, stringBuilder.length());
        LogUtil.put(LogFactory.getInstance(stringBuilder.append(REMOVE_).append(layerInterface).toString(), this, REMOVE));
    }

    public void remove(final AllBinaryLayer layerInterface, final boolean result) {
        if(!result) {
            LogUtil.put(LogFactory.getInstance(layerInterface.toString(), this, REMOVE, new Exception()));
        }
        
        stringBuilder.delete(0, stringBuilder.length());
        LogUtil.put(LogFactory.getInstance(stringBuilder.append(REMOVE_).append(result).toString(), this, REMOVE));
    }
    
}