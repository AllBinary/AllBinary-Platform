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
package org.allbinary.logic.visual.transform.info.objectConfig;

import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;

/**
 *
 * @author User
 */
public class TransformInfoObjectConfigAndManipulatorFactoryBase implements TransformInfoObjectConfigAndManipulatorFactoryInterface {
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public TransformInfoObjectConfigInterface getInstance(final AbeClientInformationInterface abeClientInformation, TransformInfoInterface transformInfoInterface, AbPath objectConfigFileAbPath) throws Exception {
        throw new RuntimeException();
    }

    public TransformInfoObjectConfigInterface getInstance(final AbeClientInformationInterface abeClientInformation, TransformInfoInterface transformInfoInterface) throws Exception {
        throw new RuntimeException();
    }

    public TransformInfoObjectConfigInterface getInstance(final AbeClientInformationInterface abeClientInformation, TransformInfoInterface transformInfoInterface, Document document) throws Exception {
        throw new RuntimeException();
    }
    
}
