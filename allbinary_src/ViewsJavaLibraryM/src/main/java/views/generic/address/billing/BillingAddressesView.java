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
package views.generic.address.billing;


import abcs.logic.communication.log.LogFactory;
import java.util.*;

import javax.servlet.http.HttpServletRequest;


import org.w3c.dom.Document;
import org.w3c.dom.Node;

import abcs.logic.communication.log.LogUtil;

import allbinary.data.tree.dom.ModDomHelper;


import allbinary.business.user.address.StreetAddress;
import allbinary.business.user.address.BillingAddressData;
import allbinary.business.user.address.StreetAddressData;


import allbinary.data.tree.dom.DomNodeInterface;

import allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;



import views.business.context.modules.storefront.HttpStoreComponentView;

public class BillingAddressesView 
extends HttpStoreComponentView
implements DomNodeInterface
{   
   private HttpServletRequest request;

   protected Vector streetAddresses;
   
   public BillingAddressesView(TransformInfoInterface transformInfoInterface) throws Exception
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
      
   public Node toXmlNode(Document document)
   {
      try
      {
         Node billingAddressesNode = document.createElement(BillingAddressData.MULTIPLE);
         
         Iterator iter = streetAddresses.iterator();
         while(iter.hasNext())
         {
            StreetAddress streetAddress = (StreetAddress) iter.next();
            billingAddressesNode.appendChild(streetAddress.toXmlNode(document));
         }
         
         billingAddressesNode.appendChild(ModDomHelper.createNameValueNodes(
         document, StreetAddressData.NUMBEROFADDRESSES,
         new Integer(streetAddresses.size()).toString()));

         return billingAddressesNode;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         abcs.logic.communication.log.config.type.LogConfigType.XSLLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"toXmlNode",e));
         }
         //throw e;
         return null;
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
         String error = "Failed to view BillingAddressesView";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }   
}
