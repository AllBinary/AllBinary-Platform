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

import org.allbinary.logic.string.StringUtil;

public class HtmlValueInput extends HtmlInput
{   
   private String value;
   private final String VALUE = "VALUE";
   
   public HtmlValueInput(String before, String type, String name, String value, String after)
   {
      super(before,type,name,after);
      final StringUtil stringUtil = StringUtil.getInstance();
      this.value = stringUtil.EMPTY_STRING;
      addAttribute(VALUE,value);
   }
   
}
