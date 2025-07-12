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
package views.admin.inventory.download;

import java.util.HashMap;

import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemValidation;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadItemData;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadableItem;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadableItemValidation;
import org.allbinary.business.user.commerce.money.MoneyException;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.globals.GLOBALS2;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.http.file.upload.HttpFileUploadUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileData;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.string.CommonStrings;
import org.apache.commons.fileupload.FileItem;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class UpdateFileValidationView
    extends DownloadableInventoryItemView
    implements ValidationComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public UpdateFileValidationView(TransformInfoInterface transformInfoInterface) throws Exception
    {
        super(transformInfoInterface);
    }

    public Boolean isValid()
    {
        try
        {
            String command = (String) this.getRequestHashMap().get(GLOBALS2.ADMINCOMMAND);

            if (StringValidationUtil.getInstance().isEmpty(command) ||
                command.compareTo(this.commonStrings.UPDATE) != 0)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    logUtil.put("Invalid AdminCommand=" + command, this, commonStrings.IS_VALID);
                }
                return Boolean.FALSE;
            }

            try
            {
                InventoryEntity inventoryEntity =
                    InventoryEntityFactory.getInstance().getInventoryEntityInstance();

                this.itemInterface = inventoryEntity.getItem(id);

            } catch (MoneyException e)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                    org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    logUtil.put("Existing Item With MoneyException", this, commonStrings.IS_VALID, e);
                }
            }

            if (this.itemInterface == null)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    logUtil.put("Item Does Not Exist", this, commonStrings.IS_VALID);
                }

                return Boolean.FALSE;
            }

            if (new BasicItemValidation(this.itemInterface).isValid() == Boolean.FALSE)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    logUtil.put("BasicItem is not valid", this, commonStrings.IS_VALID);
                }

                return Boolean.FALSE;
            }

            StoreFrontInterface storeFrontInterface =
                StoreFrontFactory.getInstance(this.getWeblisketSession().getStoreName());

            //correct category
            //this.itemInterface.setCategory(
            //storeFrontInterface.getCategoryPath()
            //+ this.itemInterface.getCategory());

            String fullCategory = new StringMaker().append(
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

            Object downloadableFileObject =
                this.getRequestHashMap().get(DownloadItemData.FILE);

            if (HttpFileUploadUtil.getInstance().isValid(downloadableFileObject))
            {
                FileItem fileItem = (FileItem) downloadableFileObject;

                String fileName = fileItem.getName();
                long size = fileItem.getSize();

                HttpFileUploadUtil.log(fileItem);

                if (this.isValid(fileName, size) == Boolean.FALSE)
                {
                    return Boolean.FALSE;
                } else
                {
                    //this.processFile(DownloadItemData.FILE);
                    this.processFile(fileItem);
                    this.getRequestHashMap().put(DownloadItemData.SIZE, Long.toString(size));
                }
            }
            else
            {
                this.getRequestHashMap().remove(DownloadItemData.FILE);
            }

            Object licenseFileObject =
                this.getRequestHashMap().get(DownloadItemData.LICENSE_FILE);

            if (HttpFileUploadUtil.getInstance().isValid(licenseFileObject))
            {
                FileItem fileItem = (FileItem) licenseFileObject;

                String fileName = fileItem.getName();
                long size = fileItem.getSize();

                HttpFileUploadUtil.log(fileItem);

                if (this.isValid(fileName, size) == Boolean.FALSE)
                {
                    //Not Required File
                    //return Boolean.FALSE;
                } else
                {
                    this.processFile(fileItem);
                    //this.processFile(DownloadItemData.LICENSE_FILE);
                }
            }
            else
            {
                this.getRequestHashMap().remove(DownloadItemData.LICENSE_FILE);
            }

            Object extraFilesObject =
                this.getRequestHashMap().get(DownloadItemData.EXTRA_FILES);

            if (HttpFileUploadUtil.getInstance().isValid(extraFilesObject))
            {
                FileItem fileItem = (FileItem) extraFilesObject;

                String fileName = fileItem.getName();
                long size = fileItem.getSize();

                HttpFileUploadUtil.log(fileItem);

                if (this.isValid(fileName, size) == Boolean.FALSE)
                {
                    //Not Required File
                    //return Boolean.FALSE;
                } else
                {
                    this.processFile(fileItem);
                    this.unzip(fileItem);
                }
            }
            else
            {
                this.getRequestHashMap().remove(DownloadItemData.EXTRA_FILES);
            }

            HashMap hashMap = this.getRequestHashMap();
            
            hashMap.put(BasicItemData.ID, this.itemInterface.getId());
            
            Object licenseObject = hashMap.get(DownloadItemData.LICENSE_FILE);

            if(licenseObject != null)
            {
            	hashMap.put(DownloadItemData.LICENSE_FILE, ((FileItem) licenseObject).getName());
            }
            else
            {
            	hashMap.put(DownloadItemData.LICENSE_FILE, StringUtil.getInstance().EMPTY_STRING);
            }

            Object fileObject = hashMap.get(DownloadItemData.FILE);

            if(fileObject != null)
            {
            	hashMap.put(DownloadItemData.FILE, ((FileItem) fileObject).getName());
            }
            else
            {
            	hashMap.put(DownloadItemData.FILE, StringUtil.getInstance().EMPTY_STRING);
            }
            
            this.downloadableItem = new DownloadableItem(hashMap);

            DownloadableItemValidation downloadableItemValidation =
                new DownloadableItemValidation(this.downloadableItem);

            if (downloadableItemValidation.isValid() == Boolean.FALSE)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    logUtil.put("DownloadableItem is not valid", this, commonStrings.IS_VALID);
                }

                return Boolean.FALSE;
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
            StringMaker stringBuffer = new StringMaker();

            String command = (String) this.getRequestHashMap().get(GLOBALS2.ADMINCOMMAND);

            if (StringValidationUtil.getInstance().isEmpty(command) ||
                command.compareTo(this.commonStrings.UPDATE) != 0)
            {
                //stringBuffer.append("Invalid Command<br/>");
                return StringUtil.getInstance().EMPTY_STRING;
            }

            if (this.itemInterface == null)
            {
                final String ITEM_NOT_FOUND = "Item Does Not Exist";

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    logUtil.put(ITEM_NOT_FOUND, this, "validationInfo()");
                }

                return ITEM_NOT_FOUND;
            }

            BasicItemValidation basicItemValidation =
                new BasicItemValidation(this.itemInterface);

            if (basicItemValidation.isValid() == Boolean.FALSE)
            {
                stringBuffer.append(basicItemValidation.validationInfo());
                stringBuffer.append("<br/>");
            }

            StoreFrontInterface storeFrontInterface =
                StoreFrontFactory.getInstance(
                this.getWeblisketSession().getStoreName());

            String fullCategory = new StringMaker().append(
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
                stringBuffer.append(" does not exist.");
                stringBuffer.append("<br />");
            }

            Object downloadableFileObject =
                this.getRequestHashMap().get(DownloadItemData.FILE);

            if (HttpFileUploadUtil.getInstance().isValid(downloadableFileObject))
            {
                FileItem fileItem = (FileItem) downloadableFileObject;

                String fileName = fileItem.getName();
                long size = fileItem.getSize();

                this.validationInfo(stringBuffer, fileName, size);

            } else
            {
                stringBuffer.append("Download File Missing");
                stringBuffer.append("<br/>");
            }

            Object licenseFileObject =
                this.getRequestHashMap().get(DownloadItemData.LICENSE_FILE);

            if (HttpFileUploadUtil.getInstance().isValid(licenseFileObject))
            {
                FileItem fileItem = (FileItem) licenseFileObject;

                String fileName = fileItem.getName();
                long size = fileItem.getSize();

                this.validationInfo(stringBuffer, fileName, size);

            } else
            {
                //Not Required
                //stringBuffer.append("License File Missing<br/>");
            }

            Object extraFilesObject =
                this.getRequestHashMap().get(DownloadItemData.EXTRA_FILES);

            if (HttpFileUploadUtil.getInstance().isValid(extraFilesObject))
            {
                FileItem fileItem = (FileItem) extraFilesObject;

                String fileName = fileItem.getName();
                long size = fileItem.getSize();

                this.validationInfo(stringBuffer, fileName, size);

            } else
            {
                //Not Required
                //stringBuffer.append("Extra Files Zip Missing<br/>");
            }

            if (this.downloadableItem != null)
            {
                DownloadableItemValidation downloadableItemValidation =
                    new DownloadableItemValidation(this.downloadableItem);

                if (downloadableItemValidation.isValid() == Boolean.FALSE)
                {
                    final String log = downloadableItemValidation.validationInfo();
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                    {
                        logUtil.put(log, this, commonStrings.IS_VALID);
                    }

                    stringBuffer.append(log);
                    stringBuffer.append("<br/>");
                }
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
        FileData fileData = FileData.getInstance();

        if (size > fileData.MINDOWNLOADABLEFILESIZE)
        {
            if (size < fileData.MAXDOWNLOADABLEFILESIZE)
            {
                if (!StringValidationUtil.getInstance().isValidRequired(
                    fileName, fileData.MINLEN, fileData.MAXLEN))
                {

                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                    {
                        StringMaker stringBuffer = new StringMaker();

                        stringBuffer.append("File name is not valid: ");
                        stringBuffer.append(fileName);

                        logUtil.put(
                            stringBuffer.toString(), this, commonStrings.IS_VALID);
                    }

                    return Boolean.FALSE;
                } else
                {
                    //Could add limitations on extension here
                    /*
                    String extension = AbPathUtil.getExtension(fileName);

                    UploadMediaSingleton uploadMedia =
                    UploadMediaSingleton.getInstance();
                    if (!uploadMedia.isWriterSupported(extension)
                    && !uploadMedia.isReaderSupported(extension))
                    {
                    return Boolean.FALSE;
                    }
                     */
                }
            } else
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
                {
                    StringMaker stringBuffer = new StringMaker();

                    stringBuffer.append("File Size To Large: ");
                    stringBuffer.append(size);
                    stringBuffer.append(">");
                    stringBuffer.append(fileData.MAXDOWNLOADABLEFILESIZE);

                    logUtil.put(
                        stringBuffer.toString(), this, commonStrings.IS_VALID);
                }
                return Boolean.FALSE;
            }
        } else
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                StringMaker stringBuffer = new StringMaker();

                stringBuffer.append("File Size To Small: ");
                stringBuffer.append(size);
                stringBuffer.append(">");
                stringBuffer.append(fileData.MINDOWNLOADABLEFILESIZE);

                logUtil.put(
                    stringBuffer.toString(), this, commonStrings.IS_VALID);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void validationInfo(
        StringMaker stringBuffer,
        String fileName, long size)
    {
        FileData fileData = FileData.getInstance();

        if (size > fileData.MINDOWNLOADABLEFILESIZE)
        {
            if (size < fileData.MAXDOWNLOADABLEFILESIZE)
            {
                if (!StringValidationUtil.getInstance().isValidRequired(
                    fileName, fileData.MINLEN, fileData.MAXLEN))
                {
                    stringBuffer.append("File Name must be more than ");
                    stringBuffer.append(fileData.MINLEN);
                    stringBuffer.append(" and less than ");
                    stringBuffer.append(fileData.MAXLEN);
                    stringBuffer.append(" characters in length: ");
                    stringBuffer.append(fileName);
                    stringBuffer.append("<br/>");

                } else
                {
                }
            } else
            {
                stringBuffer.append("File Is Not The Right Size. ");
                stringBuffer.append(fileData.MINDOWNLOADABLEFILESIZE);
                stringBuffer.append("< >");
                stringBuffer.append(fileData.MAXDOWNLOADABLEFILESIZE);
                stringBuffer.append("<br/>");
            }
        }
    }
}
