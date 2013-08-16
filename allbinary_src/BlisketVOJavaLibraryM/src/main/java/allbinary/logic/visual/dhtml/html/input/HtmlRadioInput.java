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
package allbinary.logic.visual.dhtml.html.input;

import abcs.logic.java.bool.BooleanFactory;

public class HtmlRadioInput extends HtmlValueInput
{
   private final String CHECKED = "checked";
   
   public HtmlRadioInput(String before, String name,String value, String after)
   {            
      super(before,HtmlInputData.getInstance().RADIO,name,value,after);
   }

   public void setSelected()
   {
      super.addAttribute(CHECKED, BooleanFactory.getInstance().TRUE_STRING);
   }
}
