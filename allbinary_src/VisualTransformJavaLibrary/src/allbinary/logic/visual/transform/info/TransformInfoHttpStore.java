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
package allbinary.logic.visual.transform.info;

import abcs.globals.URLGLOBALS;
import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.modules.storefront.StoreFrontData;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;

import javax.servlet.jsp.PageContext;
import java.util.HashMap;

public class TransformInfoHttpStore extends TransformInfoHttp 
   implements TransformInfoHttpStoreInterface
{

    //TWB - for Inserting transforms into database
   public TransformInfoHttpStore(
       HashMap propertiesHashMap, PageContext pageContext)
      throws Exception
   {
      super(propertiesHashMap, pageContext, true);

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Properties HashMap: " + propertiesHashMap.toString(), this, "Constructor()"));
      }
   }

   ///TWB - the propertiesHashmap has properties set in the tag
   //It can still be used even though the name of the transformInfo is
   //the only thing required to be in properties hashmap
   //but it is currently used to specify output, type, and file that could be included
   //in a special type of viewinfo
   public TransformInfoHttpStore(
		   HashMap databaseHashMap, HashMap propertiesHashMap,
		      PageContext pageContext)
      throws Exception
   {
      super(databaseHashMap, propertiesHashMap, pageContext);

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
          StringBuffer stringBuffer = new StringBuffer();

          stringBuffer.append("Database HashMap: ");
          stringBuffer.append(databaseHashMap.toString());
          stringBuffer.append("\nProperties HashMap: ");
          stringBuffer.append(propertiesHashMap.toString());
          
         LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "Constructor(HashMap, HashMap , PageContext)"));
      }

      this.setStoreName((String) databaseHashMap.get(StoreFrontData.getInstance().NAME));
   }
   
   public TransformInfoHttpStore(StoreFrontInterface storeFrontInterface, HashMap propertiesHashMap, PageContext pageContext) throws Exception
   {
      super(propertiesHashMap, pageContext);
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Properties HashMap: " + propertiesHashMap.toString(), this, "Constructor()"));
      }
      
      this.setStoreName(storeFrontInterface.getName());
   }

   private String getPath() throws Exception
   {
       StringBuffer stringBuffer = new StringBuffer();

       stringBuffer.append(URLGLOBALS.getMainPath());
       stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
       stringBuffer.append(this.getStoreName());
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       return stringBuffer.toString();
   }

   public AbPath getTemplateFilePath() throws Exception
   {
      return new AbPath(this.getPath(), this.getTemplateFile());
   }

   public AbPath getObjectConfigFilePath() throws Exception
   {
      return new AbPath(this.getPath(), this.getObjectConfigFile());
   }

   public AbPath getDataFilePath() throws Exception
   {
      return new AbPath(this.getPath(), this.getDataFile());
   }
}