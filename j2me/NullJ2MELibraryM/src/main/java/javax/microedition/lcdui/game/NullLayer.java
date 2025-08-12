/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author User
 */
public class NullLayer extends Layer {
    
    public static final Layer NULL_LAYER = new NullLayer(0, 0, 0 ,0, false);
    
    NullLayer(int x, int y, int width, int height, boolean visible) {
        super(x, y, width, height, visible);
    }
    
    @Override
    public void paint(Graphics g) {
        
    }

}
