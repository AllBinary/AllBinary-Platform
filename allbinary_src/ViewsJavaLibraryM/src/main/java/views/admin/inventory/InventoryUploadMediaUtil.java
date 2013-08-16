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

import abcs.globals.URLGLOBALS;

import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.FileUtil;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;

import abcs.logic.communication.log.LogUtil;
import abcs.logic.visual.media.MediaData;

import allbinary.business.user.commerce.inventory.item.ItemInterface;

import allbinary.business.context.modules.storefront.StoreFrontInterface;

import allbinary.logic.communication.http.file.upload.media.UploadMediaSingleton;

import allbinary.logic.visual.media.MediaTypeData;
import allbinary.logic.visual.media.MediaUtil;
import java.io.ByteArrayInputStream;

public class InventoryUploadMediaUtil
{
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
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
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

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
        {
            LogUtil.put(LogFactory.getInstance("Saving Original Image: " + originalImageFile, this, "saveFiles()"));
        }

        originalImageFile.createNewFile();

        fileUtil.write(new ByteArrayInputStream(byteArray), originalImageFile);
        //FileItemUtil.write(fileItem, originalImageFile);

        UploadMediaSingleton uploadMedia = UploadMediaSingleton.getInstance();

        boolean isMediaSupported =
            uploadMedia.isWriterSupported(mediaData.getName())
            || uploadMedia.isReaderSupported(mediaData.getName());

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
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
                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
                {
                    LogUtil.put(LogFactory.getInstance("Saving Small Image: " + this.itemInterface.getSmallImage(), this, "saveFiles()"));
                }

                MediaUtil.saveImageFile(
                    originalImageFile, itemInterface.getSmallImage(),
                    fullPath, mediaData,
                    this.SMALLWIDTH, this.SMALLHEIGHT);

                MediaUtil.saveImageFile(
                    originalImageFile, itemInterface.getMediumImage(),
                    fullPath, mediaData,
                    this.MEDIUMWIDTH, this.MEDIUMHEIGHT);

                MediaUtil.saveImageFile(
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

                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
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
                    if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
                    {
                        LogUtil.put(LogFactory.getInstance("Saving Small Image: " + this.itemInterface.getSmallImage(), this, "saveFiles()"));
                    }

                    MediaUtil.saveImageFile(
                        originalImageFile, itemInterface.getSmallImage(),
                        fullPath, defaultMediaData,
                        this.SMALLWIDTH, this.SMALLHEIGHT);

                    MediaUtil.saveImageFile(
                        originalImageFile, itemInterface.getMediumImage(),
                        fullPath, defaultMediaData,
                        this.MEDIUMWIDTH, this.MEDIUMHEIGHT);

                    MediaUtil.saveImageFile(
                        originalImageFile, itemInterface.getLargeImage(),
                        fullPath, defaultMediaData,
                        this.LARGEWIDTH, this.LARGEHEIGHT);
                } else
                {
                    //Create Multiple files without resizing

                    AbFile smallImageFile = new AbFile(itemInterface.getSmallImage());
                    smallImageFile.createNewFile();

                    fileUtil.write(new ByteArrayInputStream(byteArray), smallImageFile);
                    //FileItemUtil.write(fileItem, smallImageFile);

                    AbFile mediumImageFile = new AbFile(itemInterface.getMediumImage());
                    smallImageFile.createNewFile();

                    fileUtil.write(new ByteArrayInputStream(byteArray), mediumImageFile);
                    //FileItemUtil.write(fileItem, mediumImageFile);

                    AbFile largeImageFile = new AbFile(itemInterface.getSmallImage());
                    largeImageFile.createNewFile();

                    fileUtil.write(new ByteArrayInputStream(byteArray), largeImageFile);
                    //FileItemUtil.write(fileItem, largeImageFile);
                }
            }
        }

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
        {
            LogUtil.put(LogFactory.getInstance("End", this, "saveFiles()"));
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
    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
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

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("Saving Original Image: " + originalImageFile, this, "saveFiles()"));
    }

    originalImageFile.createNewFile();

    FileItemUtil.write(fileItem, originalImageFile);

    UploadMediaSingleton uploadMedia = UploadMediaSingleton.getInstance();
    boolean isMediaSupported =
    uploadMedia.isWriterSupported(mediaData.getName()) ||
    uploadMedia.isReaderSupported(mediaData.getName());

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("Is Image Type: " + mediaData.getName() + " supported: " + isMediaSupported, this, "saveFiles()"));
    }

    if(isMediaSupported)
    {
    boolean isMediaResizable =
    uploadMedia.isWriterMedia(
    mediaData.getName(), MediaTypeData.RESIZABLE_MEDIA);

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
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

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("Saving Small Image: " + this.itemInterface.getSmallImage(), this, "saveFiles()"));
    }

    MediaUtil.saveBufferedImageFile(
    originalImageFile, itemInterface.getSmallImage(),
    fullPath, mediaData,
    this.SMALLWIDTH, this.SMALLHEIGHT);

    MediaUtil.saveBufferedImageFile(
    originalImageFile, itemInterface.getMediumImage(),
    fullPath, mediaData,
    this.MEDIUMWIDTH, this.MEDIUMHEIGHT);

    MediaUtil.saveBufferedImageFile(
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

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
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

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("Saving Small Image: " + this.itemInterface.getSmallImage(), this, "saveFiles()"));
    }

    MediaUtil.saveBufferedImageFile(
    originalImageFile, itemInterface.getSmallImage(),
    fullPath, defaultMediaData,
    this.SMALLWIDTH, this.SMALLHEIGHT);

    MediaUtil.saveBufferedImageFile(
    originalImageFile, itemInterface.getMediumImage(),
    fullPath, defaultMediaData,
    this.MEDIUMWIDTH, this.MEDIUMHEIGHT);

    MediaUtil.saveBufferedImageFile(
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

    FileItemUtil.write(fileItem, smallImageFile);

    AbFile mediumImageFile =
    new AbFile(itemInterface.getMediumImage());
    smallImageFile.createNewFile();

    FileItemUtil.write(fileItem, mediumImageFile);

    AbFile largeImageFile =
    new AbFile(itemInterface.getSmallImage());
    largeImageFile.createNewFile();

    FileItemUtil.write(fileItem, largeImageFile);
    }
    }
    }

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
    {
    LogUtil.put(LogFactory.getInstance("End", this, "saveFiles()"));
    }
    return this.itemInterface;
    }
     */
}
