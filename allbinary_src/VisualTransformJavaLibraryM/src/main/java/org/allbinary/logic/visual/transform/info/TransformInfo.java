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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.OutputTypeData;
import org.allbinary.logic.io.path.AbFilePath;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.logic.control.crypt.Encoder;
import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfig;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigAndManipulatorFactory;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigInterface;
import org.allbinary.logic.visual.transform.info.template.TransformInfoTemplateData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;
import org.allbinary.logic.visual.transform.info.data.TransformInfoDataData;
import org.allbinary.logic.visual.transform.info.viewObject.TransformInfoObjectData;
import org.allbinary.string.CommonStrings;

public class TransformInfo implements TransformInfoInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();

    private boolean isChild = false;
    private String name;
    private String storeName;
    private String objectFileName;
    private Object object;
    private String objectConfigFileName;
    private TransformInfoObjectConfigInterface objectConfigInterface;
    private String template;
    private String templateFileName;
    private String dataFileName;
    private String data = StringUtil.getInstance().EMPTY_STRING;

    //Views come in different flavors:
    //* Complete View = A complete view is a compound or component view
    //* Component Views = A partial view for a given request like user profile
    //  can use other component views, classes that implement the DomNodeInterface,
    //  and view fragments to make a complete component view
    //* Compound View = Uses two or more other views to creat a complete view
    //* View Fragments - A portion of a view that is a static template with static data
    //* View Wrapper - An imcomplete view that contains one or more Wrapped or Fragment
    //views for a component or compound view to make a complete view
    //* Wrapped View = An incomplete view that wraps one or more other views to make a view
    //Note: The views below are wrapper template views
    //Expects Absolute URIs for files - should change file strings to Ab File Path(s)
    public TransformInfo() throws Exception
    {
    }

    public TransformInfo(String name) throws Exception
    {
        this.setName(name);
    }

    public TransformInfo(String name,
        String objectFileName, String objectConfigFileName,
        String templateFileName, String dataFileName) throws Exception
    {
        this.setName(name);
        this.setObjectFile(objectFileName);
        this.setObjectConfigFile(objectConfigFileName);
        this.setTemplateFile(templateFileName);
        this.setDataFile(dataFileName);
    }

    public void override(HashMap hashMap) throws Exception
    {
    	TransformInfoData transformInfoData = 
    		TransformInfoData.getInstance();
    	
        StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        String aName = (String) hashMap.get(transformInfoData.NAME);
        if (!stringValidationUtil.isEmpty(aName))
        {
            this.name = aName;
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("TransformInfo properties overridden for: ");
            stringBuffer.append(this.getName());
            stringBuffer.append("\n properties: ");
            stringBuffer.append(hashMap);

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "override(HashMap hashMap)"));
        }
        
        String aStoreName = (String) hashMap.get(StoreFrontData.getInstance().NAME);
        if (!stringValidationUtil.isEmpty(aStoreName))
        {
            this.storeName = aStoreName;
        }

        String aObjectFileName = (String) hashMap.get(transformInfoData.OBJECTFILENAME);
        if (!stringValidationUtil.isEmpty(aObjectFileName))
        {
            this.objectFileName = aObjectFileName;
        }

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(URLGLOBALS.getMainPath());
        stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
        stringBuffer.append(this.storeName);
        stringBuffer.append(AbPathData.getInstance().SEPARATOR);

        AbPath fileAbPath = new AbPath(stringBuffer.toString());

        String aObject = (String) hashMap.get(transformInfoData.OBJECT);
        if (!stringValidationUtil.isEmpty(aObject))
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                LogUtil.put(LogFactory.getInstance(
                    "TransformInfo override view Object for: " + this.getName(),
                    this, "override(HashMap hashMap)"));
            }

            String fileDataString = new CryptFileReader(
                TransformInfoObjectData.getInstance().UNCRYPTED_EXTENSION,
                TransformInfoObjectData.getInstance().ENCRYPTED_EXTENSION).get(
                new AbPath(fileAbPath.toString(), aObject));

            if (fileDataString != null)
            {
                this.object = fileDataString;
            }
        }

        if (!stringValidationUtil.isEmpty((String) hashMap.get(transformInfoData.OBJECTCONFIG)))
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                LogUtil.put(LogFactory.getInstance(
                    "TransformInfo override ObjectConfig for: " + this.getName(),
                    this, "override(HashMap hashMap)"));
            }
            this.setObjectConfig((String) hashMap.get(transformInfoData.OBJECTCONFIG));
        }

        String objectConfigFileName =
            (String) hashMap.get(transformInfoData.OBJECTCONFIGFILENAME);
        if (!stringValidationUtil.isEmpty(objectConfigFileName))
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                LogUtil.put(LogFactory.getInstance(
                    "TransformInfo override ObjectConfig File for: " + this.getName(),
                    this, "override(HashMap hashMap)"));
            }
            this.setObjectConfigFile(objectConfigFileName);
        }

        String type = (String) hashMap.get(OutputTypeData.getInstance().NAME);
        if (!stringValidationUtil.isEmpty(type))
        {
            //TWB - for inserting after
            if (this.getObjectConfigInterface() == null)
            {
                this.setObjectConfigInterface(new TransformInfoObjectConfig(this));
            }

            this.getObjectConfigInterface().setOutputTypeName(type);
        }

        String aTemplateFileName =
            (String) hashMap.get(transformInfoData.TEMPLATEFILENAME);
        if (!stringValidationUtil.isEmpty(aTemplateFileName))
        {
            this.templateFileName = aTemplateFileName;
        }

        String aTemplate = (String) hashMap.get(transformInfoData.TEMPLATE);
        if (!stringValidationUtil.isEmpty(aTemplate))
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                LogUtil.put(LogFactory.getInstance(
                    "TransformInfo override Template for: " + this.getName(),
                    this, "override(HashMap hashMap)"));
            }

            String fileDataString = new CryptFileReader(
                TransformInfoTemplateData.getInstance().UNCRYPTED_EXTENSION,
                TransformInfoTemplateData.getInstance().ENCRYPTED_EXTENSION).get(
                new AbPath(fileAbPath.toString(), aTemplate));

            if (!stringValidationUtil.isEmpty(fileDataString))
            {
                this.template = fileDataString;
            }
        }

        String aDataFileName = (String) hashMap.get(transformInfoData.DATAFILENAME);
        if (!stringValidationUtil.isEmpty(aDataFileName))
        {
            this.dataFileName = aDataFileName;
        }

        String aData = (String) hashMap.get(transformInfoData.DATA);
        if (!stringValidationUtil.isEmpty(aData))
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                LogUtil.put(LogFactory.getInstance(
                    "TransformInfo override Data for: " + this.getName(),
                    this, "override(HashMap hashMap)"));
            }

            String fileDataString = new CryptFileReader(
                TransformInfoDataData.getInstance().UNCRYPTED_EXTENSION,
                TransformInfoDataData.getInstance().ENCRYPTED_EXTENSION).get(
                new AbPath(fileAbPath.toString(), aData));

            if (!stringValidationUtil.isEmpty(fileDataString))
            {
                this.data = fileDataString;
            }
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            stringBuffer = new StringBuffer();

            stringBuffer.append("Name: ");
            stringBuffer.append(aName);
            stringBuffer.append("\nTemp Object File: ");
            stringBuffer.append(aObject);
            stringBuffer.append("\nTemp Object Config File: ");
            stringBuffer.append(this.getObjectConfigFilePath());
            stringBuffer.append("\nTemp Template File: ");
            stringBuffer.append(aTemplate);
            stringBuffer.append("\nTemp Data File: ");
            stringBuffer.append(aData);
            stringBuffer.append("\nStore Name: ");
            stringBuffer.append(aStoreName);
            stringBuffer.append("\nObjectFile: ");
            stringBuffer.append(this.getObjectFile());
            stringBuffer.append("->TransformInfo");

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(),
                this, "TransformInfoInterface(HashMap)"));
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY))
        {
            LogUtil.put(LogFactory.getInstance(this.log(), this, "override"));
        }
    }

    public String log()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("Name: ");
        stringBuffer.append(this.name);
        stringBuffer.append("\nObject File: ");
        stringBuffer.append(this.objectFileName);
        stringBuffer.append("\nObject Config File Path: ");
        stringBuffer.append(this.getObjectConfigFile());
        stringBuffer.append("\nTemplate File: ");
        stringBuffer.append(this.templateFileName);
        stringBuffer.append("\nData File: ");
        stringBuffer.append(this.dataFileName);
        stringBuffer.append("\nStore Name: ");
        stringBuffer.append(this.storeName);
        stringBuffer.append("\n");
        stringBuffer.append(this.getObjectFile());
        stringBuffer.append("->TransformInfo");
        stringBuffer.append("TransformInfoInterface(HashMap)");

        return stringBuffer.toString();
    }

    public void setName(String value)
    {
        this.name = value;
    }

    public void setStoreName(String value)
    {
        this.storeName = value;
    }

    public String getStoreName()
    {
        return this.storeName;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }

    public void setObjectFile(String value)
    {
        this.objectFileName = value;
    }

    public void setTemplate(String value)
    {
        this.template = value;
    }

    public void setTemplateFile(String value)
    {
        this.templateFileName = value;
    }

    protected void setObjectConfigFileName(String value) throws Exception
    {
        this.objectConfigFileName = value;
    }

    protected void setObjectConfigFile(String value) throws Exception
    {
        this.objectConfigFileName = value;
        this.setObjectConfigInterface(
            TransformInfoObjectConfigAndManipulatorFactory.getInstance().getInstance(
                abeClientInformation,this, this.getObjectConfigFilePath()));
    }

    protected void setObjectConfig(String value) throws Exception
    {
        Document document = DomDocumentHelper.create(value);
        this.setObjectConfigInterface(
            TransformInfoObjectConfigAndManipulatorFactory.getInstance().getInstance(abeClientInformation, this, document));
    }

    public void setDataFile(String value)
    {
        this.dataFileName = value;
    }

    public void setData(String value)
    {
        this.data = value;
    }

    public String getName()
    {
        return this.name;
    }

    public Object getObject()
    {
        return this.object;
    }

    public String getObjectFile()
    {
        return this.objectFileName;
    }

    public String getTemplate()
    {
        return this.template;
    }

    public String getTemplateFile()
    {
        return this.templateFileName;
    }

    //Expects Absolute URI
    public AbPath getTemplateFilePath() throws Exception
    {
        return new AbFilePath(this.getTemplateFile());
    }

    //Expects Absolute URI
    public AbPath getObjectConfigFilePath() throws Exception
    {
        return new AbFilePath(this.getObjectConfigFile());
    }

    //Expects Absolute URI
    public AbPath getDataFilePath() throws Exception
    {
        return new AbFilePath(this.getDataFile());
    }

    public String getObjectConfigFile()
    {
        return this.objectConfigFileName;
    }

    private String getData()
    {
        StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        String dataFileString = this.getDataFile();

        if (!stringValidationUtil.isEmpty(dataFileString))
        {
            try
            {
                String fileData = new CryptFileReader(
                    TransformInfoDataData.getInstance().UNCRYPTED_EXTENSION,
                    TransformInfoDataData.getInstance().ENCRYPTED_EXTENSION).get(
                    		this.getDataFilePath());

                if (!stringValidationUtil.isEmpty(this.data))
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                    {
                        LogUtil.put(LogFactory.getInstance(
                            "Data File overriding existing data: " + this.getDataFilePath(), this, "getData()"));
                    }
                }

                this.data = fileData;
            } catch (Exception e)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
                {
                    LogUtil.put(LogFactory.getInstance("Could Not Load Data from: " + this.getDataFile(), this, "getData()", e));
                }
            }
        }

        try
        {
        	if (!stringValidationUtil.isEmpty(this.data))
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
                {
                    LogUtil.put(LogFactory.getInstance(
                        "Data: " + DomDocumentHelper.toString(DomDocumentHelper.create(data)), this, "getData()"));
                }
            } else
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("Name: ");
                    stringBuffer.append(this.name);

                    stringBuffer.append("\nEmpty Data For: ");
                    stringBuffer.append(dataFileString);
                    stringBuffer.append("\nPath: ");
                    stringBuffer.append(this.getDataFilePath().toFileSystemString());


                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
                    {
                    	LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "getData()"));
                    }
                    //ForcedLogUtil.log(stringBuffer.toString(), this);
                }
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
            {
                LogUtil.put(LogFactory.getInstance(
                    "Could Not Preview Data: " + this.data, this, "getData()", e));
            }
        }

        return this.data;
    }

    //return the needed node from the data
    public Document getDataDocument() throws Exception
    {
    	String localData = this.getData();
    	
        StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
    	if (stringValidationUtil.isEmpty(localData))
    	{
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
            {
            	LogUtil.put(LogFactory.getInstance("No Data So Creating New Document", this, "getDataDocument()"));
            }
    		
    		return DomDocumentHelper.create();
    	}
    	else
    	{
            Document document = DomDocumentHelper.create(localData);
            return document;
    	}    	
    }

    public String getDataFile()
    {
        return this.dataFileName;
    }

    public HashMap toHashMap()
    {
    	TransformInfoData transformInfoData = 
    		TransformInfoData.getInstance();
    	
        HashMap hashMap = new HashMap();
        hashMap.put(transformInfoData.NAME, this.name);
        hashMap.put(StoreFrontData.getInstance().NAME, this.storeName);
        hashMap.put(transformInfoData.OBJECTFILENAME, this.objectFileName);
        hashMap.put(transformInfoData.OBJECT, this.object);
        hashMap.put(transformInfoData.OBJECTCONFIGFILENAME, this.getObjectConfigFile());

        //TWB - Encoder for GAE for XML using JIQL
        //hashMap.put(transformInfoData.OBJECTCONFIG, this.getObjectConfigInterface().toString());
        hashMap.put(transformInfoData.OBJECTCONFIG, Encoder.encode(this.getObjectConfigInterface().toString().getBytes()));

        hashMap.put(transformInfoData.TEMPLATEFILENAME, this.templateFileName);
        hashMap.put(transformInfoData.TEMPLATE, this.template);
        hashMap.put(transformInfoData.DATAFILENAME, this.dataFileName);
        hashMap.put(transformInfoData.DATA, Encoder.encode(this.data.getBytes()));

        Calendar calendar = Calendar.getInstance();
        String time = new String(new Long(calendar.getTimeInMillis()).toString());
        hashMap.put(EntryData.getInstance().LASTMODIFIED, time);

        //add time
        return hashMap;
    }

    public java.util.Vector toVector()
    {
        Vector vector = new Vector();
        vector.add(this.name);
        vector.add(this.storeName);
        vector.add(this.objectFileName);
        vector.add(this.object);
        vector.add(this.getObjectConfigFile());

        //TWB - Encoder for GAE for XML using JIQL
        //vector.add(this.getObjectConfigInterface().toString());
        vector.add(Encoder.encode(this.getObjectConfigInterface().toString().getBytes()));

        vector.add(this.templateFileName);
        vector.add(this.template);
        vector.add(this.dataFileName);
        vector.add(Encoder.encode(this.data.getBytes()));

        Calendar calendar = Calendar.getInstance();
        String time = new String(new Long(calendar.getTimeInMillis()).toString());
        vector.add(time);
        vector.add(time);

        return vector;
    }

    public Object getKey()
    {
        return (Object) this.getName();
    }

    public void setChild()
    {
        this.isChild = true;
    }

    public boolean isChild()
    {
        return this.isChild;
    }

    public TransformInfoObjectConfigInterface getObjectConfigInterface()
    {
        return objectConfigInterface;
    }

    public void setObjectConfigInterface(TransformInfoObjectConfigInterface objectConfigInterface)
    {
        this.objectConfigInterface = objectConfigInterface;
    }
}
