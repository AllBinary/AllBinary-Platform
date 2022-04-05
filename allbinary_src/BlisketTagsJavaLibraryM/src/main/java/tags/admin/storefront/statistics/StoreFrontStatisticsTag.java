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
package tags.admin.storefront.statistics;

import org.allbinary.logic.system.security.licensing.LicensingException;

import tags.StoreValidationTransformTag;

import org.allbinary.logic.communication.http.request.AbResponseHandler;

import javax.servlet.jsp.JspTagException;

public class StoreFrontStatisticsTag extends StoreValidationTransformTag
{
   public StoreFrontStatisticsTag()
   {
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.getCommand()!=null)
         {
            if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.VIEW)==0)
            {
               this.setName("Validation StoreFrontStatistics View");
               this.setObjectFile("views.admin.storefront.statistics.ValidationStoreFrontStatisticsView");
            }
            else
               {
                  throw new Exception("No Such View Command: " + this.getCommand());
               }

            return super.doStartTag();
         }
         throw new Exception("Command Null");
      }
      catch(LicensingException e)
      {
         AbResponseHandler.sendJspTagLicensingRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
}
