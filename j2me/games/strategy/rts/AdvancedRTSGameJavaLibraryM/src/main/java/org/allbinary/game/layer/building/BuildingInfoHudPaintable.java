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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.ag.layer.hud.basic.NumberStringHud;
import org.allbinary.game.ag.layer.hud.basic.NumberStringHudFactory;
import org.allbinary.game.layer.SelectionHudPaintable;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.game.layer.NullPathFindingLayer;
import org.allbinary.game.layer.PathFindingLayerInterface;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.logic.math.MathUtil;

/**
 *
 * @author user
 */
public class BuildingInfoHudPaintable extends SelectionHudPaintable {

    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final BuildingInfoHudPaintable instance = new BuildingInfoHudPaintable();

    public static final BuildingInfoHudPaintable getInstance() {
        return BuildingInfoHudPaintable.instance;
    }

    private final String HEALTH = "Health:";

    private final NumberStringHud productivityHud;
    private final NumberStringHud efficiencyHud;
    private final NumberStringHud healthHud;
    private final NumberStringHud maxHealthHud;
    
    private PathFindingLayerInterface rtsLayer = NullPathFindingLayer.NULL_PATH_FINDING_LAYER;

    private int currentHealth = 0;

    private BuildingInfoHudPaintable() {
        
        final NumberStringHudFactory numberStringHudFactory = NumberStringHudFactory.getInstance();
        NumberStringHud productivityHud = numberStringHudFactory.NULL_NUMBER_STRING_HUD;
        NumberStringHud efficiencyHud = numberStringHudFactory.NULL_NUMBER_STRING_HUD;
        NumberStringHud healthHud = numberStringHudFactory.NULL_NUMBER_STRING_HUD;
        NumberStringHud maxHealthHud = numberStringHudFactory.NULL_NUMBER_STRING_HUD;
        
        try {

            final BasicHudFactory basicHudFactory = BasicHudFactory.getInstance();
            final BasicColor basicColor = this.getBasicColorP();
            final int textX = this.textX;
            final int y = this.y;

            productivityHud = new BuildingNumberHud("Productivity:", 999, basicHudFactory,
                    basicColor, textX, y, 1, 0, true);
            efficiencyHud = new BuildingNumberHud("Efficiency:", 999, basicHudFactory,
                    basicColor, textX, y, 2, 0, false);
            healthHud = new BuildingNumberHud(this.HEALTH, 99999, basicHudFactory,
                    basicColor, textX, y, 3, 0, true);
            maxHealthHud = new BuildingNumberHud("/ ", 99999, basicHudFactory,
                    basicColor, textX, y, 3, this.HEALTH.length() + 1, false);
            
        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
        this.productivityHud = productivityHud;
        this.efficiencyHud = efficiencyHud;
        this.healthHud = healthHud;
        this.maxHealthHud = maxHealthHud;
    }


    private static final class BuildingNumberHud extends NumberStringHud {

        private final int baseTextX;
        private final int baseY;
        private final int row;
        private final int prefixLength;
        private final boolean positionBeforeMeasurement;

        BuildingNumberHud(final String label, final int maximum, final BasicHudFactory basicHudFactory,
                final BasicColor basicColor, final int baseTextX, final int baseY,
                final int row, final int prefixLength, final boolean positionBeforeMeasurement) {
            super(label, maximum, basicHudFactory.ABSOLUTE, basicHudFactory.HORIZONTAL, 0, basicColor);
            this.baseTextX = baseTextX;
            this.baseY = baseY;
            this.row = row;
            this.prefixLength = prefixLength;
            this.positionBeforeMeasurement = positionBeforeMeasurement;
        }

        @Override
        public void updateMeasurement(final Graphics graphics) {
            if (!this.positionBeforeMeasurement) {
                super.updateMeasurement(graphics);
            }

            final Font font = graphics.getFont();
            this.updateMaxWidth = this.baseTextX + this.prefixLength * font.getHeight();
            this.updateMaxHeight = this.baseY + this.row * font.getHeight();

            if (this.positionBeforeMeasurement) {
                super.updateMeasurement(graphics);
            }
        }
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        
        final int totalLength = this.HEALTH.length() + MathUtil.getInstance().getTotalDigits(this.currentHealth);
        this.maxHealthHud.setX(this.textX + MyFontProcessor.defaultStringWidth(font, totalLength));

        this.myFontProcessor = MyFontProcessor.getInstance();
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

        this.myFontProcessor.process(graphics);
        
        this.productivityHud.paint(graphics);
        this.efficiencyHud.paint(graphics);
        this.healthHud.paint(graphics);
        this.maxHealthHud.paint(graphics);

        this.getAnimationInterface().paintXY(graphics, this.imageX, this.y);
    }

    @Override
    public void updateSelectionInfo() {
        final BuildingLayer buildingLayer = (BuildingLayer) this.rtsLayer;

        this.setName(buildingLayer.getName());

        this.setAnimationInterface(buildingLayer.getVerticleBuildAnimationInterface());

        this.productivityHud.set(buildingLayer.getProductivity());

        this.efficiencyHud.set(buildingLayer.getEfficiency() / 100);

        this.currentHealth = buildingLayer.getHealthInterface().getHealth();
        this.healthHud.set(this.currentHealth);

        this.myFontProcessor = this.updateMyFontProcessor;

        this.maxHealthHud.set(buildingLayer.getHealthInterface().getMaxHealth());
    }

    public void setRtsLayer(PathFindingLayerInterface rtsLayer) {
        this.rtsLayer = rtsLayer;
    }

}
