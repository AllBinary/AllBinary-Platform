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

import org.allbinary.logic.communication.http.file.upload.FileUploadData;
import org.allbinary.business.user.role.BasicUserRoleFactory;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import java.util.Vector;
import org.allbinary.string.CommonStrings;

public class HttpFilePermissions
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final AbFile file;
    private final Vector userRoles = new Vector();

    public HttpFilePermissions(AbPath fullPath)
        throws Exception
    {
        file = new AbFile(new AbPath(fullPath.toFileSystemString(),
            FileUploadData.getInstance().FILE));

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
        {
            logUtil.put("Loading Privileges: " + fullPath.toFileSystemString(), this, this.commonStrings.CONSTRUCTOR);
        }

        if (this.isFile())
        {
            //load file and add user roles
            userRoles.add(BasicUserRoleFactory.getInstance().ADMINISTRATOR);
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
        {
            logUtil.put("User Roles: " + userRoles, this, this.commonStrings.CONSTRUCTOR);
        }

    }

    public boolean isFile()
    {
        return this.file.isFile();
    }

    /**
     * @return the userRoles
     */
    public Vector getUserRoles()
    {
        return userRoles;
    }
}
