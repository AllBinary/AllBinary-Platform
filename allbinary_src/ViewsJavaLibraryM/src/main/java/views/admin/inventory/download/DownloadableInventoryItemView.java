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

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemView;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadableItem;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadableItemView;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.http.request.MultipartRequestParams;
import org.allbinary.logic.communication.http.request.RequestMapInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FilePathData;
import org.allbinary.logic.io.file.FileUtil;
import org.allbinary.logic.io.file.zip.ZipFileUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.apache.commons.fileupload.FileItem;
import views.business.context.modules.storefront.HttpStoreComponentView;

public class DownloadableInventoryItemView
    extends HttpStoreComponentView
    implements RequestMapInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final HttpServletRequest request;
    protected String id;
    protected ItemInterface itemInterface;
    protected DownloadableItem downloadableItem;
    private HashMap requestHashMap;

    public DownloadableInventoryItemView(
        TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        super(transformInfoInterface);

        this.request = (HttpServletRequest) this.getPageContext().getRequest();
        this.getFormData();
    }

    //Hack TWB - Edit, New, Delete
    public DownloadableInventoryItemView(
        TransformInfoInterface transformInfoInterface, String empty)
        throws Exception
    {
        super(transformInfoInterface);
        this.request = (HttpServletRequest) this.getPageContext().getRequest();
    }

    public static int TYPE_ID = 11;

    public int getTypeId()
    {
        return DownloadableInventoryItemView.TYPE_ID;
    }

    private void getFormData() throws Exception
    {
        this.setRequestHashMap(new MultipartRequestParams(this.request).toHashMap());

        this.id = (String) this.getRequestHashMap().get(BasicItemData.ID);
    }

    public void addDomNodeInterfaces()
    {
        Vector vector = new Vector();

        if(this.downloadableItem != null)
        {
            vector.add(new DownloadableItemView(this.downloadableItem));
        }

        this.addDomNodeInterface(new BasicItemView(this.itemInterface, vector));
    }

    public String view() throws Exception
    {
        try
        {
            this.addDomNodeInterfaces();
            return super.view();
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
            {
                this.logUtil.put(this.commonStrings.EXCEPTION, this, "view()", e);
            }
            throw e;
        }
    }

    /*
    protected void processDownloadableFiles() throws Exception
    {
    final String[] fileKeyArray =
    {
    DownloadItemData.LICENSE_FILE, DownloadItemData.FILE
    };

    for(int index = 0; index < fileKeyArray.length; index++)
    {
    int size = this.processFile(fileKeyArray[index]);

    this.logUtil.putF("Size: " + size, this, "processFiles()");
    if(index == 1)
    {
    this.logUtil.putF("set", this, "processFiles()");
    this.getRequestHashMap().put(DownloadItemData.SIZE, Integer.valueOf(size));
    }
    }
    }
     */
    //int[]
    /*
    protected void processFile(String fileKey) throws Exception
    {
    //Do not update image if file was not provided
    final Set set = this.getRequestHashMap().keySet();
    iter = set;
    while (iter.hasNext())
    {
    String fieldName = (String) iter.next();

    if (fieldName.compareTo(fileKey) == 0)
    {
    FileItem fileItem = (FileItem)
    this.getRequestHashMap().get(fileKey);

    this.processFile(fileItem);

    //byteArray.length;
    }
    //if(!imageFile.isFile()) throw new Exception("Image File Did Not Save");
    }
    //return new int;
    }
     */
    protected void processFile(final FileItem fileItem)
        throws Exception
    {
        final String fileName = fileItem.getName();

        final String fullPath = this.getItemFilePath();
        //Make inventory item resource directory if not already there
        final AbFile itemResourceFile = AbFile.createAbFile(fullPath);
        itemResourceFile.mkdir();

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(fullPath);
        stringBuffer.append(fileName);

        this.logUtil.putF("FileName: " + fileName, this, "processFile()");

        final AbFile file = AbFile.createAbFile(stringBuffer.toString());

        this.logUtil.putF(file.getPath(), this, "processFiles()");

        file.createNewFile();

        byte[] byteArray = fileItem.get();

        FileUtil.getInstance().write(new ByteArrayInputStream(byteArray), file);
    }

    protected void unzip(final FileItem fileItem)
        throws Exception
    {
        final String fileName = fileItem.getName();
        
        final String fullPath = this.getItemFilePath();

        //AbFile itemResourceFile = AbFile.createAbFile(fullPath);

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(fullPath);
        stringBuffer.append(fileName);

        this.logUtil.putF("FileName: " + fileName, this, "unzip()");

        final AbFile file = AbFile.createAbFile(stringBuffer.toString());

        this.logUtil.putF(file.getPath(), this, "unzip()");

        ZipFileUtil.getInstance().unzip(fullPath, file, fileName);
    }

    private String getItemFilePath()
    {
        final StoreFrontInterface storeFrontInterface =
            StoreFrontFactory.getInstance(
            this.getWeblisketSession().getStoreName());

        final FilePathData filePathData = FilePathData.getInstance();
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(URLGLOBALS.getWebappPath());
        stringBuffer.append(storeFrontInterface.getCurrentHostNamePath());
        stringBuffer.append(this.itemInterface.getCategory());
        stringBuffer.appendchar(filePathData.SEPARATORCHAR);
        stringBuffer.append(this.itemInterface.getId());
        stringBuffer.appendchar(filePathData.SEPARATORCHAR);
        stringBuffer.append(this.downloadableItem.getId());
        stringBuffer.appendchar(filePathData.SEPARATORCHAR);

        return stringBuffer.toString();
    }

    private void setRequestHashMap(HashMap requestHashMap)
    {
        this.requestHashMap = requestHashMap;
    }

    public HashMap getRequestHashMap()
    {
        return this.requestHashMap;
    }

    /**
     * @return the downloadableItem
     */
    public DownloadableItem getDownloadableItem()
    {
        return this.downloadableItem;
    }
}
