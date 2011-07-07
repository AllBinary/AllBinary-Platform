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
package allbinary.business.context;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.communication.http.request.session.WeblisketSession;
import allbinary.logic.communication.http.request.session.WeblisketSessionInterface;

import javax.servlet.jsp.PageContext;
import java.util.HashMap;

public class AbContext
{      
   private HashMap propertiesHashMap;
   
   private PageContext pageContext;
   
   public AbContext(HashMap propertiesHashMap, PageContext pageContext)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Properties HashMap: " + propertiesHashMap.toString(), this, "AbContext"));
      }

      this.propertiesHashMap = propertiesHashMap;
      this.pageContext = pageContext;      
   }

   public WeblisketSessionInterface getWeblisketSession()
   {
      return new WeblisketSession(propertiesHashMap, pageContext);
   }

   public PageContext getPageContext()
   {
      return this.pageContext;
   }
   
   public HashMap getPropertiesHashMap()
   {
      return propertiesHashMap;
   }
}