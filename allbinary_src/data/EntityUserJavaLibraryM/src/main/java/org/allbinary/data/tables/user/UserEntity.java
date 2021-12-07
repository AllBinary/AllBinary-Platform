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
package org.allbinary.data.tables.user;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.CreateUserFactory;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.UserInterface;
import org.allbinary.business.user.modules.User;
import org.allbinary.business.user.role.UserRole;
import org.allbinary.business.user.role.UserRoleData;
import org.allbinary.business.user.role.UserRoleFactory;
import org.allbinary.globals.GLOBALS;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.control.crypt.SuperCrypt;

public class UserEntity extends AbSqlBean implements UserEntityInterface
{   
   private final String tableName = "user";
            
   private final String COMMAND_SUCCESS_FOR_USER = "Command Success for user: ";
   private final String PASSWORD_LABEL = " Password: ";
   private final String EQUALS = "==";

   private final String COMMAND_SUCCESS_BUT_LOGIN_FAILED = "Command Success but login failed for user: ";
   private final String INVALID_PASSWORD_LABEL = " Password: \n\"";
   private final String NOT_EQUAL = "\"!=\"";
   private final String END_QUOTES = "\"";

   public UserEntity()
   {
      super(new UserDbInitInfo());
      this.setTableName(tableName);
   }
   
