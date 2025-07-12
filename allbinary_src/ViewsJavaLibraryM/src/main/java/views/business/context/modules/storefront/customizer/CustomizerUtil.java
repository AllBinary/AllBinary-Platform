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

import java.util.Vector;

import org.allbinary.data.tables.transform.info.TransformInfoEntity;
import org.allbinary.data.tables.transform.info.TransformInfoEntityBuilder;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.visual.transform.info.TransformInfo;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigInterface;
import org.w3c.dom.Document;

public class CustomizerUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final CustomizerUtil instance = new CustomizerUtil();

    public static CustomizerUtil getInstance() {
        return instance;
    }

    private CustomizerUtil()
    {
    }

    public TransformInfoInterface getTransformInfoInterfaceToCustomize(
    		TransformInfoInterface customizerTransformInfoInterface,
    		TransformInfoInterface transformInfoInterface) throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("View Name: " + customizerTransformInfoInterface.getName(), this, "getTransformInfoInterfaceToCustomize()");
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
    public Vector getTransformInfoObjectConfigComponentNodesToCustomize(
    		TransformInfoInterface customizerTransformInfoInterface,
    		TransformInfoInterface transformInfoInterface) throws Exception
        {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("View Name: " + customizerTransformInfoInterface.getName(), this, "getTransformInfoObjectConfigComponentNodesToCustomize()");
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

        return this.getTransformInfoObjectConfigComponentNodes(
            customizedTransformInfoInterface);
    }

    //get all components in objectconfig
    public Vector getTransformInfoObjectConfigComponentNodes(
        TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("View Name: " + transformInfoInterface.getName(), this, "getTransformInfoObjectConfigComponentNodes()");
        }

        TransformInfoObjectConfigInterface transformInfoObjectConfigInterface =
            transformInfoInterface.getObjectConfigInterface();

        return transformInfoObjectConfigInterface.getTransforms();
    }

    public Vector getTransformInfoObjectConfigGroupComponentNodes(
        TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("View Name: " + transformInfoInterface.getName(), this, "getTransformInfoObjectConfigComponentNodes()");
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
    public void insert(
        TransformInfoInterface transformInfoInterface,
        DomNodeInterface domNodeInterface)
        throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("View Name: " + transformInfoInterface.getName(), this, "insert()");
        }

        TransformInfoEntity transformInfoEntityInterface =
        	TransformInfoEntityBuilder.getInstance();

        Vector allViewsToBeModified =
            this.getTransformInfoObjectConfigGroupComponentNodes(transformInfoInterface);

        //get the view xml/data that will replace the old xml/data
        Document document = DomDocumentHelper.create();
        document.appendChild(domNodeInterface.toXmlNode(document));
        String documentString = DomDocumentHelper.toString(document);

        StringMaker stringBuffer = new StringMaker();

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            stringBuffer.append("Total Views For Modification: ");
            stringBuffer.append(allViewsToBeModified.size());
            stringBuffer.append(" New Xml/Data: ");
            stringBuffer.append(documentString);

            logUtil.put(stringBuffer.toString(), this, "insert()");
        }

        //Adds data to self - currently used for heading not sure if this is a good idea
        //CustomizerUtil.getInstance().write(transformInfoInterface, documentString);
        transformInfoInterface.setData(documentString);

        final int size = allViewsToBeModified.size();
        for(int index = 0; index < size; index++)
        {
            TransformInfo transformInfo = (TransformInfo) allViewsToBeModified.get(index);

            String viewNameOfViewToBeModified = transformInfo.getName();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(transformInfoInterface.getName());
                stringBuffer.append(" is modifying view: ");
                stringBuffer.append(viewNameOfViewToBeModified);

                logUtil.put(stringBuffer.toString(), this, "insert()");
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
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append(transformInfoInterface.getName());
                    stringBuffer.append(" is unable to add data to view: ");
                    stringBuffer.append(viewNameOfViewToBeModified);
                    stringBuffer.append(" since it does not exist");

                    logUtil.put(stringBuffer.toString(), this, "insert()");
                }
            } else
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    logUtil.put(transformInfoInterface.getName() + " is adding data to view: " + viewNameOfViewToBeModified, this, "insert()");
                }

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append(viewNameOfViewToBeModified);
                    stringBuffer.append(" is changing data in ");
                    stringBuffer.append(specifiedTransformInfoInterface.getDataFilePath().toString());
                    stringBuffer.append(" to the following data:\n");
                    stringBuffer.append(documentString);

                    logUtil.put(stringBuffer.toString(), this, "insert()");
                }

                //save xml data to specified view
                this.write(specifiedTransformInfoInterface, documentString);
            }
        }
    }

    public void write(TransformInfoInterface specifiedTransformInfoInterface, String documentString) throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("View Name: " + specifiedTransformInfoInterface.getName(), this, "write()");
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
    public Document getViewDataForComponentsInObjectConfig(
        TransformInfoInterface transformInfoInterface) throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("View Name: " + transformInfoInterface.getName(), this, "getViewDataForComponentsInObjectConfig()");
        }

        //Are they all TRANSFORM_INFOS_GROUP?
        //Vector allViewsToBeModified = CustomizerUtil.getInstance().getTransformInfoObjectConfigComponentNodes(transformInfoInterface);
        TransformInfoObjectConfigInterface transformInfoObjectConfigInterface =
            transformInfoInterface.getObjectConfigInterface();

        Vector allViewsToBeModified = transformInfoObjectConfigInterface.getGroupTransforms();

        TransformInfoEntity transformInfoEntityInterface =
        	TransformInfoEntityBuilder.getInstance();
        
        final int size = allViewsToBeModified.size();
        for(int index = 0; index < size; index++)
        {
            TransformInfo nextTransformInfoInterface = 
                (TransformInfo) allViewsToBeModified.get(index);

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
