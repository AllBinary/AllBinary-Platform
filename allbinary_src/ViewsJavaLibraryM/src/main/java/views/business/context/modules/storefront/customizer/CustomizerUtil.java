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
package views.business.context.modules.storefront.customizer;

import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Document;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.transform.info.TransformInfoEntity;
import org.allbinary.data.tables.transform.info.TransformInfoEntityBuilder;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.visual.transform.info.TransformInfo;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigInterface;

public class CustomizerUtil
{
    private static final CustomizerUtil instance = new CustomizerUtil();

    private CustomizerUtil()
    {
    }

    public static TransformInfoInterface getTransformInfoInterfaceToCustomize(
    		TransformInfoInterface customizerTransformInfoInterface,
    		TransformInfoInterface transformInfoInterface) throws Exception
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("View Name: " + customizerTransformInfoInterface.getName(), instance, "getTransformInfoInterfaceToCustomize()"));
        }

        TransformInfoEntity transformInfoEntityInterface =
        	TransformInfoEntityBuilder.getInstance();

        String viewName = transformInfoInterface.getName();

        TransformInfoHttpInterface httpTransformInfoInterface =
            (TransformInfoHttpInterface) customizerTransformInfoInterface;

        TransformInfoInterface customizedTransformInfoInterface =
            transformInfoEntityInterface.get(viewName,
            httpTransformInfoInterface.getPropertiesHashMap(),
            httpTransformInfoInterface.getPageContext());

        return customizedTransformInfoInterface;
    }

    //get all of the components for a component that is modified by the customizer view
    public static Vector getTransformInfoObjectConfigComponentNodesToCustomize(
    		TransformInfoInterface customizerTransformInfoInterface,
    		TransformInfoInterface transformInfoInterface) throws Exception
        {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("View Name: " + customizerTransformInfoInterface.getName(), instance, "getTransformInfoObjectConfigComponentNodesToCustomize()"));
        }

        TransformInfoEntity transformInfoEntityInterface =
        	TransformInfoEntityBuilder.getInstance();

        String viewName = transformInfoInterface.getName();

        TransformInfoHttpInterface httpTransformInfoInterface =
            (TransformInfoHttpInterface) customizerTransformInfoInterface;

        TransformInfoInterface customizedTransformInfoInterface =
            transformInfoEntityInterface.get(viewName,
            httpTransformInfoInterface.getPropertiesHashMap(),
            httpTransformInfoInterface.getPageContext());

        return CustomizerUtil.getTransformInfoObjectConfigComponentNodes(
            customizedTransformInfoInterface);
    }

    //get all components in objectconfig
    public static Vector getTransformInfoObjectConfigComponentNodes(
        TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), instance, "getTransformInfoObjectConfigComponentNodes()"));
        }

        TransformInfoObjectConfigInterface transformInfoObjectConfigInterface =
            transformInfoInterface.getObjectConfigInterface();

        return transformInfoObjectConfigInterface.getTransforms();
    }

    public static Vector getTransformInfoObjectConfigGroupComponentNodes(
        TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), instance, "getTransformInfoObjectConfigComponentNodes()"));
        }

        TransformInfoObjectConfigInterface transformInfoObjectConfigInterface =
            transformInfoInterface.getObjectConfigInterface();

        return transformInfoObjectConfigInterface.getGroupTransforms();
    }

    //currently data is set and the viewinfo is updated in the db instead of updating existing data
    //(new implementation may allow multilple customizers to manipulate a single view)
    //This would require an update function separate from the insert/add
    //This modifies the data/xml for each of the views specified in the ObjectConfig
    //of the transformInfoInterface passed
    //The new data/xml is generated by the domNodeInterface
    public static void insert(
        TransformInfoInterface transformInfoInterface,
        DomNodeInterface domNodeInterface)
        throws Exception
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), instance, "insert()"));
        }

        TransformInfoEntity transformInfoEntityInterface =
        	TransformInfoEntityBuilder.getInstance();

        Vector allViewsToBeModified =
            CustomizerUtil.getTransformInfoObjectConfigGroupComponentNodes(transformInfoInterface);

        //get the view xml/data that will replace the old xml/data
        Document document = DomDocumentHelper.create();
        document.appendChild(domNodeInterface.toXmlNode(document));
        String documentString = DomDocumentHelper.toString(document);

        StringBuffer stringBuffer = new StringBuffer();

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            stringBuffer.append("Total Views For Modification: ");
            stringBuffer.append(allViewsToBeModified.size());
            stringBuffer.append(" New Xml/Data: ");
            stringBuffer.append(documentString);

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "insert()"));
        }

        //Adds data to self - currently used for heading not sure if this is a good idea
        //CustomizerUtil.write(transformInfoInterface, documentString);
        transformInfoInterface.setData(documentString);

        Iterator iter = allViewsToBeModified.iterator();
        while (iter.hasNext())
        {
            TransformInfo transformInfo = (TransformInfo) iter.next();

            String viewNameOfViewToBeModified = transformInfo.getName();

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(transformInfoInterface.getName());
                stringBuffer.append(" is modifying view: ");
                stringBuffer.append(viewNameOfViewToBeModified);

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "insert()"));
            }

            TransformInfoHttpInterface httpTransformInfoInterface =
                (TransformInfoHttpInterface) transformInfoInterface;

            TransformInfoInterface specifiedTransformInfoInterface =
                transformInfoEntityInterface.get(
                viewNameOfViewToBeModified,
                httpTransformInfoInterface.getPropertiesHashMap(),
                httpTransformInfoInterface.getPageContext());

            if (specifiedTransformInfoInterface == null)
            {
                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append(transformInfoInterface.getName());
                    stringBuffer.append(" is unable to add data to view: ");
                    stringBuffer.append(viewNameOfViewToBeModified);
                    stringBuffer.append(" since it does not exist");

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "insert()"));
                }
            } else
            {
                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
                {
                    LogUtil.put(LogFactory.getInstance(transformInfoInterface.getName() + " is adding data to view: " + viewNameOfViewToBeModified, instance, "insert()"));
                }

                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append(viewNameOfViewToBeModified);
                    stringBuffer.append(" is changing data in ");
                    stringBuffer.append(specifiedTransformInfoInterface.getDataFilePath());
                    stringBuffer.append(" to the following data:\n");
                    stringBuffer.append(documentString);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "insert()"));
                }

                //save xml data to specified view
                CustomizerUtil.write(specifiedTransformInfoInterface, documentString);
            }
        }
    }

    public static void write(TransformInfoInterface specifiedTransformInfoInterface, String documentString) throws Exception
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("View Name: " + specifiedTransformInfoInterface.getName(), instance, "write()"));
        }

        TransformInfoEntity transformInfoEntityInterface =
        	TransformInfoEntityBuilder.getInstance();
        
        //if file is specified save to file
      /*
        if(specifiedTransformInfoInterface.getDataFile()!=null && specifiedTransformInfoInterface.getDataFile().compareTo(null")!=0)
        {
        File dataFile = new File(specifiedTransformInfoInterface.getDataFilePath());
        dataFile.createNewFile();

        AbCrypt abCrypt = new AbCrypt(KeySpecFactory.DESEDE, AbKeys.getClassKey());
        byte[] encrypted = abCrypt.encrypt(documentString.getBytes());

        FileOutputStream idFile = new FileOutputStream(dataFile);
        DataOutputStream idOutData = new DataOutputStream(idFile);
        idOutData.write(encrypted);
        }
        //otherwise update viewinfo with new data
        else
         */
        //{
        //should encrypt
        specifiedTransformInfoInterface.setData(documentString);
        //specifiedTransformInfoInterface.setData();
        transformInfoEntityInterface.update(specifiedTransformInfoInterface.toHashMap());
        //}
    }

    //retrieve data xml from view specified in objectconfig
    //Note: Multiple views contain the same xml with the same custom nodes
    //that are all updated at once with the CustomizerUtils.write
    public static Document getViewDataForComponentsInObjectConfig(
        TransformInfoInterface transformInfoInterface) throws Exception
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), instance, "getViewDataForComponentsInObjectConfig()"));
        }

        //Are they all TRANSFORM_INFOS_GROUP?
        //Vector allViewsToBeModified = CustomizerUtil.getTransformInfoObjectConfigComponentNodes(transformInfoInterface);
        TransformInfoObjectConfigInterface transformInfoObjectConfigInterface =
            transformInfoInterface.getObjectConfigInterface();

        Vector allViewsToBeModified = transformInfoObjectConfigInterface.getGroupTransforms();

        TransformInfoEntity transformInfoEntityInterface =
        	TransformInfoEntityBuilder.getInstance();
        
        Iterator iter = allViewsToBeModified.iterator();
        while (iter.hasNext())
        {
        	TransformInfo nextTransformInfoInterface =
             (TransformInfo) iter.next();        	 

            String viewNameOfViewToBeModified =
            	nextTransformInfoInterface.getName();

            TransformInfoHttpInterface httpTransformInfoInterface =
                (TransformInfoHttpInterface) transformInfoInterface;

            TransformInfoInterface specifiedTransformInfoInterface =
                transformInfoEntityInterface.get(viewNameOfViewToBeModified,
                httpTransformInfoInterface.getPropertiesHashMap(),
                httpTransformInfoInterface.getPageContext());

            return specifiedTransformInfoInterface.getDataDocument();
        }
        throw new Exception("No Components Specified in ObjectConfig");
    }
}
