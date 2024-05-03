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
package org.allbinary.logic.java.jar;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 *
 * @author USER
 */
public class JarUtil
{
   private URL jarURL;
   
   /** Creates a new instance of JarUtil */
   public JarUtil(URL jarURL)
   {
      this.jarURL = jarURL;
   }
   
   public void show()
   {
      byte[] cache = new byte[1024];
      try
      {
         URLConnection conn = jarURL.openConnection();
         JarInputStream jis = new JarInputStream(conn.getInputStream());
         while (true)
         {
            JarEntry entry = jis.getNextJarEntry();
            if (entry != null)
            {
               if (!entry.isDirectory())
               {
                  int offset = 0;
                  int i = 0;

                  while ((i = jis.read(cache, offset, cache.length - offset)) != -1)
                  {
                     offset += i;

                     if (offset >= cache.length)
                     {
                        byte[] newcache = new byte[cache.length + 1024];

                        System.arraycopy(cache, 0, newcache, 0, cache.length);
                        cache = newcache;
                     }
                  }

                  byte[] tmp = new byte[offset];
                  System.arraycopy(cache, 0, tmp, 0, offset);
                  System.out.println(entry.getName());
                  //entries.put(entry.getName(), tmp);
               }
            }
            else
            {
               break;
            }
         }
      }
      catch (IOException ex)
      {
         System.err.println(ex);
         ex.printStackTrace();
      }
   }

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) throws Exception
   {
      new JarUtil(new URL("file:///G:/mnt/bc/mydev/working/j2me/MiniSpaceWars/MiniSpaceWars/dist/MiniSpaceWars.jar")).show();
   }
   
}
