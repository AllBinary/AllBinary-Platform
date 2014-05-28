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
package org.allbinary.media.image.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

//TWB - replace with file visitor version
public class ImageFileFilter extends FileFilter
{

   public boolean accept (File f)
   {
      if (f.isDirectory ())
      {
          return true;
      }

      String s = f.getName ();
      int i = s.lastIndexOf ('.');

      if (i > 0 && i < s.length ()-1)
      {
          String ext = s.substring (i+1).toLowerCase ();

          if (ext.equals ("jpg") || ext.equals ("jpeg"))
              return true;
      }

      return false;
   }

   public String getDescription ()
   {
      return "Accepts .jpg and .jpeg files";
   }
}
