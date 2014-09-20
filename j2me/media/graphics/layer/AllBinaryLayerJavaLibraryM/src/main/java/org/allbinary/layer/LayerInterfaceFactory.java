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
package org.allbinary.layer;

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class LayerInterfaceFactory
{
   private static final LayerInterfaceFactory SINGLETON = 
       new LayerInterfaceFactory();
   
   private BasicArrayList list;

   public static LayerInterfaceFactory getInstance()
   {
       return SINGLETON;
   }
   
   public void init()
   {
       list = new BasicArrayList();
   }
   
   private final String HASHTABLE_LABEL = "Hashtable: ";

   public AllBinaryLayer getInstance(Hashtable hashtable, int x, int y, int z)
           throws Exception
   {
      LogUtil.put(LogFactory.getInstance(HASHTABLE_LABEL + hashtable, this, CommonStrings.getInstance().GET_INSTANCE));

      Integer typeInteger = (Integer) hashtable.get(Layer.ID);

      /*
      if(list.objectArray.length <= typeInteger.intValue() - 1)
      {
    	  PreLogUtil.put("Layer ID: " + typeInteger.toString(), this, "getInstance");
      }
      */

      LayerInterfaceFactoryInterface layerInterfaceFactoryInterface = 
            (LayerInterfaceFactoryInterface) list.objectArray[typeInteger.intValue() - 1];

      /*
      if(layerInterfaceFactoryInterface == null)
      {
    	  PreLogUtil.put("Layer ID: " + typeInteger.toString(), this, "getInstance");
      }
      */

      return layerInterfaceFactoryInterface.getInstance(hashtable, x, y, z);
   }
   
   public void add(LayerInterfaceFactoryInterface layerInterfaceFactoryInterface)
   {
       list.add(layerInterfaceFactoryInterface);
   }
}