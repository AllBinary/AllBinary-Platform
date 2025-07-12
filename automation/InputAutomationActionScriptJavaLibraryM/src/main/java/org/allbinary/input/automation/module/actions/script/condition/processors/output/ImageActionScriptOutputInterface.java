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
package org.allbinary.input.automation.module.actions.script.condition.processors.output;

public interface ImageActionScriptOutputInterface
    extends ProfileActionScriptOutputInterface
{
    ImageTypes getImageTypes();
    void setImageTypes(ImageTypes imageTypes);
        
    boolean isSaved();
    void setSaved(boolean saved);
    
    boolean isDisplay();
    void setDisplay(boolean display);
    
    void log();
}
