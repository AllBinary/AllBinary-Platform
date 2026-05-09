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
package org.allbinary.logic.system.hardware.android;

import java.io.Closeable;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Hashtable;

import org.allbinary.logic.io.NullCloseable;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.hardware.HardwareInterface;
import org.allbinary.logic.system.hardware.components.android.UnknownHardware;
import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class AndroidHardware implements HardwareInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private BasicArrayList componentInterfaceVector = new BasicArrayListD();
    private final String PROC_BUS_INPUT_DIRECTORY = "/proc/bus/input/";
    private final String DEVICES = this.PROC_BUS_INPUT_DIRECTORY + "devices/";
    //private final String HANDLERS = PROC_BUS_INPUT_DIRECTORY + "handlers/";
    private final int MINHARDWARE = 3;

    public AndroidHardware() throws Exception
    {
        this.init(this.DEVICES);

        final int size = this.componentInterfaceVector.size();
        if (size < this.MINHARDWARE)
        {
            throw new Exception("Not Enough Data For A Valid License On Linux");
        }

        //this.logUtil.putF("Hardware Data: " + this.toString(), this, this.commonStrings.CONSTRUCTOR);
    }

    private void init(final String filePath) throws Exception
    {
        Closeable lineNumberReader = NullCloseable.NULL_CLOSEABLE;
        try
        {

            lineNumberReader = this.get(filePath);

        } catch (Exception e) {
            this.logUtil.put("Hardware Data: " + this.toString(), this, this.commonStrings.INIT, e);
            throw e;
        }
        finally
        {
            if (lineNumberReader != null)
            {
                //this.logUtil.putF("closing", this, this.commonStrings.INIT);
                lineNumberReader.close();
            }
        }
    }

    private LineNumberReader get(final String filePath) throws Exception
    {
        this.componentInterfaceVector = new BasicArrayListD();

        final FileReader pciFile = new FileReader(filePath);
        final LineNumberReader lineNumberReader = new LineNumberReader(pciFile);

        //if (lineNumberReader != null)
        //{
        this.logUtil.putF("File Found", this, this.commonStrings.CONSTRUCTOR);

        String nextLine = lineNumberReader.readLine();

        //Get to the first line with Bus info
        //lineNumberReader != null && 
        while (nextLine != null) {
            //this.logUtil.putF("Found Hardware Device: " + componentInterfaceVector.size(), this, this.commonStrings.INIT);

            nextLine = lineNumberReader.readLine();
            this.componentInterfaceVector.add(new UnknownHardware(nextLine));
        }
        //}
        /*
            else
            {
                this.logUtil.putF("Could not load File", this, this.commonStrings.INIT);
            }
         */

        return lineNumberReader;
    }

    @Override
    public HardwareComponentInterface getComponent(int index)
    {
        return (HardwareComponentInterface) this.componentInterfaceVector.get(index);
    }

    public String toString()
    {
        final StringMaker hardwareBuffer = new StringMaker();
        
        final int size = this.componentInterfaceVector.size();
        
        for (int index = 0; index < size; index++)
        {
            HardwareComponentInterface componentInterface = (HardwareComponentInterface) 
                this.componentInterfaceVector.get(index);

            hardwareBuffer.append(componentInterface.toString());
            hardwareBuffer.append(CommonSeps.getInstance().NEW_LINE);
        }
        return hardwareBuffer.toString();
    }

    @Override
    public boolean compareTo(HardwareInterface hardwareInterface)
    {
        return true;
    }

    @Override
    public Hashtable difference(HardwareInterface hardwareInterface)
    {
        return NullUtil.getInstance().NULL_TABLE;
    }

}
