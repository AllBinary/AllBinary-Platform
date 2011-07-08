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
package allbinary.business.category.properties.root.store.theme;

import abcs.globals.URLGLOBALS;
import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import allbinary.business.category.properties.root.store.RootStoreCategoryProperties;
import allbinary.business.context.modules.storefront.StoreFrontFactory;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import allbinary.logic.visual.transform.info.TransformInfoHttpStoreInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Node;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RootStoreThemeCategoryProperties extends RootStoreCategoryProperties 
  //implements RootCategoryPropertiesInterface, CategoryPropertiesInterface
{

   public RootStoreThemeCategoryProperties(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public RootStoreThemeCategoryProperties(
      TransformInfoInterface transformInfoInterface, AbPath abPath) throws Exception
   {
      super(transformInfoInterface, abPath);
   }
      
   public RootStoreThemeCategoryProperties(
      TransformInfoInterface transformInfoInterface, Node node) throws Exception
   {
      super(transformInfoInterface, node);
   }

   public RootStoreThemeCategoryProperties(
      TransformInfoInterface transformInfoInterface,
      HashMap categoryPropertiesHashMap) throws Exception
   {
      super(transformInfoInterface, categoryPropertiesHashMap);
   }

   public void initPath() throws Exception
   {
      TransformInfoHttpStoreInterface transformInfoHttpStoreInterface = 
         (TransformInfoHttpStoreInterface) this.transformInfoInterface;
      
      StoreFrontInterface storeFrontInterface =
         StoreFrontFactory.getInstance(
            transformInfoHttpStoreInterface.getStoreName());
      
      StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append(AbPathData.getInstance().SEPARATOR);
      stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
      stringBuffer.append(storeFrontInterface.getName());
      stringBuffer.append(AbPathData.getInstance().SEPARATOR);
      stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().THEMEPATH);
      
      AbPath abPath = new AbPath(stringBuffer.toString());

      //URLGLOBALS.getWebappPath() +
      //storeFrontInterface.getCurrentHostNamePath() + 
      HttpServletRequest httpServletRequest = (HttpServletRequest)
         transformInfoHttpStoreInterface.getPageContext().getRequest();

      this.webAppAbPath = new AbPath(
         httpServletRequest.getContextPath() + abPath.toString());

      this.setRootFilePath(new AbPath(
         URLGLOBALS.getMainPath() + abPath.toString()));
   }
}