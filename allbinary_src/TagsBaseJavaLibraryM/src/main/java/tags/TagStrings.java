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
package tags;

/**
 *
 * @author User
 */
public class TagStrings {
    
    private static final TagStrings instance = new TagStrings();

    /**
     * @return the instance
     */
    public static TagStrings getInstance() {
        return TagStrings.instance;
    }

    public final String DO_START_TAG = "doStartTag";
}
