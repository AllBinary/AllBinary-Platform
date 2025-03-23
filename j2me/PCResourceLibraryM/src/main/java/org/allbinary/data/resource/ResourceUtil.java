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
package org.allbinary.data.resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;

public class ResourceUtil {

    private static final ResourceUtil instance = new ResourceUtil();

    public static ResourceUtil getInstance() {
        return instance;
    }

    private String path;
    private String ext;
    
    private ResourceUtil() {
    }

    public void setLoadingPaths(String path, String ext) {
        this.path = path;
        this.ext = ext;
    }
    
//    public ClassLoader getClassLoader() {
//        return ResourceUtil.classLoader;
//    }
    public void setClassLoader(final ClassLoader classLoader) {
    }

    public InputStream getResourceAsStream(final String resource)
            //, Object emulatorObject)
            throws Exception {
        InputStream inputStream = this.getResourceAsStream(resource, 2);

        if (inputStream == null) {
            inputStream = this.getResourceAsStream(resource, 1);
            if (inputStream == null) {
                throw new Exception(new StringMaker().append("Unable to obtain: ").append(resource).toString());
            }
        }
        return inputStream;
    }

    //private final String RESOURCE_FOUND = "Resource Found: ";
    //private final String RESOURCE_FOUND_WITH = "Resource Found with: ";
    //private final String RESOURCE_FOUND_WITH_CONTEXT_CLASS_LOADER = "Resource Found with ContextClassLoader: ";
    //private final String METHOD_NAME = "getResourceAsStream";

    private InputStream getResourceAsStream(final String resource, final int startIndex)
            //, Object emulatorObject)
            throws Exception {
        final StringMaker stringMaker = new StringMaker();

        final CommonSeps commonSeps = CommonSeps.getInstance();

        //Try getting resource with normal resource access
        InputStream inputStream = new FileInputStream(new StringMaker().append(path).append(resource).append(ext).toString());

        if (inputStream != null) {
//            stringMaker.delete(0, stringMaker.length());
//            LogUtil.put(LogFactory.getInstance(stringMaker.append(RESOURCE_FOUND).append(resource).toString(), this, METHOD_NAME));
            final byte[] byteArray = new byte[inputStream.available()];
            StreamUtil.getInstance().getByteArray(inputStream, new ByteArrayOutputStream(), byteArray);
            return new ByteArrayInputStream(byteArray);
        }

        return inputStream;
    }
    
    public void addResource(final String resource, final Integer value)
    {
    }
    
}
