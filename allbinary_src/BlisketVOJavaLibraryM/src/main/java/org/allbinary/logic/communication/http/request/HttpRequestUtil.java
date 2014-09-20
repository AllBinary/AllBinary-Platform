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
package org.allbinary.logic.communication.http.request;

import org.allbinary.logic.basic.path.AbPathData;

public class HttpRequestUtil {
	
	private static final HttpRequestUtil instance = new HttpRequestUtil();
	
	public static HttpRequestUtil getInstance() {
		return instance;
	}
	
	private HttpRequestUtil() {
	}

	public int getLastSeparatorIndex(String requestPath) {
		int beginIndex = requestPath
				.lastIndexOf(AbPathData.getInstance().SEPARATOR);
		if (beginIndex < 0) {
			beginIndex = requestPath.lastIndexOf("\\");
		}
		return beginIndex;
	}

	public String generateFileName(String requestPath) throws Exception {
		// Since I don't know what machine it is coming from I will check for
		// both

		int beginIndex = this.getLastSeparatorIndex(requestPath);

		if (beginIndex < 0) {
			return requestPath;
		} else {
			return requestPath.substring(beginIndex + 1);
		}
	}
}
