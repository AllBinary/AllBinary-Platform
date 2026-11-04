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

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    private static final XmlDocumentHelper instance = new XmlDocumentHelper();

    /**
     * @return the instance
     */
    public static XmlDocumentHelper getInstance() {
        return XmlDocumentHelper.instance;
    }
    
    private final String INDENT_NUMBER = "indent-number";
    
    private int indent;
    private boolean ignoreDeclaration;
    
    private XmlDocumentHelper() {
        this.init(4, true);
    }

    public synchronized void init(final int indent, final boolean ignoreDeclaration) {
        this.indent = indent;
        this.ignoreDeclaration = ignoreDeclaration;
    }   

    private Transformer createTransformer(final int indent, final boolean ignoreDeclaration)
            throws TransformerConfigurationException {
        final BooleanFactory booleanFactory = BooleanFactory.getInstance();
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute(this.INDENT_NUMBER, indent);
        final Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, CharacterSetData.getInstance().UTF_8);
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
            ignoreDeclaration ? booleanFactory.YES : booleanFactory.NO);
        transformer.setOutputProperty(OutputKeys.INDENT, booleanFactory.YES);
        return transformer;
    }
        
    public Transformer createTransformer() throws TransformerConfigurationException {
        final int currentIndent;
        final boolean currentIgnoreDeclaration;
        synchronized (this) {
            currentIndent = this.indent;
            currentIgnoreDeclaration = this.ignoreDeclaration;
        }
        return this.createTransformer(currentIndent, currentIgnoreDeclaration);
    }

    public String format(final String xmlString) throws Exception {
        return this.formatWithTransformer(xmlString, this.createTransformer());
    }

    public String formatWithTransformer(final String xmlString, final Transformer transformer) throws Exception {
        final InputSource src = new InputSource(new StringReader(xmlString));
        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src);

        final Writer out = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(out));
        return out.toString();
    }
    
   public static String toString(Document document) throws Exception
   {
      try
      {
         final DOMSource domSource = new DOMSource(document);
         
         final ByteArrayOutputStream byteArrayOutputStream
               = new ByteArrayOutputStream();
         
         final StreamResult streamResult = new StreamResult(byteArrayOutputStream);
         
         final TransformerFactory copyTransformerFactory
               = TransformerFactory.newInstance();
         
         final Transformer copyTransformer
               = copyTransformerFactory.newTransformer();
         
         copyTransformer.transform(domSource,streamResult);
         
         return byteArrayOutputStream.toString();
      }
      catch (Exception e)
      {
         throw e;
      }
   }
    
}
