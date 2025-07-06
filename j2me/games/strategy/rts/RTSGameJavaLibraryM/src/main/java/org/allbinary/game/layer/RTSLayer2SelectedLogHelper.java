/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.game.layer;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;

/**
 *
 * @author User
 */
public class RTSLayer2SelectedLogHelper extends RTSLayer2LogHelper {
    
    protected static final RTSLayer2SelectedLogHelper instance = new RTSLayer2SelectedLogHelper();

    /**
     * @return the instance
     */
    public static RTSLayer2SelectedLogHelper getInstance() {
        return instance;
    }

    private final String TRACK_TO_FIRE_OR_MOVE = "trackTo:fireOrMove";
    private final String BUILDING_CHASE = "buildingChase";
    
    public void steeringUp(final PathFindingLayerInterface pathFindingLayerInterface) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(pathFindingLayerInterface.getName()).append(" steering - Up").toString(), this, TRACK_TO_FIRE_OR_MOVE));
    }

    public void steeringFireOrStop(final PathFindingLayerInterface pathFindingLayerInterface) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(pathFindingLayerInterface.getName()).append(" steering - Fire/All Stop").toString(), this, TRACK_TO_FIRE_OR_MOVE));
    }
    
    public void buildingAbove(final PathFindingLayerInterface pathFindingLayerInterface) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(pathFindingLayerInterface.getName()).append(" Building Above").toString(), this, BUILDING_CHASE));
    }
    
    public void buildingChaseLeft(final PathFindingLayerInterface pathFindingLayerInterface, final int angle) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(pathFindingLayerInterface.getName()).append(" steering - Going Left: ").append(angle).toString(), this, BUILDING_CHASE));
    }
        
    public void buildingChaseRight(final PathFindingLayerInterface pathFindingLayerInterface, final int angle) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(pathFindingLayerInterface.getName()).append(" steering - Going Right: ").append(angle).toString(), this, BUILDING_CHASE));
    }
    
    public void buildingDown(final PathFindingLayerInterface pathFindingLayerInterface) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(pathFindingLayerInterface.getName()).append(" Building Below").toString(), this, BUILDING_CHASE));
    }
    
    public void buildingChaseDown(final PathFindingLayerInterface pathFindingLayerInterface, final int angle) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(pathFindingLayerInterface.getName()).append(" steering - Going Down: ").append(angle).toString(), this, BUILDING_CHASE));
    }

    public void buildingChaseUp(final PathFindingLayerInterface pathFindingLayerInterface, final int angle) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(pathFindingLayerInterface.getName()).append(" steering - Going Up: ").append(angle).toString(), this, BUILDING_CHASE));
    }

    public void buildingLeft(final PathFindingLayerInterface pathFindingLayerInterface) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(pathFindingLayerInterface.getName()).append(" Building Left").toString(), this, BUILDING_CHASE));
    }
    
    public void buildingRight(final PathFindingLayerInterface pathFindingLayerInterface) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(pathFindingLayerInterface.getName()).append(" Building Right").toString(), this, BUILDING_CHASE));
    }
    
}
