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
package org.allbinary.globals;


import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.system.loader.WebappClassLoaderInfo;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;

public class Globals {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final Globals instance = new Globals();
    
    public static Globals getInstance() {
        return instance;
    }
    
    private Globals() {
    }

    public void init(ClassLoader classLoader, String pathString) 
    //throws Exception 
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();

        final AppUrlGlobals appUrlGlobals = new AppUrlGlobals();
        appUrlGlobals.setWebappPath(pathString);
        URLGLOBALS.init(appUrlGlobals);

        WebappClassLoaderInfo.setLoader(classLoader);

        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Setting Up AllBinary System Configuration Args: ");
        stringBuffer.append(pathString);
        stringBuffer.append(" ClassLoader: ");
        stringBuffer.append(classLoader.getClass().getName());
        
        logUtil.put(stringBuffer.toString(), this, commonStrings.INIT);

        final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if (stringValidationUtil.isEmpty(pathString)) {
        	
        	stringBuffer.delete(0, stringBuffer.length());
        	
        	stringBuffer.append("No Path Provided. Using Process Path: ");
        	stringBuffer.append(pathString);
        	stringBuffer.append(CommonSeps.getInstance().SPACE);
        	stringBuffer.append(CommonSeps.getInstance().EQUALS);
        	stringBuffer.append(CommonSeps.getInstance().SPACE);
        	stringBuffer.append(URLGLOBALS.getWebappPath());

            //pathString = WebappPathCmdLineFile.getPath("./");
            logUtil.put(stringBuffer.toString(), this, commonStrings.INIT);
        }

    	stringBuffer.delete(0, stringBuffer.length());
    	
    	stringBuffer.append("Webapp Path Set To: ");
    	stringBuffer.append(URLGLOBALS.getWebappPath());

        logUtil.put(stringBuffer.toString(), this, commonStrings.INIT);
    }
}