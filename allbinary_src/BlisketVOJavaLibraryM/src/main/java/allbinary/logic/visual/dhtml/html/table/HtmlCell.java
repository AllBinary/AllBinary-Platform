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
package allbinary.logic.visual.dhtml.html.table;

import allbinary.logic.visual.dhtml.html.HtmlTag;

import java.util.Iterator;

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
      String result = "";
      Iterator attributeIter = otherAttributes.keySet().iterator();
      result = before;
      result += START;
      result += " ";      
      
      while(attributeIter.hasNext())
      {
         String key = (String) attributeIter.next();
         String value = (String) otherAttributes.get(key);
         if(value!=null && value.compareTo("")!=0)
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
