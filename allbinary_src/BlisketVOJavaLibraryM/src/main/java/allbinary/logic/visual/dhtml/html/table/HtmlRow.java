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
import java.util.Vector;

public class HtmlRow extends HtmlTag
{     
   private final String END = ">";
   private final String START = "<tr ";
   private final String ENDTAG = "</tr>";
   
   private int numberOfColumns = 0;  
   
   private String before;
   private String after;         
      
   private Vector htmlCellsVector;
   
   public HtmlRow(String before, String after)
   {
      this.before = new String(before);
      this.after = new String(after);      
      this.htmlCellsVector = new Vector();
   }
      
   public void addCell(HtmlCell htmlCell)
   {
      this.htmlCellsVector.add(htmlCell);
      this.numberOfColumns++;
   }

   public int getNumberOfColumns()
   {
      return numberOfColumns;
   }
   
   public String toString()
   {
      String result = "";
      Iterator attributeIter = otherAttributes.keySet().iterator();
      Iterator inputIter = this.htmlCellsVector.iterator();
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
      
      while(inputIter.hasNext())
      {
         result += inputIter.next().toString();
         result += " ";
      }

      result += ENDTAG;
      result += after;
      return result;
   }
}
