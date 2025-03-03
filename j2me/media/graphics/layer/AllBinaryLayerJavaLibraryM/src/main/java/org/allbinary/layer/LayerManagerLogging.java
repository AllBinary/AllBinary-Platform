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

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;

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
    
    private final StringMaker stringBuilder = new StringMaker();
    
    private final String APPEND = "append";
    private final String APPEND_ = " append: ";
    private final String _AT_ = " at: ";
    private final String ATTEMPT_REMOVE_ = " Remove Attempt: ";
    private final String REMOVE_ = " Remove: ";
    private final String REMOVE = "remove";
    private final String DID_NOT_REMOVE = " Did not remove: ";
    private final String CLEAR = " Clear List";

    private static boolean removeFailed = false;
    
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
            LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(ATTEMPT_REMOVE_).append(StringUtil.getInstance().toString(layerInterface)).toString(), this, REMOVE));
        } else {
            stringBuilder.delete(0, stringBuilder.length());
            LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(ATTEMPT_REMOVE_).append(layerInterface.getName()).toString(), this, REMOVE));
        }
    }

    public void remove(final LayerManager layerManager, final AllBinaryLayer layerInterface, final boolean result) {
        
        if(layerInterface == null) {
            stringBuilder.delete(0, stringBuilder.length());
            LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(REMOVE_).append(StringUtil.getInstance().toString(layerInterface)).toString(), this, REMOVE));
        } else if(result) {
            if (this.removeFailed) {
                stringBuilder.delete(0, stringBuilder.length());
                LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(REMOVE_).append(layerInterface.getName()).toString(), this, REMOVE));
            }
            
            //if (this.removeFailed) {
                //this.log(layerManager);
            //}

        } else {
            stringBuilder.delete(0, stringBuilder.length());
            //LogUtil.put(LogFactory.getInstance(stringBuilder.append(DID_NOT_REMOVE).append(layerInterface.toString()).toString(), this, REMOVE, new Exception()));
            LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(DID_NOT_REMOVE).append(layerInterface.getName()).toString(), this, REMOVE));
            
            //this.log(layerManager);
            removeFailed = true;
        }
        
    }
    
    private void log(final LayerManager layerManager) {
        stringBuilder.delete(0, stringBuilder.length());
        final int size = layerManager.getSize();
        final CommonSeps commonSeps = CommonSeps.getInstance();
        stringBuilder.append(Integer.toHexString(layerManager.hashCode())).append(commonSeps.COLON_SEP);
        stringBuilder.append(size).append(commonSeps.COLON_SEP);

        for (int index = 0; index < size; index++) { 
            stringBuilder.append(((AllBinaryLayer) layerManager.getLayerAt(index)).getName()).append(commonSeps.COMMA);
        }
        LogUtil.put(LogFactory.getInstance(stringBuilder.toString(), this, REMOVE));
    }

    public void clear() {
        stringBuilder.delete(0, stringBuilder.length());
        LogUtil.put(LogFactory.getInstance(stringBuilder.append(this.hashCode()).append(CLEAR).toString(), this, CLEAR));
    }
}