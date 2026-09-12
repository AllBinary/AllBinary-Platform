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
package org.allbinary.logic;

import javax.microedition.lcdui.Item;

import org.allbinary.graphics.displayable.screen.CommandForm;

/**
 *
 * @author User
 */
//Handle the differences in the public platform vs the raw JS build
public class MEUtil {

    /**
     * @return the instance
     */
    public static MEUtil getInstance() {
        return instance;
    }
    
    private static final MEUtil instance = new MEUtil();
    
    public void appendItem(final CommandForm form, final Item item) {
        form.append(item);
    }
    
}
