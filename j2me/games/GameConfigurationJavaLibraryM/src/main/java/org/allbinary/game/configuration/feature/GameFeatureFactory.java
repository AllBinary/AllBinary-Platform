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
package org.allbinary.game.configuration.feature;

public class GameFeatureFactory
{
    private static final GameFeatureFactory instance = new GameFeatureFactory();
    
    public static GameFeatureFactory getInstance()
    {
        return instance;
    }
    
    private GameFeatureFactory()
    {
        
    }
    
    public final GameFeature SOUND = new GameFeature("Sound");
    public final GameFeature MUSIC = new GameFeature("Music");

    public final Feature SCREEN_SHAKE = new Feature("Screen Shake");
    public final Feature POST_IMAGE_LOADING_MODIFICATION = new Feature("Post Image Loading Modification (Scaling, Rotation, or Other)");
    
    public final GameFeature HEALTH_BARS = new GameFeature("Health Bars");
    public final GameFeature DAMAGE_FLOATERS = new GameFeature("Damage Floaters");

    public final GameFeature GAME_INPUT_LAYER_PROCESSOR = 
        new GameFeature("Game Input Layer Processor");
    
    public final GameFeature ARTIFICIAL_INTELLEGENCE_PROCESSOR = 
        new GameFeature("Artificial Intelligence Layer Processor");

    public final GameFeature COLLIDABLE_INTERFACE_LAYER_PROCESSOR = 
        new GameFeature("Collidable Interface Layer Processor");

    public final GameFeature TICKABLE_LAYER_PROCESSOR = 
        new GameFeature("Tickable Layer Processor");
    
    public final GameFeature CHEATING = 
        new GameFeature("Cheating");
    
    public final GameFeature DROPPED_ITEMS = 
        new GameFeature("Drops");
    public final GameFeature DROPPED_ITEMS_FROM_DEATH = 
        new GameFeature("Drops from Dead");
    // public final GameFeature PICKUP_ITEMS = new
    // GameFeature("Pickups");
 
    public final GameFeature TEST_DESTROYED_LAYER_PROCESSOR = 
        new GameFeature("Destroyed Layer Processor");

    public final GameFeature COLLISIONS_WITH_SOURCE = 
        new GameFeature("Allow Collisions With Source");
    
    public final GameFeature COLLISIONS_FORCED_TWO_DIMENSIONAL = 
            new GameFeature("Collisions Forced Two Dimensional");
    
}
