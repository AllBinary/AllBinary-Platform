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
package org.allbinary.data.tree.dom;

import java.io.StringBufferInputStream;

import javax.xml.transform.stream.StreamSource;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.io.AbFileLocalInputStream;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.path.AbPath;
import org.w3c.dom.Document;

public class SimpleTransform {

    private final AbPath xsltFilePath;
    private final AbPath dataFilePath;

    public SimpleTransform(AbPath xsltFilePath, AbPath dataFilePath)
    {
        this.xsltFilePath = xsltFilePath;
        this.dataFilePath = dataFilePath;
    }

    public String transform() throws Exception
    {
        final AbFileLocalInputStream inputStream =
            new AbFileLocalInputStream(new AbFile(xsltFilePath));

        final Document document = DomDocumentHelper.create(
            new AbFileLocalInputStream(new AbFile(dataFilePath)));

        final String result = XslHelper.getInstance().translate(
            new StreamSource(inputStream),
            new StreamSource(
            new StringBufferInputStream(DomDocumentHelper.toString(document))
            ));

        return result;
    }
}
