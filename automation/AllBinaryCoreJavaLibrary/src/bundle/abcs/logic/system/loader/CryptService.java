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
package bundle.abcs.logic.system.loader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import abcs.globals.Globals;
import abcs.globals.URLGLOBALS;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.security.AbCryptUtil;
import abcs.logic.system.security.AbKeys;
import abcs.logic.system.security.licensing.AbeLicenseInterface;
import abcs.logic.system.security.licensing.AbeLicenseInterfaceFactory;
import abcs.logic.system.security.licensing.AbeNoLicense;
import abcs.logic.system.security.licensing.LicensingException;
import allbinary.gui.dialog.BasicTextJDialog;
import allbinary.gui.dialog.ExitCloseListener;

public class CryptService
{

   public CryptService()
   {
      Globals.init(this.getClass().getClassLoader(), "./");

      LogUtil.put(new Log("Set Globals: " + URLGLOBALS.getWebappPath(), this, "Constructor"));
   }

   public InputStream getDecryptedInputStream(String name, InputStream inputStream)
   {
      try
      {
         String key = AbKeys.getKey(name);

         byte[] decrypted = AbCryptUtil.decrypt(inputStream, key);

         /*
         //TWB - test decryption
         ByteArrayInputStream byteArrayInputStream =
         new ByteArrayInputStream(decrypted);
         DataInputStream dataInputStream =
         new DataInputStream(byteArrayInputStream);
         byte bytes[] = new byte[decrypted.length];
         dataInputStream.readFully(bytes);
          */
         return new ByteArrayInputStream(decrypted);
      } catch (LicensingException e)
      {
         this.showLicenseDialog(e);
      } catch (Exception e)
      {
         this.showLicenseDialog(e);
      }
      return null;
   }

   private void showLicenseDialog(Exception e)
   {
      try
      {
         String error = "Licensing Exception";

         LogUtil.put(new Log(error, this, "init", e));

         BasicTextJDialog basicTextJDialog = new BasicTextJDialog(e.getMessage());

         try
         {
            AbeLicenseInterface abeLicenseInterface = AbeLicenseInterfaceFactory.getInstance();

            if (abeLicenseInterface != AbeNoLicense.getInstance())
            {
               if (abeLicenseInterface.isValid())
               {
                  basicTextJDialog.setText("Subscription Invalid");
               }
            }
         } catch (LicensingException e2)
         {
            LogUtil.put(new Log(error, this, "init", e2));
         }

         basicTextJDialog.addCloseListener(new ExitCloseListener());
         basicTextJDialog.setVisible(true);
      } catch (Exception e3)
      {
         String error = "Error";
         LogUtil.put(new Log(error, this, "init", e3));
      }
   }
}