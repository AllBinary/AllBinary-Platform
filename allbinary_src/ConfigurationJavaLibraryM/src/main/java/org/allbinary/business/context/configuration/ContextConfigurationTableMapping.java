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
package org.allbinary.business.context.configuration;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.data.tables.TableMappingInterface;

public class ContextConfigurationTableMapping implements TableMappingInterface
{
   private ContextConfigurationInterface contextConfigurationInterface;
   
   public ContextConfigurationTableMapping(
      ContextConfigurationInterface contextConfigurationInterface)
   {
      this.contextConfigurationInterface = contextConfigurationInterface;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();

      return hashMap;
   }
   
   public Object getKey() throws Exception
   {
      return ContextConfigurationData.getInstance().NAME;
   }
   
   public Vector toVector() throws Exception
   {
      Vector vector = new Vector();

      return vector;
   }   
}
