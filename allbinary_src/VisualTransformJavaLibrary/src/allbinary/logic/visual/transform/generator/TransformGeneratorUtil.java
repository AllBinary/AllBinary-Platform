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
package allbinary.logic.visual.transform.generator;

import abcs.logic.basic.io.InputOutputTypeData;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.visual.transform.TransformFactory;
import allbinary.logic.visual.transform.TransformInterface;
import allbinary.logic.visual.transform.info.TransformInfoDomNode;
import allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import allbinary.logic.visual.transform.info.objectConfig.generator.StoreFileGenerator;

public class TransformGeneratorUtil
{
   private static final TransformGeneratorUtil instance = new TransformGeneratorUtil();

   private TransformGeneratorUtil()
   {
   }

   public static void generate(
		      TransformInfoInterface transformInfoInterface,
		      TransformInfoInterface ownerTransformInfoInterface) throws Exception
		   {
	      try
	      {
	         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
	         {
	            LogUtil.put(LogFactory.getInstance("Generating View: " + transformInfoInterface.getName(), 
	               instance, "generate()"));
	         }
	         
	         TransformInterface componentInterface = TransformFactory.getInstance(
	        		 transformInfoInterface.getName(), ownerTransformInfoInterface);

	         String result = componentInterface.view();

	         TransformInfoHttpInterface httpTransformInfoInterface = 
	            (TransformInfoHttpInterface) componentInterface.getTransformInfoInterface();
	         
	         InputOutputTypeData inputOutputTypeData = 
	        	 InputOutputTypeData.getInstance();
	         
	         if(result.indexOf("<HTML>") >= 0)
	         {
		         httpTransformInfoInterface.getPropertiesHashMap().put(
		        		 inputOutputTypeData.NAME, inputOutputTypeData.DEFAULT);
	         }
	         else
	         {
		         httpTransformInfoInterface.getPropertiesHashMap().put(
			 	            inputOutputTypeData.NAME, inputOutputTypeData.DEFAULT_FRAGMENT);
	         }

	         new StoreFileGenerator(
	            componentInterface.getTransformInfoInterface()).process(result);
	      }
	      catch(Exception e)
	      {
	         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
	         {
	            LogUtil.put(LogFactory.getInstance("Failed to generate a view",
	                    instance, "generate()", e));
	         }
	         throw e;
	      }
		   }
   
   public static void generate(
		      TransformInfoDomNode transformInfoDomNode,
		      TransformInfoInterface ownerTransformInfoInterface) throws Exception
		   {
	   generate(transformInfoDomNode.getTransformInfoInterface(), ownerTransformInfoInterface);
	   
   }   
}
