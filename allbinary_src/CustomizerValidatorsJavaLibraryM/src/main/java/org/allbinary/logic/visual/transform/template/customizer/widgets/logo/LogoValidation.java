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
package org.allbinary.logic.visual.transform.template.customizer.widgets.logo;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.io.file.FileData;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.http.request.HttpRequestUtil;
import org.allbinary.logic.control.validate.Validation;
import org.apache.commons.fileupload.FileItem;
import org.allbinary.logic.string.StringValidationUtil;

public class LogoValidation extends Validation implements DomNodeInterface
{
    
    private FileItem logoFileItem;
    private AbPath logoAbPath;
    private String logoFile;
    private boolean isFile;

    public LogoValidation()
    {
        this.isFile = false;

        this.logoAbPath = null;
        this.logoFile = null;
    }

    public LogoValidation(Node node) throws Exception
    {
        this.isFile = false;

        this.logoAbPath = new AbPath(DomSearchHelper.getNode(
            LogoData.getInstance().IMAGEPATH, node.getChildNodes()).getNodeValue());

        this.logoFile = DomSearchHelper.getNode(
            LogoData.getInstance().IMAGEFILENAME, node.getChildNodes()).getNodeValue();
    }

    public LogoValidation(HashMap hashMap) throws Exception
    {
        this.isFile = true;

        this.getFormData(hashMap);
    }

    public void getFormData(HashMap hashMap) throws Exception
    {
        this.logoFileItem = (FileItem) hashMap.get(LogoData.getInstance().IMAGE);
        this.logoAbPath = new AbPath((String) hashMap.get(LogoData.getInstance().IMAGEPATH));

        if (this.logoFileItem != null)
        {
            this.logoFile = HttpRequestUtil.getInstance().generateFileName(
            		this.logoFileItem.getName());
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("HashMap: ");
            stringBuffer.append(hashMap.toString());
            stringBuffer.append("\nLogoFileName: ");
            stringBuffer.append(this.logoFile);

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "getFormData()"));
        }
    }

    public Boolean isValid()
    {
        try
        {
            Boolean valid = Boolean.TRUE;

            FileData fileData = FileData.getInstance();
            
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                LogUtil.put(LogFactory.getInstance("LogoValidation", this, commonStrings.IS_VALID));
            }

            if (!StringValidationUtil.getInstance().isValidRequired(this.logoAbPath.toString(), 0, 512))
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    LogUtil.put(LogFactory.getInstance("logoPath is invalid: " + this.logoAbPath.toString(), this, commonStrings.IS_VALID));
                }
                return Boolean.FALSE;
            }

            if (!StringValidationUtil.getInstance().isValidRequired(
                this.logoFile, fileData.MINLEN, fileData.MAXLEN))
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    LogUtil.put(LogFactory.getInstance("logoFile is invalid: " + this.logoFile, this, commonStrings.IS_VALID));
                }
                return Boolean.FALSE;
            }

            //future version may require file for edit
            if (this.isFile)
            {
                if (this.logoFileItem == null)
                {
                    return Boolean.FALSE;
                }

                if (this.logoFileItem.getSize() > fileData.MAXIMAGEFILESIZE || logoFileItem.getSize() < fileData.MINIMAGEFILESIZE)
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                    {
                        LogUtil.put(LogFactory.getInstance("Image File Is Not The Right Size. "
                            + fileData.MINIMAGEFILESIZE + "< > "
                            + fileData.MAXIMAGEFILESIZE, this, commonStrings.IS_VALID));
                    }
                    return Boolean.FALSE;
                }
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Logo Validation: " + valid, this, commonStrings.IS_VALID));
            }

            return valid;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed to validate form", this, commonStrings.IS_VALID, e));
            }
            return Boolean.FALSE;
        }
    }

    public String validationInfo()
    {
        try
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Logo is not valid.");

            return stringBuffer.toString();
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed to generate validation error info", this, "validationInfo()", e));
            }
            return "Error Validating Form";
        }
    }

    public Document toValidationInfoDoc()
    {
        return null;
    }

    public Node toValidationInfoNode(Document document)
    {
        return null;
    }

    public HashMap toHashMap()
    {
        HashMap hashMap = new HashMap();
        hashMap.put(LogoData.getInstance().IMAGE, "");
        if (this.logoFile != null)
        {
            hashMap.put(LogoData.getInstance().IMAGEPATH, this.logoAbPath.toString());
            hashMap.put(LogoData.getInstance().IMAGEFILENAME, this.logoFile);
        } else
        {
            hashMap.put(LogoData.getInstance().IMAGEPATH, null);
            hashMap.put(LogoData.getInstance().IMAGEFILENAME, this.logoFile);
        }

        return hashMap;
    }

    public Node toXmlNode(Document document) throws Exception
    {
        HashMap hashMap = this.toHashMap();
        return ModDomHelper.createNameValueNodes(document, LogoData.getInstance().NAME, hashMap);
    }

    public void processLogoFile() throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Processing logoFile: ");
            stringBuffer.append(this.logoAbPath.toString());
            stringBuffer.append(this.logoFile);

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "processLogoFile()"));
        }
        //Do not update image if file was not provided
        new LogoImageFileUtil(this.logoAbPath, this.logoFile).saveFiles(this.logoFileItem);
        //if(!imageFile.isFile()) throw new Exception("Image File Did Not Save");
    }
}
