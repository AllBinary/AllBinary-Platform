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
package org.allbinary.game.behavior.topview.placement;

import org.mapeditor.core.TiledMap;

/**
 *
 * @author User
 */
public class AllAnimationsEverywhereTileMapPlacementVisitor extends TileMapPlacementVisitor {

    public void visit(final TiledMap lastMap, final int[][] mapData) {

        final int size = mapData.length;
        final int size2 = mapData[0].length - 7;
        for (int index = 0; index < size; index++) {
            for (int index2 = 0; index2 < size2; index2 += 7) {
                mapData[index][index2] = 1;
                mapData[index][index2 + 1] = 17;
                mapData[index][index2 + 2] = 33;
                mapData[index][index2 + 3] = 49;
                mapData[index][index2 + 4] = 65;
                mapData[index][index2 + 5] = 81;
                mapData[index][index2 + 6] = 97;
            }
        }
    }

}