   public void insert(Vector values)
   {
      try
      {
         super.insert(values);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS,this,INSERT));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE,this,INSERT,e));
         }
      }
   }
   
   public Vector getAdministrators() throws Exception
   {
      return getUsersWithRole(UserRoleFactory.getInstance().ADMINISTRATOR);
   }

   public Vector getStoreManagers(StoreFrontInterface storeFrontInterface) throws Exception
   {
      HashMap keysAndValues = new HashMap();
      Vector usersVector = new Vector();
      
      keysAndValues.put(UserRoleData.NAME.toString(), UserRoleFactory.getInstance().STOREMANAGER.toString());
      keysAndValues.put(UserData.PERMISSIONS, storeFrontInterface.getName());
      
      Vector usersHashMapVector = super.getRows(keysAndValues);
      
      Iterator iter = usersHashMapVector.iterator();
      while(iter.hasNext())
      {
         HashMap userHashMap = (HashMap) iter.next();
         if(userHashMap!=null)
            usersVector.add(new User(userHashMap));
      }
      
      return usersVector;
   }
   
   public Vector getCustomers() throws Exception
   {
      return getUsersWithRole(UserRoleFactory.getInstance().CUSTOMER);
   }

   public Vector getUsersWithRole(UserRole userRole) throws Exception
   {
      HashMap keysAndValues = new HashMap();
      Vector usersVector = new Vector();
      
      keysAndValues.put(UserRoleData.NAME.toString(), userRole.toString());
      
      Vector usersHashMapVector = super.getRows(keysAndValues);
      
      Iterator iter = usersHashMapVector.iterator();
      while(iter.hasNext())
      {
         HashMap userHashMap = (HashMap) iter.next();
         if(userHashMap!=null)
            usersVector.add(new User(userHashMap));
      }
      
      return usersVector;
   }
   
   public Vector getUsers(StoreFrontInterface storeFrontInterface)
      throws Exception
   {
      HashMap keysAndValues = new HashMap();
      Vector usersVector = new Vector();
      
      keysAndValues.put(StoreFrontData.getInstance().NAME, storeFrontInterface.getName());
      
      Vector usersHashMapVector = super.getRows(keysAndValues);
      
      Iterator iter = usersHashMapVector.iterator();
      while(iter.hasNext())
      {
         HashMap userHashMap = (HashMap) iter.next();
         if(userHashMap!=null)
            usersVector.add(new User(userHashMap));
      }
      
      return usersVector;
   }
   
   public UserInterface getUser(String userName) throws Exception
   {      
      HashMap row = new HashMap();
      row.put(UserData.USERNAME,userName);
      HashMap userHashMap = super.getRow(row);
      if(userHashMap!=null)
      {
         return CreateUserFactory.getInstance(userHashMap);
      }
      else
      {
         return null;
      }
   }   
   
   public void deleteWhere(String key,String value)
   {
      try
      {
         super.deleteWhere(key,value);
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, "deleteWhere"));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "deleteWhere", e));
         }
      }
   }
      
   //doesn't check to make sure you are using a primary key for the key value
   //parameter of the getField method
   /*
   public String getUserRole(String userName, String password)
   {
      try
      {
         if(this.login(userName,password).compareTo(org.allbinary.globals.GLOBALS.LOGINSUCCESS)==0)
         {
            String role = new String(super.getField(UserData.USERNAME,userName,RoleData.ROLE));
            
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
               LogUtil.put(LogFactory.getInstance("Command Successful",this,"getUserRole");
            }
            return role;
         }
         else
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
               LogUtil.put(LogFactory.getInstance("Command Failed Incorrect Login",this,"getUserRole");
            }
            return "Incorrect Login";
         }
         
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE,this,"getUserRole",e);
         }
         return "Error";
      }
   }
   */
   
   public String login(String userName, String password)
   {
      try
      {
         int isUserNameAndPasswordCorrect = 0;
         
         String result = super.getField(         
            UserData.USERNAME,userName,
            UserData.PASSWORD);
         
         String encryption = super.getField(         
            UserData.USERNAME,userName,
            EntryData.getInstance().ENCRYPTION);
         
         if(encryption!=null && encryption.compareTo(this.stringUtil.EMPTY_STRING)!=0)
            isUserNameAndPasswordCorrect = result.compareTo(new SuperCrypt(new Integer(encryption).intValue()).encrypt(password));
         else
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
               LogUtil.put(LogFactory.getInstance("Command Success but login failed for user: " +
                 userName + " because user did not exist",this,"login"));
            }
            return GLOBALS.LOGINFAILED;
         }

         if(isUserNameAndPasswordCorrect==0)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {                
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append(COMMAND_SUCCESS_FOR_USER);
                stringBuffer.append(userName);
                stringBuffer.append(PASSWORD_LABEL);
                stringBuffer.append(password);
                stringBuffer.append(EQUALS);
                stringBuffer.append(result);

               LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "login"));
            }
            return GLOBALS.LOGINSUCCESS;
         }
         else
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append(COMMAND_SUCCESS_BUT_LOGIN_FAILED);
                stringBuffer.append(userName);
                stringBuffer.append(INVALID_PASSWORD_LABEL);
                stringBuffer.append(new SuperCrypt(new Integer(encryption).intValue()).encrypt(password));
                stringBuffer.append(NOT_EQUAL);
                stringBuffer.append(result);
                stringBuffer.append(END_QUOTES);

               LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "login"));
            }
            return GLOBALS.LOGINFAILED;
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "login", e));
         }
         return "Error";
      }
   }

   public void update(String userName,HashMap updatedValues)
   {
      super.updateWhere(UserData.USERNAME,userName,updatedValues);
   }

   /*
   public String getUserForm(String userName)
   {
      return super.getInputWhere(UserData.USERNAME,userName);
   }
*/
   
   public String dropTables()
   {
      return super.dropTable();
   }
      
   /*
   public String getTable(String userName)
   {            
      return super.getTableWhere(UserData.USERNAME,userName);
   }
   
   public String getTable()
   {            
      return super.getTable();d
   }
*/
   
    public final String createTableStatement()
    {
    	final EntryData entryData = EntryData.getInstance();
    	
        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE)
                .append(tableName)
                .append(this.sqlStrings.START)
                .append(UserData.USERNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(UserData.PREFIXNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.FIRSTNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.LASTNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.MIDDLENAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.SUFFIXNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.COMPANY)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.POSITIONATCOMPANY)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.MAINEMAIL)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(UserData.SECONDARYEMAIL)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.HOMEPHONE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.CELLPHONE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.WORKPHONE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.OTHERCONTACT)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.ELECTRONICDEVICE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.FAX)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserRoleData.NAME.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(UserData.CONFIGURATION)
                .append(this.sqlTypeStrings.BLOB_NOT_NULL)
                .append(UserData.PERMISSIONS)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(entryData.ENCRYPTION)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(UserData.SECRET)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(UserData.PASSWORD)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(entryData.ENABLE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(entryData.TIMECREATED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(entryData.LASTMODIFIED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(this.sqlStrings.PRIMARY_KEY)
                .append(UserData.USERNAME)
                .append(this.sqlStrings.END);

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }

}
