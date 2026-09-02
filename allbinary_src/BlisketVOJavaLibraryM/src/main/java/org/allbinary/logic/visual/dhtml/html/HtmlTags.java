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

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import org.allbinary.logic.StdUtil;

public class HtmlTags
{
   private BasicArrayList htmlTagVector;
   
   public HtmlTags()
   {
      this.htmlTagVector = new BasicArrayListD();
   }

   public HtmlTag get(int index)
   {
      return (HtmlTag) this.htmlTagVector.get(index);
   }

   public void add(HtmlTag htmlTag)
   {
      this.htmlTagVector.add(htmlTag);
   }
   
   public int size()
   {
      return this.htmlTagVector.size();
   }
}