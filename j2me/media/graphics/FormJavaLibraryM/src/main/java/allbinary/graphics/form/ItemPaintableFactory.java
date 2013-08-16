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
package allbinary.graphics.form;

public class ItemPaintableFactory extends ItemPaintable
{   
    private static final ItemPaintableFactory instance = 
        new ItemPaintableFactory();
    
    public ItemPaintable getInstance(PaintableForm paintableForm)
    throws Exception
    {
        return instance;
    }

    public static ItemPaintable getInstance()
    {
        return instance;
    }
}
