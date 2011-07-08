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
package views.admin.payment.gateways;

import org.w3c.dom.Document;
import org.w3c.dom.Node;


import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.control.validate.ValidationComponentInterface;


import views.business.context.modules.storefront.HttpStoreComponentView;

public class SelectExistingValidationView extends HttpStoreComponentView implements ValidationComponentInterface
{
   public SelectExistingValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public Boolean isValid()
   {
      return Boolean.TRUE;
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
   public String validationInfo() throws Exception
   {
      throw new Exception("validationInfo");
   }
      
   public Document toXmlDoc()
   {
      return null;
   }
   
   public Node toXmlNode(Document document)
   {
      return null;
   }
   
   public String view() throws Exception
   {
      return new SelectExistingView(this.getTransformInfoInterface()).view();
   }
}
