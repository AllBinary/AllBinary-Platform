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
package tags.transform.info;

import admin.tags.TableTag;
import taghelpers.transform.info.TransformInfoHelperFactory;


public class TransformInfoTableTag extends TableTag
{
   public TransformInfoTableTag()
   {
      this.setTagHelperFactory(new TransformInfoHelperFactory());
   }
}
