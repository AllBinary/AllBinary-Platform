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
package org.allbinary.globals;

import java.util.HashMap;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class UrlGlobalsView implements DomNodeInterface
{
   public UrlGlobalsView()
   {
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      HashMap hashMap = new HashMap();
      
      hashMap.put(UrlGlobalsData.WEBAPPPATH, org.allbinary.globals.URLGLOBALS.getWebappPath());
      
      Node node = 
         ModDomHelper.createNameValueNodes(
            document, UrlGlobalsData.NAME, hashMap);

      return node;
   }
}
