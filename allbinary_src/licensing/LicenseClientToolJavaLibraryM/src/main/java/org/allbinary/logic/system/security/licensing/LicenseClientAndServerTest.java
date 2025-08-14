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
import org.allbinary.logic.string.StringMaker;


import org.allbinary.business.init.LicenseInitInfo;
import org.allbinary.business.init.LicenseInitInfoUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.client.XmlRpcAbeLicenseRetrievalClient;
import org.allbinary.globals.PATH_GLOBALS;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.util.BasicArrayList;

public class LicenseClientAndServerTest
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private BasicArrayList vector = new BasicArrayList();

    public LicenseClientAndServerTest(String[] options) throws Exception
    {
        //options[0]
        //NoLicense.init("G:/mnt/bc/mydev/working/automation/obf/", this.getClass().getClassLoader());
        //Globals.init(this.getClass().getClassLoader(), "G:\\mnt\\bc\\mydev\\licenseserver\\testing\\" + PATH_GLOBALS.getInstance().INIT_PATH);

        LicenseInitInfoUtil.getInstance().setFilePath(PrintLicenseInitInfo.PATH + PATH_GLOBALS.getInstance().INIT_PATH);

        for (int index = 1; index < options.length; index++)
        {
            vector.add(options[index]);
        }

        if(options.length < 2)
        {
            File file = new File(PrintLicenseInitInfo.PATH + "testlicenseservers.txt");
            FileReader reader = new FileReader(file);

            LineNumberReader lineNumberReader = new LineNumberReader(reader);

            while (true)
            {
                String line = lineNumberReader.readLine();

                if(line != null)
                {
                    vector.add(line);
                }
                else
                {
                    break;
                }
            }
        }
    }

    public synchronized void test()
    {
        StringMaker stringBuffer = new StringMaker();
        try
        {
            BasicArrayList list = new BasicArrayList();

            final String TESTING = "Testing with new licence:<br/>";
            final String PROCESSING = "Processing: <br/>";
            final String END = "<br/>End<br/>";
            final String RESULT = "Test Result: ";

            for (int index = 0; index < vector.size(); index++)
            {
                stringBuffer.append(TESTING);

                String serverName = (String) vector.get(index);
        try
        {

                LicenseInitInfo licenseInitInfo = new LicenseInitInfo();
                licenseInitInfo.addServer(serverName);
                LicenseInitInfoUtil.getInstance().write(licenseInitInfo);

                AbeClientInformationInterface abeClientInformation =
                    AbeClientInformationInterfaceFactory.getInstance();
                XmlRpcAbeLicenseRetrievalClient xmlRpcAbeLicenseClient = 
                    new XmlRpcAbeLicenseRetrievalClient(abeClientInformation);

                 AbeLicenseInterface abeLicenseInterface =
                     (AbeLicenseInterface) xmlRpcAbeLicenseClient.get(null);

                //Save license id and server list to file

                if (abeLicenseInterface != null)
                {
                    stringBuffer.append(PROCESSING);
                    stringBuffer.append(abeLicenseInterface.toString());
                }

                stringBuffer.append(END);

                logUtil.put(RESULT + stringBuffer.toString(), this, "test");
                
                stringBuffer.delete(0, stringBuffer.length());

        } catch (Exception e)
        {
            list.add(serverName);
            logUtil.put("Test Error: " + stringBuffer.toString(), this, "test", e);
        }

            }

            logUtil.put("Total Servers Failed: " + list.size() + " Failed Servers: " + list.toString(), this, "test");
        } catch (Exception e)
        {
            logUtil.put("Test Error: " + stringBuffer.toString(), this, "test", e);
        }
    }

    /*
    public void test()
    {
    try
    {
    AbeLicenseClient licenseClient = new AbeLicenseClient();
    String licenseInfo = licenseClient.test();
    System.out.println(licenseInfo);
    } catch (Exception e)
    {
    logUtil.put(this.commonStrings.EXCEPTION, this, "test()", e);
    }
    }
     */
    public static void main(String[] args)
    {
        try
        {
            LicenseClientAndServerTest licensingTest = new LicenseClientAndServerTest(args);
            licensingTest.test();
        } catch (Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, "main()", "main()", e);
        }
    }
}