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
package org.allbinary.business.category.properties;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.category.CategoryData;
import org.allbinary.business.category.CategoryInterface;
import org.allbinary.business.category.CategoryUtil;
import org.allbinary.business.category.hierarchy.CategoryHierarchyInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlData;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.util.BasicArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
/*
 *Nothing more than a parent category reference to a category on the fs.
 *i.e. unloaded category.
 */
public class CategoryProperties implements CategoryPropertiesInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private static final int MAXDEPTH = 100;
   private String category;
   
   public CategoryProperties(String name)
   {
      this.category = name;
   }
   
   public CategoryProperties(Node node)
   {
      this.category = CategoryUtil.getNameFromNode(node);
   }
   
   public CategoryProperties(HashMap categoryPropertiesHashMap)
   {
      this.category = new String((String)
          categoryPropertiesHashMap.get(CategoryData.getInstance().NAME));
   }

   public boolean isRealRoot()
   {
      return false;
   }

   public boolean isRoot()
   {
      return false;
   }

   public Object getKey()
   {
      return (Object) this.getValue();
   }
   
   public String getValue()
   {
      return this.category;
   }
   
   public AbPath getPath(CategoryHierarchyInterface categoryHierarchyInterface)
      throws Exception
   {
      final AbPathData abPathData = AbPathData.getInstance();
      final StringMaker pathStringBuffer = new StringMaker();
      final BasicArrayList list = new BasicArrayList();
      
      CategoryInterface nextParentCategoryInterface = 
         categoryHierarchyInterface.getParent();
      
      //if first not is a parent is pointing to self it is a root
      //i.e. it would create an infinite loop
      if(this != nextParentCategoryInterface.getProperties())
      {
         int depthIndex = 0;
         while(nextParentCategoryInterface != null)
         {
            //ignore the root if it is the real root to stop from using the root category value
            if(nextParentCategoryInterface.getProperties().isRealRoot())
            {
               break;
            }
            
            //if a root that is also not the real root bu an offset then add the path
            if(nextParentCategoryInterface.getProperties().isRoot())
            {
               list.add(0, nextParentCategoryInterface.getPath().toString());
            }
            else
            {
               pathStringBuffer.delete(0, pathStringBuffer.length());
               list.add(0, pathStringBuffer.append(abPathData.SEPARATOR).append(nextParentCategoryInterface.getProperties().getValue()).toString());
            }

            //at root
            if(nextParentCategoryInterface == nextParentCategoryInterface.getHierarchy().getParent())
            {
               break;
            }
            
            nextParentCategoryInterface = nextParentCategoryInterface.getHierarchy().getParent();
            
            depthIndex++;
            if(depthIndex > MAXDEPTH)
            {
               //throw new Exception("Depth is to high: Probably child == parent");
               throw new Exception("Probably Major Error");
            }
         }
      }

      pathStringBuffer.delete(0, pathStringBuffer.length());
      
      pathStringBuffer.append(abPathData.SEPARATOR).append(this.getValue());
      
      final int size = list.size();
      for(int index = 0; index < size; index++) {
          pathStringBuffer.append((String) list.get(index));
      }

      pathStringBuffer.append(abPathData.SEPARATOR);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
      {
         logUtil.put("path = " + pathStringBuffer.toString(), this, "getPath");
      }
      return new AbPath(pathStringBuffer.toString());
   }
   
   public String getFileName()
   {
      return this.getValue() + AbPathData.getInstance().EXTENSION_SEP + CategoryData.getInstance().UNCRYPTED_EXTENSION;
   }
   
   public AbPath getWebAppPath() throws Exception
   {
      throw new Exception("Not Root");
   }
   
   /*
   public String getFilePath(CategoryHierarchyInterface categoryHierarchyInterface)
      throws Exception
   {
      return this.getPath(categoryHierarchyInterface) + FileData.SEPARATOR + this.getFileName();
   }
   */
   
   public void setValue(String value)
   {
      this.category = value;
   }
   
   public Boolean isValid()
   {
      Boolean returnBoolean = Boolean.FALSE;
      if(StringValidationUtil.getInstance().isValidRequired(this.category, 1, AbSqlData.MAXSTRING))
      {
         returnBoolean = Boolean.TRUE;
      }
      return returnBoolean;
   }
   
   public HashMap toHashMap()
   {
      HashMap categoryHashMap = new HashMap();
      categoryHashMap.put(CategoryData.getInstance().NAME, this.category);
      return categoryHashMap;
   }
   
   public Vector toVector()
   {
      Vector categoryVector = new Vector();
      categoryVector.add(category);
      //categoryVector.add(new Integer(this.level).toString());
      return categoryVector;
   }
   
   public Document toValidationInfoDoc() throws Exception
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document) throws Exception
   {
      return null;
   }
   
   public String validationInfo() throws Exception
   {
      return null;
   }
}