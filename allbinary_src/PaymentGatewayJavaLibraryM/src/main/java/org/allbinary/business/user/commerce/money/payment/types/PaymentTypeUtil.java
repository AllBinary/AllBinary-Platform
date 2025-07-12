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
package org.allbinary.business.user.commerce.money.payment.types;

import java.util.Vector;

import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewaysData;
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigData;
import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author user
 */
public class PaymentTypeUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final PaymentTypeUtil instance = new PaymentTypeUtil();

    static
    {
    	//PaymentTypeFactory.getInstance().A1MPLATFORM.hashCode();
    }
    
    /**
     * @return the instance
     */
    public static PaymentTypeUtil getInstance()
    {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final String PAYMENTGATEWAYFILEPATHSTRING;
    private final String SHIPPINGMETHODSFILENAME = "paymentGatewayTypes.xml";

    private String defaultName = null;

    private Vector paymentTypeVector = new Vector();
    
    private PaymentTypeUtil()
    {
       final StringMaker stringBuffer = new StringMaker();

       final String sep = AbPathData.getInstance().SEPARATOR;

       stringBuffer.append(sep);
       stringBuffer.append("generic");
       stringBuffer.append(sep);
       stringBuffer.append("user");
       stringBuffer.append(sep);
       stringBuffer.append("commerce");
       stringBuffer.append(sep);
       stringBuffer.append("money");
       stringBuffer.append(sep);
       stringBuffer.append("payment");
       stringBuffer.append(sep);
       stringBuffer.append("gateway");

       stringBuffer.append(sep);

       PAYMENTGATEWAYFILEPATHSTRING = stringBuffer.toString();
    }

   public void add(PaymentType paymentType)
   {
       this.paymentTypeVector.add(paymentType);
   }

   public PaymentType get(String paymentTypeString)
   throws Exception
   {
      int size = this.paymentTypeVector.size();
      for (int i = 0; i < size; i++)
      {
         PaymentType paymentType = (PaymentType) this.paymentTypeVector.get(i);
         if(paymentType.getBasicPaymentType().getName().compareTo(paymentTypeString) == 0)
         {
            return paymentType;
         }
      }
      throw new Exception("Unknown PaymentType: " + paymentTypeString);
   }

   public void initDefault(String storeName)
      throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENT))
      {
         logUtil.put("initDefault Payment: " + this.defaultName, this, "initDefault");
      }

      if(StringValidationUtil.getInstance().isEmpty(this.defaultName))
      {
          StringMaker stringBuffer = new StringMaker();

          stringBuffer.append(URLGLOBALS.getMainPath());
          stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
          stringBuffer.append(storeName);
          stringBuffer.append(AbPathData.getInstance().SEPARATOR);
          stringBuffer.append(PAYMENTGATEWAYFILEPATHSTRING);

         AbPath abPath = (AbPath)
            new AbPath(stringBuffer.toString(),SHIPPINGMETHODSFILENAME);

         String data = new CryptFileReader(
            TransformInfoObjectConfigData.getInstance().UNCRYPTED_EXTENSION,
            TransformInfoObjectConfigData.getInstance().ENCRYPTED_EXTENSION).get(abPath);

         Document document = DomDocumentHelper.create(data);

         NodeList nodeList = document.getElementsByTagName(PaymentGatewaysData.NAME);

         for(int index = 0; index < nodeList.getLength(); index++)
         {
            Node node = nodeList.item(index);

            /*
            NodeList paymentGatewayNodeList = node.getChildNodes();

            Node paymentGatewayNode =
               DomSearchHelper.getNode(PaymentGatewayData.NAME.toString(), paymentGatewayNodeList);

            Node paymentGatewayNodeName =
               DomSearchHelper.getNode(PaymentGatewayData.NAME.toString(), paymentGatewayNode.getChildNodes());

            String paymentGatewayNodeNameValue =
               DomNodeHelper.getTextNodeValue(paymentGatewayNodeName);
             */

            Node defaultPaymentGatewayNode = DomSearchHelper.getNode(
               EntryData.getInstance().DEFAULT, node.getChildNodes());
            //paymentGatewayNode.getChildNodes()

            String defaultPaymentGatewayNodeValue =
               DomNodeHelper.getTextNodeValue(defaultPaymentGatewayNode);

            if( org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENT))
            {
               logUtil.put("DefaultPaymentGatewayNodeValue: " + defaultPaymentGatewayNodeValue, this, "initDefault");
            }

            if(!StringValidationUtil.getInstance().isEmpty(defaultPaymentGatewayNodeValue))
            {
               this.defaultName = defaultPaymentGatewayNodeValue;
            }
         }
      }
   }

   public PaymentType getDefault(String storeName)
      throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENT))
      {
         logUtil.put(this.commonStrings.START, this, "getDefault");
      }

      this.initDefault(storeName);

      if(!StringValidationUtil.getInstance().isEmpty(this.defaultName))
      {
         return this.get(this.defaultName);
      }
      throw new Exception("No Default: " + this.defaultName);
   }

   public boolean isContain(PaymentType paymentType)
   {
      return this.paymentTypeVector.contains(paymentType);
   }

   public Vector difference(Vector a_PaymentTypeVector)
   {
      Vector diff = new Vector();
      int size = this.paymentTypeVector.size();
      for (int i = 0; i < size; i++)
      {
         PaymentType paymentType = (PaymentType) this.paymentTypeVector.get(i);
         if(!a_PaymentTypeVector.contains(paymentType))
            diff.add(paymentType);
      }
      return diff;
   }

}
