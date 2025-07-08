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
package org.allbinary.game.layer.waypoint;

import org.allbinary.game.layer.PathFindingLayerInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

/**
 *
 * @author User
 */
public class WaypointRunnableSelectedLogHelper extends WaypointRunnableLogHelper {
    protected final LogUtil logUtil = LogUtil.getInstance();


    protected static final WaypointRunnableSelectedLogHelper instance = new WaypointRunnableSelectedLogHelper();

    /**
     * @return the instance
     */
    public static WaypointRunnableSelectedLogHelper getInstance() {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    //@Override
    public void start(final PathFindingLayerInterface pathFindingLayerInterface) {
        logUtil.put(new StringMaker().append(pathFindingLayerInterface.getName()).append(commonStrings.START_RUNNABLE).toString(), this, commonStrings.RUN);
    }

    //@Override
    public void end(final PathFindingLayerInterface pathFindingLayerInterface) {
        logUtil.put(new StringMaker().append(pathFindingLayerInterface.getName()).append(commonStrings.END_RUNNABLE).toString(), this, commonStrings.RUN);
    }
    
}
