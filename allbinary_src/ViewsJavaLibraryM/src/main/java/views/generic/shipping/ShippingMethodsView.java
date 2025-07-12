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
package views.generic.shipping;

import java.util.Vector;

import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.commerce.inventory.basket.BasketInterface;
import org.allbinary.business.user.commerce.inventory.order.OrderInterface;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.business.user.commerce.money.tax.TaxFactory;
import org.allbinary.business.user.commerce.shipping.ShippingMethods;
import org.allbinary.business.user.commerce.shipping.ShippingMethodsData;
import org.allbinary.business.user.commerce.shipping.modules.ShippingInterface;
import org.allbinary.data.tables.user.address.StreetAddressesEntityInterface;
import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntityFactory;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import views.business.context.modules.storefront.HttpStoreComponentView;

public class ShippingMethodsView extends HttpStoreComponentView implements DomNodeInterface
//extends allbinary.business.component.user.commerce.inventory.shipping.ShippingMethods
{
    protected final LogUtil logUtil = LogUtil.getInstance();


   private ShippingMethods shippingMethods;
   private StoreFrontInterface storeFrontInterface;
   
   public ShippingMethodsView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      this.storeFrontInterface = 
         StoreFrontFactory.getInstance(this.getTransformInfoInterface().getStoreName());
      
      this.shippingMethods = new ShippingMethods(this.abeClientInformation, storeFrontInterface);
   }
      
   public Node toXmlNode(Document document) throws Exception
   {
      try
      {      
         Node shippingMethodsNode =
         document.createElement(
         ShippingMethodsData.SHIPPINGMETHODSORDERSUMMARIES);
         
         OrderInterface order = this.getWeblisketSession().getOrder();
         
         BasketInterface basket = this.getWeblisketSession().getOrder().getBasket();
         
         StreetAddressesEntityInterface shippingAddressesEntityInterface 
            = ShippingAddressesEntityFactory.getInstance().getInstance(
            this.getWeblisketSession().getUserName());
         
         StreetAddress streetAddress 
               = shippingAddressesEntityInterface.getDefault();
         
         Vector shippingVector = this.shippingMethods.get();
         
         final int size = shippingVector.size();
         for (int index = 0; index < size; index++)
          {
              Node shippingMethodNode =
                  document.createElement(
                      ShippingMethodsData.SHIPPINGMETHODORDERSUMMARY);

              ShippingInterface shipping = (ShippingInterface) shippingVector.get(index);

              DomNodeInterface shippingView = (DomNodeInterface) shipping;
              shippingMethodNode.appendChild(shippingView.toXmlNode(document));

              Money shippingCost = shipping.getCost(order);
              Money subTotal = basket.getSubTotal();
              Float taxRate = TaxFactory.getInstance().getInstance(this.abeClientInformation, storeFrontInterface).getTaxRate(streetAddress, storeFrontInterface);

              Money tax = new Money();
              Money total = new Money();

              total.add(shippingCost.toString());
              total.add(subTotal.toString());

              tax.add(total.toString());
              tax.multiply(taxRate);

              total.add(tax.toString());

              shippingMethodNode.appendChild(
                  ModDomHelper.createNameValueNodes(
                      document, ShippingMethodsData.SHIPPINGCOST, shippingCost.toString()));

              shippingMethodNode.appendChild(
                  ModDomHelper.createNameValueNodes(
                      document, ShippingMethodsData.TAXRATE, taxRate.toString()));

              shippingMethodNode.appendChild(
                  ModDomHelper.createNameValueNodes(
                      document, ShippingMethodsData.SUBTOTAL, subTotal.toString()));

              shippingMethodNode.appendChild(
                  ModDomHelper.createNameValueNodes(
                      document, ShippingMethodsData.TAX, tax.toString()));

              shippingMethodNode.appendChild(
                  ModDomHelper.createNameValueNodes(
                      document, ShippingMethodsData.TOTAL, total.toString()));

              shippingMethodNode.appendChild(
                  ModDomHelper.createNameValueNodes(
                      document, EntryData.getInstance().DEFAULT, this.shippingMethods.getDefault().getName()));

              shippingMethodsNode.appendChild(shippingMethodNode);
          }

         return shippingMethodsNode;
         
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
   
   public String view()
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         String error = "Failed to view ShippingMethods";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"view()",e);
         }
         return error;
      }
   }   
}
