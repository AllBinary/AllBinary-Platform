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
package org.allbinary.input.automation.module.actions.script.condition.processors.output;

import java.util.Iterator;
import java.util.Vector;

import org.allbinary.input.media.image.InputImageIOInterfaceFactory;
import org.allbinary.input.media.image.InputImageType;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.image.io.ImageIOInterface;

public class ImageActionScriptOutputProcessor
{
    private ImageActionScriptOutputProcessor()
    {
    }
    
    public static void process(
        ImageActionScriptOutputInterface imageActionScriptOutputInterface,
        Long frame)
        throws Exception
    {
        LogUtil.put(LogFactory.getInstance(
            "Start - Processing at: " + imageActionScriptOutputInterface.toString(),
            "ImageActionScriptOutputProcessor", "process"));

        ImageTypes imageTypes =
            imageActionScriptOutputInterface.getImageTypes();
        Vector vector = imageTypes.getVector();
        
        Iterator iterator = vector.iterator();
        while(iterator.hasNext())
        {
            InputImageType imageType = (InputImageType) iterator.next();

            ImageIOInterface imageIOInterface =
                InputImageIOInterfaceFactory.getInstance(imageType);
            
            imageIOInterface.save(frame);
        }
        
    }
}
