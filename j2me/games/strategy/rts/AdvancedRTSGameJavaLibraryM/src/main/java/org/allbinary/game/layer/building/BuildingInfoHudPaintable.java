/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer.building;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.SelectionHudPaintable;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.game.layer.NullPathFindingLayer;
import org.allbinary.game.layer.PathFindingLayerInterface;
import org.allbinary.game.layer.hud.basic.NumberStringHud;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.math.MathUtil;

/**
 *
 * @author user
 */
public class BuildingInfoHudPaintable extends SelectionHudPaintable {

    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final BuildingInfoHudPaintable instance = new BuildingInfoHudPaintable();

    public static final BuildingInfoHudPaintable getInstance() {
        return instance;
    }

    private final String HEALTH = "Health:";

    private final NumberStringHud productivityHud;
    private final NumberStringHud efficiencyHud;
    private final NumberStringHud healthHud;
    private final NumberStringHud maxHealthHud;

    private PathFindingLayerInterface rtsLayer = NullPathFindingLayer.NULL_PATH_FINDING_LAYER;

    private BuildingInfoHudPaintable() {
        
        NumberStringHud productivityHud = NumberStringHud.NULL_NUMBER_STRING_HUD;
        NumberStringHud efficiencyHud = NumberStringHud.NULL_NUMBER_STRING_HUD;
        NumberStringHud healthHud = NumberStringHud.NULL_NUMBER_STRING_HUD;
        NumberStringHud maxHealthHud = NumberStringHud.NULL_NUMBER_STRING_HUD;
        
        try {

            int index = 0;

            final BasicHudFactory basicHudFactory = BasicHudFactory.getInstance();

            final int DEFAULT_CHAR_HEIGHT = myFont.DEFAULT_CHAR_HEIGHT;

            productivityHud = new NumberStringHud(
                "Productivity:", 999,
                basicHudFactory.ABSOLUTE,
                basicHudFactory.HORIZONTAL,
                this.textX, y + ((index + 1) * DEFAULT_CHAR_HEIGHT),
                0, this.getBasicColorP());
            index++;

            efficiencyHud = new NumberStringHud(
                "Efficiency:", 999,
                basicHudFactory.ABSOLUTE,
                basicHudFactory.HORIZONTAL,
                this.textX, y + ((index + 1) * DEFAULT_CHAR_HEIGHT),
                0, this.getBasicColorP());
            index++;

            int totalLength = HEALTH.length() + 1;
            healthHud = new NumberStringHud(
                HEALTH, 99999,
                basicHudFactory.ABSOLUTE,
                basicHudFactory.HORIZONTAL,
                this.textX, y + ((index + 1) * DEFAULT_CHAR_HEIGHT),
                0, this.getBasicColorP());

            maxHealthHud = new NumberStringHud(
                "/ ", 99999,
                basicHudFactory.ABSOLUTE,
                basicHudFactory.HORIZONTAL,
                this.textX + (totalLength * DEFAULT_CHAR_HEIGHT), y + ((index + 1) * DEFAULT_CHAR_HEIGHT),
                0, this.getBasicColorP());
            
        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
        this.productivityHud = productivityHud;
        this.efficiencyHud = efficiencyHud;
        this.healthHud = healthHud;
        this.maxHealthHud = maxHealthHud;
    }

    @Override
    public void setBasicColorP(BasicColor basicColor) {
        super.setBasicColorP(basicColor);
        this.productivityHud.setBasicColorP(basicColor);
        this.efficiencyHud.setBasicColorP(basicColor);
        this.healthHud.setBasicColorP(basicColor);
        this.maxHealthHud.setBasicColorP(basicColor);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        this.productivityHud.paint(graphics);
        this.efficiencyHud.paint(graphics);
        this.healthHud.paint(graphics);
        this.maxHealthHud.paint(graphics);

        this.getAnimationInterface().paint(graphics, this.imageX, y);
    }

    @Override
    public void updateSelectionInfo() {
        final BuildingLayer buildingLayer = (BuildingLayer) this.rtsLayer;

        this.setName(buildingLayer.getName());

        this.setAnimationInterface(buildingLayer.getVerticleBuildAnimationInterface());

        this.productivityHud.set(buildingLayer.getProductivity());

        this.efficiencyHud.set(buildingLayer.getEfficiency() / 100);

        int health = buildingLayer.getHealthInterface().getHealth();
        this.healthHud.set(health);

        int totalLength = HEALTH.length() + MathUtil.getInstance().getTotalDigits(health);
        this.maxHealthHud.setX(this.textX + MyFont.getInstance().stringWidth(totalLength));

        this.maxHealthHud.set(
            buildingLayer.getHealthInterface().getMaxHealth());
    }

    public void setRtsLayer(PathFindingLayerInterface rtsLayer) {
        this.rtsLayer = rtsLayer;
    }

}
