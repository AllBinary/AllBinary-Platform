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
package org.osgi.framework;

/**
 *
 * @author User
 */
public class NullServiceReferenceFactory {
    
    private static final NullServiceReferenceFactory instance = new NullServiceReferenceFactory();

    /**
     * @return the instance
     */
    public static NullServiceReferenceFactory getInstance() {
        return instance;
    }

    public NullServiceReference NULL_SERVICE_REFERENCE = new NullServiceReference();

}
