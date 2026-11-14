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
package org.allbinary.game.ag.layer.hud.basic.notification;

import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.graphics.color.BasicColorFactory;

class NoGameGameNotificationHud extends GameNotificationHud {

    NoGameGameNotificationHud() {
        super(BasicHudFactory.getInstance().TOPCENTER, BasicHudFactory.getInstance().HORIZONTAL, 2, BasicColorFactory.getInstance().RED);

        this.updateMaxWidth = 40;
        this.updateMaxHeight = 14;
    }

//    public void updateMeasurement(final Graphics graphics) {
//        final Font font = graphics.getFont();
//        super.updateMeasurement(graphics);
//    }

}
