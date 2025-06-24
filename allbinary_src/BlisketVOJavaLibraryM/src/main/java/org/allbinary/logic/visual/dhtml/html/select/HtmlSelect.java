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
package org.allbinary.logic.visual.dhtml.html.select;

import org.allbinary.logic.io.LineReader;
import org.allbinary.logic.visual.dhtml.html.HtmlTag;

import java.util.Vector;

public class HtmlSelect extends HtmlTag
{
   private final String END = ">";
   private final String STARTSELECT = "<SELECT ";
   private final String ENDSELECT = "</SELECT>";
   private final String SIZE = "SIZE=\"";
   private final String NAME = "NAME=\"";
   
   private final String STARTOPTION = "<OPTION";
   private final String SELETED = "SELECTED";
   private final String ENDOPTION = "</OPTION>";
   
   private String before;
   private String name;
   private String after;
   private String size;
   private String selected;
   
   private Vector options;
   
   private String multiple = "multiple";
   private boolean isMultipleSelect = false;
   
   public HtmlSelect(String before, String size, String name, String after)
   {
      this.before = before;
      this.size = size;
      this.name = name;
      this.after = after;
      this.options = new Vector();
   }
   
   public void addOption(String key)
   {
      this.options.add(key);
   }
   
   public void addOptions(String fileName)
   {
      try
      {
         LineReader lineReader = new LineReader(fileName);
         
         while(lineReader.hasNext())
         {
            String option = lineReader.next();
            this.addOption(option);
         }
      }
      catch(Exception e)
      {
         this.addOption("Error");
      }
   }
   
   public void setSelected(String selected)
   {
      this.selected=selected;
   }
   
   public boolean isMultiple()
   {
      return this.isMultipleSelect;
   }
   
   public void setMultiple(boolean value)
   {
      this.isMultipleSelect = value;
   }
   
   private String getOptions()
   {
      String result = "";
      Object[] optionsArray = options.toArray();
      int optionsSize = optionsArray.length;
      
      for (int i = 0; i < optionsSize; i++)
      {
         String value = (String) optionsArray[i];
         if(value!=null && value.compareTo("")!=0)
         {
            result += STARTOPTION;
            result += END;
            result += value;
            result += ENDOPTION;
         }
      }
      return result;
   }
   
   public String toString()
   {
      String result = "";
      Object[] attributeKeys = otherAttributes.keySet().toArray();
      int attributeSize = attributeKeys.length;
      result = before;
      result += STARTSELECT;
      result += NAME;
      result += name;
      result += "\" ";
      result += SIZE;
      result += size;
      result += "\" ";
      
      for (int i = 0; i < attributeSize; i++)
      {
         String key = (String) attributeKeys[i];
         String value = (String) otherAttributes.get(key);
         if(value!=null && value.compareTo("")!=0)
         {
            result += key;
            result += "=\"";
            result += value;
            result += "\" ";
         }
      }
      
      if(this.isMultiple())
         result += " " + multiple + " ";
      
      result += END;
      result += getOptions();
      result += ENDSELECT;
      result += after;
      return result;
   }
}
