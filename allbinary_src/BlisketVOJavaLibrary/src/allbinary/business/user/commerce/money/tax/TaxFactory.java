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
package allbinary.business.user.commerce.money.tax;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.data.tree.dom.DomNodeHelper;
import abcs.data.tree.dom.DomSearchHelper;
import abcs.globals.URLGLOBALS;
import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.loader.AbeFactory;

import allbinary.business.DynamicObjectData;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.user.commerce.money.tax.components.TaxData;
import allbinary.business.user.commerce.money.tax.modules.SimpleStateTaxModule;
import allbinary.business.user.commerce.money.tax.modules.TaxModuleInterface;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import allbinary.logic.control.crypt.file.CryptFileReader;
import allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigData;

public class TaxFactory
{
	private static final TaxFactory instance = new TaxFactory();
	
   private final static String TAXATIONFILENAME = "taxationMethod.xml";
   
   private TaxFactory()
   {
   }
   
   //StreetAddress streetAddress, StoreFrontInterface storeFrontInterface
   //streetAddress, storeFrontInterface
   public static TaxModuleInterface getInstance(StoreFrontInterface storeFrontInterface) throws Exception
   {
	   StringBuffer stringBuffer = new StringBuffer();
	   
	   stringBuffer.append(URLGLOBALS.getMainPath());
	   stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
	   stringBuffer.append(storeFrontInterface.getName());
	   stringBuffer.append(AbPathData.getInstance().SEPARATOR);
	   stringBuffer.append("generic");
	   stringBuffer.append(AbPathData.getInstance().SEPARATOR);
	   stringBuffer.append("taxes");
	   stringBuffer.append(AbPathData.getInstance().SEPARATOR);
	 
      AbPath abPath = new AbPath(stringBuffer.toString(), TAXATIONFILENAME);
      
      String data = new CryptFileReader(
      TransformInfoObjectConfigData.getInstance().UNCRYPTED_EXTENSION,
      TransformInfoObjectConfigData.getInstance().ENCRYPTED_EXTENSION).get(abPath);
      
      Document document = DomDocumentHelper.create(data);
      
      NodeList taxNameNodeList = document.getElementsByTagName(TaxData.NAME);
      
      for(int index = 0; index < taxNameNodeList.getLength(); index++)
      {
         //document.getChildNodes()
         //DomSearchHelper.getNode(TaxData.NAME, );
         //.getElementsByTagName(TaxData.NAME);
         Node node = taxNameNodeList.item(index);
         NodeList nodeList = node.getChildNodes();
         
         if(nodeList!=null)
         {
            Node classNameNode =
               DomSearchHelper.getNode(DynamicObjectData.NAME, nodeList);
            
            if(classNameNode!=null)
            {
               String className =
                  DomNodeHelper.getTextNodeValue(classNameNode);
               return (TaxModuleInterface) AbeFactory.getInstance(className);
            }
            else
            {
               if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAX))
               {
                  LogUtil.put(LogFactory.getInstance("Class Node Null", instance, "getInstance()"));
               }
            }
         }
         else
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAX))
            {
               LogUtil.put(LogFactory.getInstance("Tax Name Node Node Children", instance, "getInstance()"));
            }
         }
         
      }
      
      //Defaults to simple state tax
      return (TaxModuleInterface) new SimpleStateTaxModule();      
   }
   
}