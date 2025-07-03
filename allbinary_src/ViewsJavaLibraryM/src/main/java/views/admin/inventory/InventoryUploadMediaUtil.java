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

import org.allbinary.globals.URLGLOBALS;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileUtil;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.media.MediaData;

import org.allbinary.business.user.commerce.inventory.item.ItemInterface;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;

import org.allbinary.logic.communication.http.file.upload.media.UploadMediaSingleton;

import org.allbinary.logic.visual.media.MediaTypeData;
import org.allbinary.logic.visual.media.MediaUtil;
import java.io.ByteArrayInputStream;
import org.allbinary.string.CommonStrings;
import org.apache.commons.fileupload.FileItemUtil;

public class InventoryUploadMediaUtil
{
    //private final FileItemUtil fileItemUtil = FileItemUtil.getInstance();
    private final MediaUtil mediaUtil = MediaUtil.getInstance();

    private final int LARGEWIDTH = 1024;
    private final int LARGEHEIGHT = 1024;
    private final int MEDIUMWIDTH = 256;
    private final int MEDIUMHEIGHT = 256;
    private final int SMALLWIDTH = 128;
    private final int SMALLHEIGHT = 128;
    private final String SMALL = "Small";
    private final String MEDIUM = "Medium";
    private final String LARGE = "Large";
    private StoreFrontInterface storeFrontInterface;
    private ItemInterface itemInterface;

    //Save to default file type
    public InventoryUploadMediaUtil(StoreFrontInterface storeFrontInterface, ItemInterface itemInterface)
    {
        this.storeFrontInterface = storeFrontInterface;
        this.itemInterface = itemInterface;
    }

    public ItemInterface saveFiles(byte[] byteArray, String fileName, MediaData mediaData) throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
        {
            LogUtil.put(LogFactory.getInstance("Start FileName: " + fileName, this, "saveFiles()"));
        }

        if (StringValidationUtil.getInstance().isEmpty(fileName))
        {
            throw new Exception("Image File Name Was Null");
        }

        FileUtil fileUtil = FileUtil.getInstance();
        
        StringBuffer stringBuffer = new StringBuffer();
        
        stringBuffer.append(URLGLOBALS.getWebappPath());
        stringBuffer.append(storeFrontInterface.getCurrentHostNamePath());
        //storeFrontInterface.getCategoryPath()
        stringBuffer.append(itemInterface.getCategory());
        
        String fullPath = stringBuffer.toString();

        AbFile imageDirectoryFile = new AbFile(fullPath);
        if (!imageDirectoryFile.isDirectory())
        {
            throw new Exception("Unable to save file to non existant directory");
        }

        stringBuffer.delete(0, stringBuffer.length());
        
        stringBuffer.append(fullPath);
        stringBuffer.append(fileName);
        stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
        stringBuffer.append(mediaData.getName());
        
        AbFile originalImageFile = new AbFile(stringBuffer.toString());

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
        {
            LogUtil.put(LogFactory.getInstance("Saving Original Image: " + originalImageFile, this, "saveFiles()"));
        }

        originalImageFile.createNewFile();

        fileUtil.write(new ByteArrayInputStream(byteArray), originalImageFile);
        //fileItemUtil.write(fileItem, originalImageFile);

        UploadMediaSingleton uploadMedia = UploadMediaSingleton.getInstance();

