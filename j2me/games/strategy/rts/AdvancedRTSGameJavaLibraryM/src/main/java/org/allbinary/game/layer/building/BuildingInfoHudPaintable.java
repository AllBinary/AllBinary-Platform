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

import org.allbinary.game.layer.SelectionHudPaintable;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.game.layer.NullPathFindingLayer;
import org.allbinary.game.layer.PathFindingLayerInterface;
import org.allbinary.game.layer.hud.basic.NumberStringHud;
import org.allbinary.game.layer.hud.basic.NumberStringHudFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
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

            int index = 0;

            final BasicHudFactory basicHudFactory = BasicHudFactory.getInstance();
            final BasicColor basicColor = this.getBasicColorP();

            final int textX = this.textX;
            final int y = this.y;
            final int firstIndex = index;
            class ProductivityNumberStringHud extends NumberStringHud {
                
                ProductivityNumberStringHud() {
                    super("Productivity:", 999,
                        basicHudFactory.ABSOLUTE, basicHudFactory.HORIZONTAL,
                        0, basicColor);
                }
                
                @Override
                public void updateMeasurement(final Graphics graphics) {
                    final Font font = graphics.getFont();
                    this.updateMaxWidth = textX;
                    this.updateMaxHeight = y + ((firstIndex + 1) * font.getHeight());
                    
                    super.updateMeasurement(graphics);
                }

            };
            
            productivityHud = new ProductivityNumberStringHud();
            index++;

            final int secondIndex = index;
            class EfficiencyNumberStringHud extends NumberStringHud {
                
                EfficiencyNumberStringHud() {
                    super("Efficiency:", 999,
                        basicHudFactory.ABSOLUTE, basicHudFactory.HORIZONTAL,
                        0, basicColor);
                }
                
                @Override
                public void updateMeasurement(final Graphics graphics) {
                    super.updateMeasurement(graphics);

                    final Font font = graphics.getFont();
                    this.updateMaxWidth = textX;
                    this.updateMaxHeight = y + ((secondIndex + 1) * font.getHeight());
                }

            };

            efficiencyHud = new EfficiencyNumberStringHud();
            index++;

            final String HEALTH = this.HEALTH;
            final int thirdIndex = index;
            class HealthNumberStringHud extends NumberStringHud {
                
                HealthNumberStringHud() {
                    super(HEALTH, 99999,
                        basicHudFactory.ABSOLUTE, basicHudFactory.HORIZONTAL,
                        0, basicColor);
                }
                
                @Override
                public void updateMeasurement(final Graphics graphics) {
                    
                    final Font font = graphics.getFont();
                    this.updateMaxWidth = textX;
                    this.updateMaxHeight = y + ((thirdIndex + 1) * font.getHeight());
                    
                    super.updateMeasurement(graphics);
                }

            };
            
            final int totalLength = HEALTH.length() + 1;
            healthHud = new HealthNumberStringHud();

            final int fourthIndex = index;
            class MaxHealthNumberStringHud extends NumberStringHud {
                
                MaxHealthNumberStringHud() {
                    super("/ ", 99999,
                        basicHudFactory.ABSOLUTE, basicHudFactory.HORIZONTAL, 
                        0, basicColor);
                }
                
                @Override
                public void updateMeasurement(final Graphics graphics) {
                    super.updateMeasurement(graphics);
                    
                    final Font font = graphics.getFont();
                    this.updateMaxWidth = textX + (totalLength * font.getHeight());
                    this.updateMaxHeight = y + ((fourthIndex + 1) * font.getHeight());
                }

            };

            maxHealthHud = new MaxHealthNumberStringHud();
            
        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
        this.productivityHud = productivityHud;
        this.efficiencyHud = efficiencyHud;
        this.healthHud = healthHud;
        this.maxHealthHud = maxHealthHud;
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
