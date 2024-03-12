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
package admin.taghelpers;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;

public class CustomLoaderHelperFactory implements TagHelperFactoryInterface
{
   private static final String CLASSNAME = "admin.taghelpers.CustomLoaderHelper";
   private static final String FACTORYNAME = "CustomLoaderHelperFactory";
   
   public CustomLoaderHelperFactory()
   {
   }

   public Object getInstance(
       final HashMap hashMap, final PageContext pageContext) 
      throws LicensingException
   {
       final AbeClientInformationInterface abeClientInformation = 
           ServiceClientInformationInterfaceFactory.getInstance();
       
      return HelperFactory.getInstance(abeClientInformation, FACTORYNAME, CLASSNAME, hashMap, pageContext);
   }  

   public Object getInstance(final AbeClientInformationInterface abeClientInformation) 
      throws LicensingException
   {
      return HelperFactory.getInstance(abeClientInformation, FACTORYNAME, CLASSNAME);
   }
}
