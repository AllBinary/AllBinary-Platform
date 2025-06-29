/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.java.characters.CharacterSetData;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author User
 */
public class XmlDocumentHelper {
    
    private static final XmlDocumentHelper instance = new XmlDocumentHelper();

    /**
     * @return the instance
     */
    public static XmlDocumentHelper getInstance() {
        return instance;
    }
    
    private final String INDENT_NUMBER = "indent-number";
    
    private Transformer transformer;
    
    private XmlDocumentHelper() {
        this.init(4, true);
    }

    public void init(final int indent, final boolean ignoreDeclaration) {
        try {
            final BooleanFactory booleanFactory = BooleanFactory.getInstance();
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute(INDENT_NUMBER, indent);
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, CharacterSetData.getInstance().UTF_8);
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, ignoreDeclaration ? booleanFactory.YES : booleanFactory.NO);
            transformer.setOutputProperty(OutputKeys.INDENT, booleanFactory.YES);
        } catch(Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.INIT, e));
        }
    }   
        
    public String format(final String xmlString) throws Exception {

        final InputSource src = new InputSource(new StringReader(xmlString));
        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src);

        final Writer out = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(out));
        return out.toString();
    }
    
}
