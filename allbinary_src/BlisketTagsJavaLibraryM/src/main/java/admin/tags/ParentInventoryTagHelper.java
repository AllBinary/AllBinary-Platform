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
package admin.tags;

import javax.servlet.jsp.tagext.Tag;

import org.allbinary.logic.string.StringMaker;

/**
 *
 * @author user
 */
public class ParentInventoryTagHelper
{
    private static final ParentInventoryTagHelper instance =
        new ParentInventoryTagHelper();

    /**
     * @return the instance
     */
    public static ParentInventoryTagHelper getInstance()
    {
        return instance;
    }

    public void isValid(Object tagClass, Tag parentTag)
        throws Exception
    {
        if (parentTag == null)
        {
            throw new Exception("Must have parent tag.");
            //tags.admin.inventory.InventoryTag
            //tags.admin.inventory.DownloadItemTag
        } else if (!(parentTag instanceof tags.HelperTag))
        {
            StringMaker stringBuffer = new StringMaker();

            //tagClass
            stringBuffer.append("Must have at least a ");
            stringBuffer.append("tags.HelperTag");
            stringBuffer.append(" as parent");
            stringBuffer.append("and not: ");
            stringBuffer.append(parentTag.getClass().getName());

            throw new Exception(stringBuffer.toString());
        }
    }
}
