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
package allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.processors.output;

import java.util.Iterator;
import java.util.Vector;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

import allbinary.input.media.image.InputImageIOInterfaceFactory;
import allbinary.input.media.image.InputImageType;
import allbinary.media.image.io.ImageIOInterface;

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
        LogUtil.put(new Log(
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
