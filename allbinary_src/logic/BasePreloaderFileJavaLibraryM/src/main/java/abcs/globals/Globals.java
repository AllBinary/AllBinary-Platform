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
package abcs.globals;

import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringMaker;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.loader.WebappClassLoaderInfo;

public class Globals {

	private static final Globals instance = new Globals();
	
    private Globals() {
    }

    public static void init(ClassLoader classLoader, String pathString) 
    //throws Exception 
    {
        AppUrlGlobals appUrlGlobals = new AppUrlGlobals();
        appUrlGlobals.setWebappPath(pathString);
        abcs.globals.URLGLOBALS.init(appUrlGlobals);

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
        	stringBuffer.append(abcs.globals.URLGLOBALS.getWebappPath());

            //pathString = WebappPathCmdLineFile.getPath("./");
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, CommonStrings.getInstance().INIT));
        }

    	stringBuffer.delete(0, stringBuffer.length());
    	
    	stringBuffer.append("Webapp Path Set To: ");
    	stringBuffer.append(abcs.globals.URLGLOBALS.getWebappPath());

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, CommonStrings.getInstance().INIT));
    }
}