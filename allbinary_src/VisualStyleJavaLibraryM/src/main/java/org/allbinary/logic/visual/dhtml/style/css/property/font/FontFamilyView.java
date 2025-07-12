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
package org.allbinary.logic.visual.dhtml.style.css.property.font;

import org.allbinary.logic.visual.dhtml.style.css.property.CssPropertyView;
import org.w3c.dom.Node;

public class FontFamilyView extends CssPropertyView
{
    
   public FontFamilyView()
   {
      super(FontFamilyData.getInstance().VALUE);
   }

   public FontFamilyView(Node node) throws Exception
   {
      super(node);
   }

   /*
   public FontFamilyView(HashMap hashMap)
   {
      super(hashMap);
   }
    **/
}
    
