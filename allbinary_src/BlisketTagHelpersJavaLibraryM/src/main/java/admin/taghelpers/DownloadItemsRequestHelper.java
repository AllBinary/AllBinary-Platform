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
package admin.taghelpers;

import java.util.HashMap;
import java.util.Vector;

import javax.servlet.jsp.PageContext;

import tags.HelperTag;
import views.admin.inventory.download.DownloadableInventoryItemView;
import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import admin.tags.AbTagData;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadableItem;
import org.allbinary.data.tables.user.commerce.inventory.item.downloads.DownloadItemsEntityFactory;

public class DownloadItemsRequestHelper implements ModifyTableInterface
{
    private final DownloadableItem downloadableItem;

    public DownloadItemsRequestHelper(HashMap hashMap, PageContext pageContext)
        throws Exception
    {
        HelperTag inventoryTag = (HelperTag) hashMap.get(AbTagData.PARENT);

        InventoryItemViewParentTagHelper inventoryItemViewParentTagHelper =
            InventoryItemViewParentTagHelper.getInstance();

        DownloadableInventoryItemView downloadableInventoryItemView =
            inventoryItemViewParentTagHelper.getDownloadableInventoryItemView(
            inventoryTag);

        this.downloadableItem =
            downloadableInventoryItemView.getDownloadableItem();
    }

    public String insert()
    {
        try
        {
            Vector vector = this.downloadableItem.toVector();

            DownloadItemsEntityFactory.getInstance().getDownloadItemsEntityInstance().insert(vector);

            String success = "Successfully inserted " + this.downloadableItem.getId() + " into downloaditems table";

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "insert()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to insert " + this.downloadableItem.getId() + " into downloaditems table";

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "inserts()", e));
            }
            return error;
        }
    }

    public String delete()
    {
        try
        {
            DownloadItemsEntityFactory.getInstance().getDownloadItemsEntityInstance().delete(
                this.downloadableItem.getId());

            String success = "Successfully deleted";

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "delete()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to delete";

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "delete()", e));
            }
            return error;
        }
    }

    public String update()
    {
        try
        {
            String success = "Update Successful";

            HashMap values = this.downloadableItem.toHashMap();
            DownloadItemsEntityFactory.getInstance().getDownloadItemsEntityInstance().update(values);

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(this.downloadableItem.getId() + CommonSeps.getInstance().SPACE + success, this, "update()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to update: " + this.downloadableItem.getId();

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "update()", e));
            }
            return error;
        }
    }
}
