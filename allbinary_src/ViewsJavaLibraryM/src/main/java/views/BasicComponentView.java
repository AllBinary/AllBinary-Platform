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
package views;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.visual.transform.BasicTransformer;

import org.allbinary.logic.visual.transform.TransformInterface;
import org.allbinary.logic.visual.transform.data.TransformDocumentInterface;
import org.allbinary.logic.visual.transform.data.TransformDocumentFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public class BasicComponentView implements TransformInterface
{	
   private TransformDocumentInterface transformDocumentInterface;
   private TransformInfoInterface transformInfoInterface;

   public BasicComponentView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance(
                 "View Name: " + transformInfoInterface.getName(), this, "ComponentView()"));
      }

      this.setTransformInfoInterface(transformInfoInterface);
      this.setTransformDocumentInterface(
         TransformDocumentFactory.getInstance());
   }

   public int NO_TYPE = 0;
   
   public int getTypeId()
   {
	   return NO_TYPE;
   }
   
   public TransformDocumentInterface getTransformDocumentInterface()
   {
      return transformDocumentInterface;
   }

   public void setTransformDocumentInterface(TransformDocumentInterface transformDocumentInterface)
   {
      this.transformDocumentInterface = transformDocumentInterface;
   }

   public TransformInfoInterface getTransformInfoInterface()
   {
      return transformInfoInterface;
   }

   public void setTransformInfoInterface(TransformInfoInterface transformInfoInterface)
   {
      this.transformInfoInterface = transformInfoInterface;
   }

   public Document toXmlDoc()
   {
      return this.transformDocumentInterface.getDoc();
   }

   public Document getDoc() throws Exception
   {
      Document document = this.getTransformInfoInterface().getDataDocument();
      Node dataNode = this.getTransformDocumentInterface().getDoc().importNode(
            document.getFirstChild(),true);

      if(dataNode != null)
      {
         this.getTransformDocumentInterface().getBaseNode().appendChild(dataNode);
      }
      
      return this.getTransformDocumentInterface().getDoc();
   }

   public String view() throws Exception
   {
      try
      {
         String success = DomDocumentHelper.toString(this.getDoc());

         String result = new BasicTransformer(
            this.getTransformInfoInterface()).translate(success);

         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to Component view";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "view()", e));
         }
         throw e;
      }
   }
}
