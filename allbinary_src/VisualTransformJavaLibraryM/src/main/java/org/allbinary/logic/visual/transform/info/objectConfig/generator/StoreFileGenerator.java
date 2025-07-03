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
package org.allbinary.logic.visual.transform.info.objectConfig.generator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.InputOutputTypeData;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileUtil;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.regex.replace.Replace;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpStoreInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.visual.transform.info.TransformInfosData;
import org.allbinary.string.CommonStrings;

public class StoreFileGenerator 
    extends TransformInfoObjectConfigGenerator
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final String output;
    private final AbPath fileAbPath;
    private final AbFile file;

    public StoreFileGenerator(TransformInfoInterface transformInfoInterface) throws Exception
    {
        TransformInfoHttpInterface httpTransformInfoInterface =
            (TransformInfoHttpInterface) transformInfoInterface;

        WeblisketSession weblisketSession = new WeblisketSession(
            httpTransformInfoInterface.getPropertiesHashMap(),
            httpTransformInfoInterface.getPageContext());

        //File name selection process
        //1. Use requested file name
        //2. Use objectconfig specified filename
        //3. Use root view name as default output file name

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(URLGLOBALS.getWebappPath());
        stringBuffer.append(weblisketSession.getStoreName());
        stringBuffer.append(AbPathData.getInstance().SEPARATOR);

        AbPath abPath = new AbPath(stringBuffer.toString());

        //1. Use requested file name output
        String fileName =
            (String) httpTransformInfoInterface.getPropertiesHashMap().get(
            InputOutputTypeData.getInstance().FILE);

        StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if (stringValidationUtil.isEmpty(fileName))
        {
            //2. Use objectconfig specified output - no imp

            TransformInfoHttpStoreInterface transformInfoHttpStoreInterface =
                (TransformInfoHttpStoreInterface) transformInfoInterface;

            //3. Use root view name as default output file name
            fileName = transformInfoInterface.getName().substring(
                transformInfoHttpStoreInterface.getStoreName().length() + 1);

            //& Replace TRANSFORM_COMPONENTS_PREVIEW TRANSFORM_COMPONENTS_SMALL_PREVIEW
            HashMap outputMappingHashMap = new HashMap();
            outputMappingHashMap.put(TransformInfosData.getInstance().PREVIEW, "Preview");
            outputMappingHashMap.put(TransformInfosData.getInstance().SMALL_PREVIEW, "SmallPreview");
            outputMappingHashMap.put(CommonSeps.getInstance().SPACE, StringUtil.getInstance().EMPTY_STRING);
            Replace replace = new Replace(outputMappingHashMap);
            fileName = replace.all(fileName);
        }

        if (stringValidationUtil.isEmpty(fileName))
        {
            throw new Exception("TransformInfoObjectConfigGenerator FileName Not Specified");
        }

        //Output selection process
        //1. Use requested output
        //2. Use objectconfig specified output
        //3. Use jsp as default output
        String tempOutput =
            (String) httpTransformInfoInterface.getPropertiesHashMap().get(
            InputOutputTypeData.getInstance().NAME);

        if (stringValidationUtil.isEmpty(tempOutput))
        {
        	tempOutput = InputOutputTypeData.getInstance().DEFAULT;
        }

        this.output = tempOutput;

        if (stringValidationUtil.isEmpty(this.output))
        {
            throw new Exception("TransformInfoObjectConfigGenerator Output=extension Not Specified");
        }

        stringBuffer = new StringBuffer();

        stringBuffer.append(fileName);
        stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
        stringBuffer.append(this.output);

        abPath = new AbPath(abPath.toString(), stringBuffer.toString());

        this.fileAbPath = abPath;
        
        this.file = new AbFile(this.fileAbPath);
        
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance("File: " + this.fileAbPath.toString(), this, this.commonStrings.CONSTRUCTOR));
        }
    }

    public String process(String input) throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Processing", this, commonStrings.PROCESS));
        }

        if (!this.file.exists())
        {
        	this.file.createNewFile();
        }

        if (this.file.exists())
        {
        	InputStream inputStream = new ByteArrayInputStream(input.getBytes());
            FileUtil.getInstance().write(inputStream, this.file);
        } else
        {
            throw new Exception("Could Not Create: " + this.fileAbPath.toString());
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Processed", this, commonStrings.PROCESS));
        }

        return StringUtil.getInstance().EMPTY_STRING;
    }
}
