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
package org.allbinary.logic.communication.http.file.upload;

public class FileUploadData
{
	private static final FileUploadData instance = new FileUploadData();

	public static FileUploadData getInstance() {
		return instance;
	}

        public final String FILE = "download_file_permissions.xml";

	public final String UPLOAD_TO_FILE_PATH = "FILE_UPLOAD_UPLOAD_TO_FILE_PATH";
	public final String FILE_DATA = "FILE_UPLOAD_FILE_DATA";
}
