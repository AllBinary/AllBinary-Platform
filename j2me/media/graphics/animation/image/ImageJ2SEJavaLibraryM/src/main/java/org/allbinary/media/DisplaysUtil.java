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
package org.allbinary.media;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;

/**
 *
 * @author User
 */
public class DisplaysUtil {

    /**
     * @return the instance
     */
    public static DisplaysUtil getInstance() {
        return instance;
    }
    
    private static final DisplaysUtil instance = new DisplaysUtil();
    
    public Rectangle getPrimaryScreenSize() {
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        return new Rectangle(PointFactory.getInstance().ZERO_ZERO, dimension.width, dimension.height);
    }
    
    public Rectangle[] getScreenSizesAsRectangleArray() {
        final GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice[] graphicsDeviceArray = graphicsEnvironment.getScreenDevices();
        GraphicsDevice graphicsDevice;
        DisplayMode displayMode;
        Rectangle rectangle;
        final int size = graphicsDeviceArray.length;
        final Rectangle[] rectangleArray = new Rectangle[size];
        for(int index = 0; index < size; index++)
        {
            graphicsDevice = graphicsDeviceArray[index];
            displayMode = graphicsDevice.getDisplayMode();
            rectangle = new Rectangle(PointFactory.getInstance().ZERO_ZERO, displayMode.getWidth(), displayMode.getHeight());
            rectangleArray[index] = rectangle;
            
            //System.out.println(displayMode.getWidth() + " x " + displayMode.getHeight());
        }
        
        return rectangleArray;
    }

}
