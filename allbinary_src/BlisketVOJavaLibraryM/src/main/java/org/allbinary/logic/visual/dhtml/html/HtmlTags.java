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
package org.allbinary.logic.visual.dhtml.html;

import java.util.Vector;

public class HtmlTags
{
   private Vector htmlTagVector;
   
   public HtmlTags()
   {
      htmlTagVector = new Vector();
   }

   public HtmlTag get(int index)
   {
      return (HtmlTag) htmlTagVector.get(index);
   }

   public void add(HtmlTag htmlTag)
   {
      htmlTagVector.add(htmlTag);
   }
   
   public int size()
   {
      return htmlTagVector.size();
   }
}