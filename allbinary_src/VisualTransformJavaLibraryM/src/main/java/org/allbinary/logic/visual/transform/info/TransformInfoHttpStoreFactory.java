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
public class TransformInfoHttpStoreFactory implements TransformInfoFactoryInterface
{
    private static final TransformInfoHttpStoreFactory instance =
        new TransformInfoHttpStoreFactory();

    /**
     * @return the instance
     */
    public static TransformInfoHttpStoreFactory getInstance()
    {
        return instance;
    }

    private TransformInfoHttpStoreFactory()
    {

    }

    public TransformInfoInterface getInstance(
        HashMap databaseHashMap, HashMap propertiesHashMap, PageContext pageContext)
        throws Exception
    {
        return new TransformInfoHttpStore(databaseHashMap, propertiesHashMap, pageContext);
    }
}
