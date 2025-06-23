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
package taghelpers;

import org.allbinary.logic.communication.log.LogFactory;
import javax.servlet.jsp.PageContext;

import org.w3c.dom.Document;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.visual.transform.TransformFactory;
import org.allbinary.logic.visual.transform.TransformInterface;

import org.allbinary.logic.visual.transform.data.TransformDocumentInterface;

import org.allbinary.logic.communication.log.LogUtil;
import java.util.HashMap;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;
import org.allbinary.string.CommonStrings;

public class ViewHelper implements TransformInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();
    
   private TransformInterface componentInterface;
   
   public ViewHelper(HashMap hashMap, PageContext pageContext) throws Exception
   {
      this.componentInterface = 
         TransformFactory.getInstance().getInstance(this.abeClientInformation, hashMap, pageContext);
   }
   
   public int NO_TYPE = 0;
   
   public int getTypeId()
   {
	   return NO_TYPE;
   }
   
   public String view() throws Exception
   {
      try
      {
         return componentInterface.view();
      }
      catch(Exception e)
      {
         String error = "Failed to View: ";
         if(this.componentInterface!=null) error += this.componentInterface.getClass().getName();
         else error += "Unknown";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }

   public Document toXmlDoc() throws Exception
   {
      try
      {
          throw new Exception("Hmm: " + componentInterface.getTransformDocumentInterface().getDoc());
         //return componentInterface.toXmlDoc();
      }
      catch(Exception e)
      {
         String error = "Failed to View: ";
         if(this.componentInterface!=null) error += this.componentInterface.getClass().getName();
         else error += "Unknown";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }
   
   /*
   protected PageContext getPageContext()
   {
      return this.pageContext;
   }
*/
   
   public TransformInterface getViewObject()
   {
      return this.componentInterface;
   }

   public TransformInfoInterface getTransformInfoInterface() throws Exception
   {
      return this.componentInterface.getTransformInfoInterface();       
   }
   
   public TransformDocumentInterface getTransformDocumentInterface()
   {
      return null;
   }
   
   public void setTransformDocumentInterface(TransformDocumentInterface viewDocumentInterface)
   {
      return;
   }
   
}
