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

package org.allbinary.logic.basic.string;

/**
 *
 * @author User
 */
public class CommonLabels {

    private static final CommonLabels instance = new CommonLabels();

    /**
     * @return the instance
     */
    public static CommonLabels getInstance() {
        return instance;
    }
    
    public final String END_LABEL = "End: ";
    public final String TOTAL_LABEL = "Total: ";
    public final String INDEX_LABEL = "index: ";
    public final String START_LABEL = "Start: ";
    public final String COMMAND_LABEL = "Command: ";
    public final String NAME_LABEL = "Name: ";
    
}