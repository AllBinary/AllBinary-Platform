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
package allbinary.business.category.store.theme;

import abcs.data.tree.dom.DomSearchHelper;
import allbinary.business.category.Category;
import allbinary.business.category.CategoryInterface;
import allbinary.business.category.properties.CategoryPropertiesFactoryInterface;
import allbinary.logic.visual.theme.ThemeData;
import allbinary.logic.visual.theme.ThemeValidation;
import allbinary.logic.visual.theme.ThemesData;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Vector;

public class StoreThemeCategory extends Category implements StoreThemeCategoryInterface
{
   private TransformInfoInterface transformInfoInterface;
   //Vector of themes
   private Vector themeVector;

   //New Loner Category
   public StoreThemeCategory(
      TransformInfoInterface transformInfoInterface,
      CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface, 
      int level) throws Exception
   {
      super(categoryPropertiesFactoryInterface, level);

      this.transformInfoInterface = transformInfoInterface;
      this.themeVector = new Vector();
   }
   
   //New Category With Parent Child Relationships
   public StoreThemeCategory(
      TransformInfoInterface transformInfoInterface,
      CategoryInterface rootCategoryInterface, 
      CategoryInterface parentCategoryInterface,
      CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface)
      throws Exception
   {
      super(rootCategoryInterface, parentCategoryInterface, 
         categoryPropertiesFactoryInterface);

      this.transformInfoInterface = transformInfoInterface;
      this.themeVector = new Vector();
   }

   public StoreThemeCategory(
      TransformInfoInterface transformInfoInterface,
      Node node,
      CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface, int level) 
      throws Exception
   {
      super(categoryPropertiesFactoryInterface, level);

      this.transformInfoInterface = transformInfoInterface;
      this.themeVector = new Vector();
      this.addThemes(node);
   }
   
   //New Category With Parent Child Relationships
   public StoreThemeCategory(
      TransformInfoInterface transformInfoInterface,
      Node node,
      CategoryInterface rootCategoryInterface, 
      CategoryInterface parentCategoryInterface,
      CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface) 
      throws Exception
   {
      super(rootCategoryInterface, parentCategoryInterface, 
         categoryPropertiesFactoryInterface);

      this.transformInfoInterface = transformInfoInterface;
      this.themeVector = new Vector();
      this.addThemes(node);
   }
   
   public TransformInfoInterface getTransformInfoInterface()
   {
      return this.transformInfoInterface;
   }
   
   public void addThemes(Node node) throws Exception
   {
      Node themesNode = 
         DomSearchHelper.getNode(ThemesData.getInstance().NAME, node.getChildNodes());
      
      NodeList nodeList = themesNode.getChildNodes();
      for(int index = 0; index < nodeList.getLength(); index++)
      {
         Node themeNode = nodeList.item(index);
         
         if(themeNode.getNodeName().compareTo(ThemeData.getInstance().NAME) == 0)
         {
            this.themeVector.add(
               new ThemeValidation(this, themeNode));
         }
      }
   }

   //ThemeInterface themeInterface
   public void addTheme(ThemeValidation themeValidation)
   {
      //Interface
      this.themeVector.add(themeValidation);
   }
   
   public Vector getThemes()
   {
      return this.themeVector;
   }
      
   public Boolean isValid() throws Exception
   {
      if(!super.isValid().booleanValue())
      {
         return Boolean.FALSE;
      }
      
      /*
      Iterator iter = this.themeVector.iterator();
      while(iter.hasNext())
      {
         Category category = (Category) iter.next();
         if(!category.isValid().booleanValue()) return Boolean.FALSE;
      }
       */
      return Boolean.TRUE;
   }

   public HashMap toHashMap() throws Exception
   {
      HashMap categoryHashMap = super.toHashMap();
      
      categoryHashMap.put(ThemesData.getInstance().NAME, this.themeVector);
      
      return categoryHashMap;
   }

   public Vector toVector() throws Exception
   {
      Vector categoryVector = super.toVector();
      //categoryVector.add(this.themeVector);
      return categoryVector;
   }
}
