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
package admin.taghelpers;

import java.util.Vector;

import org.allbinary.business.user.role.BasicUserRole;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.role.UserRole;
import org.allbinary.logic.communication.http.file.upload.HttpFilePermissions;
import org.allbinary.logic.communication.http.request.HttpRequestUtil;

public class AuthenticationHelperUtil
{
    private static final AuthenticationHelperUtil instance = new AuthenticationHelperUtil();

    public static AuthenticationHelperUtil getInstance()
    {
        return instance;
    }

    public final boolean isAuthorized(
        AuthenticationHelper authenticationHelper, String filePath)
        throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
        {
            LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START + filePath, this, "isAuthorized()"));
        }

        int endIndex = HttpRequestUtil.getInstance().getLastSeparatorIndex(filePath);

        AbPath fullPath = new AbPath(URLGLOBALS.getWebappPath() + filePath.substring(0, endIndex));

        HttpFilePermissions downloadFilePermissions = new HttpFilePermissions(fullPath);

        Vector userRoles = downloadFilePermissions.getUserRoles();

        UserRole userRole = authenticationHelper.getRole();

        BasicUserRole basicUserRole = userRole.getBasicUserRole();

        if (userRoles.contains(basicUserRole))
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Authorized", this, "isAuthorized()"));
            }
        	
            return true;
        } else
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Not Authorized: " + basicUserRole.toString(), this, "isAuthorized()"));
            }

            return false;
        }
    }
}
