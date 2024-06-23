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
package org.allbinary.graphics.displayable;

import javax.microedition.lcdui.Canvas;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.thread.SoundThreadPool;

/**
 *
 * @author User
 */
public class RepaintBehavior {

    private static final RepaintBehavior instance = new RepaintBehavior();

    /**
     * @return the instance
     */
    public static RepaintBehavior getInstance() {
        return instance;
    }

    public void repaint(final Canvas canvas) {

    }

    public void onChangeRepaint(final Canvas canvas) {

        SoundThreadPool.getInstance().runTask(new Runnable() {
            public void run() {
                try {
                    canvas.repaint();
                    DisplayInfoSingleton.getInstance().process();
                } catch (Exception e) {
                    LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
                }
            }
        });
    }

}
