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

import org.w3c.dom.Node;
import org.w3c.dom.Document;

//import org.apache.commons.fileupload.FileItem;

import org.allbinary.globals.URLGLOBALS;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileData;
import org.allbinary.logic.io.path.AbPathUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.user.commerce.inventory.item.BasicItemData;

import org.allbinary.business.user.commerce.inventory.item.BasicItemValidation;

import org.allbinary.business.user.commerce.money.MoneyException;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;

import org.allbinary.globals.GLOBALS2;

import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.logic.communication.http.file.upload.HttpFileUploadUtil;

import org.allbinary.logic.communication.http.file.upload.media.UploadMediaSingleton;

import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.io.path.PathUtil;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.apache.commons.fileupload.FileItem;

public class AddFileValidationView
    extends InventoryItemView
    implements ValidationComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final String ADDPRODUCT = "Add Product";
    private static final String NEXTSTEP = "Next Step";

    //itemInterface = InventoryEntityFactory.getInstance().getItem(id);
    public AddFileValidationView(TransformInfoInterface transformInfoInterface) throws Exception
    {
        super(transformInfoInterface);
    }

    public Boolean isValid()
    {
        try
        {
            String command = (String) this.getRequestHashMap().get(GLOBALS2.ADMINCOMMAND);

            if (StringValidationUtil.getInstance().isEmpty(command)
                || (command.compareTo(ADDPRODUCT) != 0
                && command.compareTo(NEXTSTEP) != 0))
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("Invalid AdminCommand: ");
                    stringBuffer.append(command);
                    stringBuffer.append(" must be: ");
                    stringBuffer.append(ADDPRODUCT);
                    stringBuffer.append(" or ");
                    stringBuffer.append(NEXTSTEP);

                    logUtil.put(stringBuffer.toString(), this, commonStrings.IS_VALID);
                }
                return Boolean.FALSE;
            }

            BasicItemValidation basicItemValidation =
                new BasicItemValidation(this.itemInterface);

            if (basicItemValidation.isValid() == Boolean.FALSE)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    logUtil.put(basicItemValidation.validationInfo(), this, commonStrings.IS_VALID);
                }

                return Boolean.FALSE;
            }

            StoreFrontInterface storeFrontInterface =
                StoreFrontFactory.getInstance(this.getWeblisketSession().getStoreName());

            //correct category
            this.itemInterface.setCategory(
                storeFrontInterface.getCategoryPath()
                + this.itemInterface.getCategory());

            String fullCategory = new StringBuffer().append(
                URLGLOBALS.getWebappPath()).append(
                storeFrontInterface.getCurrentHostNamePath()).append(
                this.itemInterface.getCategory()).toString();
            //storeFrontInterface.getCategoryPath()

            AbFile categoryFile = new AbFile(fullCategory);
            if (!categoryFile.isDirectory())
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    logUtil.put("Category Does Not Exist: " + fullCategory, this, commonStrings.IS_VALID);
                }

                return Boolean.FALSE;
            }

            if (InventoryEntityFactory.getInstance().getInventoryEntityInstance().getItem(this.itemInterface.getId()) != null)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    logUtil.put("Item Already Exists", this, commonStrings.IS_VALID);
                }

                return Boolean.FALSE;
            }

            Object imageFileItemObject =
                this.getRequestHashMap().get(BasicItemData.IMAGE);

            if (HttpFileUploadUtil.getInstance().isValid(imageFileItemObject))
            {
                FileItem fileItem = (FileItem) imageFileItemObject;

                String fileName = fileItem.getName();
                long size = fileItem.getSize();

                HttpFileUploadUtil.log(fileItem);

                if (this.isValid(fileName, size) == Boolean.FALSE)
                {
                    return Boolean.FALSE;
                }
            }

            return Boolean.TRUE;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
            {
                logUtil.put("Exception in validation", this, commonStrings.IS_VALID, e);
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

    public String validationInfo()
    {
        try
        {
            StringBuffer stringBuffer = new StringBuffer();

            String command = (String) this.getRequestHashMap().get(GLOBALS2.ADMINCOMMAND);

            if (StringValidationUtil.getInstance().isEmpty(command)
                || (command.compareTo(ADDPRODUCT) != 0
                && command.compareTo(NEXTSTEP) != 0))
            {
                return StringUtil.getInstance().EMPTY_STRING;
                //stringBuffer.append("Invalid Command<br/>");
            }

            BasicItemValidation basicItemValidation =
                new BasicItemValidation(this.itemInterface);

            if (basicItemValidation.isValid() == Boolean.FALSE)
            {
                stringBuffer.append(basicItemValidation.validationInfo());
                stringBuffer.append("<br/>");
            }

            StoreFrontInterface storeFrontInterface =
                StoreFrontFactory.getInstance(this.getWeblisketSession().getStoreName());

            String fullCategory = new StringBuffer().append(
                URLGLOBALS.getWebappPath()).append(
                storeFrontInterface.getCurrentHostNamePath()).append(
                this.itemInterface.getCategory()).toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                logUtil.put("Category: " + fullCategory, this, "validationInfo()");
            }

            AbFile categoryFile = new AbFile(fullCategory);
            if (!categoryFile.isDirectory())
            {
                stringBuffer.append("Category ");
                stringBuffer.append(this.itemInterface.getCategory());
                stringBuffer.append(" does not exist.<br />");
            }

            try
            {
                if (InventoryEntityFactory.getInstance().getInventoryEntityInstance().getItem(this.itemInterface.getId()) != null)
                {
                    stringBuffer.append("Id is already in use. Refresh the page for a valid id.<br/>");
                }
            } catch (MoneyException e)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                    org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    logUtil.put("Existing Item With MoneyException", this, "validationInfo()");
                }
            }

            Object object = this.getRequestHashMap().get(BasicItemData.IMAGE);

            if (HttpFileUploadUtil.getInstance().isValid(object))
            {
                FileItem fileItem = (FileItem) object;

                String fileName = fileItem.getName();
                String fileItemFieldName = fileItem.getFieldName();
                long size = fileItem.getSize();

                this.validationInfo(stringBuffer, fileName, fileItemFieldName, size);
            }

            return stringBuffer.toString();
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
            {
                logUtil.put("Failed to generate validation error info", this, "validationInfo()", e);
            }
            return "Error Getting Validation Info";
        }
    }

    private Boolean isValid(
        String fileName, long size)
        throws Exception
    {
        final FileData fileData = FileData.getInstance();

        if (size > fileData.MINIMAGEFILESIZE)
        {
            if (size < fileData.MAXIMAGEFILESIZE)
            {
                if (!StringValidationUtil.getInstance().isValidRequired(
                    fileName, fileData.MINLEN, fileData.MAXLEN))
                {
                    return Boolean.FALSE;
                } else
                {
                    final PathUtil pathUtil = PathUtil.getInstance();
                    final String extension = pathUtil.getExtension(fileName);

                    final UploadMediaSingleton uploadMedia = UploadMediaSingleton.getInstance();
                    if (!uploadMedia.isWriterSupported(extension)
                        && !uploadMedia.isReaderSupported(extension))
                    {
                        return Boolean.FALSE;
                    }
                }
            } else
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    final StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("File Size To Large: ");
                    stringBuffer.append(size);
                    stringBuffer.append(">");
                    stringBuffer.append(fileData.MAXIMAGEFILESIZE);

                    logUtil.put(
                        stringBuffer.toString(), this, commonStrings.IS_VALID);

                }
                return Boolean.FALSE;
            }
            this.processImageFiles();
        } else
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                final StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("File Size To Small: ");
                stringBuffer.append(size);
                stringBuffer.append(">");
                stringBuffer.append(fileData.MINIMAGEFILESIZE);

                logUtil.put(stringBuffer.toString(), this, commonStrings.IS_VALID);
            }
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private void validationInfo(
        final StringBuffer stringBuffer,
        final String fileName, final String fileItemFieldName, final long size)
    {
        final FileData fileData = FileData.getInstance();

        if (size > fileData.MINIMAGEFILESIZE)
        {
            if (fileItemFieldName.compareTo(BasicItemData.IMAGE) == 0)
            {
                if (size < fileData.MAXIMAGEFILESIZE)
                {
                    if (!StringValidationUtil.getInstance().isValidRequired(
                        fileName, fileData.MINLEN, fileData.MAXLEN))
                    {
                        stringBuffer.append("FileName must be >");
                        stringBuffer.append(fileData.MINLEN);
                        stringBuffer.append(" and <");
                        stringBuffer.append(fileData.MAXLEN);
                        stringBuffer.append("<br/>");
                    } else
                    {
                        final PathUtil pathUtil = PathUtil.getInstance();
                        String extension = pathUtil.getExtension(fileName);

                        final UploadMediaSingleton uploadMedia = UploadMediaSingleton.getInstance();
                        if (!uploadMedia.isWriterSupported(extension)
                            && !uploadMedia.isReaderSupported(extension))
                        {
                            stringBuffer.append("Image type: ");
                            stringBuffer.append(extension);
                            stringBuffer.append(" not supported.<br />");
                        }
                    }
                } else
                {
                    stringBuffer.append("Image File Is To Large. ");
                    stringBuffer.append(fileData.MINIMAGEFILESIZE);
                    stringBuffer.append(" < > ");
                    stringBuffer.append(fileData.MAXIMAGEFILESIZE);
                    stringBuffer.append("<br/>");
                }
            }
        } else
        {
            stringBuffer.append("Image File Is To Small. ");
            stringBuffer.append(fileData.MINIMAGEFILESIZE);
            stringBuffer.append(" < > ");
            stringBuffer.append(fileData.MAXIMAGEFILESIZE);
            stringBuffer.append("<br/>");
        }
    }
}
