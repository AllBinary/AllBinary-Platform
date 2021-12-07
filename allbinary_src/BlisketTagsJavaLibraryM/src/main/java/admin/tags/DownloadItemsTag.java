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

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import admin.taghelpers.DownloadItemsHelperFactory;
import admin.taghelpers.DownloadItemsRequestHelperFactory;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;


public class DownloadItemsTag extends TableTag
{
   public DownloadItemsTag()
   {
      this.setTagHelperFactory(new DownloadItemsHelperFactory());
      this.setTagRequestHelperFactory(new DownloadItemsRequestHelperFactory());
   }

   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.isEnabled())
         {
            if(this.getCommand() != null)
            {
               if(this.getCommand().compareTo(org.allbinary.globals.GLOBALS.BACKUP) != 0 &&
               this.getCommand().compareTo(org.allbinary.globals.GLOBALS.RESTORE) != 0 &&
               this.getCommand().compareTo(this.commonStrings.CREATE) != 0 &&
               this.getCommand().compareTo(this.commonStrings.DROP) != 0)
               {
                  Tag parentTag = this.getParent();

                  ParentInventoryTagHelper.getInstance().isValid(this, parentTag);

                  this.getPropertiesHashMap().put(AbTagData.PARENT, parentTag);
               }

               return super.doStartTag();
            }
         }
         return SKIP_BODY;
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }

}
