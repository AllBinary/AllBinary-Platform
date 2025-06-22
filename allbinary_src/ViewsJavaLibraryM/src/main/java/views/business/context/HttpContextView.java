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
package views.business.context;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.visual.transform.ContextTransformer;

import org.allbinary.logic.visual.transform.data.TransformHttpRequestDocumentFactory;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import views.HttpComponentView;

public class HttpContextView extends HttpComponentView
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   public HttpContextView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, this.commonStrings.CONSTRUCTOR));
      }

      this.setTransformDocumentInterface(
         TransformHttpRequestDocumentFactory.getInstance(
            this.getPageContext(),
            this.getWeblisketSession()));
   }
   
   public String view() throws Exception
   {
      try
      {
         this.toXmlDoc();
         String success = DomDocumentHelper.toString(this.getDoc());

         String result = new ContextTransformer(this.abeClientInformation,
            this.getTransformInfoInterface()).translate(success);

         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to view ContextView";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "view", e));
         }
         throw e;
      }
   }
}
