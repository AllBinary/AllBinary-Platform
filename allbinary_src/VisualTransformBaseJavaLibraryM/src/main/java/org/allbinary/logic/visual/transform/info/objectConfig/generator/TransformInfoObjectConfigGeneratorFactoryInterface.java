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
package org.allbinary.logic.visual.transform.info.objectConfig.generator;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

/**
 *
 * @author user
 */
public interface TransformInfoObjectConfigGeneratorFactoryInterface {

    TransformInfoObjectConfigGenerator getInstance(
        TransformInfoInterface transformInfoInterface)
        throws Exception;
}
