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
package org.allbinary.logic.visual.transform.template.customizer.widgets.logo;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileUtil;
import org.allbinary.logic.io.file.directory.Directory;
import org.allbinary.logic.io.path.AbPath;
import java.io.ByteArrayInputStream;
import org.apache.commons.fileupload.FileItem;

public class LogoImageFileUtil
{
    private final Directory directory = Directory.getInstance();
    
    private final String fileName;
    private final AbPath imageFileAbPath;
    
    //Save to default file type
    public LogoImageFileUtil(final AbPath imageAbPath, final String imageFile)
    {
        this.fileName = imageFile;
        this.imageFileAbPath = imageAbPath;
    }
        
    public void saveFiles(final FileItem fileItem) throws Exception
    {
        if(fileName == null)
        {
           throw new Exception("Image File Name Was Null");
        }
        
        if(!this.directory.create(this.imageFileAbPath))
        {
           throw new Exception("Unable to save file to non creatable directory");
        }

        final AbFile originalImageFile = new AbFile(
            this.imageFileAbPath.toString() + fileName);

        originalImageFile.createNewFile();

        FileUtil.getInstance().write(new ByteArrayInputStream(fileItem.get()), originalImageFile);
        //FileItemUtil.write(fileItem, originalImageFile);
        
    }
}
