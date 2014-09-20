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
package tags.admin.customizers;

import java.util.*;

import javax.servlet.*;

import javax.servlet.http.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;




import tags.StoreValidationTransformTag;

import org.allbinary.logic.communication.http.request.AbResponseHandler;

public class ViewCustomizersTag extends StoreValidationTransformTag
{
   public ViewCustomizersTag()
   {
   }

   public int doStartTag() throws JspTagException
   {
      try
      {
         this.setName("Add Validation Customizers View");
                                                   
         this.setObjectFile("views.admin.customizers.CustomizersValidationView");
         return super.doStartTag();
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
}
