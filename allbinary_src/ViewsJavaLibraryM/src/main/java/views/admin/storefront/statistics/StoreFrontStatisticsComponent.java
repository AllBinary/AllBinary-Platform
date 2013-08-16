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
package views.admin.storefront.statistics;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

//import allbinary.data.tables.context.module.storefronts.StoreFrontsEntityFactory;
   
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.context.modules.storefront.StoreFrontFactory;

import allbinary.business.context.modules.storefront.statistics.RealTimeStoreFrontStatistics;
import allbinary.business.context.modules.storefront.statistics.RealTimeStoreFrontStatisticsView;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.data.tree.dom.DomNodeInterface;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class StoreFrontStatisticsComponent extends HttpStoreComponentView
{
   protected StoreFrontInterface newStoreFrontInterface;
   private DomNodeInterface domNodeInterface;
   
   public StoreFrontStatisticsComponent(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);

      this.newStoreFrontInterface = (StoreFrontInterface) 
         StoreFrontFactory.getInstance(transformInfoInterface.getStoreName());

      this.domNodeInterface =
         new RealTimeStoreFrontStatisticsView(
	    new RealTimeStoreFrontStatistics(
	       this.newStoreFrontInterface), this.getWeblisketSession().getRole());
   }

   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) this.domNodeInterface);
   }
   
   public String view() throws Exception
   {
      try
      {
         //Store is already included in store view
	 this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         String error = "Failed to view StoreFrontComponent";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }   
}
