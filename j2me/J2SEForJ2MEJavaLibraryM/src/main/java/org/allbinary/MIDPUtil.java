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
package org.allbinary;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

//J2SE
//The MIDP methods that are overloaded. This platform calls the microemu methods with the names that are not overloaded,
//like microemu-playn, so the languages without overloading can be generated. The J2ME version uses the MIDP overloads.
public class MIDPUtil {

    private static final MIDPUtil instance = new MIDPUtil();

    /**
     * @return the instance
     */
    public static MIDPUtil getInstance() {
        return MIDPUtil.instance;
    }

    public Image createImageWH(final int width, final int height) {
        return Image.createImageWH(width, height);
    }

    public Image createImageFromByteArray(final byte[] imageData, final int imageOffset, final int imageLength) {
        return Image.createImageFromByteArray(imageData, imageOffset, imageLength);
    }

    public Image createImageFromInputStream(final InputStream inputStream) throws IOException {
        return Image.createImageFromInputStream(inputStream);
    }

    public Player createPlayerFromInputStream(final InputStream inputStream, final String type) throws IOException, MediaException {
        return Manager.createPlayerFromInputStream(inputStream, type);
    }

    public int appendItem(final Form form, final Item item) {
        return form.appendItem(item);
    }

}
