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

import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.Document;


import abcs.logic.basic.string.StringValidationUtil;

import allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;

import allbinary.business.user.commerce.inventory.item.BasicItemData;


import allbinary.logic.communication.http.request.RequestParams;


import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.control.validate.ValidationComponentInterface;


public class DeleteFileValidationView extends InventoryItemView implements ValidationComponentInterface
{
   private String id;
      
   public DeleteFileValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface,"");
      
      this.getFormData();
   }
   
   public void getFormData() throws Exception
   {
      HashMap hashMap = new RequestParams(request).toHashMap();
      
      if(hashMap==null) throw new Exception("No Request Params Found");
      
      this.id = (String) hashMap.get(BasicItemData.ID);
   }
   
   public Boolean isValid()
   {
      try
      {
         if(id==null || !StringValidationUtil.getInstance().isNumber(this.id))
         {
            return Boolean.FALSE;
         }
         
         this.itemInterface = 
                 InventoryEntityFactory.getInstance().getInventoryEntityInstance().getItem(this.id);
         
         if(this.itemInterface==null)
            return Boolean.FALSE;
         
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
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         if(id == null || !StringValidationUtil.getInstance().isNumber(this.id))
         {
            stringBuffer.append("Id is not valid.<br />");
         }
         
         if(InventoryEntityFactory.getInstance().getInventoryEntityInstance().getItem(this.id)==null)
         {
            stringBuffer.append("Item does not exist.<br />");
         }
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         return "Unkown Validation Error.<br />";
      }
   }
}
