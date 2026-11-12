/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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
package org.allbinary.game.layer;

public class LayerCaptionResources
{
    private static final LayerCaptionResources instance = new LayerCaptionResources();
    
    public static LayerCaptionResources getInstance()
    {
        return LayerCaptionResources.instance;
    }
    
    private LayerCaptionResources()
    {
        
    }
    
    public final String RESOURCE = "/unit_message_bubbled_box.png";
}
