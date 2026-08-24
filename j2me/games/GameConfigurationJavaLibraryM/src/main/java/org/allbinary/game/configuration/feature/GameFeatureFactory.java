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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class GameFeatureFactory
{
    private static final GameFeatureFactory instance = new GameFeatureFactory();
    
    @JsMethod
    public static GameFeatureFactory getInstance()
    {
        return GameFeatureFactory.instance;
    }
    
    @JsConstructor
    private GameFeatureFactory()
    {
        
    }
    
    @JsProperty
    public final GameFeature SOUND = new GameFeature("Sound");
    @JsProperty
    public final GameFeature MUSIC = new GameFeature("Music");

    @JsProperty
    public final Feature SCREEN_SHAKE = new Feature("Screen Shake");
    @JsProperty
    public final Feature POST_IMAGE_LOADING_MODIFICATION = new Feature("Post Image Loading Modification (Scaling, Rotation, or Other)");
    
    @JsProperty
    public final GameFeature HEALTH_BARS = new GameFeature("Health Bars");
    @JsProperty
    public final GameFeature DAMAGE_FLOATERS = new GameFeature("Damage Floaters");

    @JsProperty
    public final GameFeature GAME_INPUT_LAYER_PROCESSOR = 
        new GameFeature("Game Input Layer Processor");
    
    @JsProperty
    public final GameFeature ARTIFICIAL_INTELLEGENCE_PROCESSOR = 
        new GameFeature("Artificial Intelligence Layer Processor");

    @JsProperty
    public final GameFeature COLLIDABLE_INTERFACE_LAYER_PROCESSOR = 
        new GameFeature("Collidable Interface Layer Processor");

    @JsProperty
    public final GameFeature TICKABLE_LAYER_PROCESSOR = 
        new GameFeature("Tickable Layer Processor");
    
    @JsProperty
    public final GameFeature CHEATING = 
        new GameFeature("Cheating");
    
    @JsProperty
    public final GameFeature DROPPED_ITEMS = 
        new GameFeature("Drops");
    @JsProperty
    public final GameFeature DROPPED_ITEMS_FROM_DEATH = 
        new GameFeature("Drops from Dead");
    // public final GameFeature PICKUP_ITEMS = new
    // GameFeature("Pickups");
 
    @JsProperty
    public final GameFeature TEST_DESTROYED_LAYER_PROCESSOR = 
        new GameFeature("Destroyed Layer Processor");

    @JsProperty
    public final GameFeature COLLISIONS_WITH_SOURCE = 
        new GameFeature("Allow Collisions With Source");
    
    @JsProperty
    public final GameFeature COLLISIONS_FORCED_TWO_DIMENSIONAL = 
            new GameFeature("Collisions Forced Two Dimensional");

}
