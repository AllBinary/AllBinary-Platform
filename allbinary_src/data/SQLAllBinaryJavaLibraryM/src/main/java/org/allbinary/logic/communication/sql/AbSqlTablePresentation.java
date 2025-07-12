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
package org.allbinary.logic.communication.sql;

import org.allbinary.business.init.db.DbConnectionInfo;

public class AbSqlTablePresentation extends AbSqlBasic
{
   
   public AbSqlTablePresentation(DbConnectionInfo databaseConnectionInfoInterface)
   {
      super(databaseConnectionInfoInterface);
   }

   /*
   public String getTable()
   {
      String sqlStatement = "SELECT * FROM " + tableName;
      
      try
      {
         this.executeQuery(sqlStatement);
         return this.getResultTable();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
         {
            logUtil.put("Get Table Failed\nSQL Statement: " + sqlStatement,this,"getTable",e);
         }
         return "Get Table Failed";
      }
   }
   */
   
   /*
   public String getTableWhere(String key,String value)
   {
      String sqlStatement = "SELECT * FROM " + tableName;
      sqlStatement += sqlStrings.WHERE;
      sqlStatement +=  key;
      sqlStatement += sqlStrings.EQUAL_QUOTE;
      sqlStatement += value + sqlStrings.CLOSE_QUOTE;
      
      try
      {
         this.executeQuery(sqlStatement);
         return this.getResultTable();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
         {
            logUtil.put("Get Table Failed\nSQL Statement: " + sqlStatement,this,"getTableWhere(key,value)",e);
         }
         return "getTableWhere(key,value)";
      }
   }
   
   public String getTableWhere(HashMap whereKeyValuePairs)
   {
      String sqlStatement = "SELECT * FROM " + tableName;
      
      sqlStatement += sqlStrings.WHERE;
      
      Set set = whereKeyValuePairs.keySet();
      Object[] keyArray = set.toArray();
      int size = keyArray.length;
      for (int i = 0; i < size; i++)
      {
         String key = (String) keyArray[i];
         String value = (String) whereKeyValuePairs.get(key);
         
         sqlStatement +=  key;
         sqlStatement += sqlStrings.EQUAL_QUOTE;
         sqlStatement += value + sqlStrings.CLOSE_QUOTE;
         if(i < size - 1) sqlStatement += sqlStrings.AND;
      }
      
      try
      {
         this.executeQuery(sqlStatement);
         return this.getResultTable();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
         {
            logUtil.put("Get Table Failed\nSQL Statement: " + sqlStatement,this,"getTableWhere()",e);
         }
         return "getTableWhere()";
      }
   }
   
   public String getTableWhereBetween(HashMap whereKeyValuePairs,
   String betweenColumn, String smallest, String largest)
   {
      String sqlStatement = "SELECT * FROM " + tableName;
      
      sqlStatement += sqlStrings.WHERE;
      
      Set set = whereKeyValuePairs.keySet();
      Object[] keyArray = set.toArray();
      int size = keyArray.length;
      for (int i = 0; i < size; i++)
      {
         String key = (String) keyArray[i];
         String value = (String) whereKeyValuePairs.get(key);
         
         sqlStatement +=  key;
         sqlStatement += sqlStrings.EQUAL_QUOTE;
         sqlStatement += value + sqlStrings.CLOSE_QUOTE;
         sqlStatement += sqlStrings.AND;
      }
      
      sqlStatement +=  betweenColumn;
      sqlStatement += " > \"";
      sqlStatement += smallest + sqlStrings.CLOSE_QUOTE;
      sqlStatement += sqlStrings.AND;
      sqlStatement +=  betweenColumn;
      sqlStatement += " < \"";
      sqlStatement += largest + sqlStrings.CLOSE_QUOTE;
      
      try
      {
         this.executeQuery(sqlStatement);
         return this.getResultTable();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
         {
            logUtil.put("Get Table Failed\nSQL Statement: " + sqlStatement,this,"getTableWhereBetween()",e);
         }
         return "getTableWhereBetween() Failed";
      }
   }

    */
   
   /*
   public String getInputWhere(String key,String value)
   {
      String sqlStatement = "SELECT * FROM " + tableName;
      sqlStatement += sqlStrings.WHERE;
      sqlStatement +=  key;
      sqlStatement += sqlStrings.EQUAL_QUOTE;
      sqlStatement += value + sqlStrings.CLOSE_QUOTE;
      
      try
      {         
         ResultSet rset = this.executeSQLStatement(sqlStatement);
         
         ResultSetMetaData rsmd = rset.getMetaData();         
         StringMaker stringBuff = new StringMaker();
         
         int colNum = rsmd.getColumnCount();
         
         rset.next();
         for (int i=1; i <= colNum; i++)
         {
            String columnName = rsmd.getColumnName(i);
            String columnValue = rset.getString(i);
            if(columnValue==null || columnValue.compareTo("null)==0) columnValue=stringUtil.EMPTY_STRING;
            stringBuff.append(new HtmlTextInput(columnName + CommonLabels.getInstance().COLON_SEP,columnName,columnValue,"<br>\n").toString();
         }                  
         
         String form = stringBuff.toString();
         return form;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
         {
            logUtil.put("Get Table Failed\nSQL Statement: " + sqlStatement,this,"getInputWhere",e);
         }
         return "Get Html Inputs Failed";
      }
   }
*/

}
