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
package abcs.business.init;

import abcs.logic.basic.path.AbPath;

public interface InitInfoInterface
{
   public boolean isTesting();

   public String getTesting();
   
   public String getTestHtmlPath();

   public String getMainPath();

   public void setTesting(String value);

   public void setTestHtmlPath(AbPath value);
   
   public void setMainPath(AbPath value);

   public boolean isTestingValid(String testing) throws Exception;

   public boolean isTestHtmlPathValid(AbPath value);

   public boolean isMainPathValid(AbPath value);

   //public HashMap toHashMap();
}
