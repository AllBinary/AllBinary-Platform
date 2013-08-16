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
package abcs.logic.basic.string;

/**
 *
 * @author user
 */
public class ServletStrings {

    private static final ServletStrings instance = new ServletStrings();

    /**
     * @return the instance
     */
    public static ServletStrings getInstance()
    {
        return instance;
    }

    public final String DOPOST = "doPost";
    public final String DOGET = "doGet";

    public final String REQUEST_EXCEPTION = "Request Failed";
}
