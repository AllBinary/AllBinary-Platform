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
package org.allbinary.game.identification;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

/**
 *
 * @author User
 */
public class GroupCommonFactory {

    private static Object instance = NullUtil.getInstance().NULL_OBJECT;
    
    public static GroupCommonFactory getInstance() {
        return (GroupCommonFactory) GroupCommonFactory.instance;
    }

    public final Integer ID = SmallIntegerSingletonFactory.getInstance().getAt(10);
    public final String GROUP_NAME_LABEL = "Group Name: ";
    public final String ID_LABEL = " Id: ";
    
    public final String ADDING_LABEL = "Adding: ";
    public final String TO_LABEL = " to: ";
    
}
