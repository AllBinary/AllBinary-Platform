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
package org.allbinary.math;

/**
 *
 * @author User
 */
public class NamedAngle extends Angle {
    
    public final String name;
   
    //protected
    public NamedAngle(final short angle, final String name)
    {
        super(angle);
        
        this.name = name;

    }
    
}
