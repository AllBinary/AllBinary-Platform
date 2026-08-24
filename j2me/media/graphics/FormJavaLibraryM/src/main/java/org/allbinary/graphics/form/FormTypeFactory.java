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
package org.allbinary.graphics.form;

import jsinterop.annotations.JsType;

import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class FormTypeFactory
{
    private static final FormTypeFactory instance = new FormTypeFactory();
    
    @JsMethod
    public static FormTypeFactory getInstance()
    {
        return FormTypeFactory.instance;
    }
    
    @JsProperty
    public final String UNK = "Unknown FormType";
    @JsProperty
    public final FormType NULL_FORM_TYPE = new FormType(StringUtil.getInstance().NULL_STRING);
    @JsProperty
    public final FormType TEMP_HORIZONTAL_FORM = new FormType("TempHorizontal");
    @JsProperty
    public final FormType HORIZONTAL_FORM = new FormType("Horizontal");
    @JsProperty
    public final FormType VERTICAL_CENTER_FORM = new FormType("Vertical");
    
    @JsMethod
    public FormType getFormType()
    {
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        
        FormType formType;

        if (displayInfo.isPortrait())
        {
            formType = this.VERTICAL_CENTER_FORM;
        } else
        {
            formType = this.HORIZONTAL_FORM;
        }

        return formType;
    }
}
