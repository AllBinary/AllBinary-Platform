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
import jakarta.servlet.jsp.PageContext;

import org.allbinary.logic.communication.log.LogUtil;
import admin.taghelpers.TagHelperInterface;

import org.allbinary.logic.control.workflow.StoreTagWorkFlowFactory;
import org.allbinary.logic.control.workflow.StoreWorkFlowInterface;
import java.util.HashMap;

public class StoreTagWorkFlowHelper
    implements TagHelperInterface
    //implements StoreWorkFlowInterface
{
   private StoreWorkFlowInterface storeWorkFlowInterface;
   
   public StoreTagWorkFlowHelper(HashMap hashMap, PageContext pageContext) throws Exception
   {      
      /*
      HashMap multipartRequestHashMap = 
         new MultipartRequestParams(
            pageContext).toHashMap();
   
      hashMap.put(MultipartRequestParamsData.HASHMAP, multipartRequestHashMap);
      */
      
      //uses hidden field in form for viewname specification 
      //set View for Customizer should be in seperate helper
      //hack could be replace when the workflow system is complete
      //String viewName = (String) hashMap.get(TransformInfoData.NAME);
      
      //String pageName = (String) multipartRequestHashMap.get(TransformInfoData.NAME);
      
      /*
      StoreFrontInterface storeFrontInterface =
         StoreFrontFactory.getInstance(
            new WeblisketSession(hashMap, pageContext).getStoreName());
      
      viewName = storeFrontInterface.getName() + " " + viewName + " " + CustomizerTransformInfoData.NAME ;
      hashMap.put(TransformInfoData.NAME, viewName);
      */
     
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
      {
         LogUtil.put(LogFactory.getInstance("Properties: " + hashMap.toString(), this, "StoreTagWorkFlowHelper()"));
      }
      
      this.storeWorkFlowInterface = StoreTagWorkFlowFactory.getInstance(hashMap, pageContext);
           
   }
      
   public Integer process() throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
         {
            LogUtil.put(LogFactory.getInstance("Process",this,"process()"));
         }

         return this.storeWorkFlowInterface.process();
      }
      catch(Exception e)
      {
         String error = "Failed to process workflow: ";
         //if(this.componentInterface!=null) error += this
         //else error += "Unknown";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }
}
