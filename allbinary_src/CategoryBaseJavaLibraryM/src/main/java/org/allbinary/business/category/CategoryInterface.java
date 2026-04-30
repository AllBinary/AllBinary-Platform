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
   Vector getChildNodes();
   
   boolean addChildProperty(CategoryPropertiesInterface categoryPropertiesInterface);
   boolean addChild(CategoryInterface categoryInterface);
   boolean removeChild(CategoryInterface categoryInterface);
   boolean isLeaf() throws Exception;
   
   CategoryPropertiesInterface getProperties();

   void setProperties(CategoryPropertiesInterface categoryPropertiesInterface);

   CategoryHierarchyInterface getHierarchy();

   void setHierarchy(CategoryHierarchyInterface categoryHierarchyInterface);

   AbPath getPath() throws Exception;
   AbPath getFilePath() throws Exception;
   AbPath getWebAppPath() throws Exception;
   AbPath getRootFilePath() throws Exception;
   
   void log() throws Exception;
}
