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

import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.loader.WebappClassLoaderInfo;

public class Globals {

    private static final Globals instance = new Globals();
	
    private Globals() {
    }

    public static void init(ClassLoader classLoader, String pathString) 
    //throws Exception 
    {
        AppUrlGlobals appUrlGlobals = new AppUrlGlobals();
        appUrlGlobals.setWebappPath(pathString);
        org.allbinary.globals.URLGLOBALS.init(appUrlGlobals);

        WebappClassLoaderInfo.setLoader(classLoader);

        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Setting Up AllBinary System Configuration Args: ");
        stringBuffer.append(pathString);
        stringBuffer.append(" ClassLoader: ");
        stringBuffer.append(classLoader.getClass().getName());
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, CommonStrings.getInstance().INIT));

        final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if (stringValidationUtil.isEmpty(pathString)) {
        	
        	stringBuffer.delete(0, stringBuffer.length());
        	
        	stringBuffer.append("No Path Provided. Using Process Path: ");
        	stringBuffer.append(pathString);
        	stringBuffer.append(CommonSeps.getInstance().SPACE);
        	stringBuffer.append(CommonSeps.getInstance().EQUALS);
        	stringBuffer.append(CommonSeps.getInstance().SPACE);
        	stringBuffer.append(org.allbinary.globals.URLGLOBALS.getWebappPath());

            //pathString = WebappPathCmdLineFile.getPath("./");
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, CommonStrings.getInstance().INIT));
        }

    	stringBuffer.delete(0, stringBuffer.length());
    	
    	stringBuffer.append("Webapp Path Set To: ");
    	stringBuffer.append(org.allbinary.globals.URLGLOBALS.getWebappPath());

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, CommonStrings.getInstance().INIT));
    }
}