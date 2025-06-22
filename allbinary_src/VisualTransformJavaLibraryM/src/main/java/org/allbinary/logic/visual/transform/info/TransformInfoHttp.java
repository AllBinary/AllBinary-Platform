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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.AbContext;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.logic.communication.http.request.RequestParams;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfig;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.sql.AbSqlData;

public class TransformInfoHttp extends TransformInfo
    implements TransformInfoHttpInterface
{
    private final AbContext abContext;

    //TWB - for inserting transforms into database
    public TransformInfoHttp(
    		HashMap propertiesHashMap,
            PageContext pageContext, boolean crud) throws Exception
    {
        super();

        this.abContext = new AbContext(propertiesHashMap, pageContext);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance(
                "Properties HashMap: " + propertiesHashMap.toString(), 
                this, "Constructor(HashMap, PageContext, boolean)"));
        }

        this.override(propertiesHashMap);
    }

    public TransformInfoHttp(
    		HttpServletRequest request,
            HashMap propertiesHashMap,
            PageContext pageContext) throws Exception
    {
        super();

        this.abContext = new AbContext(propertiesHashMap, pageContext);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance(
                "Properties HashMap: " + propertiesHashMap.toString(), 
                this, "Constructor(HttpServletRequest, HashMap, PageContext)"));
        }

        this.set(new RequestParams((HttpServletRequest) request).toHashMap());
        this.override(propertiesHashMap);
    }

    public TransformInfoHttp(HashMap databaseHashMap,
        HashMap propertiesHashMap,
        PageContext pageContext) throws Exception
    {
        super();

        this.abContext = new AbContext(propertiesHashMap, pageContext);
        //super(propertiesHashMap, pageContext);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance(
                "Properties HashMap: " + propertiesHashMap.toString(), 
                this, "Constructor(HashMap, HashMap, PageContext)"));
        }

        this.set(databaseHashMap);
    }

    public TransformInfoHttp(HashMap propertiesHashMap, PageContext pageContext) throws Exception
    {
        super();

        this.abContext = new AbContext(propertiesHashMap, pageContext);
        //super(propertiesHashMap, pageContext);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance(
                "Properties HashMap: " + propertiesHashMap.toString(), 
                this, "Constructor(HashMap, PageContext)"));
        }

        this.set(propertiesHashMap);
    }

    public TransformInfoHttp(TransformInfoHttp parentViewOfThisTransformInfoInterface) throws Exception
    {
        super();

        this.abContext = new AbContext(
        		parentViewOfThisTransformInfoInterface.getPropertiesHashMap(), 
        		parentViewOfThisTransformInfoInterface.getPageContext());

        //this.abContext = null;

        //super(parentViewOfThisTransformInfoInterface.getPropertiesHashMap(),
        //parentViewOfThisTransformInfoInterface.getPageContext());
    }

    private void set(HashMap hashMap) throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Properties HashMap: " + hashMap.toString(), this, "set()"));
        }

    	TransformInfoData transformInfoData = 
    		TransformInfoData.getInstance();
        
        this.setName((String) hashMap.get(transformInfoData.NAME));
        this.setStoreName((String) hashMap.get(StoreFrontData.getInstance().NAME));

        this.setObjectFile((String) hashMap.get(transformInfoData.OBJECTFILENAME));
        this.setObject((String) hashMap.get(transformInfoData.OBJECT));

        StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        String objectConfigString = (String) hashMap.get(transformInfoData.OBJECTCONFIG);
        if (stringValidationUtil.isValidRequired(objectConfigString, 10, AbSqlData.MAXBLOB))
        {
            this.setObjectConfig(objectConfigString);
        } else
        {
            this.setObjectConfig(new TransformInfoObjectConfig(this).toString());
        }

        if (!stringValidationUtil.isEmpty((String) hashMap.get(transformInfoData.OBJECTCONFIGFILENAME)))
        {
            this.setObjectConfigFile((String) hashMap.get(transformInfoData.OBJECTCONFIGFILENAME));
        }

        this.setTemplateFile((String) hashMap.get(transformInfoData.TEMPLATEFILENAME));
        this.setTemplate((String) hashMap.get(transformInfoData.TEMPLATE));

        this.setDataFile((String) hashMap.get(transformInfoData.DATAFILENAME));
        this.setData((String) hashMap.get(transformInfoData.DATA));

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY))
        {
            LogUtil.put(LogFactory.getInstance(this.log(), this, "set()"));
        }
    }

    public WeblisketSessionInterface getWeblisketSession()
    {
        return this.abContext.getWeblisketSession();
    }

    public PageContext getPageContext()
    {
        return this.abContext.getPageContext();
    }

    public HashMap getPropertiesHashMap()
    {
        return this.abContext.getPropertiesHashMap();
    }

    public AbPath getTemplateFilePath() throws Exception
    {
        return new AbPath(URLGLOBALS.getMainPath() + FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH, this.getTemplateFile());
    }

    public AbPath getObjectConfigFilePath() throws Exception
    {
        return new AbPath(URLGLOBALS.getMainPath() + FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH, this.getObjectConfigFile());
    }

    public AbPath getDataFilePath() throws Exception
    {
        return new AbPath(URLGLOBALS.getMainPath() + FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH, this.getDataFile());
    }
}
