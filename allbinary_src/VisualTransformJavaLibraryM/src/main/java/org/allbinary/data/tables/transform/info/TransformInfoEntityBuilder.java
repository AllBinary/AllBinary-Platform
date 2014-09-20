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
package org.allbinary.data.tables.transform.info;

import org.allbinary.logic.visual.transform.info.TransformInfoHttpStoreFactory;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigAndManipulatorFactory;
import org.allbinary.logic.visual.transform.info.objectConfig.generator.TransformInfoObjectConfigGeneratorFactory;

public class TransformInfoEntityBuilder {

	public static TransformInfoEntity getInstance()
	{
		return TransformInfoEntityFactory.getInstance(
                TransformInfoObjectConfigGeneratorFactory.getInstance(),
                TransformInfoObjectConfigAndManipulatorFactory.getInstance(),
                TransformInfoHttpStoreFactory.getInstance()
                );
	}
}
