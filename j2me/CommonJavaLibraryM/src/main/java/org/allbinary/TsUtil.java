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
package org.allbinary;

/**
 *
 * @author User
 */
public class TsUtil {
    
    private static final TsUtil instance = new TsUtil();

    /**
     * @return the instance
     */
    public static TsUtil getInstance() {
        return TsUtil.instance;
    }

    public int hashCode(Object object) {
        return object.hashCode();
    }

    public void waitFor(Object object, final long timeoutMillis) throws InterruptedException {
        object.wait(timeoutMillis);
    }
    
//    public void drawTextC(final Canvas g, final char[] text, final int index, final int count, final float x, final float y, final Paint paint) {
//        g.drawText(text, index, count, x, y, paint);
//    }
//
//    public  void drawText(final Canvas g, final String text, final float x, final float y, final Paint paint) {
//        g.drawText(text, x, y, paint);
//    }
//
//    public  void drawTextO(final Canvas g, final String text, final int start, final int end, final float x, final float y, final Paint paint) {
//        g.drawText(text, start, end, x, y, paint);
//    }
    
}
