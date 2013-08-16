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
package abcs.logic.system.security.licensing.server;

public class AbeServerLicenseData {

    private static final AbeServerLicenseData instance =
            new AbeServerLicenseData();

    public static AbeServerLicenseData getInstance() {
        return instance;
    }

    private AbeServerLicenseData() {
    }
    public final String AVAILABLE = "AVAILABLE";
    public final String CREATEDBY = "CREATEDBY";
    public final String CURRENTKEY = "CURRENTKEY";
    public final String DESCRIPTION = "DESCRIPTION";
    public final String ENABLE = "ENABLE";
    public final String ENDDATE = "ENDDATE";
    public final String FILE = "FILE";
    public final String FILENAME = "FILENAME";
    public final String INUSE = "INUSE";
    public final String KEYINUSE = "KEYINUSE";
    public final String LICENSETYPE = "LICENSETYPE";
    public final String NEXTKEY = "NEXTKEY";
    public final String NUMBER = "NUMBER";
    public final String RESETCOND = "RESETCOND";
    public final String REUSABLE = "REUSABLE";
    public final String ROTATE = "ROTATE";
    public final String STARTDATE = "STARTDATE";
    public final String TIMEINTERVAL = "TIMEINTERVAL";
}
