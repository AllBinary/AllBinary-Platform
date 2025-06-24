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
package org.allbinary.logic.visual.dhtml.html.input;

import org.allbinary.logic.visual.dhtml.html.HtmlTag;


public class HtmlInput extends HtmlTag
{
   private final String END = ">";
   private final String STARTINPUT = "<INPUT ";
   private final String ENDINPUT = "</INPUT>";
   private final String TYPE = "TYPE=\"";
   private final String NAME = "NAME=\"";
   
    private String before;
    private String type;
    private String name;
    private String after;
            
    public HtmlInput(String before, String type,String name, String after)
    {
       this.before = before;
       this.type = type;
       this.name = name;
       this.after = after;
    }
        
    public String toString()
    {
       String result = "";
       Object[] attributeKeys = otherAttributes.keySet().toArray();
       int attributeSize = attributeKeys.length;
       result = before;
       result += STARTINPUT;
       result += TYPE;
       result += type;
       result += "\" ";
       result += NAME;
       result += name;
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
       result += END;
       result += ENDINPUT;
       result += after;
       return result;
    }
}
