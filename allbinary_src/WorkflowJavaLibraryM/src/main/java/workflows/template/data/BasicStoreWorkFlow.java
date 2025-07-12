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
package workflows.template.data;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.logic.control.workflow.StoreWorkFlowInterface;

public class BasicStoreWorkFlow extends BasicWorkFlow implements StoreWorkFlowInterface
{
   public BasicStoreWorkFlow(HashMap propertiesHashMap, PageContext pageContext) throws Exception
   {
      super(propertiesHashMap, pageContext);
   }

   public String getStoreName() throws Exception
   {
      return (String) this.getPropertiesHashMap().get(StoreFrontData.getInstance().NAME);
   }
}
