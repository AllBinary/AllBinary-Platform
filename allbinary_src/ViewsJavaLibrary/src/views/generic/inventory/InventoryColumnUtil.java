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
package views.generic.inventory;

import java.util.HashMap;
import java.util.Vector;

import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.commerce.inventory.item.BasicItemData;
import allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;

public class InventoryColumnUtil
{
    private static final InventoryColumnUtil instance = new InventoryColumnUtil();

    public static InventoryColumnUtil getInstance()
    {
        return instance;
    }

    //private final String NEXTCATEGORY = "Next Item Category: ";

    //Doesn't really get keywords
    public Vector getColumnWhereLike(
        InventoryEntity inventoryEntity, String category, String column)
    {
        LogUtil.put(LogFactory.getInstance("Start Category: " + category, instance, "getKeywords"));

        /////////
        // TWB - GAE upgrade uses JIQL and it doesn't like LIKE in the SQL so I
        // fixed it
        // Vector keywords = this.inventory.getColumnWhereLike(
        // BasicItemData.KEYWORDS, BasicItemData.CATEGORY,
        // storeFront.getCategoryPath() + AbSqlData.ANYMULTICHARACTERMATCH);

        Vector keywords = new Vector();

        Vector vectorOfHashMaps = inventoryEntity.getAllRows();

        int size = vectorOfHashMaps.size();
        for (int index = 0; index < size; index++)
        {
            HashMap hashMap = (HashMap) vectorOfHashMaps.get(index);

            String categoryName = (String) hashMap.get(BasicItemData.CATEGORY);

            //LogUtil.put(LogFactory.getInstance(NEXTCATEGORY + categoryName, instance, "getKeywords"));

            if (!StringValidationUtil.getInstance().isEmpty(categoryName)
                && categoryName.startsWith(category))
            {
                //Add next ID
                keywords.add(hashMap.get(column));
            }
        }

        // BasicItemData.CATEGORY
        // storeFront.getCategoryPath() + AbSqlData.ANYMULTICHARACTERMATCH

        /////////
        return keywords;
    }
}
