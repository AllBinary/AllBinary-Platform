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

import org.allbinary.TsUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;

/**
 *
 * @author User
 */
public class LayerManagerLogging extends LayerManagerLoggingBase {
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    private static final LayerManagerLogging instance = new LayerManagerLogging();

    /**
     * @return the instance
     */
    public static LayerManagerLogging getInstance() {
        return LayerManagerLogging.instance;
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
    
    @Override
    public void append(final AllBinaryLayer layerInterface) throws Exception
    {
        this.stringBuilder.delete(0, this.stringBuilder.length());
        this.logUtil.putF(this.stringBuilder.appendint(TsUtil.getInstance().hashCode(this)).append(this.APPEND_).append(layerInterface.getName()).toString(), this, this.APPEND);
    }
    
    @Override
    public void appendAt(final AllBinaryLayer layerInterface, final int index) {
        this.stringBuilder.delete(0, stringBuilder.length());
        this.logUtil.putF(this.stringBuilder.appendint(TsUtil.getInstance().hashCode(this)).append(this.APPEND_).append(layerInterface.getName()).append(this._AT_).appendint(index).toString(), this, this.APPEND);
    }
    
    @Override
    public void remove(final AllBinaryLayer layerInterface) {
        
        if(layerInterface == null) {
            this.stringBuilder.delete(0, this.stringBuilder.length());
            this.logUtil.putF(this.stringBuilder.appendint(TsUtil.getInstance().hashCode(this)).append(this.ATTEMPT_REMOVE_).append(StringUtil.getInstance().toString(layerInterface)).toString(), this, this.REMOVE);
        } else {
            this.stringBuilder.delete(0, this.stringBuilder.length());
            this.logUtil.putF(this.stringBuilder.appendint(TsUtil.getInstance().hashCode(this)).append(this.ATTEMPT_REMOVE_).append(layerInterface.getName()).toString(), this, this.REMOVE);
        }
    }

    @Override
    public void removeResult(final LayerManager layerManager, final AllBinaryLayer layerInterface, final boolean result) {
        
        if(layerInterface == null) {
            this.stringBuilder.delete(0, this.stringBuilder.length());
            this.logUtil.putF(this.stringBuilder.appendint(TsUtil.getInstance().hashCode(this)).append(this.REMOVE_).append(StringUtil.getInstance().toString(layerInterface)).toString(), this, this.REMOVE);
        } else if(result) {
            if (LayerManagerLogging.removeFailed) {
                this.stringBuilder.delete(0, this.stringBuilder.length());
                this.logUtil.putF(this.stringBuilder.appendint(TsUtil.getInstance().hashCode(this)).append(this.REMOVE_).append(layerInterface.getName()).toString(), this, this.REMOVE);
            }
            
            //if (this.removeFailed) {
                //this.log(layerManager);
            //}

        } else {
            this.stringBuilder.delete(0, this.stringBuilder.length());
            //this.logUtil.put(stringBuilder.append(DID_NOT_REMOVE).append(layerInterface.toString()).toString(), this, REMOVE, new Exception());
            this.logUtil.putF(this.stringBuilder.appendint(TsUtil.getInstance().hashCode(this)).append(this.DID_NOT_REMOVE).append(layerInterface.getName()).toString(), this, this.REMOVE);
            
            //this.log(layerManager);
            LayerManagerLogging.removeFailed = true;
        }
        
    }
    
    private void log(final LayerManager layerManager) {
        this.stringBuilder.delete(0, stringBuilder.length());
        final int size = layerManager.getSize();
        final CommonSeps commonSeps = CommonSeps.getInstance();
        this.stringBuilder.append(Integer.toHexString(TsUtil.getInstance().hashCode(layerManager))).append(commonSeps.COLON_SEP);
        this.stringBuilder.appendint(size).append(commonSeps.COLON_SEP);

        AllBinaryLayer allBinaryLayer;
        for (int index = 0; index < size; index++) { 
            allBinaryLayer = ((AllBinaryLayer) layerManager.getLayerAt(index));
            this.stringBuilder.append(allBinaryLayer.getName()).append(commonSeps.COMMA);
        }
        this.logUtil.putF(this.stringBuilder.toString(), this, this.REMOVE);
    }

    @Override
    public void clear() {
        this.stringBuilder.delete(0, stringBuilder.length());
        this.logUtil.putF(this.stringBuilder.appendint(TsUtil.getInstance().hashCode(this)).append(this.CLEAR).toString(), this, this.CLEAR);
    }
}