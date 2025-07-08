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
package admin.taghelpers;

import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.business.installer.Portion;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.NewUserFactory;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.UserInterface;
import org.allbinary.business.user.username.UserName;
import org.allbinary.data.tables.user.UserEntityFactory;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.logic.communication.http.request.RequestParams;
import org.allbinary.logic.communication.sql.AbSqlTableUtil;

public class UserHelper extends Table
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private final HashMap hashMap;
   private final PageContext pageContext;
   private final HttpServletRequest request;
   
   private final String path;

   private final Portion portion;
   
   public UserHelper(HashMap hashMap, PageContext pageContext) throws Exception
   {
      this.hashMap = hashMap;
      this.pageContext = pageContext;
      
      this.request = (HttpServletRequest) pageContext.getRequest();
      
      this.path = URLGLOBALS.getMainPath() + FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH;

      this.portion = new Portion(hashMap);
      
      /*
      String role = (String) hashMap.get(UserRoleData.NAME);
      if(role!=null && role.compareTo(stringUtil.EMPTY_STRING)!=0)
      {
         this.user.setRole(role);
      }
       **/
   }
      
   public String delete()
   {
      try
      {
         HashMap requestHashMap = 
            new RequestParams(this.request).toHashMap();
         UserName userName = new UserName(requestHashMap);

         UserEntityFactory.getInstance().deleteWhere(
             UserData.USERNAME, userName.get());

         StringBuffer stringBuffer = new StringBuffer();
         
         stringBuffer.append("Successfully Removed the user with ");
         stringBuffer.append(UserData.USERNAME);
         stringBuffer.append("=");
         stringBuffer.append(userName.get());
         stringBuffer.append(" from to the user table");
         
         String success = stringBuffer.toString();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"delete()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to remove user with " + UserData.USERNAME + " from User table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"delete()",e);
         }
         return error;
      }
   }

   public String insert()
      throws Exception
   {
      try
      {
         UserInterface userInterface = NewUserFactory.getInstance(this.request, hashMap);

         //Property Enable can be included in Tag for user inserts
         String enable = (String) this.hashMap.get(EntryData.getInstance().ENABLE);
         if(!StringValidationUtil.getInstance().isEmpty(enable))
         {
            userInterface.setEnable(enable);
         }

         Vector values = userInterface.toVector();

         UserEntityFactory.getInstance().insert(values);

         String success = "New User Successfully added to the Users Table";

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"add()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add User";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"add()",e);
         }
         return error;
      }
   }
   
   public String update()
   {
      try
      {
         UserInterface user = NewUserFactory.getInstance(this.request, hashMap);
         HashMap values = user.toHashMap();
         
         UserEntityFactory.getInstance().update(user.getUserName(),values);
         
         String success
         = "New User Successfully added to the Users Table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"update()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add User";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"update()",e);
         }
         return error;
      }
      
   }
         
   public String drop()
   {
      try
      {
         String success = UserEntityFactory.getInstance().dropTable();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
         {
            logUtil.put(success,this,commonStrings.DROP);
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to drop user table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,commonStrings.DROP,e);
         }
         return error;
      }
   }
   
   public String create()
   {
      try
      {
         String success = UserEntityFactory.getInstance().createTable();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
         {
            logUtil.put(success,this,"create()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to create user table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"create()",e);
         }
         return error;
      }
   }
   
   public String restore()
   {
      try
      {
         final String success = "Restore Successful";
         final String result = AbSqlTableUtil.getInstance().restoreTable(UserEntityFactory.getInstance(), this.portion);
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"restore()");
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to restore backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"restore()",e);
         }
         return error;
      }
   }
   
   public String backup()
   {
      try
      {
         final String success = "Restore Successful";
         final String result = AbSqlTableUtil.getInstance().backupTable(UserEntityFactory.getInstance());
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
         {
            logUtil.put(success,this,"backup()");
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to make backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"backup()",e);
         }
         return error;
      }
   }
      
}
