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
package abcs.logic.basic.io;

import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.AbFileNativeUtil;
import java.io.FileNotFoundException;

public class AbFileLocalInputStream
    extends AbFileInputStream
{
    public AbFileLocalInputStream(AbFile file) throws FileNotFoundException {
        super(AbFileNativeUtil.get(file));
    }
}
