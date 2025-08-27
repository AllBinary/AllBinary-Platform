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

import org.allbinary.business.init.LicenseInitInfo;
import org.allbinary.business.init.LicenseInitInfoUtil;

//This allows you to set the initialization data for weblisket
import org.allbinary.logic.io.file.FileFactory;

//This allows you to set the initialization data for weblisket
import org.allbinary.globals.PATH_GLOBALS;
import org.allbinary.util.BasicArrayList;
public class PrintLicenseInitInfo
{
    public static final String PATH = "G:\\mnt\\bc\\mydev\\work\\allbinary_src\\licensing\\data\\";
    //public static final String PATH = "G:\\mnt\\bc\\mydev\\work_other\\allbinary_webapp\\src\\main\\webapp\\";
    
   //private static BufferedReader stdinput = new BufferedReader(new InputStreamReader(System.in));

   public PrintLicenseInitInfo(String[] options) throws Exception
   {
      //options[0]
      //NoLicense.init(options[0], this.getClass().getClassLoader());
      //NoLicense.init("G:\\mnt\\bc\\mydev\\licenseserver\\testing\\", this.getClass().getClassLoader());
       //Globals.init(this.getClass().getClassLoader(), "G:\\mnt\\bc\\mydev\\licenseserver\\testing\\");
       LicenseInitInfoUtil.getInstance().setFilePath(PATH + PATH_GLOBALS.getInstance().INIT_PATH);
   }

   public LicenseInitInfo getLicenseInitInfo() throws Exception
   {
       final LicenseInitInfoUtil licenseInitInfoUtil = LicenseInitInfoUtil.getInstance();

      if (FileFactory.getInstance().isFile(licenseInitInfoUtil.getFilePath()))
      {
         return licenseInitInfoUtil.read();
      } else
      {
          System.out.println("No Licence File at: " + licenseInitInfoUtil.getFilePath());
         return new LicenseInitInfo();
      }
   }

   public void printInitInfo()
   {
      try
      {
         final LicenseInitInfo licenseInitInfo = this.getLicenseInitInfo();
         
         System.out.println("License ID: " + licenseInitInfo.getLicenseId());
         
         final BasicArrayList list = licenseInitInfo.getServerList();
         
         final int size = list.size();
         for (int index = 0; index < size; index++)
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
      final PrintLicenseInitInfo initInfo =new PrintLicenseInitInfo(args);
      initInfo.printInitInfo();
   }
}