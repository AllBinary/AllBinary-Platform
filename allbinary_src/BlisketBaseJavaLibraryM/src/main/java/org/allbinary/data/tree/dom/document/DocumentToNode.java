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
package org.allbinary.data.tree.dom.document;

import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.basic.string.regex.replace.Replace;

public class DocumentToNode
{
    //private static final String TEMPNODE = "TEMP_NODE";
    //private static String tempNodeStr = "<TEMP_NODE></TEMP_NODE>";
    //null;

    private static final String XMLROOT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private static final String XMLROOTSTART = "<?xml";
    private static final String XMLROOTEND = "?>";
    private static final String XMLJSPROOTSTART = "<jsp:root";
    private static final String XMLEND = ">";
    private static final String XMLJSPROOTENDSTART = "</jsp:root";

    private DocumentToNode()
    {
    }

    /*
    public static final Node createTempNode(Document document)
    {
    return document.createElement(TEMPNODE);
    }

    public static String getKey()
    {
    return tempNodeStr;
    }
     */
    public static String convertDocumentToNodeString(String documentString)
        throws Exception
    {
        //TWB - Remove jsp:root from inserted component
        documentString = remove(documentString, XMLJSPROOTSTART, XMLEND);
        //"1: " + documentString);
        documentString = remove(documentString, XMLJSPROOTENDSTART, XMLEND);
        //"2: " + documentString);
        //this replaces the xml info and could cause a problem with different transformations
        Replace replaceXmlRoot = new Replace(XMLROOT, StringUtil.getInstance().EMPTY_STRING);
        return replaceXmlRoot.all(documentString);
    }

    public static String removeXmlDefinitionFromDocumentString(String documentString)
    {
        return remove(documentString, XMLROOTSTART, XMLROOTEND);
    }

    public static String remove(String documentString, String start, String end)
    {
        //this replaces the xml info and could cause a problem with different transformations
        int startIndex;

        while ((startIndex = documentString.indexOf(start)) != -1)
        {
            if (startIndex >= 0)
            {
                int endIndex = documentString.indexOf(end, startIndex);

                if (endIndex >= 0)
                {
                    String startString = documentString.substring(0, startIndex);
                    String resultString = documentString.substring(endIndex + 1);
                    return startString + resultString;
                }
            }
        }
        return documentString;
    }

    /*
    public static void main(String[] args) throws Exception
    {
AppUrlGlobals urlGlobals = new AppUrlGlobals();
urlGlobals.setWebappPath("G:\\mnt\\bc\\mydev\\working\\allbinary_src\\blisket\\FreeblisketWebApplication\\web\\");
URLGLOBALS.init(urlGlobals);

        System.out.print("Result: "+
            DocumentToNode.convertDocumentToNodeString(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?><jsp:root xmlns:jsp=\"http://java.sun.com/JSP/Page\" xmlns:transformInfoObjectConfig=\"/WEB-INF/transformInfoObjectConfig.tld\" xmlns:transform=\"/WEB-INF/transform.tld\" xmlns:payment=\"/WEB-INF/payment.tld\" xmlns:generic=\"/WEB-INF/generic.tld\" xmlns:ecommerce=\"/WEB-INF/ecommerce.tld\" xmlns:admin=\"/WEB-INF/admin.tld\" xmlns:jutil=\"/WEB-INF/jutil.tld\" version=\"1.2\"><jsp:scriptlet></jsp:scriptlet><div class=\"mainHeading\"><p>About</p><div class=\"main\"><jsp:expression>DEFAULTBODYMESSAGE</jsp:expression></div></div></jsp:root>"
            ));
    }
     */
}
