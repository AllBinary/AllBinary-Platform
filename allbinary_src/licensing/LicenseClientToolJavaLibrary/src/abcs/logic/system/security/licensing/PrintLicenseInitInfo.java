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
package abcs.logic.system.security.licensing;

import abcs.business.init.LicenseInitInfo;
import abcs.business.init.LicenseInitInfoUtil;

//This allows you to set the initialization data for weblisket
import abcs.logic.basic.io.file.FileFactory;

//This allows you to set the initialization data for weblisket
import allbinary.globals.PATH_GLOBALS;
import org.allbinary.util.BasicArrayList;
public class PrintLicenseInitInfo
{
   //private static BufferedReader stdinput = new BufferedReader(new InputStreamReader(System.in));

   public PrintLicenseInitInfo(String[] options) throws Exception
   {
      //options[0]
      //NoLicense.init(options[0], this.getClass().getClassLoader());
      //NoLicense.init("G:\\mnt\\bc\\mydev\\licenseserver\\testing\\", this.getClass().getClassLoader());
       //Globals.init(this.getClass().getClassLoader(), "G:\\mnt\\bc\\mydev\\licenseserver\\testing\\");
       LicenseInitInfoUtil.getInstance().setFilePath("G:\\mnt\\bc\\mydev\\licenseserver\\testing\\" + PATH_GLOBALS.getInstance().INIT_PATH);
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

   public void printInitInfo()
   {
      try
      {
         LicenseInitInfo licenseInitInfo = this.getLicenseInitInfo();
         
         System.out.println("License ID: " + licenseInitInfo.getLicenseId());
         
         BasicArrayList list = licenseInitInfo.getServerList();
         
         for (int index = 0; index < list.size(); index++)
         {
            System.out.println("Lic Serv: " + list.get(index));
         }

      } catch (Exception e)
      {
         System.out.print("Error: " + e);
         e.printStackTrace();
      }
   }

   public static void main(String[] args) throws Exception
   {
      PrintLicenseInitInfo initInfo =
         new PrintLicenseInitInfo(args);
      initInfo.printInitInfo();
   }
}