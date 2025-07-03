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

import org.allbinary.logic.visual.dhtml.html.HtmlTag;

import java.util.Vector;
import org.allbinary.logic.string.StringUtil;

public class HtmlTable extends HtmlTag
{   
   private final String END = ">";
   private final String START = "<table ";
   private final String ENDTAG = "</table>";
   private final String CLASS = "class";
   private final String DIRECTION = "dir";
   private final String ID = "id";
   private final String LANG = "lang";
   private final String UNITS = "units";
   private final String BORDER = "border";
   private final String CELLPADDING = "cellpadding";
   private final String CELLSPACING = "cellspacing";
   private final String COLS = "cols";
   private final String FLOAT = "float";
   
   private final String FRAME = "frame";
   
   private final String RULES = "rules";
   
   private final String WIDTH = "width";
   
   private final String STYLE = "style";
   
   public final String  BORDERCOLLAPSE="border-collapse: collapse";

   private int numberOfColumns = 0;  
   
   private String before;
   private String border;
   private String cellpadding;
   private String cellspacing;
   private String style;
   private String name;
   private String after;      
   
   private Vector htmlRowsVector;
   
   public HtmlTable(String before, String cellpadding, String cellspacing, String after)
   {
      this.before = before;
      //this.border = new String(border);
      this.cellpadding = cellpadding;
      this.cellspacing = cellspacing;
      //this.style = new String(style);  
      this.after = after;
      this.htmlRowsVector = new Vector();
      addAttribute(BORDER,"0");
      addAttribute(CELLPADDING,this.cellpadding);
      addAttribute(CELLSPACING,this.cellspacing);      
      addAttribute(STYLE,this.BORDERCOLLAPSE);
   }      
      
   public void addRow(HtmlRow htmlRow)
   {
      if(this.numberOfColumns<htmlRow.getNumberOfColumns())
         this.numberOfColumns=htmlRow.getNumberOfColumns();
      
      this.htmlRowsVector.add(htmlRow);
      //addAttribute(COLS,new Integer(this.numberOfColumns).toString());
   }

   public String toString()
   {
      final StringUtil stringUtil = StringUtil.getInstance();
      String result = stringUtil.EMPTY_STRING;
      Object[] attributeKeys = otherAttributes.keySet().toArray();
      result = before;
      result += START;
      result += " ";      
      
      for (int i = 0; i < attributeKeys.length; i++)
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
      
      final int size = htmlRowsVector.size();
      for (int i = 0; i < size; i++)
      {
         result += htmlRowsVector.get(i).toString();
         result += " ";
      }

      result += ENDTAG;
      result += after;
      return result;
   }
}
