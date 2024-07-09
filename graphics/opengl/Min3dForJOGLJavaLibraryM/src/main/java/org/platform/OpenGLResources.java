package org.platform;

import java.io.InputStream;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.logic.string.StringUtil;


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
    
    private final ResourceUtil resourceUtil = ResourceUtil.getInstance();
    
    private String packageName;
    
    public void init() {
    }
    
    public int getIdentifier(final String name, final String defType, final String defPackage) {
        return -1;
    }
    
    public InputStream openRawResource(final int id) throws Exception {
        throw new RuntimeException();
        //return this.resourceUtil.getResourceAsStream(resourceName);
    }
    
    public InputStream openRawResourceFromName(final String resourceName) throws Exception {
        final String resourceNameWithoutRaw = resourceName.replace("raw/", StringUtil.getInstance().EMPTY_STRING);
        return this.getResourceAsStream(resourceNameWithoutRaw);
    }
    
    public InputStream getResourceAsStream(final String resourceName) throws Exception {
        return this.resourceUtil.getResourceAsStream(resourceName);
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return this.packageName;
    }
}
