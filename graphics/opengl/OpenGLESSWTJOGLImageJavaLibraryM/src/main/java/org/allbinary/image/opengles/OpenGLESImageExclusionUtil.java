/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.image.opengles;

import javax.microedition.lcdui.Image;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class OpenGLESImageExclusionUtil {
    
    private static final OpenGLESImageExclusionUtil instance = new OpenGLESImageExclusionUtil();


    /**
     * @return the instance
     */
    public static OpenGLESImageExclusionUtil getInstance() {
        return instance;
    }

    private final String EXCLUSION = "font";
    public final BasicArrayList list = new BasicArrayList();
    
    private OpenGLESImageExclusionUtil() {
    }

    public boolean isCustomScaling(final Image image) {
        final String imageName = image.getName();
        boolean isNormalScaling = list.size() == 0 || imageName.startsWith(EXCLUSION);
        if(isNormalScaling) {
            return false; 
        } else {

            final int size = list.size();
            String name;
            for (int index = 0; index < size; index++) {
                name = (String) list.get(index);
                if (imageName.startsWith(name)) {
                    isNormalScaling = true;
                }
            }
        }

        if(isNormalScaling) {
            return false; 
        } else {
            return true;
        }
    }
    
}
