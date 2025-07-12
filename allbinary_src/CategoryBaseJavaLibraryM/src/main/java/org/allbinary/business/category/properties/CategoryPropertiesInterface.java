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

import org.allbinary.business.category.hierarchy.CategoryHierarchyInterface;
import org.allbinary.data.tables.TableMappingInterface;
import org.allbinary.logic.control.validate.ValidationInterface;
import org.allbinary.logic.io.path.AbPath;

public interface CategoryPropertiesInterface extends TableMappingInterface, ValidationInterface
{
   public void setValue(String value);
   public String getValue();
   
   //Provides the path of the category
   public AbPath getWebAppPath() throws Exception;
   public AbPath getPath(CategoryHierarchyInterface categoryHierarchyInterface) throws Exception;
   public String getFileName() throws Exception;
   
   public boolean isRoot() throws Exception;
   public boolean isRealRoot() throws Exception;
}
