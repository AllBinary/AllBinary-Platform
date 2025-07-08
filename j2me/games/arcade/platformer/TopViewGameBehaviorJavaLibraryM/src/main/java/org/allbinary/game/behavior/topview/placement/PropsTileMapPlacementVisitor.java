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

import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.mapeditor.core.TileLayer;
import org.mapeditor.core.TiledMap;

/**
 *
 * @author User
 */
public class PropsTileMapPlacementVisitor extends TileMapPlacementVisitor {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final MyRandomFactory randomFactory = MyRandomFactory.getInstance();
    
    public void visit(final TiledMap lastMap, final int[][] mapData) {

        final int layerIndex = 0;
        final TileLayer tileLayer = ((TileLayer) lastMap.getLayer(layerIndex));
        final int[][] mapArray = tileLayer.getMapArray();
        final int size = mapData.length;
        final int size2 = mapData[0].length;
                
        for (int index = 0; index < size; index++) {
            for (int index2 = 0; index2 < size2; index2++) {
                
                //corner
                //top 
                //left 0
                //right 2
                //inner
                //left 3
                //right 4
                //bottom
                //left 32
                //right 34
                
                //wall
                //left 18
                //right 16
                //top 33

                if(mapArray[index][index2] == 14) {
                } else {
                    this.visit2(mapArray, mapData, index, index2);
                }
                
                if(index % 7 == 0) {
                    if (mapArray[index][index2] == 19) {

                        final int randomInt = randomFactory.getAbsoluteNextIntAllowZero(3);
                        if (randomInt == 0) {
                            mapData[index][index2] = 1;
                        } else if (randomInt == 1) {
                            mapData[index][index2] = 81;
                        } else if (randomInt == 2) {
                            mapData[index][index2] = 97;
                        }

                    } else if (mapArray[index][index2] == 17) {

                        final int randomInt = randomFactory.getAbsoluteNextIntAllowZero(3);
                        if (randomInt == 0) {
                            mapData[index][index2] = 17;
                        } else if (randomInt == 1) {
                            mapData[index][index2] = 81;
                        } else if (randomInt == 2) {
                            mapData[index][index2] = 97;
                        }

                    }
                }
   
                if(index2 % 7 == 0) {
                    if (mapArray[index][index2] == 34) {

                        final int randomInt = randomFactory.getAbsoluteNextIntAllowZero(4);
                        if (randomInt == 0) {
                            mapData[index][index2] = 33;
                        } else if (randomInt == 1) {
                            mapData[index][index2] = 65;
                        } else if (randomInt == 2) {
                            mapData[index][index2] = 81;
                        } else if (randomInt == 3) {
                            mapData[index][index2] = 97;
                        }

                    }
                }

            }
        }
    }

    public void visit2(final int[][] mapArray, final int[][] mapData, final int index, final int index2) {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        int countX = 0;
        int countY = 0;
        int index3 = index - 1;
        for (int index4 = index2 - 1; index4 > 0; index4--) {
            index3--;

            if (index3 < 0 || index4 < 0) {
                return;
            }

            if (mapArray[index3][index4] != 14 || mapData[index3][index4] != 0) {

                if (countX > 10 && countY > 10) {
                    
                    for (int index5 = index3 + 1; index5 < index - 1; index5++) {
                        for (int index6 = index4 + 1; index6 < index2 - 1; index6++) {
                            if (mapArray[index5][index6] != 14 || mapData[index5][index6] != 0) {
                                //logUtil.put("Room is already cluttered", this, commonStrings.PROCESS);
                                return;
                            }
                        }
                    }
                    
                    //logUtil.put("Found Clearing", this, commonStrings.PROCESS);
                    final int x = index - (countX / 2);
                    final int y = index2 - (countY / 2);
                    //logUtil.put("x: " + x + " y: " + y, this, commonStrings.PROCESS);
                    if (x >= 0 && y >= 0 && x < mapData.length && y < mapData[0].length) {
                        //logUtil.put("Placing Middle of Room Prop", this, commonStrings.PROCESS);
                        //mapData[x][y] = 49;
                    }
                }
                return;
            }
            
            countY++;
            countX++;
        }
    }

}
