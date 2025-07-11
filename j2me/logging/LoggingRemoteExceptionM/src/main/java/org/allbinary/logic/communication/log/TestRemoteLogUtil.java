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
package org.allbinary.logic.communication.log;

import org.allbinary.globals.Globals;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author user
 */
public class TestRemoteLogUtil {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    
    public static final String PATH = "G:\\mnt\\bc\\mydev\\work\\allbinary_src\\licensing\\data\\";
    
    public TestRemoteLogUtil()
    {
        Globals.getInstance().init(this.getClass().getClassLoader(), PATH);
        
        final LogUtil logUtil = LogUtil.getInstance();
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put(commonStrings.EXCEPTION, "Test", "TestClass", new Exception("Pretend Exception"));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        new TestRemoteLogUtil();
    }

}
