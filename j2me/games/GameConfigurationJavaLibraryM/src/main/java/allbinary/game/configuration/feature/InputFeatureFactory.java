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
package allbinary.game.configuration.feature;

public class InputFeatureFactory
{
    private static final InputFeatureFactory instance = new InputFeatureFactory();
    
    public static InputFeatureFactory getInstance()
    {
        return instance;
    }
    
    private InputFeatureFactory()
    {
        
    }
    
    public final InputFeature REMOVE_DUPLICATE_KEY_PRESSES = 
        new InputFeature("Remove Duplicate Key Presses");
    
    public final InputFeature INPUT_MAPPING = 
        new InputFeature("Input Mapping");

    public final InputFeature MULTI_KEY_PRESS = 
        new InputFeature("Multi Key Press");
    public final InputFeature SINGLE_KEY_REPEAT_PRESS = 
        new InputFeature("Single Key Repeat Press");
    
    public final InputFeature SINGLE_KEY_PRESS = 
        new InputFeature("Single Key Press");
    
    public boolean isSingleKeyProcessing()
    {
        Features features = Features.getInstance();
        if(features.isFeature(this.SINGLE_KEY_REPEAT_PRESS) || features.isFeature(this.SINGLE_KEY_PRESS))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
