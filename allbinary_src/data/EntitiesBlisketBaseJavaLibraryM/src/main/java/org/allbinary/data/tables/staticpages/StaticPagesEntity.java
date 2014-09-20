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
package org.allbinary.data.tables.staticpages;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.init.db.StaticPagesDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.BasicItemData;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.control.search.SearchData;

public class StaticPagesEntity extends AbSqlBean implements StaticPagesEntityInterface
{
   private final String TABLENAME = "staticpages";

    public StaticPagesEntity() 
    {
       super(new StaticPagesDbInitInfo());
       this.setTableName(TABLENAME);
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
   
   public String getFile(String store, String keywords)
   {
      HashMap whereHashMap = new HashMap();
      whereHashMap.put(StoreFrontData.getInstance().NAME, store);
      whereHashMap.put(BasicItemData.KEYWORDS, keywords);
      String file = super.getField(whereHashMap, SearchData.PAGE);
      return file;
   }
   
   /*
   public String getTable()
   {
      return super.getTable();
   }
*/
   
   public void delete(String keywords)
   {
      super.deleteWhere(BasicItemData.KEYWORDS,keywords);
   }
   
   public String dropTable()
   {
      String returnStr = super.dropTable();
      return returnStr;
   }

   /*
   public String getInputForm(String keywords)
   {
      return super.getInputWhere(SearchData.KEYWORDS, keywords);
   }
   */
   
   public void update(HashMap updatedValues)
   {
      super.updateWhere(BasicItemData.KEYWORDS,(String) updatedValues.get(BasicItemData.KEYWORDS),updatedValues);
   }

    public final String createTableStatement()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");

        stringBuffer.append(TABLENAME);
        stringBuffer.append(" (");

        stringBuffer.append(   StoreFrontData.getInstance().NAME + " VARCHAR(60) NOT NULL," +
   BasicItemData.KEYWORDS + " VARCHAR(255) NOT NULL," +
   SearchData.PAGE + " VARCHAR(255) NOT NULL,");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(StoreFrontData.getInstance().NAME);
        stringBuffer.append(",");
        stringBuffer.append(BasicItemData.KEYWORDS);
        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    //return super.createTable("CREATE TABLE " + super.getTableName() + tableData);
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
