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
package org.allbinary.business.context;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class AbContext
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private HashMap propertiesHashMap;
   
   private PageContext pageContext;
   
   public AbContext(HashMap propertiesHashMap, PageContext pageContext)
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("Properties HashMap: " + propertiesHashMap.toString(), this, commonStrings.CONSTRUCTOR);
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