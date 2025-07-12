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
package org.allbinary.logic.visual.transform.info;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class TransformInfoHttpContext extends TransformInfoHttp
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    ///TWB - the propertiesHashmap has properties set in the tag
    //It can still be used even though the name of the transformInfo is
    //the only thing required to be in properties hashmap
    //but it is currently used to specify output, type, and file that could be included
    //in a special type of viewinfo
    public TransformInfoHttpContext(
        HashMap databaseHashMap,
        HashMap propertiesHashMap,
        PageContext pageContext)
        throws Exception
    {
        super(databaseHashMap, propertiesHashMap, pageContext);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append("Database HashMap: ");
            stringBuffer.append(databaseHashMap.toString());
            stringBuffer.append("\nProperties HashMap: ");
            stringBuffer.append(propertiesHashMap.toString());

            logUtil.put(stringBuffer.toString(), this, this.commonStrings.CONSTRUCTOR);
        }
        this.setStoreName(StringUtil.getInstance().EMPTY_STRING);
    }

    public TransformInfoHttpContext(HashMap propertiesHashMap, PageContext pageContext) throws Exception
    {
        super(propertiesHashMap, pageContext);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("Properties HashMap: " + propertiesHashMap.toString(), this, this.commonStrings.CONSTRUCTOR);
        }

        this.setStoreName(StringUtil.getInstance().EMPTY_STRING);
    }

    private String getPath() throws Exception
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(URLGLOBALS.getMainPath());
        stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
        stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().INSTALLPATH);
        stringBuffer.append(this.getStoreName());
        stringBuffer.append(AbPathData.getInstance().SEPARATOR);

        return stringBuffer.toString();
    }

    public AbPath getTemplateFilePath() throws Exception
    {

        return new AbPath(this.getPath(), this.getTemplateFile());
    }

    public AbPath getObjectConfigFilePath() throws Exception
    {
        return new AbPath(this.getPath(), this.getObjectConfigFile());
    }

    public AbPath getDataFilePath() throws Exception
    {
        return new AbPath(this.getPath(), this.getDataFile());
    }
}
