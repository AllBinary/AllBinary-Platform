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
package allbinary.graphics.j2me.workarea;

import org.w3c.dom.Document;

public interface WorkAreaJPanelInterface extends java.awt.event.KeyListener
{
    public String getName();
    public void deselect();
    public void select();
    public void changeZoom(int factor);
    
    public void explodeAll();
    
    public void autoRotate(int increments, int totalAngle)throws Exception;
    public void autoExplode(int numberOfFrames, int explodeType)throws Exception;
    void autoMirror()throws Exception;
       
    public void play();
    public void stop();
    public boolean isPlaying();

    public Document toDocument() throws Exception;
}
