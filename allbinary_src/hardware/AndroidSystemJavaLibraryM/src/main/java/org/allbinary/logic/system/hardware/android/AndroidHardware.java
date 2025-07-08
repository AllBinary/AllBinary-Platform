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

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Hashtable;
import java.util.Vector;

import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.hardware.HardwareInterface;
import org.allbinary.logic.system.hardware.components.android.UnknownHardware;
import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class AndroidHardware implements HardwareInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private Vector componentInterfaceVector = new Vector();
    private final String PROC_BUS_INPUT_DIRECTORY = "/proc/bus/input/";
    private final String DEVICES = PROC_BUS_INPUT_DIRECTORY + "devices/";
    //private final String HANDLERS = PROC_BUS_INPUT_DIRECTORY + "handlers/";
    private final int MINHARDWARE = 3;

    public AndroidHardware() throws Exception
    {
        this.init(DEVICES);

        if (componentInterfaceVector.size() < MINHARDWARE)
        {
            throw new Exception("Not Enough Data For A Valid License On Linux");
        }

        //logUtil.put("Hardware Data: " + this.toString(), this, commonStrings.CONSTRUCTOR);
    }

    private void init(String filePath) throws Exception
    {
        LineNumberReader lineNumberReader = null;
        try
        {
            this.init(lineNumberReader, filePath);
        } catch (Exception e)
        {
            logUtil.put("Hardware Data: " + this.toString(), this, commonStrings.CONSTRUCTOR, e);
            throw e;
        }
    }

    private void init(LineNumberReader lineNumberReader, String filePath) throws Exception
    {
        try
        {
            componentInterfaceVector = new Vector();

            FileReader pciFile = new FileReader(filePath);
            lineNumberReader = new LineNumberReader(pciFile);

            //if (lineNumberReader != null)
            //{
                logUtil.put("File Found", this, commonStrings.CONSTRUCTOR);

                String nextLine = lineNumberReader.readLine();

                //Get to the first line with Bus info
                //lineNumberReader != null && 
                while (nextLine != null)
                {
                    //logUtil.put("Found Hardware Device: " + componentInterfaceVector.size(), this, commonStrings.INIT);

                    nextLine = lineNumberReader.readLine();
                    componentInterfaceVector.add(new UnknownHardware(nextLine));
                }
            //}
            /*
            else
            {
                logUtil.put("Could not load File", this, commonStrings.INIT);
            }
            */

            lineNumberReader.close();
        }
        finally
        {
            if (lineNumberReader != null)
            {
                lineNumberReader.close();
            }
        }
    }

    public HardwareComponentInterface getComponent(int index)
    {
        return (HardwareComponentInterface) componentInterfaceVector.get(index);
    }

    public String toString()
    {
        StringBuilder hardwareBuffer = new StringBuilder();
        
        int size = this.componentInterfaceVector.size();
        
        for (int index = 0; index < size; index++)
        {
            HardwareComponentInterface componentInterface = (HardwareComponentInterface) 
                this.componentInterfaceVector.get(index);

            hardwareBuffer.append(componentInterface.toString());
            hardwareBuffer.append(CommonSeps.getInstance().NEW_LINE);
        }
        return hardwareBuffer.toString();
    }

    public boolean compareTo(HardwareInterface hardwareInterface)
    {
        return true;
    }

    public Hashtable difference(HardwareInterface hardwareInterface)
    {
        return new Hashtable();
    }
}
