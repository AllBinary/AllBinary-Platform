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
package org.allbinary.business.category;

import java.util.Vector;

import org.allbinary.business.category.hierarchy.CategoryHierarchyInterface;
import org.allbinary.business.category.properties.CategoryPropertiesInterface;
import org.allbinary.data.tables.TableMappingInterface;
import org.allbinary.logic.control.validate.ValidationInterface;
import org.allbinary.logic.io.path.AbPath;

public interface CategoryInterface extends TableMappingInterface, ValidationInterface
{
   public Vector getChildNodes();
   
   public boolean addChildProperty(CategoryPropertiesInterface categoryPropertiesInterface);
   public boolean addChild(CategoryInterface categoryInterface);
   public boolean removeChild(CategoryInterface categoryInterface);
   public boolean isLeaf() throws Exception;
   
   public CategoryPropertiesInterface getProperties();

   public void setProperties(CategoryPropertiesInterface categoryPropertiesInterface);

   public CategoryHierarchyInterface getHierarchy();

   public void setHierarchy(CategoryHierarchyInterface categoryHierarchyInterface);

   public AbPath getPath() throws Exception;
   public AbPath getFilePath() throws Exception;
   public AbPath getWebAppPath() throws Exception;
   public AbPath getRootFilePath() throws Exception;
   
   public void log() throws Exception;
}
