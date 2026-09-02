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
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import org.allbinary.logic.StdUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringValidationUtil;

public class InventoryColumnUtil
{    
    private static final InventoryColumnUtil instance = new InventoryColumnUtil();

    public static InventoryColumnUtil getInstance()
    {
        return InventoryColumnUtil.instance;
    }

    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final BasicItemData basicItemData = BasicItemData.getInstance();
    
    //private final String NEXTCATEGORY = "Next Item Category: ";

    //Doesn't really get keywords
    public BasicArrayList getColumnWhereLike(
        InventoryEntity inventoryEntity, String category, String column)
    {
        this.logUtil.putF("Start Category: " + category, this, "getKeywords");

        /////////
        // TWB - GAE upgrade uses JIQL and it doesn't like LIKE in the SQL so I
        // fixed it
        // BasicArrayList keywords = this.inventory.getColumnWhereLike(
        // basicItemData.KEYWORDS, basicItemData.CATEGORY,
        // storeFront.getCategoryPath() + AbSqlData.ANYMULTICHARACTERMATCH);

        BasicArrayList keywords = new BasicArrayListD();

        BasicArrayList vectorOfHashMaps = inventoryEntity.getAllRows();

        int size = vectorOfHashMaps.size();
        for (int index = 0; index < size; index++)
        {
            HashMap hashMap = (HashMap) vectorOfHashMaps.get(index);

            String categoryName = (String) hashMap.get(basicItemData.CATEGORY);

            //this.logUtil.putF(NEXTCATEGORY + categoryName, this, "getKeywords");

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
