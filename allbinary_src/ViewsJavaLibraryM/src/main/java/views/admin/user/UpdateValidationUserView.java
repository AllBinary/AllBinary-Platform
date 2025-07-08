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
package views.admin.user;



import org.allbinary.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;


import org.allbinary.logic.communication.log.LogUtil;


import org.allbinary.business.user.NewUserFactory;
import org.allbinary.business.user.UserInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.data.tables.user.UserEntityFactory;


import views.business.context.HttpContextView;

public class UpdateValidationUserView extends HttpContextView
   implements ValidationComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private UserInterface user;

   public UpdateValidationUserView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      this.user = NewUserFactory.getInstance(transformInfoInterface);
      //this.user = UserFactory.getInstance((HttpServletRequest) this.getPageContext().getRequest());
   }
   
   public Boolean isValid()
   {
      try
      {
         if(UserEntityFactory.getInstance().getUser(this.user.getUserName())==null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("User does not exist",this,commonStrings.IS_VALID);
            }
            return Boolean.FALSE;
         }
         
         if(this.user.isValid() == Boolean.FALSE)
         {
            return Boolean.FALSE;
         }

         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to validate",this,commonStrings.IS_VALID,e);
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();

         if(UserEntityFactory.getInstance().getUser(this.user.getUserName())==null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("User does not exist",this,"validationInfo()");
            }
            stringBuffer.append("Unable to update user that does not exist.<br />");
         }
         
         if(this.user.isValid() == Boolean.FALSE)
         {
            stringBuffer.append(this.user.validationInfo());
         }
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to generate validation error info",this,"validationInfo()",e);
         }
         return "Error Getting Validation Info";
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

   public String view() throws Exception
   {
      return views.ValidationOnlyTempUtil.getInstance().view(this);
   }
}
