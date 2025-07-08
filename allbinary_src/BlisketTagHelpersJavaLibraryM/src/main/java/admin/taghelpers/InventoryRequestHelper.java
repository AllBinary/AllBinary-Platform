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
import views.admin.inventory.InventoryItemView;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import admin.tags.AbTagData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.data.tables.TableMappingInterface;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;

public class InventoryRequestHelper extends ModifyTable
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    //private final PageContext pageContext;
    //private final HttpServletRequest request;
    private final ItemInterface itemInterface;

    public InventoryRequestHelper(HashMap propertiesHashMap, PageContext pageContext) throws Exception
    {
        //this.pageContext = pageContext;

        HelperTag inventoryTag = (HelperTag)
            propertiesHashMap.get(AbTagData.PARENT);

        InventoryItemView itemView =
            InventoryItemViewParentTagHelper.getInstance().getInventoryItemView(
            inventoryTag);

        this.itemInterface = itemView.getItemInterface();
    }

    public String insert() throws Exception
    {
        try
        {
            TableMappingInterface dataMappingInterface =
                (TableMappingInterface) this.getItemInterface();

            Vector values = dataMappingInterface.toVector();

            InventoryEntityFactory.getInstance().getInventoryEntityInstance().insert(values);

            String success = "Successfully Added Product";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                logUtil.put(success, this, "insert()");
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to add item to Inventory";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "insert()", e);
            }
            return error;
        }
    }

    public String delete()
    {
        try
        {
            TableMappingInterface dataMappingInterface =
                (TableMappingInterface) this.getItemInterface();

            String id = (String) dataMappingInterface.getKey();
            InventoryEntityFactory.getInstance().getInventoryEntityInstance().deleteWhere(BasicItemData.ID, id);

            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Successfully Removed the item with ");
            stringBuffer.append(BasicItemData.ID);
            stringBuffer.append("=");
            stringBuffer.append(id);
            stringBuffer.append(" from to the Inventory table");

            String success = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                logUtil.put(success, this, "doStartTag()");
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to remove item with from Inventory";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "doStartTag()", e);
            }
            return error;
        }
    }

    public String update()
    {
        try
        {
            //TWB - JIQL has problem updated with commas so a short fix is to delete and then insert
            this.delete();
            this.insert();
            /*
            TableMappingInterface dataMappingInterface =
                (TableMappingInterface) this.getItemInterface();

            HashMap hashMap = dataMappingInterface.toHashMap();
            InventoryEntityFactory.getInstance().getInventoryEntityInstance().update(hashMap);

            String number = this.getItemInterface().getNumber();
            if (number != null && !StringValidationUtil.isNumber(number))
            {
                Long numberLong = new Long(number);
                long number_long = numberLong.longValue();
                if (number_long == 0)
                {
                    //send event when product is unavailable
                } else if (number_long > 0)
                {
                    //send event when product is unavailable
                } else
                {
                    throw new Exception("Sanity Check On Number Of Items In Stock");
                }
            }
            */

            String success = "Item Successfully Updated";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                logUtil.put(success, this, "update()");
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed To Update Item";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "update()", e);
            }
            return error;
        }
    }

    public ItemInterface getItemInterface()
    {
        return itemInterface;
    }
}
