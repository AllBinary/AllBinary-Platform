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
package views.generic.user;

import org.allbinary.business.user.username.UserName;
import org.allbinary.data.tables.user.UserEntityFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class EditValidationView extends UserView 
   implements ValidationComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public EditValidationView(TransformInfoInterface transformInfoInterface) 
      throws Exception
   {
      super(transformInfoInterface);
   }

   public Boolean isValid()
   {
      try
      {
         if(UserName.getInstance().isValid(this.getWeblisketSession().getUserName()) == Boolean.FALSE)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("Failed to validate username",this,commonStrings.IS_VALID);
            }
            
            return Boolean.FALSE;
         }

         this.user = UserEntityFactory.getInstance().getUser(this.getWeblisketSession().getUserName());
         
         if(this.user==null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("User does not exist",this,commonStrings.IS_VALID);
            }
            return Boolean.FALSE;
         }
         
         if(this.user.isValid() == Boolean.FALSE)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("User exists but is invalid - Probably manually modified",this,commonStrings.IS_VALID);
            }
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
         StringMaker stringBuffer = new StringMaker();
         
         if(UserName.getInstance().isValid(this.getWeblisketSession().getUserName()) == Boolean.FALSE)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("Failed to validate username",this,"validationInfo()");
            }
            stringBuffer.append("Session User Name is not valid - Your session data has been logged!");
         }

         if(this.user==null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("User does not exist",this,commonStrings.IS_VALID);
            }
            stringBuffer.append("User profile does not exist - Your session data has been logged!");
         }
         
         if(this.user.isValid() == Boolean.FALSE)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("User exists but is invalid - Probably manually modified",this,commonStrings.IS_VALID);
            }
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
   
}
