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
        return instance;
    }

    private final CommonSeps commonSeps = CommonSeps.getInstance();

    public String process(String string) {
        String simplifiedString = string.toLowerCase();
        simplifiedString = simplifiedString.replace(commonSeps.SPACE, commonSeps.UNDERSCORE);
        simplifiedString = simplifiedString.replace(commonSeps.DASH, commonSeps.UNDERSCORE);
        return simplifiedString;
    }

}
