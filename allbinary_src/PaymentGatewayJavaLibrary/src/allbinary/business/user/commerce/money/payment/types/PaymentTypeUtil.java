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
package allbinary.business.user.commerce.money.payment.types;

import abcs.data.tree.dom.DomNodeHelper;
import abcs.data.tree.dom.DomSearchHelper;
import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.globals.URLGLOBALS;
import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.entry.EntryData;
import allbinary.business.user.commerce.money.payment.gateway.PaymentGatewaysData;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import allbinary.logic.control.crypt.file.CryptFileReader;
import allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigData;
import java.util.Iterator;
import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author user
 */
public class PaymentTypeUtil {

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

    private PaymentTypeUtil()
    {

    }

    private final String PAYMENTGATEWAYFILEPATHSTRING;

   {
       StringBuffer stringBuffer = new StringBuffer();

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

   private final String SHIPPINGMETHODSFILENAME = "paymentGatewayTypes.xml";

   private String defaultName = null;

   private Vector paymentTypeVector = new Vector();

   public void add(PaymentType paymentType)
   {
       this.paymentTypeVector.add(paymentType);
   }

   public PaymentType get(String paymentTypeString)
   throws Exception
   {
      Iterator iter = this.paymentTypeVector.iterator();
      while(iter.hasNext())
      {
         PaymentType paymentType = (PaymentType) iter.next();
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
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PAYMENT))
      {
         LogUtil.put(LogFactory.getInstance("initDefault Payment: " + this.defaultName, this, "initDefault"));
      }

      if(StringValidationUtil.getInstance().isEmpty(this.defaultName))
      {
          StringBuffer stringBuffer = new StringBuffer();

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

            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PAYMENT))
            {
               LogUtil.put(LogFactory.getInstance("DefaultPaymentGatewayNodeValue: " + defaultPaymentGatewayNodeValue, this, "initDefault"));
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
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PAYMENT))
      {
         LogUtil.put(LogFactory.getInstance("Start", this, "getDefault"));
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
      Iterator iter = this.paymentTypeVector.iterator();
      while(iter.hasNext())
      {
         PaymentType paymentType = (PaymentType) iter.next();
         if(!a_PaymentTypeVector.contains(paymentType))
            diff.add(paymentType);
      }
      return diff;
   }

}
