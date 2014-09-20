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

public class HtmlPasswordInput extends HtmlLengthInput
{
   public HtmlPasswordInput(String before, String name,String value, String after)
   {                  
      super(before,HtmlInputData.getInstance().PASSWORD,name,value,after);
      setSize(new Integer(32));
      setMaxLength(new Integer(128));
   }   
}
