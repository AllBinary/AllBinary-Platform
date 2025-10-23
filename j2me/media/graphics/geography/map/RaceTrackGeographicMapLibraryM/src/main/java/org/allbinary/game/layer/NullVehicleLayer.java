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
package org.allbinary.game.layer;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.RotationAnimation;
import org.allbinary.game.identification.Group;
import org.allbinary.logic.math.BasicDecimal;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionTracking;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class NullVehicleLayer implements VehicleLayerInterface {

    public static final NullVehicleLayer NULL_VEHICLE_LAYER = new NullVehicleLayer();
    
    @Override
    public String getName() {
        throw new RuntimeException();
    }

    @Override
    public Group[] getGroupInterface() {
        throw new RuntimeException();
    }

    @Override
    public void setPosition(int x, int y, int z) {
        throw new RuntimeException();
    }

    @Override
    public int getHeight() {
        throw new RuntimeException();
    }

    @Override
    public int getWidth() {
        throw new RuntimeException();
    }

    @Override
    public int getHalfHeight() {
        throw new RuntimeException();
    }

    @Override
    public int getHalfWidth() {
        throw new RuntimeException();
    }

    @Override
    public int getXP() {
        throw new RuntimeException();
    }

    @Override
    public int getYP() {
        throw new RuntimeException();
    }

    @Override
    public int getZP() {
        throw new RuntimeException();
    }

    @Override
    public int getX2() {
        throw new RuntimeException();
    }

    @Override
    public int getY2() {
        throw new RuntimeException();
    }

    @Override
    public int getZ2() {
        throw new RuntimeException();
    }

    @Override
    public boolean isVisible() {
        throw new RuntimeException();
    }

    @Override
    public void move(int dx, int dy, int dz) {
        throw new RuntimeException();
    }

    @Override
    public void setVisible(boolean visible) {
        throw new RuntimeException();
    }

    @Override
    public boolean implmentsTickableInterface() {
        throw new RuntimeException();
    }

    @Override
    public boolean implmentsCollidableInterface() {
        throw new RuntimeException();
    }

    @Override
    public boolean implmentsGameInputInterface() {
        throw new RuntimeException();
    }

    @Override
    public boolean implmentsArtificialIntelligenceCompositeInterface() {
        throw new RuntimeException();
    }

    @Override
    public int getType() {
        throw new RuntimeException();
    }

    @Override
    public VehicleProperties getVehicleProperties() {
        throw new RuntimeException();
    }

//    @Override
//    public void setVehicleProperties(VehicleProperties vehicleProperties) {
//        throw new RuntimeException();
//    }

    @Override
    public BasicArrayList getGameKeyEventList() {
        throw new RuntimeException();
    }

    @Override
    public BasicDecimal getSpeedBasicDecimal() {
        throw new RuntimeException();
    }

    @Override
    public boolean isReadyForExplosion() {
        throw new RuntimeException();
    }

    @Override
    public RotationAnimation getRotationAnimationInterface() {
        throw new RuntimeException();
    }

    @Override
    public void setRotationAnimationInterface(RotationAnimation animationInterface) {
        throw new RuntimeException();
    }

    @Override
    public boolean isDestroyed() {
        throw new RuntimeException();
    }

    @Override
    public boolean isFinish() {
        throw new RuntimeException();
    }

    @Override
    public int getFinalPosition() {
        throw new RuntimeException();
    }

    @Override
    public void handleFinish() throws Exception {
        throw new RuntimeException();
    }

    @Override
    public GeographicMapCellPositionTracking getGeographicMapCellPositionTracking() {
        throw new RuntimeException();
    }

    @Override
    public GeographicMapCellHistory[] getGeographicMapCellHistoryArray() {
        throw new RuntimeException();
    }

    @Override
    public void setGeographicMapCellHistoryArray(
        GeographicMapCellHistory[] geographicMapCellHistory) {
        throw new RuntimeException();
    }

    @Override
    public void paint(Graphics graphics) {
    }

    @Override
    public void paintThreed(Graphics graphics) {
    }

}
