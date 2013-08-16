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
package allbinary.game.part.weapon;

import allbinary.game.combat.weapon.WeaponProperties;
import allbinary.game.score.ScoreableInterface;
import allbinary.layer.AllBinaryLayerManager;

public interface SalvoInterface {

	void process(AllBinaryLayerManager allbinaryLayerManager, short angle, short otherAngle) 
	throws Exception;
	
	void process(AllBinaryLayerManager allbinaryLayerManager,
			short angle, short otherAngle, WeaponProperties weaponProperties, 
			ScoreableInterface scoreableInterface)
	throws Exception;
}
