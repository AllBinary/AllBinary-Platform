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
package allbinary.business.category.properties;

import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.category.CategoryData;
import allbinary.business.category.CategoryInterface;
import allbinary.business.category.CategoryUtil;
import allbinary.business.category.hierarchy.CategoryHierarchyInterface;
import allbinary.logic.communication.sql.AbSqlData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Vector;
/*
 *Nothing more than a parent category reference to a category on the fs.
 *i.e. unloaded category.
 */
public class CategoryProperties implements CategoryPropertiesInterface
{
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
      StringBuffer pathStringBuffer = 
         new StringBuffer(AbPathData.getInstance().SEPARATOR + this.getValue());
      
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
               pathStringBuffer.insert(0, nextParentCategoryInterface.getPath().toString());
            }
            else
            {
               pathStringBuffer.insert(0, AbPathData.getInstance().SEPARATOR +
                  nextParentCategoryInterface.getProperties().getValue());
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

      pathStringBuffer.append(AbPathData.getInstance().SEPARATOR);

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
      {
         LogUtil.put(LogFactory.getInstance("path = " + pathStringBuffer.toString(), this, "getPath"));
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