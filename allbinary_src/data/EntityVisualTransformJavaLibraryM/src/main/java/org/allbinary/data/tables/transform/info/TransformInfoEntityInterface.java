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

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import javax.servlet.jsp.PageContext;
import java.util.HashMap;
import java.util.Vector;
import org.allbinary.data.tables.BasicDataTableInterface;

public interface TransformInfoEntityInterface 
   extends BasicDataTableInterface
{      
   public TransformInfoInterface get(String name, HashMap propertiesHashMap, PageContext pageContext) throws Exception;
   //public TransformInfoInterface get(String name) throws Exception;

   public Vector getObjectConfigs(String storeName) throws Exception;
   
   public Vector getNames(String storeName) throws Exception;
   
   public void delete(String name);
   
   public void insert(Vector values);

   public void update(HashMap updatedValues);
}
