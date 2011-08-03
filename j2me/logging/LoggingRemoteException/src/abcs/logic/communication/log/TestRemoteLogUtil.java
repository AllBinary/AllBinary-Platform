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
package abcs.logic.communication.log;

import abcs.globals.Globals;
import abcs.logic.basic.string.CommonStrings;

/**
 *
 * @author user
 */
public class TestRemoteLogUtil {

    public TestRemoteLogUtil()
        throws Exception
    {
        Globals.init(this.getClass().getClassLoader(), "G:\\mnt\\bc\\mydev\\licenseserver\\testing\\");
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, "Test", "TestClass", new Exception("Pretend Exception")));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        new TestRemoteLogUtil();
    }

}
