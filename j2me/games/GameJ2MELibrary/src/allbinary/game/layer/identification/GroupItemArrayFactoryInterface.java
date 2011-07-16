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
package allbinary.game.layer.identification;

import allbinary.game.identification.Group;
import allbinary.graphics.form.item.CustomItem;

/**
 *
 * @author user
 */
public interface GroupItemArrayFactoryInterface {
    CustomItem[] getInstance(Group groupInterface)
        throws Exception;
}
