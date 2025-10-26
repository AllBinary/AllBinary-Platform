/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.game.layer.unit;

/**
 *
 * @author User
 */
public class UnitWaypointStrings {
    
    private static final UnitWaypointStrings instance = new UnitWaypointStrings();

    /**
     * @return the instance
     */
    public static UnitWaypointStrings getInstance() {
        return instance;
    }
    
    public final String WANDERING = "Order?"; //"Lalala" //"What Now? //"Wander";
    public final String THINKING = "Thinking";
    public final String THINKING_ABOUT_TARGET = "Hmmm"; //"Analyzing" //"Hmmm?"
    public final String TARGET = "Target";
    public final String KILL = "Kill!";
    public final String STOP = "Stop";

    public final String WAYPOINT_DESTROYED_SHORT = "Uh Oh";
    public final String WAYPOINT_DESTROYED = "Waypoint Destroyed";
    public final String ALL_VISITED_SHORT = "Arrived";
    public final String ALL_VISITED = "All Visited";
    public final String ALREADY_THERE_SHORT = "Again?";
    public final String ALREADY_THERE = "Already There";
    public final String NEXT_PATH_NODE = "Next Path Node";
    
}