        boolean isMediaSupported =
            uploadMedia.isWriterSupported(mediaData.getName())
            || uploadMedia.isReaderSupported(mediaData.getName());

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
        {
        	stringBuffer.delete(0, stringBuffer.length());

            stringBuffer.append("Is Image Type: ");
            stringBuffer.append(mediaData.getName());
            stringBuffer.append(" supported: ");
            stringBuffer.append(isMediaSupported);

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "saveFiles()"));
        }

        MediaData defaultMediaData = MediaData.getDefault();
        this.setFileNames(fileName);

        if (isMediaSupported)
        {
            boolean isMediaResizable =
                uploadMedia.isWriterMedia(
                mediaData.getName(), MediaTypeData.getInstance().RESIZABLE_MEDIA);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
            {
            	stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append("Is ");
                stringBuffer.append(mediaData);
                stringBuffer.append(" Image Resize Supported:");
                stringBuffer.append(uploadMedia.isSupported(mediaData.getName()));
                stringBuffer.append(" WriterMedia: ");
                stringBuffer.append(isMediaResizable);

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "saveFiles()"));
            }

            //Is the uploaded File an image that ImageIO can resize
            if (isMediaResizable)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
                {
                    LogUtil.put(LogFactory.getInstance("Saving Small Image: " + this.itemInterface.getSmallImage(), this, "saveFiles()"));
                }

                mediaUtil.saveImageFile(
                    originalImageFile, itemInterface.getSmallImage(),
                    fullPath, mediaData,
                    this.SMALLWIDTH, this.SMALLHEIGHT);

                mediaUtil.saveImageFile(
                    originalImageFile, itemInterface.getMediumImage(),
                    fullPath, mediaData,
                    this.MEDIUMWIDTH, this.MEDIUMHEIGHT);

                mediaUtil.saveImageFile(
                    originalImageFile, itemInterface.getLargeImage(),
                    fullPath, mediaData,
                    this.LARGEWIDTH, this.LARGEHEIGHT);
            } else
            {
                //Is the uploaded File an image that has a reader
                //and can be converted by ImageIO and resized into
                //a different ImageIO format

                boolean isConvertable =
                    mediaData.isConvertableTo(defaultMediaData);

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
                {
                	stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append("Converting: ");
                    stringBuffer.append(defaultMediaData.getName());
                    stringBuffer.append(" into ");
                    stringBuffer.append(mediaData.getName());
                    stringBuffer.append(" if Convertable is it: ");
                    stringBuffer.append(isConvertable);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "saveFiles()"));
                }

                if (isConvertable)
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
                    {
                        LogUtil.put(LogFactory.getInstance("Saving Small Image: " + this.itemInterface.getSmallImage(), this, "saveFiles()"));
                    }

                    mediaUtil.saveImageFile(
                        originalImageFile, itemInterface.getSmallImage(),
                        fullPath, defaultMediaData,
                        this.SMALLWIDTH, this.SMALLHEIGHT);

                    mediaUtil.saveImageFile(
                        originalImageFile, itemInterface.getMediumImage(),
                        fullPath, defaultMediaData,
                        this.MEDIUMWIDTH, this.MEDIUMHEIGHT);

                    mediaUtil.saveImageFile(
                        originalImageFile, itemInterface.getLargeImage(),
                        fullPath, defaultMediaData,
                        this.LARGEWIDTH, this.LARGEHEIGHT);
                } else
                {
                    //Create Multiple files without resizing

                    AbFile smallImageFile = new AbFile(itemInterface.getSmallImage());
                    smallImageFile.createNewFile();

                    fileUtil.write(new ByteArrayInputStream(byteArray), smallImageFile);
                    //fileItemUtil.write(fileItem, smallImageFile);

                    AbFile mediumImageFile = new AbFile(itemInterface.getMediumImage());
                    smallImageFile.createNewFile();

                    fileUtil.write(new ByteArrayInputStream(byteArray), mediumImageFile);
                    //fileItemUtil.write(fileItem, mediumImageFile);

                    AbFile largeImageFile = new AbFile(itemInterface.getSmallImage());
                    largeImageFile.createNewFile();

                    fileUtil.write(new ByteArrayInputStream(byteArray), largeImageFile);
                    //fileItemUtil.write(fileItem, largeImageFile);
                }
            }
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.END, this, "saveFiles()"));
        }
        return this.itemInterface;
    }

    private void setFileNames(String fileName)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.itemInterface.getId());
        stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
        stringBuffer.append(MediaData.getDefault().getName());

        String END = stringBuffer.toString();

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append(fileName);
        stringBuffer.append(SMALL);
        stringBuffer.append(END);

        String newImageFileName = stringBuffer.toString();

        this.itemInterface.setSmallImage(newImageFileName);

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append(fileName);
        stringBuffer.append(MEDIUM);
        stringBuffer.append(END);

        newImageFileName = stringBuffer.toString();
        this.itemInterface.setMediumImage(newImageFileName);

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append(fileName);
        stringBuffer.append(LARGE);
        stringBuffer.append(END);

        newImageFileName = stringBuffer.toString();
        this.itemInterface.setLargeImage(newImageFileName);
    }

    /*
    public ItemInterface saveFiles(FileItem fileItem, String fileName, MediaData mediaData) throws Exception
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("Start FileName: " + fileName, this, "saveFiles()"));
    }

    if(StringValidationUtil.isEmpty(fileName))
    {
    throw new Exception("Image File Name Was Null");
    }

    String fullPath = URLGLOBALS.getWebappPath() +
    storeFrontInterface.getCurrentHostNamePath() +
    //storeFrontInterface.getCategoryPath() +
    itemInterface.getCategory();

    AbFile imageDirectoryFile = new AbFile(fullPath);
    if(!imageDirectoryFile.isDirectory())
    {
    throw new Exception("Unable to save file to non existant directory");
    }

    AbFile originalImageFile = new AbFile(fullPath + fileName +
    AbPathData.getInstance().EXTENSION_SEP + mediaData.getName());

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("Saving Original Image: " + originalImageFile, this, "saveFiles()"));
    }

    originalImageFile.createNewFile();

    fileItemUtil.write(fileItem, originalImageFile);

    UploadMediaSingleton uploadMedia = UploadMediaSingleton.getInstance();
    boolean isMediaSupported =
    uploadMedia.isWriterSupported(mediaData.getName()) ||
    uploadMedia.isReaderSupported(mediaData.getName());

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("Is Image Type: " + mediaData.getName() + " supported: " + isMediaSupported, this, "saveFiles()"));
    }

    if(isMediaSupported)
    {
    boolean isMediaResizable =
    uploadMedia.isWriterMedia(
    mediaData.getName(), MediaTypeData.RESIZABLE_MEDIA);

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("Is Image Resizable: " + mediaData +
    " Supported: " + uploadMedia.isSupported(mediaData.getName()) +
    " WriterMedia: " + uploadMedia.isWriterMedia(
    mediaData.getName(), MediaTypeData.RESIZABLE_MEDIA),
    this, "saveFiles()"));
    }

    //Is the uploaded File an image that ImageIO can resize
    if(isMediaResizable)
    {
    String newImageFileName = fileName + SMALL +
    this.itemInterface.getId() + AbPathData.getInstance().EXTENSION_SEP +
    mediaData.getName();
    this.itemInterface.setSmallImage(newImageFileName);
    newImageFileName = fileName + MEDIUM +
    this.itemInterface.getId() + AbPathData.getInstance().EXTENSION_SEP +
    mediaData.getName();
    this.itemInterface.setMediumImage(newImageFileName);
    newImageFileName = fileName + LARGE +
    this.itemInterface.getId() + AbPathData.getInstance().EXTENSION_SEP +
    mediaData.getName();
    this.itemInterface.setLargeImage(newImageFileName);

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("Saving Small Image: " + this.itemInterface.getSmallImage(), this, "saveFiles()"));
    }

    mediaUtil.saveBufferedImageFile(
    originalImageFile, itemInterface.getSmallImage(),
    fullPath, mediaData,
    this.SMALLWIDTH, this.SMALLHEIGHT);

    mediaUtil.saveBufferedImageFile(
    originalImageFile, itemInterface.getMediumImage(),
    fullPath, mediaData,
    this.MEDIUMWIDTH, this.MEDIUMHEIGHT);

    mediaUtil.saveBufferedImageFile(
    originalImageFile, itemInterface.getLargeImage(),
    fullPath, mediaData,
    this.LARGEWIDTH, this.LARGEHEIGHT);
    }
    else
    {
    //Is the uploaded File an image that has a reader
    //and can be converted by ImageIO and resized into
    //a different ImageIO format
    MediaData defaultMediaData = MediaData.getDefault();
    boolean isConvertable =
    mediaData.isConvertableTo(defaultMediaData);

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("Converting: " + defaultMediaData.getName() +
    " into " + mediaData.getName() +
    " if Convertable is it: " + isConvertable, this, "saveFiles()"));
    }

    if(isConvertable)
    {
    String newImageFileName = fileName + SMALL +
    this.itemInterface.getId() + AbPathData.getInstance().EXTENSION_SEP +
    defaultMediaData.getName();
    this.itemInterface.setSmallImage(newImageFileName);
    newImageFileName = fileName + MEDIUM +
    this.itemInterface.getId() + AbPathData.getInstance().EXTENSION_SEP +
    defaultMediaData.getName();
    this.itemInterface.setMediumImage(newImageFileName);
    newImageFileName = fileName + LARGE +
    this.itemInterface.getId() + AbPathData.getInstance().EXTENSION_SEP +
    defaultMediaData.getName();
    this.itemInterface.setLargeImage(newImageFileName);

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("Saving Small Image: " + this.itemInterface.getSmallImage(), this, "saveFiles()"));
    }

    mediaUtil.saveBufferedImageFile(
    originalImageFile, itemInterface.getSmallImage(),
    fullPath, defaultMediaData,
    this.SMALLWIDTH, this.SMALLHEIGHT);

    mediaUtil.saveBufferedImageFile(
    originalImageFile, itemInterface.getMediumImage(),
    fullPath, defaultMediaData,
    this.MEDIUMWIDTH, this.MEDIUMHEIGHT);

    mediaUtil.saveBufferedImageFile(
    originalImageFile, itemInterface.getLargeImage(),
    fullPath, defaultMediaData,
    this.LARGEWIDTH, this.LARGEHEIGHT);
    }
    else
    {
    //Create Multiple files without resizing
    String newImageFileName = fileName + SMALL +
    this.itemInterface.getId() + AbPathData.getInstance().EXTENSION_SEP +
    mediaData.getName();
    this.itemInterface.setSmallImage(newImageFileName);
    newImageFileName = fileName + MEDIUM +
    this.itemInterface.getId() + AbPathData.getInstance().EXTENSION_SEP +
    mediaData.getName();
    this.itemInterface.setMediumImage(newImageFileName);
    newImageFileName = fileName + LARGE +
    this.itemInterface.getId() + AbPathData.getInstance().EXTENSION_SEP +
    mediaData.getName();
    this.itemInterface.setLargeImage(newImageFileName);

    AbFile smallImageFile =
    new AbFile(itemInterface.getSmallImage());
    smallImageFile.createNewFile();

    fileItemUtil.write(fileItem, smallImageFile);

    AbFile mediumImageFile =
    new AbFile(itemInterface.getMediumImage());
    smallImageFile.createNewFile();

    fileItemUtil.write(fileItem, mediumImageFile);

    AbFile largeImageFile =
    new AbFile(itemInterface.getSmallImage());
    largeImageFile.createNewFile();

    fileItemUtil.write(fileItem, largeImageFile);
    }
    }
    }

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance(commonStrings.END, this, "saveFiles()"));
    }
    return this.itemInterface;
    }
     */
}
