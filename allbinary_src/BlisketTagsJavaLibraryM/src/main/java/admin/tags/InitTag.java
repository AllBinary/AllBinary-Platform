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
package admin.tags;

import admin.taghelpers.InitHelperFactory;

public class InitTag extends TableTag
{
   public InitTag()
   {
      this.setTagHelperFactory(new InitHelperFactory());
      this.setTagRequestHelperFactory(new InitHelperFactory());      
   }
}
