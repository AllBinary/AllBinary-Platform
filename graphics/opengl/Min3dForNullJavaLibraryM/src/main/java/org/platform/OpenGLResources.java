package org.platform;

import java.io.InputStream;


/**
 *
 * @author User
 */
public class OpenGLResources {
    
    private static final OpenGLResources instance = new OpenGLResources();

    /**
     * @return the instance
     */
    public static OpenGLResources getInstance() {
        return instance;
    }
    
    public void init() {
    }
    
    public boolean isExcluded(final String name) {
        throw new RuntimeException();
    }
    
    public int getIdentifier(final String name, final String defType, final String defPackage) {
        throw new RuntimeException();
        //return -1;
    }
    
    public InputStream openRawResource(final int id) throws Exception {
        throw new RuntimeException();
        //return null;
    }
    
    public InputStream openRawResourceFromName(final String resourceName) throws Exception {
        throw new RuntimeException();
    }
    
    public InputStream getResourceAsStream(final String resourceName) throws Exception {
        throw new RuntimeException();
        //return null;
    }
    
    public String getPackageName() {
        throw new RuntimeException();
        //return null;
    }
}
