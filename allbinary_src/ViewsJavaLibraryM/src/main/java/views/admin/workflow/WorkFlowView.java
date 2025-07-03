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
package views.admin.workflow;

import org.allbinary.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;


import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.data.tree.dom.DomNodeInterface;

import org.allbinary.logic.control.workflow.WorkFlowInterface;

import org.allbinary.logic.communication.log.LogUtil;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class WorkFlowView 
extends HttpStoreComponentView 
implements DomNodeInterface
{
   protected WorkFlowInterface workFlowInterface;
   
   public WorkFlowView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);  
   }
      
   public Node toXmlNode(Document document)
   {
      try
      {
         //return new WorkFlowView(this.workFlowInterface).toXmlNode(document);
         return null;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"toXmlNode()",e));
         }
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"view()",e));
         }
         throw e;
      }
   }   
}
