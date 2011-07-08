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
package views.compound;

import abcs.logic.communication.log.LogUtil;

import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.communication.log.LogFactory;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class CompoundComponentView extends HttpStoreComponentView 
{
    public CompoundComponentView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }

   //Use prestored data from transformInfo if any
   /*
   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         Node dataNode = this.getTransformInfoInterface().getDataNode();
         return dataNode;
      }
      catch(Exception e)
      {
         return (Node) document.createElement("NoData");
      }
   }
   */
   
   //Override view for Compound View
   public String view() throws Exception
   {
      try
      {
         String success = DomDocumentHelper.toString(this.getDoc());
         
         String result = 
            new CompoundTransform(
               this.getTransformInfoInterface()).translate(success);

         return result;
      }
      catch(Exception e)
      {
         String error = "Failed To View Store Template Compound Component";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }
}
