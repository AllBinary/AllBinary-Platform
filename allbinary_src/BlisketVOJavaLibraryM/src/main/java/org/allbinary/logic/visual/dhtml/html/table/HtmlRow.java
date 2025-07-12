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

import java.util.Vector;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.visual.dhtml.html.HtmlTag;

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
      final StringUtil stringUtil = StringUtil.getInstance();
      String result = stringUtil.EMPTY_STRING;
      Object[] attributeKeys = otherAttributes.keySet().toArray();
      int attributeSize = attributeKeys.length;
      int cellSize = htmlCellsVector.size();
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
      
      for (int i = 0; i < cellSize; i++)
      {
         result += htmlCellsVector.get(i).toString();
         result += " ";
      }

      result += ENDTAG;
      result += after;
      return result;
   }
}
