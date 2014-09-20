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
package org.allbinary.business.user.commerce.money.tax;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.basic.path.AbPath;
import org.allbinary.logic.basic.path.AbPathData;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.loader.AbeFactory;

import org.allbinary.business.DynamicObjectData;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.money.tax.components.TaxData;
import org.allbinary.business.user.commerce.money.tax.modules.SimpleStateTaxModule;
import org.allbinary.business.user.commerce.money.tax.modules.TaxModuleInterface;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigData;

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
               if(  org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.TAX))
               {
                  LogUtil.put(LogFactory.getInstance("Class Node Null", instance, "getInstance()"));
               }
            }
         }
         else
         {
            if( org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.TAX))
            {
               LogUtil.put(LogFactory.getInstance("Tax Name Node Node Children", instance, "getInstance()"));
            }
         }
         
      }
      
      //Defaults to simple state tax
      return (TaxModuleInterface) new SimpleStateTaxModule();      
   }
   
}