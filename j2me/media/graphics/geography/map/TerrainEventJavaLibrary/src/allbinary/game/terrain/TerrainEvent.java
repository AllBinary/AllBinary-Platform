/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/29/02
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.game.terrain;

import allbinary.logic.basic.util.event.AllBinaryEventObject;

public class TerrainEvent extends AllBinaryEventObject {
   
	private BasicTerrainInfo basicTerrainInfo;

	public TerrainEvent() {
		super((Object) null);
	}

	public TerrainEvent(BasicTerrainInfo basicTerrainInfo) {
		super(basicTerrainInfo);
            this.setBasicTerrainInfo(basicTerrainInfo);
	}
	
	public String toString() {
      
      StringBuilder stringBuffer = new StringBuilder();
      
      stringBuffer.append("TerrainEvent: \n");
      stringBuffer.append("LayerInterface: ");
      stringBuffer.append(this.basicTerrainInfo.toString());
      
		return stringBuffer.toString();
	}

   public BasicTerrainInfo getBasicTerrainInfo() {
      return basicTerrainInfo;
   }

   public void setBasicTerrainInfo(BasicTerrainInfo basicTerrainInfo) {
      this.basicTerrainInfo = basicTerrainInfo;
   }
   
   public void setBasicTerrainInfoForCircularStaticPool(BasicTerrainInfo basicTerrainInfo) {
      this.basicTerrainInfo = basicTerrainInfo;
   }
}
