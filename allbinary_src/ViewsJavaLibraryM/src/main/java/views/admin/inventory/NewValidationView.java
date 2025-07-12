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

import org.allbinary.business.user.commerce.inventory.item.BasicItem;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class NewValidationView extends InventoryItemView implements ValidationComponentInterface
{
   public NewValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface, StringUtil.getInstance().EMPTY_STRING);
      
      this.itemInterface = (ItemInterface) new BasicItem();
   }

   public Boolean isValid()
   {
      try
      {
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
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
      return "Error Creating New Item";
   }
}
