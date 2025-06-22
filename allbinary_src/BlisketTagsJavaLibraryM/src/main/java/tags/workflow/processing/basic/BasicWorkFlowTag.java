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
package tags.workflow.processing.basic;

import javax.servlet.jsp.*;

import org.allbinary.logic.communication.log.LogUtil;


import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.business.DynamicObjectData;

import tags.StoreWorkFlowTag;

//Future implementation use the workflow url to specify the correct customizer view
//and remove the hidden fields
public class BasicWorkFlowTag extends StoreWorkFlowTag
{
   public BasicWorkFlowTag()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
      {
         LogUtil.put(LogFactory.getInstance("Tag Constructed",this,"BasicWorkFlowTag()"));
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START,this,"doStartTag()"));
         }

         //Temporary should be replaced by view in xml of workflow
         this.getPropertiesHashMap().put(DynamicObjectData.NAME,"workflows.template.data.BasicStoreWorkFlow");
         
         return super.doStartTag();
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
}
