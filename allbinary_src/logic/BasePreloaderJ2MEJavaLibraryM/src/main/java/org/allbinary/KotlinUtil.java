package org.allbinary;

import org.allbinary.logic.string.StringUtil;

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
/**
 *
 * @author User
 */
public class KotlinUtil {
    
    //private static final String KT = "kt";
    
    public static boolean isKotlin() {
        return false;
    }

    public static String getShortName() {
        //return (KotlinUtil.isKotlin() ? KT : StringUtil.getInstance().EMPTY_STRING);
        return StringUtil.getInstance().EMPTY_STRING;
        //return KT;
    }
    
}
