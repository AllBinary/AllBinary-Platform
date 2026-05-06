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
package org.allbinary.graphics.form.item;

public class CustomItemUtil
{
    private static final CustomItemUtil instance = new CustomItemUtil();

    public static CustomItemUtil getInstance()
    {
        return CustomItemUtil.instance;
    }
    
    public final ABCustomItem[] CUSTOM_ITEM_ARRAY = new ABCustomItem[0];
}
