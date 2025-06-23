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
package org.allbinary.bundle.logic.system.loader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.allbinary.globals.Globals;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.AbCryptUtil;
import org.allbinary.logic.system.security.AbKeys;
import org.allbinary.logic.system.security.licensing.AbeLicenseInterface;
import org.allbinary.logic.system.security.licensing.AbeLicenseInterfaceFactory;
import org.allbinary.logic.system.security.licensing.AbeNoLicense;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.gui.dialog.BasicTextJDialog;
import org.allbinary.gui.dialog.ExitCloseListener;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

public class CryptService
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final AbCryptUtil abCryptUtil = AbCryptUtil.getInstance();

   public CryptService()
   {
      Globals.getInstance().init(this.getClass().getClassLoader(), "./");

      LogUtil.put(LogFactory.getInstance("Set Globals: " + URLGLOBALS.getWebappPath(), this, this.commonStrings.CONSTRUCTOR));
   }

   public InputStream getDecryptedInputStream(final AbeClientInformationInterface abeClientInformation, final String name, final InputStream inputStream)
   {
      try
      {
         final String key = AbKeys.getInstance().getKey(abeClientInformation, name);

         final byte[] decrypted = this.abCryptUtil.decrypt(inputStream, key);

         /*
         //TWB - test decryption
         ByteArrayInputStream byteArrayInputStream =
         new ByteArrayInputStream(decrypted);
         DataInputStream dataInputStream =
         new DataInputStream(byteArrayInputStream);
         byte[] bytes = new byte[decrypted.length];
         dataInputStream.readFully(bytes);
          */
         return new ByteArrayInputStream(decrypted);
      } catch (LicensingException e)
      {
         this.showLicenseDialog(abeClientInformation, e);
      } catch (Exception e)
      {
         this.showLicenseDialog(abeClientInformation, e);
      }
      return null;
   }

   private void showLicenseDialog(final AbeClientInformationInterface abeClientInformation, final Exception e)
   {
      try
      {
         String error = "Licensing Exception";

         LogUtil.put(LogFactory.getInstance(error, this, this.commonStrings.INIT, e));

         final BasicTextJDialog basicTextJDialog = new BasicTextJDialog(e.getMessage());

         try
         {
            final AbeLicenseInterface abeLicenseInterface = AbeLicenseInterfaceFactory.getInstance().getLicenseInstance(abeClientInformation);

            if (abeLicenseInterface != AbeNoLicense.getInstance())
            {
               if (abeLicenseInterface.isValid())
               {
                  basicTextJDialog.setText("Subscription Invalid");
               }
            }
         } catch (LicensingException e2)
         {
            LogUtil.put(LogFactory.getInstance(error, this, this.commonStrings.INIT, e2));
         }

         basicTextJDialog.addCloseListener(new ExitCloseListener());
         basicTextJDialog.setVisible(true);
      } catch (Exception e3)
      {
         String error = "Error";
         LogUtil.put(LogFactory.getInstance(error, this, this.commonStrings.INIT, e3));
      }
   }
}