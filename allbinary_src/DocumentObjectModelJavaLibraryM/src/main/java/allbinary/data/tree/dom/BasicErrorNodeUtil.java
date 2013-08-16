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
package allbinary.data.tree.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import allbinary.business.error.ErrorData;

public class BasicErrorNodeUtil
{
   private BasicErrorNodeUtil()
   {
   }

   public static Node get(Document document, String errorText) throws Exception
   {
      try
      {
         //String error = "Error Processing Order.  Please contact us if error persists.<p/>";
         Node node = document.createElement(ErrorData.getInstance().NAME);
         node.appendChild(
            ModDomHelper.createNameValueNodes(
               document,ErrorData.getInstance().TEXT,errorText));
         return node;
      }
      catch(Exception e)
      {
         /*
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.XMLLOGGINGERROR))
         {
            LogUtil.put("Error Node Creation Failure","BasicErrorNodeUtil","get()",e);
         }
          */
         throw new Exception("Error Node Creation Failure");
         //return null;
      }
   }
}
