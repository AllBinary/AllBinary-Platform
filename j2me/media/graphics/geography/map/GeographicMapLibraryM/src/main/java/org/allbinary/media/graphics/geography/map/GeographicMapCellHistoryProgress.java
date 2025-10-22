/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package org.allbinary.media.graphics.geography.map;

import org.allbinary.logic.NullUtil;

/**
 *
 * @author user
 */
public class GeographicMapCellHistoryProgress extends GeographicMapCellHistoryProgressBase {

    private int[] totalVisitedArray = NullUtil.getInstance().NULL_INT_ARRAY;
    private GeographicMapCellHistory[] geographicMapCellHistoryArray = GeographicMapCellHistory.NULL_GEOGRPAHIC_MAP_HISTORY_ARRAY;

    public GeographicMapCellHistoryProgress(final GeographicMapCellHistory[] geographicMapCellHistoryArray) {
        this.geographicMapCellHistoryArray = geographicMapCellHistoryArray;
        this.init();
    }

    @Override
    public void init() {
        this.totalVisitedArray = new int[this.geographicMapCellHistoryArray.length];
    }

    @Override
    public boolean isAnyProgress() {
        boolean isProgressing = false;
        int size = geographicMapCellHistoryArray.length;
        int currentTotalVisited;
        int totalVisited;
        for (int index = 0; index < size; index++) {
            totalVisited = geographicMapCellHistoryArray[index].getTotalVisited();
            currentTotalVisited = this.totalVisitedArray[index];
            if (currentTotalVisited < totalVisited) {
                isProgressing = true;
                this.totalVisitedArray[index] = totalVisited;
            }
        }
        return isProgressing;
    }

}
