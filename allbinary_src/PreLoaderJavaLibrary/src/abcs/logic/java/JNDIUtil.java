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
package abcs.logic.java;

import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import org.omg.CosNaming.NamingContext;

public class JNDIUtil
{

    public static void printNamingEnumerationPair(
        Context ctx, String spaces)
        throws NamingException
    {

        NamingEnumeration ncb = ctx.list("");
        while (ncb.hasMore())
        {
            NameClassPair binding = (NameClassPair) ncb.next();

            System.out.println(spaces + binding.getName() + " - " + binding + " - " + binding.getClass().getName());
            try
            {
                NamingContext nc = (NamingContext) ctx.lookup(binding.getName());
            //printNamingEnumerationPair(nc, " ");
            } catch (Exception e)
            {
            }
        }
    }

    /*
     *
    public static void printNamingEnumerationPair(
    NamingContext nc, String spaces)
    throws NamingException {

    NamingEnumeration ncb = nc.list("");

    while (ncb.hasMore()) {
    NameClassPair binding = (NameClassPair) ncb.next();

    System.out.println(spaces + binding.getName() + " - " + binding + " - " + binding.getClass().getName());
    try
    {
    NamingContext nc2 = (NamingContext) nc.lookup(binding.getName());
    printNamingEnumerationPair(nc2, " ");
    }catch(Exception e)
    {

    }
    }
    }
     */
}

