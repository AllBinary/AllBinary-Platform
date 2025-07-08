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
package org.allbinary.logic.java.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InterfaceCastProxy implements java.lang.reflect.InvocationHandler
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private Object object;
   
   private InterfaceCastProxy(Object object)
   {
      this.object = object;
   }
   
   public static Object newInstance(Object object)
   {
      return Proxy.newProxyInstance(
         object.getClass().getClassLoader(),
         object.getClass().getInterfaces(),//interfaceArray
         new InterfaceCastProxy(object));
   }
   
   //Invokes on the dynamic class and uses proxy class info for its invocation
   public Object invoke(Object proxyObject, Method proxyMethod, Object[] proxyArgs) throws java.lang.Throwable
   {
      String methodName = proxyMethod.getName();      
      Method realMethod = this.object.getClass().getMethod(methodName, proxyMethod.getParameterTypes());      
      
      //make sure that the function being called is really accessible
      //not this is the real object and not the proxy object methods
      //the proxy object methods should be not accessible always
      if(!realMethod.isAccessible())
         realMethod.setAccessible(true);      
      return proxyMethod.invoke(this.object,proxyArgs);      
   }      

   /*
       Class[] interfaceArray = object.getClass().getInterfaces();
      String interfaceNames = "Interface Names: ";
      for(int index =0; index< interfaceArray.length; index++)
      {         
         interfaceNames += interfaceArray[index].getName();
         interfaceNames += " ";
      }
      
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
         {
            LogUtil.put(interfaceNames, "InterfaceCastProxy","newInst");
         }      
*/

}
