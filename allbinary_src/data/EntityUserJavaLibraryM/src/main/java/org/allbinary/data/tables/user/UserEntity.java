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
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.EntryData;
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
            LogUtil.put(LogFactory.getInstance("Command Success",this,"insert"));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"insert",e));
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
            LogUtil.put(LogFactory.getInstance("Command Success",this,"deleteWhere"));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"deleteWhere",e));
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
         if(this.login(userName,password).compareTo(allbinary.globals.GLOBALS.LOGINSUCCESS)==0)
         {
            String role = new String(super.getField(UserData.USERNAME,userName,RoleData.ROLE));
            
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
               LogUtil.put("Command Successful",this,"getUserRole");
            }
            return role;
         }
         else
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
               LogUtil.put("Command Failed Incorrect Login",this,"getUserRole");
            }
            return "Incorrect Login";
         }
         
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put("Command Failed",this,"getUserRole",e);
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
         
         if(encryption!=null && encryption.compareTo("")!=0)
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

                stringBuffer.append("Command Success for user: ");
                stringBuffer.append(userName);
                stringBuffer.append(" Password: ");
                stringBuffer.append(password);
                stringBuffer.append("==");
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

                stringBuffer.append("Command Success but login failed for user: ");
                stringBuffer.append(userName);
                stringBuffer.append(" Password: \n\"");
                stringBuffer.append(new SuperCrypt(new Integer(encryption).intValue()).encrypt(password));
                stringBuffer.append("\"!=\"");
                stringBuffer.append(result);
                stringBuffer.append("\"");

               LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "login"));
            }
            return GLOBALS.LOGINFAILED;
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"login",e));
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
      return super.getTable();
   }
*/
   
    public final String createTableStatement()
    {
    	EntryData entryData = EntryData.getInstance();
    	
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");
        stringBuffer.append(tableName);
        stringBuffer.append(" (");

        stringBuffer.append(UserData.USERNAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(UserData.PREFIXNAME);
        stringBuffer.append(" VARCHAR(255),");

        stringBuffer.append(UserData.FIRSTNAME);
        stringBuffer.append(" VARCHAR(255),");

        stringBuffer.append(UserData.LASTNAME + " VARCHAR(255),");

        stringBuffer.append(UserData.MIDDLENAME + " VARCHAR(255),");

        stringBuffer.append(UserData.SUFFIXNAME + " VARCHAR(255),");
        stringBuffer.append(UserData.COMPANY + " VARCHAR(255),");
        stringBuffer.append(UserData.POSITIONATCOMPANY + " VARCHAR(255),");
        stringBuffer.append(UserData.MAINEMAIL + " VARCHAR(255) NOT NULL,");
        stringBuffer.append(UserData.SECONDARYEMAIL + " VARCHAR(255),");
        stringBuffer.append(UserData.HOMEPHONE + " VARCHAR(255),");
        stringBuffer.append(UserData.CELLPHONE + " VARCHAR(255),");
        stringBuffer.append(UserData.WORKPHONE + " VARCHAR(255),");
        stringBuffer.append(UserData.OTHERCONTACT + " VARCHAR(255),");
        stringBuffer.append(UserData.ELECTRONICDEVICE + " VARCHAR(255),");
        stringBuffer.append(UserData.FAX + " VARCHAR(255)," +
   UserRoleData.NAME.toString() + " VARCHAR(255) NOT NULL," +

   UserData.CONFIGURATION + " BLOB NOT NULL," +

   UserData.PERMISSIONS + " VARCHAR(255)," +
   entryData.ENCRYPTION + " BIGINT(11) UNSIGNED NOT NULL," +
   UserData.SECRET + " VARCHAR(255)," +
   UserData.PASSWORD + " VARCHAR(255) NOT NULL," +
   entryData.ENABLE + " VARCHAR(255)," +
   entryData.TIMECREATED + " BIGINT(19) UNSIGNED NOT NULL, " +
   entryData.LASTMODIFIED + " BIGINT(19) UNSIGNED NOT NULL, " +
   "PRIMARY KEY(" + UserData.USERNAME);
        //stringBuffer.append();

        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }

   public String backupTable()
   {
      return super.backupTable();
   }

   public String restoreTable(Portion portion)
   {
      return super.restoreTable(portion);
   }
}
