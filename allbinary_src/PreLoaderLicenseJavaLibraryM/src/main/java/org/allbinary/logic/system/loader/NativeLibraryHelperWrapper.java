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
package org.allbinary.logic.system.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.allbinary.string.CommonStrings;

public class NativeLibraryHelperWrapper
{
    private String libraryName;

    private ClassLoader loader;
    private Object object;

    public NativeLibraryHelperWrapper(String libraryName)
    {
        this.setLibraryName(libraryName);
    }

    public void load() throws Exception
    {
        Object[] params = new Object[1];
        params[0] = getLibraryName();

        Class[] classes = new Class[1];
        classes[0] = getLibraryName().getClass();
        
        this.loader = new 
            NativeLibraryClassLoader(this.getClass().getClassLoader());
        Class myClass = loader.loadClass("dynamic.NativeLibraryHelper");
        Constructor constructor = myClass.getConstructor(classes);
        this.object = constructor.newInstance(params);
        
        final CommonStrings commonStrings = CommonStrings.getInstance();
        Method method = object.getClass().getMethod(commonStrings.LOAD, null);
        method.invoke(object, null);
    }
    
    public void unload()
    {
        this.object = null;
        this.loader = null;
        System.gc();
        System.gc();
    }
    
    public String toString()
    {
        return "Library: " + this.getLibraryName();
    }

    public String getLibraryName()
    {
        return libraryName;
    }

    public void setLibraryName(String libraryName)
    {
        this.libraryName = libraryName;
    }
}
