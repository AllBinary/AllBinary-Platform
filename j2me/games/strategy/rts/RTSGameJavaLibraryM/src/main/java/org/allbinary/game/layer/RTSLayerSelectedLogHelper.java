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


import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.math.AngleInfo;
import org.allbinary.math.NamedAngle;
import org.allbinary.math.PositionStrings;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class RTSLayerSelectedLogHelper extends RTSLayerLogHelper {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final RTSLayerSelectedLogHelper instance = new RTSLayerSelectedLogHelper();
    
    /**
     * @return the instance
     */
    public static RTSLayerSelectedLogHelper getInstance() {
        return instance;
    }

    private final CommonSeps commonSeps = CommonSeps.getInstance();
    private final PositionStrings positionStrings = PositionStrings.getInstance();
    
    private final String TRACKTO_TURNTO = "trackTo:turnTo";

    @Override
    public void setClosestGeographicMapCellHistory(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final BasicArrayList pathsList) {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(CommonLabels.getInstance().START);
        stringBuffer.append(pathsList.size());
        stringBuffer.append(" -> ");
        stringBuffer.append(StringUtil.getInstance().toString(pathsList));

        logUtil.put(stringBuffer.toString(), this, "selected: setClosestGeographicMapCellHistory");
    }
    
    @Override
    public void trackTo(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final GeographicMapCellPosition nextUnvisitedPathGeographicMapCellPosition, final int dx, final int dy, final String reason) {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(StringUtil.getInstance().toString(nextUnvisitedPathGeographicMapCellPosition));
        stringBuffer.append(positionStrings.DX_LABEL);
        stringBuffer.append(dx);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.DY_LABEL);
        stringBuffer.append(dy);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(reason);

        logUtil.put(stringBuffer.toString(), this, "selected: trackTo");
        
    }
    
    @Override
    public void turnTo(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int dx, final int dy, final AngleInfo angleInfo, final int angle, final NamedAngle movementAngle, final boolean evading, int targetAngle) {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" steering - ");
//            stringBuffer.append("angleOfTarget: ");
//            stringBuffer.append(angleOfTarget);
//            stringBuffer.append(commonSeps.FORWARD_SLASH);
//            stringBuffer.append(angleOfTarget2);
        stringBuffer.append(positionStrings.DX_LABEL);
        stringBuffer.append(dx);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.DY_LABEL);
        stringBuffer.append(dy);

        stringBuffer.append(" angle: ");
        stringBuffer.append(angle);
        stringBuffer.append(" movementAngle: ");
        stringBuffer.append(movementAngle.name);
        stringBuffer.append(commonSeps.FORWARD_SLASH);
        stringBuffer.append(movementAngle.getValue());
        
        if(angleInfo != null) {
            final short angleIncrement = angleInfo.getAngleIncrementInfo().getAngleIncrement();
            stringBuffer.append(" angleIncrement: ");
            stringBuffer.append(angleIncrement);
        }
        stringBuffer.append(" Evading: ");
        stringBuffer.append(evading);

        logUtil.put(stringBuffer.toString(), this, TRACKTO_TURNTO);
        //PreLogUtil.put(stringBuffer.toString(), this, "turnTo");
        
    }

    @Override
    public void doneMoving(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - done moving 0,0").toString(), this, TRACKTO_TURNTO);
    }

    @Override
    public void closeEnough(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - close enough").toString(), this, TRACKTO_TURNTO);
    }
    
    @Override
    public void movingLeft(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - allow movingLeft").toString(), this, TRACKTO_TURNTO);
    }
    
    @Override
    public void movingRight(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - allow movingRight").toString(), this, TRACKTO_TURNTO);
    }

    @Override
    public void movingUp(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - allow movingUp").toString(), this, TRACKTO_TURNTO);
    }

    @Override
    public void movingDown(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - allow movingDown").toString(), this, TRACKTO_TURNTO);
    }

    @Override
    public void currentMoveEnded(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - correct angle, but movement not needed for current deltas so reseting movement angle").toString(), this, TRACKTO_TURNTO);
    }

    @Override
    public void evade(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - evade").toString(), this, TRACKTO_TURNTO);
    }
    
    @Override
    public void rotateLeft(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - rotating left").toString(), this, TRACKTO_TURNTO);
    }
    
    @Override
    public void rotateRight(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - rotating right").toString(), this, TRACKTO_TURNTO);
    }

    @Override
    public void handle(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final NamedAngle movementAngle) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - movementAngle: ").append(movementAngle.name).append(commonSeps.FORWARD_SLASH).append(movementAngle.getValue()).toString(), this, TRACKTO_TURNTO);
    }
    
    @Override
    public void noRotation(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" steering - Do not rotate").toString(), this, TRACKTO_TURNTO);
    }

    @Override
    public void notOnPath(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final GeographicMapCellHistory geographicMapCellHistory, final GeographicMapCellPosition currentGeographicMapCellPosition, final BasicArrayList pathList) {
        final StringUtil stringUtil = StringUtil.getInstance();
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(' ').append(geographicMapCellHistory.getTotalVisited()).append(' ').append(stringUtil.toString(currentGeographicMapCellPosition)).append(" - trying to move but not on path: ").append(stringUtil.toString(pathList)).toString(), this, "turnTo");
    }
                    

}
