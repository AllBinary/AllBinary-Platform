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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.w3c.dom.Document;

//import org.allbinary.data.tree.dom.document.DomDocumentHelper;
public class XslHelper
{

    private static final XslHelper instance = new XslHelper();

    /**
     * @return the instance
     */
    public static XslHelper getInstance()
    {
        return instance;
    }

    private XslHelper()
    {
    }

    public OutputStream translate(final StreamSource xsltStreamSource,
            final StreamSource xmlStreamSource,
            final StreamResult streamResult) throws Exception
    {
        try
        {
            //TWB - GAE needed xalan.jar and serializer.jar
            final TransformerFactory transformerFactory = TransformerFactory.newInstance( //"org.apache.xalan.processor.TransformerFactoryImpl",
                    //Thread.currentThread().getContextClassLoader()
                    );

            final Transformer transformer = transformerFactory.newTransformer(xsltStreamSource);

            transformer.transform(
                    xmlStreamSource,
                    streamResult);

            return streamResult.getOutputStream();
        } catch (Exception e)
        {
            throw e;
        }
    }

    public OutputStream translate(final URIResolver resolver,
            final StreamSource xsltStreamSource,
            final StreamSource xmlStreamSource,
            final StreamResult streamResult) throws Exception
    {
        try
        {
            //TWB - GAE needed xalan.jar and serializer.jar
            final TransformerFactory tFactory = TransformerFactory.newInstance( //"org.apache.xalan.processor.TransformerFactoryImpl",
                    //Thread.currentThread().getContextClassLoader()
                    );

            tFactory.setURIResolver(resolver);

            final Transformer transformer = tFactory.newTransformer(xsltStreamSource);

            //transformer.setURIResolver(resolver);
            transformer.transform(xmlStreamSource, streamResult);

            return streamResult.getOutputStream();
        } catch (Exception e)
        {
            throw e;
        }
    }

    public String translate(final StreamSource xsltStreamSource, final Document xmlDocument) throws Exception
    {
        try
        {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            return this.translate(xsltStreamSource,
                    new StreamSource(new StringBufferInputStream(DomDocumentHelper.toString(xmlDocument))),
                    new StreamResult(outputStream)).toString();
        } catch (Exception e)
        {
            throw e;
        }
    }

    public String translate(final StreamSource xsltStreamSource,
            final StreamSource xmlStreamSource)
            throws Exception
    {
        try
        {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            return this.translate(xsltStreamSource,
                    xmlStreamSource,
                    new StreamResult(outputStream)).toString();
        } catch (Exception e)
        {
            throw e;
        }
    }

    public String translate(
            final URIResolver resolver,
            final StreamSource xsltStreamSource,
            final StreamSource xmlStreamSource)
            throws Exception
    {
        try
        {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            return this.translate(
                    resolver, xsltStreamSource, xmlStreamSource,
                    new StreamResult(outputStream)).toString();
        } catch (Exception e)
        {
            throw e;
        }
    }

    public void export(
            final File outputFile, final String xsltFilePath, final Document xmlDocument)
            throws Exception
    {
        try
        {
            outputFile.createNewFile();

            this.translate(
                    new StreamSource(xsltFilePath),
                    new StreamSource(new StringBufferInputStream(DomDocumentHelper.toString(xmlDocument))),
                    new StreamResult(outputFile));
        } catch (Exception e)
        {
            throw e;
        }
    }
        
}
