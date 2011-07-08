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
package allbinary.logic.visual.transform.info.objectConfig;

import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.basic.string.regex.replace.Replace;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.modules.storefront.StoreFrontData;
import allbinary.logic.visual.transform.info.RootTransformInfoData;
import allbinary.logic.visual.transform.info.TransformInfoData;
import allbinary.logic.visual.transform.info.TransformInfoHttp;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import allbinary.logic.visual.transform.template.TransformTemplateFactory;
import allbinary.logic.visual.transform.template.TransformTemplateInterface;
import allbinary.logic.visual.transform.template.util.TransformTemplateCustomizerUtil;
import org.w3c.dom.Document;

import java.util.HashMap;

public class GenericStoreTransformInfoObjectConfig extends TransformInfoObjectConfig
{

    public GenericStoreTransformInfoObjectConfig(TransformInfoInterface transformInfoInterface) throws Exception
    {
        super(transformInfoInterface);
        
        //this.setDocument(this.generate(this.getDocument()));
    }

    public GenericStoreTransformInfoObjectConfig(TransformInfoInterface transformInfoInterface, Document document) throws Exception
    {
        super(transformInfoInterface, document);
                
        this.setDocument(this.generate(this.toXmlDoc()));
    }

    public GenericStoreTransformInfoObjectConfig(TransformInfoInterface transformInfoInterface, String name, String type) throws Exception
    {
        super(transformInfoInterface, name, type);
        this.setDocument(this.generate(this.toXmlDoc()));
    }

    //TWB - Hack when adding GAE support
    //"<?xml version="1.0" encoding="UTF-8" standalone="no"?><OBJECTCONFIG_NAME/>"
    //private final int HACK = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx".length();
    protected Document generate(Document objectConfigDocument) throws Exception
    {
        //final String docString = DomDocumentHelper.toString(objectConfigDocument);

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
        	  StringBuffer stringBuffer = new StringBuffer();
        	  
        	  stringBuffer.append("TransformInfo: ");
        	  
        	  if(this.getTransformInfoInterface() != null)
        	  {
        		  stringBuffer.append(this.getTransformInfoInterface().getName());
        	  }
        	  else
        	  {
        		  stringBuffer.append("No Owner!?#@");
        	  }

        	  //stringBuffer.append("Initial ObjectConfig: ");
        	  //stringBuffer.append(docString);

              LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "generate()"));
        }

        /*
        if(docString.length() < HACK + 1)
        {
        return objectConfigDocument;
        }
         *
         */

        //TWB - GAE update - Should TransformInfo have store and should I create TransformInfoHttpStore instead?
        //TransformInfoHttpStore transformInfoHttpStoreInterface =
        //(TransformInfoHttpStore)
        TransformInfoHttp transformInfoHttpStoreInterface =
            (TransformInfoHttp) this.getTransformInfoInterface();

        String objectConfigDocumentString = DomDocumentHelper.toString(objectConfigDocument);

        HashMap replaceHashMap = this.createReplaceHashMap(
            transformInfoHttpStoreInterface,
            objectConfigDocumentString);

        return this.generate(objectConfigDocumentString, replaceHashMap);
    }

    private HashMap createReplaceHashMap(
        TransformInfoHttp transformInfoHttpStoreInterface, 
        String objectConfigDocumentString) throws Exception
    {
        String storeName = transformInfoHttpStoreInterface.getStoreName();
        //String viewName = this.getTransformInfoInterface().getName();
                
        HashMap hashMap = this.createHashMap(
            transformInfoHttpStoreInterface,
            objectConfigDocumentString);
        
        TransformInfoObjectConfigData transformInfoObjectConfigData = 
        	TransformInfoObjectConfigData.getInstance();
        
        hashMap.put(transformInfoObjectConfigData.VARKEY + StoreFrontData.getInstance().NAME, storeName);
        //hashMap.put(TransformInfoObjectConfigData.VARKEY +
        // TransformInfoObjectConfigData.OWNER, viewName);

        //for customizer form object config - major hack should be in seperate class
        //created by objectconfigfactory or better yet use multiple objectconfig files

        String pageName =
            TransformTemplateCustomizerUtil.getInstance().getPageNameHack(
            		this.getTransformInfoInterface().getName(), storeName);

        hashMap.put(transformInfoObjectConfigData.VARKEY + TransformInfoData.getInstance().PARTIAL, pageName);
        
        return hashMap;
    }

    //Only add to hash map if needed
    //could cause infinite loop is templateNameKey is in root objectconfig
    private HashMap createHashMap(
        TransformInfoHttp transformInfoHttpStoreInterface,
        String objectConfigDocumentString)
        throws Exception
    {
        HashMap hashMap = new HashMap();

        String storeName = transformInfoHttpStoreInterface.getStoreName();
        //String viewName = this.getTransformInfoInterface().getName();

        //Template will force compound template to use an override template like preview
        //This does not fix situations where a preview page would be generated
        //by itself unless it is using a objectconfig that specifies preview as the parent
        HashMap propertiesHashMap = transformInfoHttpStoreInterface.getPropertiesHashMap();

        String templateNameOverride = StringUtil.getInstance().getInstance(
            (String) propertiesHashMap.get(TransformInfoData.getInstance().PARTIAL));

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("TemplateNameOverride: " + templateNameOverride, this, "generate()"));
        }

        StringBuffer templateNameStringBuffer = new StringBuffer();

        templateNameStringBuffer.append(storeName);
        templateNameStringBuffer.append(templateNameOverride);
        templateNameStringBuffer.append(CommonSeps.getInstance().SPACE);
        templateNameStringBuffer.append(RootTransformInfoData.NAME);

        String templateNameKey = TransformInfoObjectConfigData.getInstance().VARKEY + TransformInfoData.getInstance().OWNER;

        if (objectConfigDocumentString.indexOf(templateNameKey) != -1)
        {
            TransformTemplateInterface templateInterface =
                TransformTemplateFactory.getInstance(
                templateNameStringBuffer.toString(),
                transformInfoHttpStoreInterface.getPropertiesHashMap(),
                transformInfoHttpStoreInterface.getPageContext());

            String selectedTemplate = templateInterface.getName();
            hashMap.put(templateNameKey, selectedTemplate);
        }

        return hashMap;
    }

    private Document generate(
        String objectConfigDocumentString,
        HashMap hashMap)
        throws Exception
    {
        Replace replace = new Replace(hashMap);

        Document newObjectConfigDocument = DomDocumentHelper.create(
        		replace.all(objectConfigDocumentString));

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Final ObjectConfig: "
                + DomDocumentHelper.toString(newObjectConfigDocument), this, "generate()"));
        }

        return newObjectConfigDocument;
    }
}
