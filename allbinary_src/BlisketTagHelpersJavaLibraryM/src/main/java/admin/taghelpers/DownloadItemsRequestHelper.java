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

import admin.tags.AbTagData;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadableItem;
import org.allbinary.data.tables.user.commerce.inventory.item.downloads.DownloadItemsEntityFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonSeps;
import tags.HelperTag;
import views.admin.inventory.download.DownloadableInventoryItemView;

public class DownloadItemsRequestHelper extends ModifyTable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

     
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

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                logUtil.put(success, this, "insert()");
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to insert " + this.downloadableItem.getId() + " into downloaditems table";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "inserts()", e);
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

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                logUtil.put(success, this, "delete()");
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to delete";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "delete()", e);
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

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                logUtil.put(this.downloadableItem.getId() + CommonSeps.getInstance().SPACE + success, this, "update()");
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to update: " + this.downloadableItem.getId();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "update()", e);
            }
            return error;
        }
    }
}
