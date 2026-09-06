/*
 * AllBinary Open License Version 1
 * Copyright (c) 2026 AllBinary
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
package org.allbinary.animation;

/**
 *
 * @author User
 */
public class ShapeTypeFactory {
 
    private static final ShapeTypeFactory instance = new ShapeTypeFactory();

    /**
     * @return the instance
     */
    public static ShapeTypeFactory getInstance() {
        return instance;
    }
    
    public final String KEY_LINE = "line";
    public final String KEY_CIRCLE = "circle";
    public final String KEY_RECTANGLE = "rectangle";
    public final String KEY_RECT = "rect";
    
    public final ShapeType LINE = new ShapeType(KEY_LINE);
    public final ShapeType CIRCLE = new ShapeType(KEY_CIRCLE);
    public final ShapeType RECTANGLE = new ShapeType(KEY_RECTANGLE);
    
}
