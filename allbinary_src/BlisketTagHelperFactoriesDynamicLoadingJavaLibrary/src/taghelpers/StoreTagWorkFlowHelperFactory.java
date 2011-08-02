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
package taghelpers;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;



import admin.taghelpers.HelperFactory;

import abcs.logic.system.security.licensing.LicensingException;

import admin.taghelpers.TagHelperFactoryInterface;

public class StoreTagWorkFlowHelperFactory implements TagHelperFactoryInterface
{
   private static final String CLASSNAME = "taghelpers.StoreTagWorkFlowHelper";
   private static final String FACTORYNAME = "taghelpers.StoreTagWorkFlowHelperFactory";
   
   public StoreTagWorkFlowHelperFactory()
   {
   }

   public Object getInstance(
      HashMap propertiesHashMap, PageContext pageContext) 
      throws Exception, LicensingException
   {
      return HelperFactory.getInstance(FACTORYNAME, CLASSNAME, propertiesHashMap, pageContext);
   }   
}
