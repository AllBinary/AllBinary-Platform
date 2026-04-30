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
package org.allbinary.graphics.j2me.workarea;

import org.w3c.dom.Document;

public interface WorkAreaJPanelInterface extends java.awt.event.KeyListener
{
    String getName();
    void deselect();
    void select();
    void changeZoom(int factor);
    
    void explodeAll();
    
    void autoRotate(int increments, int totalAngle)throws Exception;
    void autoExplode(int numberOfFrames, int explodeType)throws Exception;
    void autoMirror()throws Exception;
       
    void play();
    void stop();
    boolean isPlaying();

    Document toDocument() throws Exception;
}
