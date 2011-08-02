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
package allbinary.business.user.commerce.shipping;

import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.data.tree.dom.DomNodeHelper;
import abcs.data.tree.dom.DomSearchHelper;
import abcs.globals.URLGLOBALS;
import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.system.loader.AbeFactory;
import allbinary.business.DynamicObjectData;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.user.commerce.shipping.modules.BasicWeightShippingModuleView;
import allbinary.business.user.commerce.shipping.modules.NoShippingModuleView;
import allbinary.business.user.commerce.shipping.modules.ShippingInterface;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import allbinary.logic.control.crypt.file.CryptFileReader;
import allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Vector;


public class ShippingMethodsFactory
{
   private ShippingInterface defaultShippingMethodInterface;
   
      //private final String DEFAULTSHIPPING
   
   private static final String SHIPPINGMETHODSFILEPATHSTRING;

   static
   {
	   StringBuffer stringBuffer = new StringBuffer();
	   
	   final String sep = AbPathData.getInstance().SEPARATOR;
	   
	   stringBuffer.append(sep);
	   stringBuffer.append("generic");
	   stringBuffer.append(sep);
	   stringBuffer.append("shipping");
	   stringBuffer.append(sep);
	   
	   SHIPPINGMETHODSFILEPATHSTRING = stringBuffer.toString();
   }
   
   private static final String SHIPPINGMETHODSFILENAME = "shippingMethods.xml";
   
   private Document document;
   
   private Vector shippingVector;
   
   public ShippingMethodsFactory(StoreFrontInterface storeFrontInterface) throws Exception
   {
	   StringBuffer stringBuffer = new StringBuffer();
	   
	   final String sep = AbPathData.getInstance().SEPARATOR;
	   
	   stringBuffer.append(URLGLOBALS.getMainPath());
	   stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
	   stringBuffer.append(storeFrontInterface.getName());
	   stringBuffer.append(sep);
	   stringBuffer.append(this.SHIPPINGMETHODSFILEPATHSTRING);
       
      AbPath abPath = (AbPath) new AbPath(stringBuffer.toString(), this.SHIPPINGMETHODSFILENAME);

      String data = new CryptFileReader(
         TransformInfoObjectConfigData.getInstance().UNCRYPTED_EXTENSION,
         TransformInfoObjectConfigData.getInstance().ENCRYPTED_EXTENSION).get(abPath);
      
      this.document = DomDocumentHelper.create(data);
      
      this.shippingVector = new Vector();

      NodeList nodeList = document.getElementsByTagName(ShippingMethodsData.NAME);

      for(int index = 0; index < nodeList.getLength(); index++)
      {
         Node node = nodeList.item(index);
         NodeList shippingMethodNodeChildren = node.getChildNodes();

         Node shippingMethodNameNode =
            DomSearchHelper.getNode(ShippingMethodData.NAME, shippingMethodNodeChildren);

         Node classNameNode =
            DomSearchHelper.getNode(DynamicObjectData.NAME, shippingMethodNameNode.getChildNodes());
         String shippingMethodClassName = 
            DomNodeHelper.getTextNodeValue(classNameNode);

         ShippingInterface shippingMethodInterface = (ShippingInterface)
            AbeFactory.getInstance(shippingMethodClassName);
         shippingVector.add(shippingMethodInterface);

         Node defaultShippingMethodNameNode =
            DomSearchHelper.getNode(ShippingMethodData.DEFAULT, shippingMethodNameNode.getChildNodes());
         String defaultShippingMethodNameNodeValue = 
            DomNodeHelper.getTextNodeValue(defaultShippingMethodNameNode);
                 
         if(defaultShippingMethodNameNodeValue!=null &&
            defaultShippingMethodNameNodeValue.compareTo(ShippingMethodData.DEFAULT)==0)
            this.defaultShippingMethodInterface = shippingMethodInterface;
      }

      //Default Shipping Options
      if(shippingVector.size()<1)
      {
         this.defaultShippingMethodInterface = new BasicWeightShippingModuleView();
         shippingVector.add(this.defaultShippingMethodInterface);
         shippingVector.add(new NoShippingModuleView());
      }
   }
   
   public Vector getInstance() throws Exception
   {      
      return shippingVector;
   }
   
   public ShippingInterface getDefaultInstance()
   {
      return defaultShippingMethodInterface;
   }
}
