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
package org.allbinary.logic.visual.transform.info.objectConfig;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.regex.replace.Replace;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.logic.visual.transform.info.TransformInfoHttp;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.template.TransformTemplateFactory;
import org.allbinary.logic.visual.transform.template.TransformTemplateInterface;
import org.allbinary.logic.visual.transform.template.util.TransformTemplateCustomizerUtil;
import org.w3c.dom.Document;

import java.util.HashMap;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.visual.transform.info.RootTransformInfoData;
import org.allbinary.logic.visual.transform.info.TransformInfoData;

public class GenericStoreTransformInfoObjectConfig extends TransformInfoObjectConfig
{
    protected final AbeClientInformationInterface abeClientInformation;

    public GenericStoreTransformInfoObjectConfig(final AbeClientInformationInterface abeClientInformation, final TransformInfoInterface transformInfoInterface) throws Exception
    {
        super(transformInfoInterface);
        
        this.abeClientInformation = abeClientInformation;

        //this.setDocument(this.generate(this.getDocument()));
    }

    public GenericStoreTransformInfoObjectConfig(final AbeClientInformationInterface abeClientInformation, final TransformInfoInterface transformInfoInterface, final Document document) throws Exception
    {
        super(transformInfoInterface, document);

        this.abeClientInformation = abeClientInformation;

        this.setDocument(this.generate(this.toXmlDoc()));
    }

    public GenericStoreTransformInfoObjectConfig(final AbeClientInformationInterface abeClientInformation, final TransformInfoInterface transformInfoInterface, final String name, final String type) throws Exception
    {
        super(transformInfoInterface, name, type);
        
        this.abeClientInformation = abeClientInformation;
        
        this.setDocument(this.generate(this.toXmlDoc()));
    }

    //TWB - Hack when adding GAE support
    //"<?xml version="1.0" encoding="UTF-8" standalone="no"?><OBJECTCONFIG_NAME/>"
    //private final int HACK = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx".length();
    protected Document generate(final Document objectConfigDocument) throws Exception
    {
        //final String docString = DomDocumentHelper.toString(objectConfigDocument);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
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
        final TransformInfoHttp transformInfoHttpStoreInterface =
            (TransformInfoHttp) this.getTransformInfoInterface();

        final String objectConfigDocumentString = DomDocumentHelper.toString(objectConfigDocument);

        final HashMap replaceHashMap = this.createReplaceHashMap(
            transformInfoHttpStoreInterface, objectConfigDocumentString);

        return this.generate(objectConfigDocumentString, replaceHashMap);
    }

    private HashMap createReplaceHashMap(
        
        final TransformInfoHttp transformInfoHttpStoreInterface, 
        final String objectConfigDocumentString) throws Exception
    {
        String storeName = transformInfoHttpStoreInterface.getStoreName();
        //String viewName = this.getTransformInfoInterface().getName();
                
        final HashMap hashMap = this.createHashMap(
            transformInfoHttpStoreInterface, objectConfigDocumentString);
        
        final TransformInfoObjectConfigData transformInfoObjectConfigData = 
        	TransformInfoObjectConfigData.getInstance();
        
        hashMap.put(transformInfoObjectConfigData.VARKEY + StoreFrontData.getInstance().NAME, storeName);
        //hashMap.put(TransformInfoObjectConfigData.VARKEY +
        // TransformInfoObjectConfigData.OWNER, viewName);

        //for customizer form object config - major hack should be in seperate class
        //created by objectconfigfactory or better yet use multiple objectconfig files

        final String pageName =
            TransformTemplateCustomizerUtil.getInstance().getPageNameHack(
            		this.getTransformInfoInterface().getName(), storeName);

        hashMap.put(transformInfoObjectConfigData.VARKEY + TransformInfoData.getInstance().PARTIAL, pageName);
        
        return hashMap;
    }

    //Only add to hash map if needed
    //could cause infinite loop is templateNameKey is in root objectconfig
    private HashMap createHashMap(
        
        final TransformInfoHttp transformInfoHttpStoreInterface,
        final String objectConfigDocumentString)
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

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
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
                TransformTemplateFactory.getInstance().getInstance(abeClientInformation,
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

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Final ObjectConfig: "
                + DomDocumentHelper.toString(newObjectConfigDocument), this, "generate()"));
        }

        return newObjectConfigDocument;
    }
}
