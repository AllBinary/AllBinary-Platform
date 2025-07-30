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

import org.allbinary.graphics.displayable.DisplayInfoSingleton;

public class FormTypeFactory
{
    private static final FormTypeFactory instance = new FormTypeFactory();
    
    public static FormTypeFactory getInstance()
    {
        return instance;
    }
    
    public final String UNK = "Unknown FormType";
    public final FormType NULL_FORM_TYPE = new FormType();
    public final FormType TEMP_HORIZONTAL_FORM = new FormType();
    public final FormType HORIZONTAL_FORM = new FormType();
    public final FormType VERTICAL_CENTER_FORM = new FormType();
    
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
