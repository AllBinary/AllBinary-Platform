/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.logic.io.file;

import org.allbinary.string.CommonSeps;

/**
 *
 * @author User
 */
public class FileUnamedUtil {

    private static final FileUnamedUtil instance = new FileUnamedUtil();

    /**
     * @return the instance
     */
    public static FileUnamedUtil getInstance() {
        return FileUnamedUtil.instance;
    }

    private final CommonSeps commonSeps = CommonSeps.getInstance();

    public String process(String string) {
        String simplifiedString = string.toLowerCase();
        simplifiedString = simplifiedString.replace(this.commonSeps.SPACE, this.commonSeps.UNDERSCORE);
        simplifiedString = simplifiedString.replace(this.commonSeps.DASH, this.commonSeps.UNDERSCORE);
        return simplifiedString;
    }

}
