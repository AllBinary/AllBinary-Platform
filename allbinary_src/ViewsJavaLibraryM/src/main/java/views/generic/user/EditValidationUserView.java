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

public class EditValidationUserView extends UserView 
   implements ValidationComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String userName;
   
   public EditValidationUserView(TransformInfoInterface transformInfoInterface) 
      throws Exception
   {
      super(transformInfoInterface);
      
      //HttpServletRequest httpServletRequest = (HttpServletRequest)
        //  this.getPageContext().getRequest();

      //HashMap hashMap = new RequestParams(httpServletRequest).toHashMap();
      //this.userName = new UserName(hashMap).get();
      this.userName = this.getWeblisketSession().getUserName();
   }
   
   public Boolean isValid()
   {
      try
      {         
         if(UserName.getInstance().isValid(this.userName) == Boolean.TRUE)
         {
            this.user = UserEntityFactory.getInstance().getUser(this.userName);
            if(user==null)
            {
            	return Boolean.FALSE;
            }
         }
         else return Boolean.FALSE;
         
         return this.user.isValid();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to validate form",this,commonStrings.IS_VALID,e);
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringMaker stringBuffer = new StringMaker();
         
         if(UserName.getInstance().isValid(this.userName).booleanValue())
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("User Name is valid",this,"validationInfo()");
            }
            
            this.user = UserEntityFactory.getInstance().getUser(this.userName);
            if(user==null)
            {
               stringBuffer.append("User does not exist.<br />");
               return stringBuffer.toString();
            }
         }
         else
         {
            stringBuffer.append("User Name is not valid.<br />");
            return stringBuffer.toString();
         }
         
         if(this.user.isValid() == Boolean.FALSE)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("User exists but is invalid - Probably manually modified",this,"validationInfo()");
            }
            stringBuffer.append("User data is not valid - Please call administrator.<br />");
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
         return "Error Validating Form";
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
