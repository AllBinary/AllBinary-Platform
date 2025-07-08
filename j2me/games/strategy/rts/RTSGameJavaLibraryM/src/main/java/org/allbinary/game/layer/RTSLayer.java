/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

import java.util.Hashtable;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.input.form.RTSFormInput;
import org.allbinary.game.multiplayer.layer.MultiPlayerGameLayer;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.animation.RotationAnimation;
import org.allbinary.animation.caption.CaptionAnimationHelperBase;
import org.allbinary.game.combat.destroy.DestroyedLayerProcessor;
import org.allbinary.game.health.Health;
import org.allbinary.game.identification.Group;
import org.allbinary.game.input.GameInputInterface;
import org.allbinary.game.input.GameInputProcessor;
import org.allbinary.game.input.GameInputProcessorUtil;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.InputFactory;
import org.allbinary.game.layer.waypoint.Waypoint2LogHelper;
import org.allbinary.game.layer.waypoint.WaypointLogHelper;
import org.allbinary.game.layer.waypoint.WaypointRunnableLogHelper;
import org.allbinary.game.part.PartInterfaceUtil;
import org.allbinary.game.tick.TickableInterface;
import org.allbinary.game.tracking.TrackingEvent;
import org.allbinary.game.view.TileLayerPositionIntoViewPosition;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.view.ViewPosition;
import org.allbinary.view.event.ViewPositionEventHandler;
import org.allbinary.game.multiplayer.layer.RemoteInfo;
import org.allbinary.math.LayerDistanceUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
public class RTSLayer
    extends MultiPlayerGameLayer
    implements
    TickableInterface,
    GameInputInterface,
    GameKeyEventSourceInterface,
    RTSInterface,
    PathFindingLayerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final boolean debug = true;
    public final boolean showMoreCaptionStates = debug;
    
    protected final GameInputProcessor[] inputProcessorArray = 
        new GameInputProcessor[InputFactory.getInstance().MAX];

    public RTSLayer2LogHelper rtsLayer2LogHelper = RTSLayer2LogHelper.getInstance();

    public WaypointLogHelper waypointLogHelper = WaypointLogHelper.getInstance();
    public Waypoint2LogHelper waypoint2LogHelper = Waypoint2LogHelper.getInstance();
    public WaypointRunnableLogHelper waypointRunnableLogHelper = WaypointRunnableLogHelper.getInstance();
    
    private final RTSFormInput rtsFormInput;
    private IndexedAnimation verticleBuildAnimationInterface;
    protected Animation rangeAnimation;
    protected Animation initRangeAnimation;
    protected Animation sensorRangeAnimation;
    protected Animation initSensorRangeAnimation;
    protected final Animation baseAnimationInterface;
    private final TimeDelayHelper buildFrameTimeHelper = new TimeDelayHelper(50);
    private final Animation buildAnimationInterface;
    private Animation animationInterface;
    protected final IndexedAnimation initAnimationInterface;
    protected IndexedAnimation indexedButShouldBeRotationAnimationInterface;
    protected final IndexedAnimation emptyAnimationInterface;
    protected final IndexedAnimation destroyAnimationInterface;
    protected final TimeDelayHelper fireTimeHelper = new TimeDelayHelper(700);

    /*
    private static final TimeDelayHelper towersFireTimeHelper = new TimeDelayHelper(50);
    if (RTSLayer.towersFireTimeHelper.isTime())
    {
    }
     */
    private final String rootName;

    protected final Hashtable hashtable = new Hashtable();
    private final int BUILD_VALUE = 63;
    public final GeographicMapCellPositionArea geographicMapCellPositionArea;
    
    private Health healthInterface;
    private static final int id = 0;
    private int level = 1;
    private int maxLevel = 1;
    //Could change to health
    private int hackVerticleBuild = BUILD_VALUE;
    //private int hackBuild = 0;
    protected int slightAngle = 0;
    protected static final int MAIN_INDEX = 0;
    protected static final int SECONDARY_INDEX = 1;
    protected int percentComplete;
    private boolean destroyed = false;

    private boolean selected = false;

    protected RTSLayer(
        final RemoteInfo remoteInfo,
        final Group[] groupInterface,
        final String rootName,
        final String name,
        final Health healthInterface,
        final RTSFormInput rtsFormInput,
        final AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface emptyAnimationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface baseAnimationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface buildAnimationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface verticleBuildAnimationInterfaceFactoryInterface,
        final ProceduralAnimationInterfaceFactoryInterface proceduralAnimationInterfaceFactoryInterface,
        final Rectangle rectangle,
        final int x, final int y)
        throws Exception
    {
        this(remoteInfo, groupInterface, rootName, name, 
            healthInterface, rtsFormInput, 
            animationInterfaceFactoryInterface,
            emptyAnimationInterfaceFactoryInterface,
            baseAnimationInterfaceFactoryInterface,
            buildAnimationInterfaceFactoryInterface,
            verticleBuildAnimationInterfaceFactoryInterface,
            proceduralAnimationInterfaceFactoryInterface,
            rectangle,
            x, y, new TileLayerPositionIntoViewPosition());
    }
    
    protected RTSLayer(
        final RemoteInfo remoteInfo,
        final Group[] groupInterface,
        final String rootName,
        final String name,
        final Health healthInterface,
        final RTSFormInput rtsFormInput,
        final AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface emptyAnimationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface baseAnimationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface buildAnimationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface verticleBuildAnimationInterfaceFactoryInterface,
        final ProceduralAnimationInterfaceFactoryInterface proceduralAnimationInterfaceFactoryInterface,
        final Rectangle rectangle,
        final int x, final int y,
        final ViewPosition viewPosition)
        throws Exception
    {
        super(remoteInfo, groupInterface, name, rectangle, viewPosition);
        
        this.initInputProcessors();
        
        this.rootName = rootName;

        this.healthInterface = healthInterface;

        this.rtsFormInput = rtsFormInput;        

        //this.setPosition(x - this.getWidth() / 2, y - this.getHeight() / 2);
        this.setPosition(x, y, z);

        this.verticleBuildAnimationInterface =
            (IndexedAnimation) verticleBuildAnimationInterfaceFactoryInterface.getInstance(0);

        this.buildAnimationInterface =
            buildAnimationInterfaceFactoryInterface.getInstance(0);

        this.baseAnimationInterface =
            baseAnimationInterfaceFactoryInterface.getInstance(0);

        this.emptyAnimationInterface =
            (IndexedAnimation) emptyAnimationInterfaceFactoryInterface.getInstance(0);

        this.indexedButShouldBeRotationAnimationInterface =
            (IndexedAnimation) animationInterfaceFactoryInterface.getInstance(0);

        this.initAnimationInterface = this.indexedButShouldBeRotationAnimationInterface;

        this.destroyAnimationInterface = (IndexedAnimation) proceduralAnimationInterfaceFactoryInterface.getInstance(
            this.indexedButShouldBeRotationAnimationInterface);

        this.rangeAnimation =
            NullAnimationFactory.getFactoryInstance().getInstance(0);

        this.sensorRangeAnimation = NullAnimationFactory.getFactoryInstance().getInstance(0);

        this.animationInterface =
            this.initAnimationInterface;

        this.setPartInterfaceArray(PartInterfaceUtil.getZeroArray());

        this.geographicMapCellPositionArea = new GeographicMapCellPositionArea(this);

        //this.setVisible(false);
        //ViewPositionEventHandler.getInstance().addListener(this);
    }

    //used to simulate cost
    protected RTSLayer(final RemoteInfo remoteInfo)
        throws Exception
    {
        super(remoteInfo, null, RectangleFactory.SINGLETON,
            new TileLayerPositionIntoViewPosition());

        this.initInputProcessors();
        
        this.rtsFormInput = null;

        this.baseAnimationInterface = null;
        this.buildAnimationInterface = null;

        this.initAnimationInterface = null;
        this.emptyAnimationInterface = null;
        this.destroyAnimationInterface = null;

        this.rootName = null;
        this.geographicMapCellPositionArea = null;
    }

    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        super.setAllBinaryGameLayerManager(allBinaryGameLayerManager);

        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) this.allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];

        final AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
        final TileLayerPositionIntoViewPosition viewPosition2 =
            (TileLayerPositionIntoViewPosition) this.getViewPosition();
        viewPosition2.setTiledLayer(tiledLayer);
    
        this.updateWaypointBehavior(geographicMapInterface);
        
    }
    
    public void updateWaypointBehavior(final BasicGeographicMap geographicMapInterface) throws Exception {
        this.geographicMapCellPositionArea.update(geographicMapInterface);
    }
    
    public void construct(final RTSPlayerLayerInterface rtsPlayerLayerInterface)
    throws Exception
    {
        ViewPositionEventHandler.getInstance().addListener((AllBinaryLayer) this);
        this.animationInterface = this.buildAnimationInterface;
        this.hackVerticleBuild = 0;
    }
    
    public void select()
    {
        //logUtil.put("selected: ", this, "selected: select");
        
        this.rangeAnimation = this.initRangeAnimation;
        this.sensorRangeAnimation = this.initSensorRangeAnimation;
        
        this.setSelected(true);
    }

    public void deselect()
    {
        this.rangeAnimation = NullAnimationFactory.getFactoryInstance().getInstance(0);
        this.sensorRangeAnimation = NullAnimationFactory.getFactoryInstance().getInstance(0);
        
        this.setSelected(false);
    }

    protected void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public boolean isSelected()
    {
        return this.selected;
    }
    
    public int getPercentComplete()
    {
        return this.percentComplete;
    }

    public ScrollSelectionForm getScrollSelectionForm()
    {
        return null;
    }
    
    public void initInputProcessors()
    {
        GameInputProcessorUtil.init(this.inputProcessorArray);
    }
    
    public void processBuiltTick(final AllBinaryLayerManager allBinaryLayerManager)
        throws Exception
    {
        this.animationInterface = this.indexedButShouldBeRotationAnimationInterface;
    }

    public void processTick(final AllBinaryLayerManager allBinaryLayerManager)
    {
        try
        {
            if (this.isCompleted())
            {
                this.processBuiltTick(allBinaryLayerManager);
            }
            else
            {
                this.build();
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "processTick", e);
        }
    }

    public void processInput(final AllBinaryLayerManager layerManager)
        throws Exception
    {
    }

    protected void reload()
    {
    }

    public void onMovement(final TrackingEvent trackingEvent)
    {
    }

    public void onMovementFound(final TrackingEvent trackingEvent) throws Exception
    {
    }
    
    public void paint(final Graphics graphics)
    {
        super.paintFirst(graphics);
        
        final ViewPosition viewPosition = this.getViewPosition();
        int viewX = viewPosition.getX();
        int viewY = viewPosition.getY();

        this.getAnimationInterface().paint(graphics, viewX, viewY);

        //super.paint(graphics);
    }

    public void damage(final int damage, final int damageType)
        throws Exception
    {
        //super.damage(damage, damageType);
    }

    public int getSourceId()
    {
        return id;
    }

    private final RTSLayerUtil rtsLayerUtil = RTSLayerUtil.getInstance();

    public void downgrade()
    {
        rtsLayerUtil.downgrade(this);
    }

    public void upgrade()
    {
        rtsLayerUtil.upgrade(this);
    }

    public boolean isCompleted()
    {
        if (this.hackVerticleBuild < BUILD_VALUE)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private void build() throws Exception
    {
        if (this.buildFrameTimeHelper.isTime())
        {
            this.animationInterface = this.buildAnimationInterface;

            //this.buildAnimationInterface.nextFrame()
            this.verticleBuildAnimationInterface.nextFrame();
            this.hackVerticleBuild++;
        }

        this.percentComplete = 100 * this.hackVerticleBuild / BUILD_VALUE;

        this.getHudPaintable().updateInfo();
    }

    public boolean isSelfUpgradeable()
    {
        return true;
    }
    
    public int getCost()
    {
        return rtsLayerUtil.getCost(this);
    }

    public int getDowngradeCost()
    {
        return rtsLayerUtil.getDowngradeCost(this);
    }

    public int getUpgradeCost()
    {
        return rtsLayerUtil.getUpgradeCost(this);
    }

    public boolean isUpgradeable()
    {
        if (this.getLevel() < this.getMaxLevel())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isDowngradeable()
    {
        if (this.getLevel() > 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * @return the level
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level)
    {
        this.level = level;
    }

    /**
     * @return the verticleBuildAnimationInterface
     */
    public Animation getVerticleBuildAnimationInterface()
    {
        return verticleBuildAnimationInterface;
    }

    /**
     * @return the animationInterface
     */
    public Animation getAnimationInterface()
    {
        return animationInterface;
    }

    /**
     * @param animationInterface the animationInterface to set
     */
    public void setAnimationInterface(final Animation animationInterface)
    {
        this.animationInterface = animationInterface;
    }

    private final LayerDistanceUtil layerDistanceUtil = LayerDistanceUtil.getInstance();
    public void setTarget(final PathFindingLayerInterface targetGameLayer) throws Exception {
        final int anotherTargetDistance = layerDistanceUtil.getDistance(
            (AllBinaryLayer) this, (AllBinaryLayer) targetGameLayer);

        final WaypointBehaviorBase waypointBehaviorBase = this.getWaypointBehavior();
        waypointBehaviorBase.setTarget((PathFindingLayerInterface) targetGameLayer, anotherTargetDistance);
    }
    
    public SelectionHudPaintable createHudPaintable()
    {
        return null;
    }
    
    public SelectionHudPaintable getHudPaintable()
    {
        return null;
    }

    /**
     * @return the maxLevel
     */
    public int getMaxLevel()
    {
        return maxLevel;
    }

    /**
     * @param maxLevel the maxLevel to set
     */
    public void setMaxLevel(final int maxLevel)
    {
        this.maxLevel = maxLevel;
    }

    /**
     * @return the healthInterface
     */
    public Health getHealthInterface()
    {
        return healthInterface;
    }

    /**
     * @param healthInterface the healthInterface to set
     */
    public void setHealthInterface(final Health healthInterface)
    {
        this.healthInterface = healthInterface;
    }

    /**
     * @return the rtsFormInput
     */
    public RTSFormInput getRTSFormInput()
    {
        return rtsFormInput;
    }

    public boolean isDestroyed()
        throws Exception
    {
        return this.destroyed;
    }

    public void setDestroyed(final boolean destroyed)
        throws Exception
    {
        this.destroyed = destroyed;

        if (this.isDestroyed())
        {
            /*
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append("setDestroyed: ");
            stringBuffer.append(this.getName());
            
            logUtil.put(stringBuffer.toString(), this, "setDestroyed");
            */
            
            DestroyedLayerProcessor.getInstance().add(this);
            ViewPositionEventHandler.getInstance().removeListener(this);
        }
    }
    
    public BasicArrayList getEndGeographicMapCellPositionList() {
        return this.geographicMapCellPositionArea.getOccupyingGeographicMapCellPositionList();
    }

    public GeographicMapCellPositionArea getGeographicMapCellPositionArea() {
        return geographicMapCellPositionArea;
    }

    public boolean shouldHandleStartSameAsEnd() {
        return true;
    }

    public void handleCost(PathFindingLayerInterface ownerLayer) throws Exception {
    }

    public WaypointBehaviorBase getWaypointBehavior() {
        return null;
    }

    public PathFindingLayerInterface getParentLayer() {
        return null;
    }

    public RTSLayer2LogHelper getRTSLayer2LogHelper() {
        return this.rtsLayer2LogHelper;
    }
    
    public WaypointLogHelper getWaypointLogHelper() {
        return this.waypointLogHelper;
    }

    public Waypoint2LogHelper getWaypoint2LogHelper() {
        return this.waypoint2LogHelper;
    }
    
    public WaypointRunnableLogHelper getWaypointRunnableLogHelper() {
        return this.waypointRunnableLogHelper;
    }

    public boolean shouldAddWaypointFromBuilding() {
        return false;
    }

    public CaptionAnimationHelperBase getCaptionAnimationHelper() {
        return null;
    }

    public boolean isShowMoreCaptionStates() {
        return this.showMoreCaptionStates;
    }
    
    public void init(final GeographicMapCellHistory geographicMapCellHistory,
        final BasicArrayList geographicMapCellPositionBasicArrayList) throws Exception { 
        
    }
    
    public GeographicMapCellPosition getCurrentGeographicMapCellPosition()
    throws Exception
    {
        return null;
    }

    public GeographicMapCellPosition getTopLeftGeographicMapCellPosition()
    throws Exception
    {
        return null;
    }
        
    public BasicArrayList getMoveOutOfBuildAreaPath(
        final GeographicMapCellPosition geographicMapCellPosition) {
        return null;
    }   

    public void setClosestGeographicMapCellHistory(final BasicArrayList pathsList)
        throws Exception
    {
    }
    
    public void teleportTo(final GeographicMapCellPosition geographicMapCellPosition) {
        
    }
    
    public void setLoad(short resource) throws Exception {
        
    }

    public BasicArrayList getSurroundingGeographicMapCellPositionList() 
        throws Exception {
        return null;
    }
    
    public void trackTo(final String reason) 
        throws Exception {
        
    }

    public void trackTo(final int dx, final int dy) 
        throws Exception {
        
    }
    
    public boolean isWaypointListEmptyOrOnlyTargets() {
        return false;
    }

    public TrackingEvent getTrackingEvent() {
        return null;
    }

    public boolean buildingChase(final AllBinaryLayer allbinaryLayer, final GeographicMapCellPosition cellPosition) throws Exception {
        return false;
    }
    
    public void allStop() {
        
    }
    

    public static int getMinStaticType()
    {
        return 1;
    }

    public static int getMaxStaticType()
    {
        return 4;
    }
    
    public static boolean isRTSLayer(final AllBinaryLayer layerInterface)
    {
        if(layerInterface.getType() >= RTSLayer.getMinStaticType() &&
                layerInterface.getType() <= RTSLayer.getMaxStaticType())
        {
            return true;
        }
        return false;
    }
    
    public boolean implmentsTickableInterface()
    {
        return true;
    }
    
    public boolean implmentsGameInputInterface()
    {
        return true;
    }

    public String getRootName()
    {
        return rootName;
    }    

    /**
     * @return the rotationAnimationInterface
     */
    public RotationAnimation getRotationAnimationInterface()
    {
        return (RotationAnimation) indexedButShouldBeRotationAnimationInterface;
    }

    public void setFrame(int index) {
        this.getRotationAnimationInterface().setFrame(index);
    }
    
    /**
     * @param rotationAnimationInterface the rotationAnimationInterface to set
     */
    public void setRotationAnimationInterface(
        final RotationAnimation rotationAnimationInterface)
    {
        this.indexedButShouldBeRotationAnimationInterface = rotationAnimationInterface;
    }
}
