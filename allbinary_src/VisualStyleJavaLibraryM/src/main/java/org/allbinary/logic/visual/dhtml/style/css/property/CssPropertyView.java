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
package org.allbinary.logic.visual.dhtml.style.css.property;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class CssPropertyView implements DomNodeInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private CssProperty cssProperty;
   
   public CssPropertyView(String name)
   {
      this.cssProperty = new CssProperty(name);
   }

   public CssPropertyView(Node node) throws Exception
   {
      this.cssProperty = new CssProperty(node);
   }

   /*
   public CssPropertyView(HashMap hashMap)
   {
      this.cssProperty = new CssProperty(hashMap);
   }
*/
  
   public CssProperty getProperty()
   {
      return cssProperty;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      return this.cssProperty.toXmlNode(document);
   }
}
