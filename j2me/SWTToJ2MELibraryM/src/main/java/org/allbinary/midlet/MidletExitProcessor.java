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
package org.allbinary.midlet;

import javax.microedition.midlet.MIDlet;

import org.allbinary.canvas.Processor;

public class MidletExitProcessor extends Processor
{
    private final MIDlet midlet;

    public MidletExitProcessor(MIDlet midlet)
    {
        this.midlet = midlet;
    }

    @Override
    public void process() throws Exception
    {
        //Emulator could have an exit but currently it is only the special Android version
        //this.midlet.exit();
        System.exit(0);
    }
}
