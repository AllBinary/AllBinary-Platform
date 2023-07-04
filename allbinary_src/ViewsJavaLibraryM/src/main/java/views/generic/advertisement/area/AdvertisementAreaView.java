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
package views.generic.advertisement.area;

//import java.util.*;



import org.allbinary.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

//import org.allbinary.logic.basic.string.regex.replace.Replace;

import org.allbinary.business.advertisement.area.AdvertisementAreaData;
import org.allbinary.business.advertisement.area.AdvertisementAreaInterface;

//import org.allbinary.business.user.commerce.money.Money;

//import org.allbinary.data.tree.dom.DomHelper;
//import org.allbinary.data.tree.dom.ModDomHelper;

import org.allbinary.data.tables.advertisement.areas.AdvertisementAreasEntityInterface;
import org.allbinary.data.tables.advertisement.areas.AdvertisementAreasEntityFactory;

//import org.allbinary.logic.control.search.SearchData;


import org.allbinary.data.tree.dom.DomNodeInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.communication.log.LogUtil;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class AdvertisementAreaView extends HttpStoreComponentView implements DomNodeInterface
{
   //private HashMap propertiesHashMap;
   //this.propertiesHashMap = hashMap;
   //private HttpServletRequest request;
   //this.request = (HttpServletRequest) this.getPageContext().getRequest();
   
   private String advertisementAreaName;
   
   public AdvertisementAreaView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);

      this.advertisementAreaName = (String) 
         this.getPropertiesHashMap().get(AdvertisementAreaData.getInstance().NAME);
   }

   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         //1 get ad area info
         AdvertisementAreasEntityInterface advertisementAreasEntityInterface =
            AdvertisementAreasEntityFactory.getInstance();

         AdvertisementAreaInterface advertisementAreaInterface = 
            advertisementAreasEntityInterface.get(
               this.getTransformInfoInterface().getStoreName(), 
               advertisementAreaName);

         //2 search ads from campaign associated with ad area
         //advertisementAreaInterface.getConstraints()

         //AdvertisementInterface advertisementInterface;

         //return new AdvertisementView(advertisementInterface).toXmlNode(document);
         return null;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XSLLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE,this,"toXmlNode",e));
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
         String error = "Failed to view AdvertisementAreaView";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }   
}
