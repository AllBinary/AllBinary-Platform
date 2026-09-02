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
package org.allbinary.data.tables.transform.info;

import java.util.HashMap;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import javax.servlet.jsp.PageContext;

import org.allbinary.data.tables.BasicDataTableInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public interface TransformInfoEntityInterface 
   extends BasicDataTableInterface
{      
   TransformInfoInterface get(String name, HashMap propertiesHashMap, PageContext pageContext) throws Exception;
   //public TransformInfoInterface get(String name) throws Exception;

   BasicArrayList getObjectConfigs(String storeName) throws Exception;
   
   BasicArrayList getNames(String storeName) throws Exception;
   
   void delete(String name);
   
   void insert(BasicArrayList values);

   void update(HashMap updatedValues);
}
