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
package org.allbinary.logic.visual.transform.info;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

/**
 *
 * @author user
 */
public interface TransformInfoFactoryInterface {

    TransformInfoInterface getInstance(
        HashMap databaseHashMap, HashMap propertiesHashMap, PageContext pageContext)
        throws Exception;
    
}
