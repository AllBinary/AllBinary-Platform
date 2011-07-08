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
package allbinary.logic.visual.transform.info.objectConfig.generator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import abcs.globals.URLGLOBALS;
import abcs.logic.basic.io.InputOutputTypeData;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.FileUtil;
import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.basic.string.regex.replace.Replace;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.communication.http.request.session.WeblisketSession;
import allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import allbinary.logic.visual.transform.info.TransformInfoHttpStoreInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import allbinary.logic.visual.transform.info.TransformInfosData;

public class StoreFileGenerator 
    implements TransformInfoObjectConfigGeneratorInterface
{
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
        
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("File: " + this.fileAbPath.toString(), this, "Constructor()"));
        }
    }

    public String process(String input) throws Exception
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Processing", this, "process()"));
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

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Processed", this, "process()"));
        }

        return StringUtil.getInstance().EMPTY_STRING;
    }
}
