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

public class HtmlLengthInput extends HtmlValueInput
{
   private final String MAXLENGTH="MAXLENGTH";
   private final String SIZE="SIZE";
   private String length;
   private String size;
   
   public HtmlLengthInput(String before, String type, String name,String value, String after)
   {                  
      super(before,type,name,value,after);
   }

   public void setMaxLength(Integer length)
   {
      this.length=new String(length.toString());
      addAttribute(MAXLENGTH,this.length);
   }

   public void setSize(Integer size)
   {
      this.size=new String(size.toString());
      addAttribute(MAXLENGTH,this.size);
   }   
}
