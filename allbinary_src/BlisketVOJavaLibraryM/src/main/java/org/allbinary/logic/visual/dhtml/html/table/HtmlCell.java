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
package org.allbinary.logic.visual.dhtml.html.table;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.visual.dhtml.html.HtmlTag;


public class HtmlCell extends HtmlTag
{
   private final String END = ">";
   private final String START = "<td ";
   private final String ENDTAG = "</td>";
   private final String HEIGHT = "height";
   private final String WIDTH = "width";

   private String height;
   private String width;
   
   private String before;
   private String body;   
   private String after;   
      
   public HtmlCell(String before, String width, String height, String after)
   {
      this.before = new String(before);
      this.after = new String(after);
      this.height=new String(height);
      this.width=new String(width);   
      addAttribute(HEIGHT,this.height);
      addAttribute(WIDTH,this.width);
   }
      
   public void addText(String body)
   {
      this.body=body;
   }
   
   public String toString()
   {
      final StringUtil stringUtil = StringUtil.getInstance();
      String result = stringUtil.EMPTY_STRING;
      Object[] attributeKeys = otherAttributes.keySet().toArray();
      int attributeSize = attributeKeys.length;
      result = before;
      result += START;
      result += " ";      
      
      for (int i = 0; i < attributeSize; i++)
      {
         String key = (String) attributeKeys[i];
         String value = (String) otherAttributes.get(key);
         if(value!=null && value.compareTo(stringUtil.EMPTY_STRING)!=0)
         {
            result += key;
            result += "=\"";
            result += value;
            result += "\" ";
         }
      }
      result += END;
      result += body;
      result += ENDTAG;
      result += after;
      return result;
   }
}
