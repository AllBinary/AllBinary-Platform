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
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
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
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigData;
import org.allbinary.string.CommonStrings;

public class TaxFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final TaxFactory instance = new TaxFactory();
    
    public static TaxFactory getInstance() {
        return instance;
    }

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private final String TAXATIONFILENAME = "taxationMethod.xml";
   
   private TaxFactory()
   {
   }
   
   //StreetAddress streetAddress, StoreFrontInterface storeFrontInterface
   //streetAddress, storeFrontInterface
   public TaxModuleInterface getInstance(final AbeClientInformationInterface abeClientInformation, final StoreFrontInterface storeFrontInterface) throws Exception
   {
	   final StringBuffer stringBuffer = new StringBuffer();
	   
	   stringBuffer.append(URLGLOBALS.getMainPath());
	   stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
	   stringBuffer.append(storeFrontInterface.getName());
	   stringBuffer.append(AbPathData.getInstance().SEPARATOR);
	   stringBuffer.append("generic");
	   stringBuffer.append(AbPathData.getInstance().SEPARATOR);
	   stringBuffer.append("taxes");
	   stringBuffer.append(AbPathData.getInstance().SEPARATOR);
	 
      final AbPath abPath = new AbPath(stringBuffer.toString(), TAXATIONFILENAME);
      
      final TransformInfoObjectConfigData transformInfoObjectConfigData = TransformInfoObjectConfigData.getInstance();
      final String data = new CryptFileReader(
      transformInfoObjectConfigData.UNCRYPTED_EXTENSION,
      transformInfoObjectConfigData.ENCRYPTED_EXTENSION).get(abPath);
      
      final Document document = DomDocumentHelper.create(data);
      
      final NodeList taxNameNodeList = document.getElementsByTagName(TaxData.NAME);
      
      for(int index = 0; index < taxNameNodeList.getLength(); index++)
      {
         //document.getChildNodes()
         //DomSearchHelper.getNode(TaxData.NAME, );
         //.getElementsByTagName(TaxData.NAME);
         final Node node = taxNameNodeList.item(index);
         final NodeList nodeList = node.getChildNodes();
         
         if(nodeList!=null)
         {
            final Node classNameNode = DomSearchHelper.getNode(DynamicObjectData.NAME, nodeList);
            
            if(classNameNode!=null)
            {
               final String className = DomNodeHelper.getTextNodeValue(classNameNode);
               return (TaxModuleInterface) AbeFactory.getInstance().getInstance(abeClientInformation, className);
            }
            else
            {
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAX))
               {
                  logUtil.put("Class Node Null", this, commonStrings.GET_INSTANCE);
               }
            }
         }
         else
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAX))
            {
               logUtil.put("Tax Name Node Node Children", this, commonStrings.GET_INSTANCE);
            }
         }
         
      }
      
      //Defaults to simple state tax
      return (TaxModuleInterface) new SimpleStateTaxModule();      
   }
   
}