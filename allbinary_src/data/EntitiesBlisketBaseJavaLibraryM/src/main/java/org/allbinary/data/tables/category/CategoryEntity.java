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
package org.allbinary.data.tables.category;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.init.db.InventoryDbInitInfo;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.category.Category;
import org.allbinary.business.category.CategoryData;
import org.allbinary.business.category.CategoryFactoryInterface;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class CategoryEntity extends AbSqlBean implements CategoryEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   protected final String tableName = "categories";
   
   //private CategoryFactoryInterface categoryFactoryInterface;
   
   public CategoryEntity(CategoryFactoryInterface categoryFactoryInterface)
   {
      super(new InventoryDbInitInfo());
      //this.categoryFactoryInterface = categoryFactoryInterface;
      
      this.setTableName(tableName);
      
   }

   public CategoryEntity()
   {
      super(new InventoryDbInitInfo());
      this.setTableName(tableName);
   }

   /*
   public Category getUpToLevel(Integer level)
   {
      CategoryInterface categoryInterface = 
         categoryFactoryInterface.getRootInstance(categoryPath);
      new Category();
      
      for(int index = 1; index <= level.intValue(); index++)
      {
         categoryInterface.addChild(getAtLevel(level.toString()));
      }
      return categoryInterface;
   }
   
   public Category getAtLevel(String level)
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(CategoryData.LEVEL,level);
      Vector vectorOfChildCategoriesHashMaps = super.getRows(keysAndValues);
      return new Category(vectorOfChildCategoriesHashMaps);
   }
   
   public Category getAll()
   {
      Vector vectorOfChildCategoriesHashMaps = super.getAllRows();
      return new Category(vectorOfChildCategoriesHashMaps);
   }
   */
     /*
   public Categories getSubCategories(String category)
   {
      HashMap startsWithKeysAndValues = new HashMap();
      startsWithKeysAndValues.put(CategoryData.CATEGORY, category);
      Vector vectorOfHashMaps = super.getRowsStartWith(new HashMap(), startsWithKeysAndValues);
      return new Categories(vectorOfHashMaps);
   }
      */

   //ignore child categories
   public void insert(Category category)
   {
      try
      {
         Vector categoryVector = category.toVector();

         Vector values = new Vector();
         values.add(categoryVector.get(0));
         values.add(categoryVector.get(1));
         super.insert(values);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            logUtil.put(this.commonStrings.SUCCESS,this,INSERT);
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            logUtil.put(this.commonStrings.FAILURE,this,INSERT,e);
         }
      }
   }

   public void delete(String value)
   {
      try
      {
         super.deleteWhere(CategoryData.getInstance().NAME,value);
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            logUtil.put(this.commonStrings.SUCCESS,this, commonStrings.delete);
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            logUtil.put(this.commonStrings.FAILURE,this, commonStrings.delete,e);
         }
      }
   }
   
    public final String createTableStatement()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);

        stringBuffer.append(tableName);
        stringBuffer.append(this.sqlStrings.START);

        stringBuffer.append(CategoryData.getInstance().NAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(CategoryData.getInstance().LEVEL);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(this.sqlStrings.PRIMARY_KEY);
        stringBuffer.append(CategoryData.getInstance().NAME);
        stringBuffer.append(this.sqlStrings.END);

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }

   public void update(HashMap updatedValues)
   {
      super.updateWhere(CategoryData.getInstance().NAME,(String) updatedValues.get(CategoryData.getInstance().NAME),updatedValues);
   }
   
   public String dropTable()
   {
      return super.dropTable();
   }
   
   /*
   public String getTable()
   {
      return super.getTable();
   }
    */
}
