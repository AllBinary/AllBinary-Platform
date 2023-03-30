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
public class LayerManagerLogging extends LayerManagerLoggingBase {
    
    private static final LayerManagerLogging instance = new LayerManagerLogging();

    /**
     * @return the instance
     */
    public static LayerManagerLogging getInstance() {
        return instance;
    }
    
    private final StringBuilder stringBuilder = new StringBuilder();
    
    private final String APPEND = "append";
    private final String APPEND_ = " append: ";
    private final String _AT_ = " at: ";
    private final String ATTEMPT_REMOVE_ = " Remove Attempt: ";
    private final String REMOVE_ = " Remove: ";
    private final String REMOVE = "remove";
    private final String DID_NOT_REMOVE = " Did not remove: ";
    private final String CLEAR = " Clear List";

    public void append(final AllBinaryLayer layerInterface) throws Exception
    {
        stringBuilder.delete(0, stringBuilder.length());
        LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(APPEND_).append(layerInterface.getName()).toString(), this, APPEND));
    }
    
    public void append(final AllBinaryLayer layerInterface, final int index) {
        stringBuilder.delete(0, stringBuilder.length());
        LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(APPEND_).append(layerInterface.getName()).append(_AT_).append(index).toString(), this, APPEND));
    }

    public void remove(final AllBinaryLayer layerInterface) {
        
        if(layerInterface == null) {
            stringBuilder.delete(0, stringBuilder.length());
            LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(ATTEMPT_REMOVE_).append(layerInterface).toString(), this, REMOVE));
        } else {
            stringBuilder.delete(0, stringBuilder.length());
            LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(ATTEMPT_REMOVE_).append(layerInterface.getName()).toString(), this, REMOVE));
        }
    }

    public void remove(final AllBinaryLayer layerInterface, final boolean result) {
        
        if(layerInterface == null || result) {
            stringBuilder.delete(0, stringBuilder.length());
            LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(REMOVE_).append(result).toString(), this, REMOVE));
        } else {
            stringBuilder.delete(0, stringBuilder.length());
            //LogUtil.put(LogFactory.getInstance(stringBuilder.append(DID_NOT_REMOVE).append(layerInterface.toString()).toString(), this, REMOVE, new Exception()));
            LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(DID_NOT_REMOVE).append(layerInterface.getName()).toString(), this, REMOVE));
        }
    }
    
    public void clear() {
        stringBuilder.delete(0, stringBuilder.length());
        LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(CLEAR).toString(), this, CLEAR));
    }
}