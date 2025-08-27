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
package org.allbinary.logic.system.security.licensing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.allbinary.business.init.LicenseInitInfo;
import org.allbinary.business.init.LicenseInitInfoUtil;

//This allows you to set the initialization data for weblisket
import org.allbinary.logic.io.file.FileFactory;
import org.allbinary.globals.PATH_GLOBALS;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import org.allbinary.util.BasicArrayList;

public class SetLicenseInitInfo
{

    private static BufferedReader stdinput =
            new BufferedReader(new InputStreamReader(System.in));
    private BasicArrayList vector = new BasicArrayList();

    public SetLicenseInitInfo(String[] options) throws Exception
    {
        //options[0]
        //NoLicense.init(options[0], this.getClass().getClassLoader());
        //NoLicense.init("G:\\mnt\\bc\\mydev\\licenseserver\\testing\\", this.getClass().getClassLoader());
        //Globals.init(this.getClass().getClassLoader(), "G:\\mnt\\bc\\mydev\\licenseserver\\testing\\");
        LicenseInitInfoUtil.getInstance().setFilePath(PrintLicenseInitInfo.PATH + PATH_GLOBALS.getInstance().INIT_PATH);

        for (int index = 1; index < options.length; index++)
        {
            vector.add(options[index]);
        }
    }

    public LicenseInitInfo getLicenseInitInfo() throws Exception
    {
        LicenseInitInfoUtil licenseInitInfoUtil = LicenseInitInfoUtil.getInstance();

        if (FileFactory.getInstance().isFile(licenseInitInfoUtil.INITFILENAME))
        {
            return licenseInitInfoUtil.read();
        } else
        {
            return new LicenseInitInfo();
        }
    }

    public void createNewLicenseInitInfo(BasicArrayList licenceServerVector)
    {
        try
        {
            if (licenceServerVector == null)
            {
                throw new Exception("null Vector Passed - Just Make Empty if you want");
            }

            LicenseInitInfo licenseInitInfo = this.getLicenseInitInfo();

            System.out.println("License Serv Example: http://www.dartstatistics.com/LicServ/serverssl.php");

            licenseInitInfo.clearServers();

            /*
            for(int index = 0; index < 2; index++)
            {
            System.out.println("New Lic Serv: ");
            String licServer = stdinput.readLine();
            licenseInitInfo.addLicenseServer(licServer);
            index++;
            }
             */

            /*
            final int size = vector.size();
            for (int index = 0; index < size; index++)
            {
                String serverName = (String) vector.get(index);
                licenseInitInfo.addServer(serverName);
            }
            */

            /*
            int index = 0;
            while (true)
            {
                System.out.println("New Lic Serv: ");
                String licServer = stdinput.readLine();

                if (licServer == null || licServer.length() < 2)
                {
                    break;
                }
                licenseInitInfo.addServer(licServer);

                index++;
            }
            */

            File file = new File(PrintLicenseInitInfo.PATH + "licenseservers.txt");
            FileReader reader = new FileReader(file);

            LineNumberReader lineNumberReader = new LineNumberReader(reader);

            while (true)
            {
                String line = lineNumberReader.readLine();
                
                if(line != null)
                {
                    licenseInitInfo.addServer(line);
                }
                else
                {
                    break;
                }
            }

            LicenseInitInfoUtil.getInstance().write(licenseInitInfo);
            LicenseInitInfoUtil.getInstance().read();
        } catch (Exception e)
        {
            System.out.print("Error: " + e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception
    {
        SetLicenseInitInfo initInfo = new SetLicenseInitInfo(args);
        initInfo.createNewLicenseInitInfo(new BasicArrayList());
    }
}
