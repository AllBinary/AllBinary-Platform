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
package org.allbinary.business.init;

import org.allbinary.logic.io.path.AbPath;

public interface InitInfoInterface
{
   boolean isTesting();

   String getTesting();
   
   String getTestHtmlPath();

   String getMainPath();

   void setTesting(String value);

   void setTestHtmlPath(AbPath value);
   
   void setMainPath(AbPath value);

   boolean isTestingValid(String testing) throws Exception;

   boolean isTestHtmlPathValid(AbPath value);

   boolean isMainPathValid(AbPath value);

   //public HashMap toHashMap();
}
