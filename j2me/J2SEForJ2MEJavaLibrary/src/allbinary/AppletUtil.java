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
package allbinary;

//import abcs.logic.communication.log.PreLogUtil;

public class AppletUtil 
{
    private AppletUtil()
    {
    }

    private static boolean applet = false;

    public static void setApplet()
    {
        applet = true;
    }
    //private static final String APPLET1 = "Applet".toLowerCase();
    //private static final String APPLET = "sun.plugin.security.PluginClassLoader".toLowerCase();
    
   public static boolean isAppletLoader(Object object)
   {
       //String className = object.getClass().getClassLoader().getClass().getName().toLowerCase();

      //LogUtil.put(LogFactory.getInstance("Applet ClassLoader? " + className, "AppletUtil", "isAppletLoader"));
       //PreLogUtil.put("Applet ClassLoader? " + className
           //+
           //" " + className.indexOf(APPLET1) +
           //" " + className.indexOf(APPLET)
           //,
           //"AppletUtil", "isAppletLoader");
       
      //if (className.indexOf(APPLET1) >= 0 || className.indexOf(APPLET) >= 0)
      //{
         //LogUtil.put(LogFactory.getInstance("Applet: " + className, "AppletUtil", "isAppletLoader"));
        // return true;
      //}
      //else
      //{
        // return false;
      //}

       return applet;
   }
}
