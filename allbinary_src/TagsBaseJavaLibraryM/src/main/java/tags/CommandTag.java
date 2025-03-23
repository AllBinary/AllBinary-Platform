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
package tags;

import javax.servlet.jsp.tagext.TagSupport;
import org.allbinary.string.CommonStrings;

public class CommandTag extends TagSupport
{
    protected CommonStrings commonStrings = CommonStrings.getInstance();

   private String command;
   
   public CommandTag()
   {
   }
   
   public void setCommand(String command)
   {
      this.command=command;
   }
   
   public String getCommand()
   {
      return this.command;
   }
}
