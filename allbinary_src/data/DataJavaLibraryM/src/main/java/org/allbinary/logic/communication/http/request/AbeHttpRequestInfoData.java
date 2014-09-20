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

public class AbeHttpRequestInfoData
{
	private static final AbeHttpRequestInfoData instance = new AbeHttpRequestInfoData();

	   public static AbeHttpRequestInfoData getInstance() {
			return instance;
		}
	
   private AbeHttpRequestInfoData()
   {
   }

public final String HTTP_USER_AGENT = "HTTP_USER_AGENT";
   public final String REMOTE_ADDRESS = "REMOTE_ADDRESS";
   public final String REMOTE_HOST = "REMOTE_HOST";
   public final String REMOTE_HOST_BY_ADDRESS = "REMOTE_HOST_BY_ADDRESS";
   public final String REMOTE_PORT = "REMOTE_PORT";
   public final String REQUEST_FILE_PATH = "REQUEST_FILE_PATH";
}
