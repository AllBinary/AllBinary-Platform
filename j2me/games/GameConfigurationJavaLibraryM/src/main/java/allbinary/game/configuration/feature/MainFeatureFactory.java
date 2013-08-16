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

public class MainFeatureFactory
{
    private static final MainFeatureFactory instance = new MainFeatureFactory();
    
    public static MainFeatureFactory getInstance()
    {
        return instance;
    }
    
    public final Feature FULL_SCREEN = new Feature("Full Screen");
    public final Feature TITLE_BAR = new Feature("Title Bar");

    public final Feature STATIC = new Feature("Static Dependent Feature");

    public final Feature LOAD_ONDEMAND = new Feature("Load OnDemand");

    public final Feature LOAD_ALL = new Feature("Load All");
    
}
