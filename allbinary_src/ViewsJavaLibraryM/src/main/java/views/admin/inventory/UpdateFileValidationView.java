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
package views.admin.inventory;

import org.apache.commons.fileupload.FileItem;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileData;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemValidation;
import org.allbinary.business.user.commerce.money.MoneyException;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.globals.GLOBALS2;
import org.allbinary.logic.communication.http.file.upload.HttpFileUploadUtil;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.string.CommonStrings;

public class UpdateFileValidationView 
    extends InventoryItemView
    implements ValidationComponentInterface
{

    private static final String UPDATEPRODUCT = CommonStrings.getInstance().UPDATE;

    //itemInterface = InventoryEntityFactory.getInstance().getItem(id);
    public UpdateFileValidationView(TransformInfoInterface transformInfoInterface) throws Exception
    {
        super(transformInfoInterface);
    }

    public Boolean isValid()
    {
        try
        {
            String command = (String) 
                this.getRequestHashMap().get(GLOBALS2.ADMINCOMMAND);
            
            if (command == null || command.compareTo(UPDATEPRODUCT) != 0)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    LogUtil.put(LogFactory.getInstance("Invalid AdminCommand=" + command, this, commonStrings.IS_VALID));
                }
                return Boolean.FALSE;
            }

            if (new BasicItemValidation(this.itemInterface).isValid() == Boolean.FALSE)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    LogUtil.put(LogFactory.getInstance("BasicItem is not valid", this, commonStrings.IS_VALID));
                }
                return Boolean.FALSE;
            }

            StoreFrontInterface storeFrontInterface =
                StoreFrontFactory.getInstance(this.getWeblisketSession().getStoreName());

            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(URLGLOBALS.getWebappPath());
            stringBuffer.append(storeFrontInterface.getCurrentHostNamePath());
            //storeFrontInterface.getCategoryPath() +
            stringBuffer.append(this.itemInterface.getCategory());

            String fullCategory = stringBuffer.toString();

            AbFile categoryFile = new AbFile(fullCategory);
            if (!categoryFile.isDirectory())
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    LogUtil.put(LogFactory.getInstance("Category Does Not Exist: " + fullCategory, this, commonStrings.IS_VALID));
                }
                return Boolean.FALSE;
            }

            InventoryEntity inventoryEntity =
                InventoryEntityFactory.getInstance().getInventoryEntityInstance();

            if (inventoryEntity.getItem(this.itemInterface.getId()) == null)
            {
                return Boolean.FALSE;
            }

            Object object = this.getRequestHashMap().get(BasicItemData.IMAGE);

            if (HttpFileUploadUtil.getInstance().isValid(object))
            {
                FileItem fileItem = (FileItem) object;

                long size = fileItem.getSize();
                String fileName = fileItem.getName();

                HttpFileUploadUtil.log(fileItem);

                if(this.isValid(fileName, size) == Boolean.FALSE)
                {
                    return Boolean.FALSE;
                }
            }

            return Boolean.TRUE;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Exception in validation", this, commonStrings.IS_VALID, e));
            }

            return Boolean.FALSE;
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

    public String validationInfo() throws Exception
    {
        StringBuffer stringBuffer = new StringBuffer();

        String command = (String) this.getRequestHashMap().get(GLOBALS2.ADMINCOMMAND);
        if (command == null || command.compareTo(UPDATEPRODUCT) != 0)
        {
            return CommonSeps.getInstance().SPACE;
        }

        stringBuffer.append(
            new BasicItemValidation(this.itemInterface).validationInfo());

        StoreFrontInterface storeFrontInterface =
            StoreFrontFactory.getInstance(this.getWeblisketSession().getStoreName());

        String fullCategory = (String) URLGLOBALS.getWebappPath()
            + storeFrontInterface.getCurrentHostNamePath()
            + //storeFrontInterface.getCategoryPath() +
            this.itemInterface.getCategory();

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Category: " + fullCategory, this, "validationInfo()"));
        }

        try
        {
            if (InventoryEntityFactory.getInstance().getInventoryEntityInstance().getItem(this.itemInterface.getId()) == null)
            {
                stringBuffer.append("Item does not exist.<br>");
            }
        } catch (MoneyException e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Existing Item With MoneyException", this, "validationInfo()"));
            }
        }

        Object object = this.getRequestHashMap().get(BasicItemData.IMAGE);

            if (HttpFileUploadUtil.getInstance().isValid(object))
            {
                FileItem fileItem = (FileItem) object;

            long size = fileItem.getSize();
            String fileName = fileItem.getName();
            String fileItemFieldName = fileItem.getFieldName();

            this.validationInfo(stringBuffer, fileName, fileItemFieldName, size);

        } 

        //else stringBuffer.append("Image File Form Data Error");

        return stringBuffer.toString();
    }

    private Boolean isValid(
        String fileName, long size)
        throws Exception
    {
    	FileData fileData = FileData.getInstance();
    	
        if (size > fileData.MINIMAGEFILESIZE)
        {
            if (size < fileData.MAXIMAGEFILESIZE)
            {
                if (!StringValidationUtil.getInstance().isValidRequired(fileName, fileData.MINLEN, fileData.MAXLEN))
                {
                    return Boolean.FALSE;
                }
            } else
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("File Size To Large: ");
                    stringBuffer.append(size);
                    stringBuffer.append(">");
                    stringBuffer.append(fileData.MAXIMAGEFILESIZE);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, commonStrings.IS_VALID));
                }
                return Boolean.FALSE;
            }
            this.processImageFiles();
        }
        return Boolean.TRUE;
    }

    private void validationInfo(
        StringBuffer stringBuffer,
        String fileName, String fileItemFieldName, long size)
    {
    	FileData fileData = FileData.getInstance();
    	
        if (size > fileData.MINIMAGEFILESIZE)
        {
            if (fileItemFieldName.compareTo(BasicItemData.IMAGE) == 0)
            {
                if (size < fileData.MAXIMAGEFILESIZE)
                {
                    if (!StringValidationUtil.getInstance().isValidRequired(fileName, fileData.MINLEN, fileData.MAXLEN))
                    {
                        stringBuffer.append("FileName must be >");
                        stringBuffer.append(fileData.MINLEN);
                        stringBuffer.append("and <");
                        stringBuffer.append(fileData.MAXLEN);
                        stringBuffer.append("<br />");
                    }
                } else
                {
                    stringBuffer.append("Image File Is Not The Right Size. ");
                    stringBuffer.append(fileData.MINIMAGEFILESIZE);
                    stringBuffer.append("< > ");
                    stringBuffer.append(fileData.MAXIMAGEFILESIZE);
                    stringBuffer.append("<br />");
                }
            }
        }
    }
}
