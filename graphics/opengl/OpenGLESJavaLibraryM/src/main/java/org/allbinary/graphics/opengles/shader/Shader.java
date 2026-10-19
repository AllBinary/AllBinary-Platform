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
package org.allbinary.graphics.opengles.shader;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

/**
 *
 * @author User
 */
public class Shader {
 
    public String shaderName = StringUtil.getInstance().EMPTY_STRING;
    public BasicArrayList shaderStringList = BasicArrayListUtil.getInstance().getImmutableInstance();
    public String shaderAsString = StringUtil.getInstance().EMPTY_STRING;
    public int shaderHandle;
    
}
