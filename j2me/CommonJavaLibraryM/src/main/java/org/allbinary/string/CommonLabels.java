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

package org.allbinary.string;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class CommonLabels {

    private static final CommonLabels instance = new CommonLabels();

    /**
     * @return the instance
     */
    @JsMethod
    public static CommonLabels getInstance() {
        return CommonLabels.instance;
    }
    
    @JsProperty
    public final String WIDTH_LABEL = " Width: ";
    @JsProperty
    public final String HEIGHT_LABEL = " Height: ";
    
    @JsProperty
    public final String END_LABEL = "End: ";
    @JsProperty
    public final String TOTAL_LABEL = "Total: ";
    @JsProperty
    public final String INDEX_LABEL = "index: ";
    @JsProperty
    public final String START_LABEL = "Start: ";
    @JsProperty
    public final String COMMAND_LABEL = "Command: ";
    @JsProperty
    public final String NAME_LABEL = "Name: ";
    
    @JsProperty
    public final String START = this.START_LABEL;
    @JsProperty
    public final String ELAPSED = " Elapsed: ";
    @JsProperty
    public final String CURRENT = " Current: ";
    @JsProperty
    public final String ITEM_LABEL = "Item: ";
    
    @JsProperty
    public final String RESULT_ = "Result: ";
    
    @JsProperty
    public final String COLON_SEP = ": ";
    
}