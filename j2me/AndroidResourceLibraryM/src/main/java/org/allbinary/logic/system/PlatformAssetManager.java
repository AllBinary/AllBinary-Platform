/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.logic.system;

import java.io.InputStream;

import org.allbinary.data.resource.ResourceUtil;

/**
 *
 * @author User
 */
public class PlatformAssetManager {
    
    private static final PlatformAssetManager instance = new PlatformAssetManager();

    /**
     * @return the instance
     */
    public static PlatformAssetManager getInstance() {
        return instance;
    }
    
    public InputStream getResourceAsStream(final String resource) throws Exception {
        final ResourceUtil resourceUtil = ResourceUtil.getInstance();
        final InputStream inputStream = resourceUtil.getResourceAsStream(resource);
        return inputStream;
    }
    
}
