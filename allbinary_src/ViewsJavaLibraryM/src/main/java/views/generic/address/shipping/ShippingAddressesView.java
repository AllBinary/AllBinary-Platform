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
package views.generic.address.shipping;

import org.allbinary.logic.communication.log.LogFactory;
import java.util.*;

import javax.servlet.http.HttpServletRequest;


import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.data.tree.dom.ModDomHelper;


import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.address.ShippingAddressData;
import org.allbinary.business.user.address.StreetAddressData;


import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.data.tree.dom.DomNodeInterface;



import views.business.context.modules.storefront.HttpStoreComponentView;

public class ShippingAddressesView 
   extends HttpStoreComponentView 
   implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private HttpServletRequest request;
   
   protected Vector streetAddressList;
   
   public ShippingAddressesView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      TransformInfoHttpInterface httpTransformInfoInterface = 
         (TransformInfoHttpInterface) this.getTransformInfoInterface();
      
      this.request = (HttpServletRequest) 
         httpTransformInfoInterface.getPageContext().getRequest();
   }
   
   protected HttpServletRequest getRequest()
   {
      return this.request;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         Node billingAddressesNode = document.createElement(ShippingAddressData.MULTIPLE);
         
         int size = streetAddressList.size();
         for (int index = 0; index < size; index++)
         {
            StreetAddress streetAddress = (StreetAddress) streetAddressList.get(index);
            billingAddressesNode.appendChild(streetAddress.toXmlNode(document));
         }
         
         billingAddressesNode.appendChild(ModDomHelper.createNameValueNodes(document, StreetAddressData.NUMBEROFADDRESSES,
         new Integer(streetAddressList.size()).toString()));

         return billingAddressesNode;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XSLLOGGINGERROR))
         {
            logUtil.put(this.commonStrings.FAILURE,this,"toXmlNode",e);
         }
         throw e;
      }
   }

   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) this);
   }
   
   public String view() throws Exception
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"view()",e);
         }
         throw e;
      }
   }
}
