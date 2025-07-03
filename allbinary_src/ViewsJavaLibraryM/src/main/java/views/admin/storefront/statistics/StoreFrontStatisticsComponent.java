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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

//import org.allbinary.data.tables.context.module.storefronts.StoreFrontsEntityFactory;
   
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;

import org.allbinary.business.context.modules.storefront.statistics.RealTimeStoreFrontStatistics;
import org.allbinary.business.context.modules.storefront.statistics.RealTimeStoreFrontStatisticsView;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.data.tree.dom.DomNodeInterface;

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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"view()",e));
         }
         throw e;
      }
   }   
}
