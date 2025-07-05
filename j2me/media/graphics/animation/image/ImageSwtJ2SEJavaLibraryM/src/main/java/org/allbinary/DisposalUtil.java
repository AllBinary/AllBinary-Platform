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
package org.allbinary;

import javax.microedition.lcdui.Image;

import org.allbinary.string.CommonStrings;
import org.eclipse.swt.graphics.Resource;
import org.microemu.device.swt.SwtImmutableImage;
import org.microemu.device.swt.SwtMutableImage;

/**
 *
 * @author User
 */
public class DisposalUtil {
    
    private static final DisposalUtil instance = new DisposalUtil();
    
    /**
     * @return the instance
     */
    public static DisposalUtil getInstance() {
        return instance;
    }
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();

    public void dispose(final Image image) {
        
        if(image == null) {
            return;
        }

        if (image.isMutable()) {
            final SwtMutableImage mutableImage = (SwtMutableImage) image;
            this.dispose((Resource) mutableImage.getImage());
        } else {
            //final SwtImmutableImage immutableImage = (SwtImmutableImage) image;
            //this.dispose(immutableImage.getImage());
        }
    }
    
    public void dispose(final Resource resource) {
        if(!resource.isDisposed()) {
            System.out.println(commonStrings.CLOSE);
            resource.dispose();
        }
    }

}
