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
import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.animation.NullIndexedAnimationFactory;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.animation.RotationAnimation;
import org.allbinary.animation.caption.CaptionAnimationHelperBase;
import org.allbinary.game.combat.destroy.DestroyedLayerProcessor;
import org.allbinary.game.health.Health;
import org.allbinary.game.identification.Group;
import org.allbinary.game.identification.GroupFactory;
import org.allbinary.game.input.GameInputInterface;
import org.allbinary.game.input.GameInputProcessor;
import org.allbinary.game.input.GameInputProcessorUtil;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.InputFactory;
import org.allbinary.game.input.form.NullRTSFormInputFactory;
import org.allbinary.game.layer.waypoint.Waypoint2LogHelper;
import org.allbinary.game.layer.waypoint.WaypointLogHelper;
import org.allbinary.game.layer.waypoint.WaypointRunnableLogHelper;
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
import org.allbinary.logic.string.StringUtil;
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

    protected final boolean debug = true;
    public final boolean showMoreCaptionStates = debug;
    
    protected final GameInputProcessor[] inputProcessorArray = 
        new GameInputProcessor[InputFactory.getInstance().MAX];

    public RTSLayer2LogHelper rtsLayer2LogHelper = RTSLayer2LogHelper.getInstance();

    public WaypointLogHelper waypointLogHelperP = WaypointLogHelper.getInstance();
    public Waypoint2LogHelper waypoint2LogHelperP = Waypoint2LogHelper.getInstance();
    public WaypointRunnableLogHelper waypointRunnableLogHelperP = WaypointRunnableLogHelper.getInstance();
    
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
    public final GeographicMapCellPositionAreaBase geographicMapCellPositionAreaBase;
    
    private Health healthInterface = Health.NULL_HEALTH;
    private static final int id = 0;
    private int level = 1;
    private int maxLevel = 1;
    //Could change to health
    private int hackVerticleBuild = BUILD_VALUE;
    //private int hackBuild = 0;
    protected int slightAngle = 0;
    protected static final int MAIN_INDEX = 0;
    protected static final int SECONDARY_INDEX = 1;
    protected int percentCompleteP;
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

        final Animation animation = NullAnimationFactory.getFactoryInstance().getInstance(0);
        this.rangeAnimation = animation;
        this.initRangeAnimation = animation;
        this.sensorRangeAnimation = animation;
        this.initSensorRangeAnimation = animation;
        
        this.animationInterface = this.initAnimationInterface;

        this.geographicMapCellPositionAreaBase = new GeographicMapCellPositionArea(this);

        //this.setVisible(false);
        //ViewPositionEventHandler.getInstance().addListener(this);
    }

    //used to simulate cost
    protected RTSLayer(final RemoteInfo remoteInfo)
        throws Exception
    {
        super(remoteInfo, GroupFactory.getInstance().NULL_GROUP_ARRAY, RectangleFactory.SINGLETON, new TileLayerPositionIntoViewPosition());

        this.initInputProcessors();
        
        this.rtsFormInput = NullRTSFormInputFactory.getInstance();

        final Animation animation = NullAnimationFactory.getFactoryInstance().getInstance(0);
        this.baseAnimationInterface = animation;
        this.buildAnimationInterface = animation;

        this.rangeAnimation = animation;
        this.initRangeAnimation = animation;
        this.sensorRangeAnimation = animation;
        this.initSensorRangeAnimation = animation;
        
        this.animationInterface = animation;

        final IndexedAnimation indexedAnimation = (IndexedAnimation) NullIndexedAnimationFactory.getFactoryInstance().getInstance(0);
        this.indexedButShouldBeRotationAnimationInterface = indexedAnimation;
        this.initAnimationInterface = indexedAnimation;
        this.emptyAnimationInterface = indexedAnimation;
        this.destroyAnimationInterface = indexedAnimation;
        
        this.verticleBuildAnimationInterface = indexedAnimation;

        this.rootName = StringUtil.getInstance().EMPTY_STRING;
        this.geographicMapCellPositionAreaBase = GeographicMapCellPositionAreaBase.NULL_GEOGRPAHIC_MAP_POSITION_AREA_BASE;
    }

    @Override
    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        super.setAllBinaryGameLayerManager(allBinaryGameLayerManager);

        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) this.allBinaryGameLayerManagerP;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];

        final AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
        final TileLayerPositionIntoViewPosition viewPosition2 =
            (TileLayerPositionIntoViewPosition) this.getViewPosition();
        viewPosition2.setTiledLayer(tiledLayer);
    
        this.updateWaypointBehavior(geographicMapInterface);
        
    }
    
    public void updateWaypointBehavior(final BasicGeographicMap geographicMapInterface) throws Exception {
        this.geographicMapCellPositionAreaBase.update(geographicMapInterface);
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
        final Animation animation = NullAnimationFactory.getFactoryInstance().getInstance(0);
        this.rangeAnimation = animation;
        this.sensorRangeAnimation = animation;
        
        this.setSelected(false);
    }

    protected void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    @Override
    public boolean isSelected()
    {
        return this.selected;
    }
    
    @Override
    public int getPercentComplete()
    {
        return this.percentCompleteP;
    }

    public ScrollSelectionForm getScrollSelectionForm()
    {
        return ScrollSelectionForm.NULL_SCROLL_SELECTION_FORM;
    }
    
    @Override
    public void initInputProcessors()
    {
        GameInputProcessorUtil.init(this.inputProcessorArray);
    }
    
    public void processBuiltTick(final AllBinaryLayerManager allBinaryLayerManager)
        throws Exception
    {
        this.animationInterface = this.indexedButShouldBeRotationAnimationInterface;
    }

    @Override
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

    @Override
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
    
    @Override
    public void paint(final Graphics graphics)
    {
        super.paintFirst(graphics);
        
        final ViewPosition viewPosition = this.getViewPosition();
        int viewX = viewPosition.getX();
        int viewY = viewPosition.getY();

        this.getAnimationInterface().paint(graphics, viewX, viewY);

        //super.paint(graphics);
    }

    @Override
    public void damage(final int damage, final int damageType)
        throws Exception
    {
        //super.damage(damage, damageType);
    }
    
    @Override
    public int getSourceId()
    {
        return id;
    }

    private final RTSLayerUtil rtsLayerUtil = RTSLayerUtil.getInstance();

    @Override
    public void downgrade()
    {
        rtsLayerUtil.downgrade(this);
    }

    @Override
    public void upgrade()
    {
        rtsLayerUtil.upgrade(this);
    }

    @Override
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

        this.percentCompleteP = 100 * this.hackVerticleBuild / BUILD_VALUE;

        this.getHudPaintable().updateInfo();
    }

    @Override
    public boolean isSelfUpgradeable()
    {
        return true;
    }
    
    @Override
    public int getCost()
    {
        return rtsLayerUtil.getCost(this);
    }

    @Override
    public int getDowngradeCost()
    {
        return rtsLayerUtil.getDowngradeCost(this);
    }

    @Override
    public int getUpgradeCost()
    {
        return rtsLayerUtil.getUpgradeCost(this);
    }

    @Override
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

    @Override
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
    @Override
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
    @Override
    public void setTarget(final PathFindingLayerInterface targetGameLayer) throws Exception {
        final int anotherTargetDistance = layerDistanceUtil.getDistance(
            (AllBinaryLayer) this, (AllBinaryLayer) targetGameLayer);

        final WaypointBehaviorBase waypointBehaviorBase = this.getWaypointBehavior();
        waypointBehaviorBase.setTarget((PathFindingLayerInterface) targetGameLayer, anotherTargetDistance);
    }
    
    public SelectionHudPaintable createHudPaintable()
    {
        throw new RuntimeException();
    }
    
    @Override
    public SelectionHudPaintable getHudPaintable()
    {
        throw new RuntimeException();
    }

    /**
     * @return the maxLevel
     */
    @Override
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
        return this.rtsFormInput;
    }

    @Override
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
    
    @Override
    public BasicArrayList getEndGeographicMapCellPositionList() {
        return this.geographicMapCellPositionAreaBase.getOccupyingGeographicMapCellPositionList();
    }

    @Override
    public GeographicMapCellPositionAreaBase getGeographicMapCellPositionArea() {
        return geographicMapCellPositionAreaBase;
    }

    @Override
    public boolean shouldHandleStartSameAsEnd() {
        return true;
    }

    @Override
    public void handleCost(PathFindingLayerInterface ownerLayer) throws Exception {
    }

    @Override
    public WaypointBehaviorBase getWaypointBehavior() {
        throw new RuntimeException();
    }

    @Override
    public PathFindingLayerInterface getParentLayer() {
        throw new RuntimeException();
    }

    @Override
    public RTSLayer2LogHelper getRTSLayer2LogHelper() {
        return this.rtsLayer2LogHelper;
    }
    
    @Override
    public WaypointLogHelper getWaypointLogHelper() {
        return this.waypointLogHelperP;
    }

    @Override
    public Waypoint2LogHelper getWaypoint2LogHelper() {
        return this.waypoint2LogHelperP;
    }
    
    @Override
    public WaypointRunnableLogHelper getWaypointRunnableLogHelper() {
        return this.waypointRunnableLogHelperP;
    }

    @Override
    public boolean shouldAddWaypointFromBuilding() {
        return false;
    }

    @Override
    public CaptionAnimationHelperBase getCaptionAnimationHelper() {
        throw new RuntimeException();
    }

    @Override
    public boolean isShowMoreCaptionStates() {
        return this.showMoreCaptionStates;
    }
    
    @Override
    public void init(final GeographicMapCellHistory geographicMapCellHistory,
        final BasicArrayList geographicMapCellPositionBasicArrayList) throws Exception { 
        
    }
    
    @Override
    public GeographicMapCellPosition getCurrentGeographicMapCellPosition()
    throws Exception
    {
        throw new RuntimeException();
    }

    @Override
    public GeographicMapCellPosition getTopLeftGeographicMapCellPosition()
    throws Exception
    {
        throw new RuntimeException();
    }

    @Override
    public BasicArrayList getMoveOutOfBuildAreaPath(
        final GeographicMapCellPosition geographicMapCellPosition) {
        throw new RuntimeException();
    }   

    @Override
    public void setClosestGeographicMapCellHistory(final BasicArrayList pathsList)
        throws Exception
    {
    }
    
    @Override
    public void teleportTo(final GeographicMapCellPosition geographicMapCellPosition) {
        
    }
    
    @Override
    public void setLoad(short resource) throws Exception {
        
    }

    @Override
    public BasicArrayList getSurroundingGeographicMapCellPositionList() 
        throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public void trackTo(final String reason) 
        throws Exception {
        
    }

    @Override
    public void trackTo(final int dx, final int dy) 
        throws Exception {
        
    }
    
    @Override
    public boolean isWaypointListEmptyOrOnlyTargets() {
        return false;
    }

    @Override
    public TrackingEvent getTrackingEvent() {
        throw new RuntimeException();
    }

    @Override
    public boolean buildingChase(final AllBinaryLayer allbinaryLayer, final GeographicMapCellPosition cellPosition) throws Exception {
        return false;
    }
    
    @Override
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
    
    @Override
    public boolean implmentsTickableInterface()
    {
        return true;
    }
    
    @Override
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
