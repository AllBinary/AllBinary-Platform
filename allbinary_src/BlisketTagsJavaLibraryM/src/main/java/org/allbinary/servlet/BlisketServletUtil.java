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
package org.allbinary.servlet;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.globals.AppUrlGlobals;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.system.loader.WebappClassLoaderInfo;

public class BlisketServletUtil {

	private static final BlisketServletUtil instance = new BlisketServletUtil();

	public static BlisketServletUtil getInstance() {
		return instance;
	}
	
	public void init(HttpServletRequest request)
	{
	    AppUrlGlobals urlGlobals = new AppUrlGlobals();
	    //urlGlobals.setWebappPath(request.getRealPath(AbPathData.getInstance().SEPARATOR));
            urlGlobals.setWebappPath(request.getServletContext().getRealPath(AbPathData.getInstance().SEPARATOR));
	    URLGLOBALS.init(urlGlobals);
	    
	    WebappClassLoaderInfo.setLoader(getClass().getClassLoader());
	}
}
