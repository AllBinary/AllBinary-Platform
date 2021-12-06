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
import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

import org.allbinary.logic.system.security.licensing.LicensingException;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.data.tree.dom.DomNodeInterface;



import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.workflow.WorkFlowData;
import org.allbinary.logic.control.workflow.WorkFlowInterface;

import org.allbinary.data.tables.workflow.WorkFlowEntityFactory;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class WorkFlowsView extends HttpStoreComponentView implements DomNodeInterface
{
   private Vector workFlowsVector;
   
   public WorkFlowsView(TransformInfoInterface transformInfoInterface) throws Exception, LicensingException
   {
      super(transformInfoInterface);
      
      this.workFlowsVector = 
         WorkFlowEntityFactory.getInstance().get(
            this.getWeblisketSession().getStoreName());
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
         String error = "Failed to view Workflow";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }
   
   public Node toXmlNode(Document document)
   {
      try
      {
         Node node = document.createElement(WorkFlowData.getInstance().WORKFLOWS);
         
         Iterator iter = this.workFlowsVector.iterator();
         while(iter.hasNext())
         {
            WorkFlowInterface workFlowInterface = (WorkFlowInterface) iter.next();
            //node.appendChild(new WorkFlowView(workFlowInterface).toXmlNode(document));
         }
         return node;
      }
      catch(Exception e)
      {
         String error = "Failed to get node";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"toXmlNode()",e));
         }
         return null;
      }
   }
   
}
