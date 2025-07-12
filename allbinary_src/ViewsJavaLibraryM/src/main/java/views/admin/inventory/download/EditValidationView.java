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

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadItemData;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadableItem;
import org.allbinary.business.user.commerce.money.MoneyException;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.data.tables.user.commerce.inventory.item.downloads.DownloadItemsEntity;
import org.allbinary.data.tables.user.commerce.inventory.item.downloads.DownloadItemsEntityFactory;
import org.allbinary.logic.communication.http.request.RequestParams;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class EditValidationView 
    extends DownloadableInventoryItemView
    implements ValidationComponentInterface
{
    private String downloadItemId;

   public EditValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface, StringUtil.getInstance().EMPTY_STRING);
      
      this.getFormData();
   }
   
   public void getFormData() throws Exception
   {
      HashMap hashMap = new RequestParams(request).toHashMap();
      
      if(hashMap == null)
      {
          throw new Exception("No Request Params Found");
      }
      
      this.id = (String) hashMap.get(BasicItemData.ID);

      this.downloadItemId = (String) hashMap.get(DownloadItemData.ID);
   }
   
   public Boolean isValid() throws Exception
   {
      if(id==null || !StringValidationUtil.getInstance().isNumber(this.id))
      {
         return Boolean.FALSE;
      }
      
      this.itemInterface = InventoryEntityFactory.getInstance().getInventoryEntityInstance().getItem(this.id);
      
      if(this.itemInterface == null)
      {
    	  return Boolean.FALSE;
      }

      int downloadable = Integer.parseInt(this.itemInterface.getDownloads());

      //Load downloadable Item if it is downloadable
      if(downloadable != 0)
      {
         DownloadItemsEntity downloadItemsEntity =
             DownloadItemsEntityFactory.getInstance().getDownloadItemsEntityInstance();

         Vector vector =
             downloadItemsEntity.getForItem(this.id, this.downloadItemId);

         if(vector.size() != 1)
         {
             return Boolean.FALSE;
         }

         this.downloadableItem = (DownloadableItem) vector.get(0);
      }
      else
      {
          return Boolean.FALSE;
      }

      return Boolean.TRUE;
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
   public String validationInfo() throws MoneyException
   {
      StringMaker stringBuffer = new StringMaker();
      
      if(id==null || !StringValidationUtil.getInstance().isNumber(this.id))
      {
         stringBuffer.append("Id is not valid.<br />");
      }
      
      if(InventoryEntityFactory.getInstance().getInventoryEntityInstance().getItem(this.id)==null)
      {
         stringBuffer.append("Item does not exist.<br />");
      }

      int downloadable = Integer.parseInt(this.itemInterface.getDownloads());

      if(downloadable != 0)
      {
          if(this.downloadableItem == null)
          {
              stringBuffer.append("DownloadableItem does not exist for item.<br />");
          }
      }
      else
      {
          stringBuffer.append("Item should not have DownloadableItem.<br />");
      }
      
      return stringBuffer.toString();
   }
}
