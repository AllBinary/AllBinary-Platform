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
package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Image;

public class ChoiceGroupImageFactory
{
    private static final ChoiceGroupImageFactory instance = new ChoiceGroupImageFactory();

    public static ChoiceGroupImageFactory getInstance()
    {
        return instance;
    }

    private byte[] multiOff = {
        -119, 80, 78, 71, 13, 10, 26, 10, 0, 0,
        0, 13, 73, 72, 68, 82, 0, 0, 0, 10,
        0, 0, 0, 11, 2, 3, 0, 0, 0, 59,
        0, -12, -117, 0, 0, 0, 6, 80, 76, 84,
        69, -1, -1, -1, -69, -69, -69, -57, 75, -33,
        -8, 0, 0, 0, 30, 73, 68, 65, 84, 120,
        -38, 99, 96, 96, 96, 96, 12, 101, -8, -51,
        -32, -64, 32, -64, -60, -64, -64, -128, 11, 51,
        -122, 50, -4, 6, 0, 63, 116, 3, 1, 53,
        -108, 39, -26, 0, 0, 0, 0, 73, 69, 78,
        68, -82, 66, 96, -126 
    };

    private byte[] multiOn = {
        -119, 80, 78, 71, 13, 10, 26, 10, 0, 0,
        0, 13, 73, 72, 68, 82, 0, 0, 0, 10,
        0, 0, 0, 11, 2, 3, 0, 0, 0, 59,
        0, -12, -117, 0, 0, 0, 12, 80, 76, 84,
        69, -1, -1, -1, -69, -69, -69, 106, 106, 106,
        2, 2, 2, 106, -103, 14, -47, 0, 0, 0,
        53, 73, 68, 65, 84, 120, -38, 99, 96, 96,
        124, -64, -16, -1, -77, 3, -45, 65, -111, 15,
        76, 12, 108, 12, 76, 12, -4, 12, 76, 12,
        18, 12, 76, -68, 127, 24, -104, 126, 45, 96,
        96, -7, -11, -109, -127, -23, -65, 3, 3, -29,
        127, -122, -113, 0, 5, 37, 12, -34, 1, -99,
        -83, 100, 0, 0, 0, 0, 73, 69, 78, 68,
        -82, 66, 96, -126 
    };

    private byte[] radioOff = {
        -119, 80, 78, 71, 13, 10, 26, 10, 0, 0,
        0, 13, 73, 72, 68, 82, 0, 0, 0, 11,
        0, 0, 0, 11, 2, 3, 0, 0, 0, -44,
        -62, -97, -75, 0, 0, 0, 9, 80, 76, 84,
        69, -1, -1, -1, -69, -69, -69, 106, 106, 106,
        -44, 13, -1, -24, 0, 0, 0, 42, 73, 68,
        65, 84, 120, -38, 99, 96, 90, -59, -64, 32,
        -63, 48, -127, 65, -127, 65, -127, 41, -127, -31,
        5, 19, 3, 3, 3, 50, 102, 80, 96, 80,
        96, -6, -63, 80, -64, -64, -76, -118, 1, 0,
        113, 24, 5, 61, 73, -68, -100, 98, 0, 0,
        0, 0, 73, 69, 78, 68, -82, 66, 96, -126 
    };

    private byte[] radioOn = {
        -119, 80, 78, 71, 13, 10, 26, 10, 0, 0,
        0, 13, 73, 72, 68, 82, 0, 0, 0, 11,
        0, 0, 0, 11, 2, 3, 0, 0, 0, -44,
        -62, -97, -75, 0, 0, 0, 12, 80, 76, 84,
        69, -1, -1, -1, -69, -69, -69, 106, 106, 106,
        2, 2, 2, 106, -103, 14, -47, 0, 0, 0,
        50, 73, 68, 65, 84, 120, -38, 5, -63, 65,
        13, 0, 32, 12, 4, -63, -19, -11, -117, 1,
        18, 68, -100, 10, 52, 19, 94, 72, 64, 17,
        101, -122, 44, -44, -29, 98, -52, 89, 77, -102,
        40, 2, 85, -95, -73, -63, -104, -63, 37, -117,
        15, -40, 119, 10, 41, 78, 26, -79, 59, 0,
        0, 0, 0, 73, 69, 78, 68, -82, 66, 96,
        -126 
    };
    
    private Image imgMultiOff = Image.createImage(multiOff, 0, multiOff.length);
    private Image imgMultiOn = Image.createImage(multiOn, 0, multiOn.length);
    private Image imgRadioOff = Image.createImage(radioOff, 0, radioOff.length);
    private Image imgRadioOn = Image.createImage(radioOn, 0, radioOn.length);
    
    private final Image[] imageArray = {
        imgMultiOff,
        imgMultiOn,
        imgRadioOff,
        imgRadioOn
        };

    public Image[] getImageArray()
    {
        return imageArray;
    }
}
