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
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.allbinary.data.tree.dom.document.XmlDocumentHelper;

import org.w3c.dom.Document;

//import org.allbinary.data.tree.dom.document.DomDocumentHelper;
public class XslHelper
{

    public static final class Processor {
        public static final String QUIXSLT_FACTORY_PROPERTY = "allbinary.xslt.quixslt.factory";

        //TWB - GAE needed xalan.jar and serializer.jar
        public static final Processor GAE = new Processor("GAE", "org.apache.xalan.processor.TransformerFactoryImpl");
        public static final Processor JAXP = new Processor("JAXP", TransformerFactory::newInstance);
        public static final Processor JDK_XSLTC = named("JDK_XSLTC",
            "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
        public static final Processor SAXON = named("SAXON", "net.sf.saxon.TransformerFactoryImpl");
        public static final Processor XALAN = named("XALAN", "org.apache.xalan.xsltc.trax.TransformerFactoryImpl");
        public static final Processor QUIXSLT = new Processor("QUIXSLT", Processor::newQuiXsltFactory);

        private final String name;
        private final Supplier<TransformerFactory> factory;

        private Processor(final String name, final Supplier<TransformerFactory> factory) {
            this.name = name;
            this.factory = factory;
        }

        private static Processor named(final String name, final String factoryClass) {
            return new Processor(name, () -> loadFactory(factoryClass));
        }

        private static TransformerFactory loadFactory(final String factoryClass) {
            return TransformerFactory.newInstance(factoryClass, Thread.currentThread().getContextClassLoader());
        }

        private static TransformerFactory newQuiXsltFactory() {
            final String factoryClass = System.getProperty(QUIXSLT_FACTORY_PROPERTY, "").trim();
            if (factoryClass.isEmpty()) {
                throw new TransformerFactoryConfigurationError(
                    "QuiXSLT requires a JAXP adapter on the classpath. Set " + QUIXSLT_FACTORY_PROPERTY
                    + " to its TransformerFactory class name.");
            }
            return loadFactory(factoryClass);
        }

        /** Creates a factory for this operation without changing the shared selection. */
        public TransformerFactory newTransformerFactory() {
            return this.factory.get();
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
    private volatile Processor processor = Processor.JAXP;

    public Processor getProcessor() {
        return this.processor;
    }

    /** Selects the processor for later operations that use this helper. */
    public void setProcessor(final Processor processor) {
        this.processor = Objects.requireNonNull(processor);
    }

    public TransformerFactory newTransformerFactory() {
        return this.processor.newTransformerFactory();
    }

    /** Caches a single stylesheet and recompiles it when the selected processor changes. */
    public static final class TemplatesCache {
        private final URIResolver resolver;
        private Processor processor;
        private Templates templates;

        public TemplatesCache(final URIResolver resolver) {
            this.resolver = resolver;
        }

        public synchronized Templates get(final Callable<? extends Source> stylesheet) throws Exception {
            final Processor selected = XslHelper.getInstance().getProcessor();
            if (this.templates == null || this.processor != selected) {
                final TransformerFactory factory = selected.newTransformerFactory();
                factory.setURIResolver(this.resolver);
                this.templates = factory.newTemplates(stylesheet.call());
                this.processor = selected;
            }
            return this.templates;
        }
    }
    private static final XslHelper instance = new XslHelper();

    /**
     * @return the instance
     */
    public static XslHelper getInstance()
    {
        return XslHelper.instance;
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
            final TransformerFactory transformerFactory = this.newTransformerFactory();

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
            final TransformerFactory tFactory = this.newTransformerFactory();

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
    
    public String translateTemplate(final Templates templates,
            final StreamSource xmlStreamSource) throws Exception
    {
        try
        {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            final StreamResult streamResult = new StreamResult(outputStream);
            templates.newTransformer().transform(xmlStreamSource, streamResult);
            return streamResult.getOutputStream().toString();
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
                    new StreamSource(new StringBufferInputStream(XmlDocumentHelper.toString(xmlDocument))),
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
                    new StreamSource(new StringBufferInputStream(XmlDocumentHelper.toString(xmlDocument))),
                    new StreamResult(outputFile));
        } catch (Exception e)
        {
            throw e;
        }
    }
        
}
