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

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.io.path.AbFilePath;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpStoreInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.template.TransformInfoTemplateData;

public class StoreUriResolver implements URIResolver {

    protected final LogUtil logUtil = LogUtil.getInstance();

    private final URIResolverStrings uriResolverStrings = URIResolverStrings.getInstance();

    private BasicUriResolver basicURIResolver;

    private TransformInfoInterface parentTransformInfoInterface;

    public StoreUriResolver(TransformInfoInterface parentTransformInfoInterface, BasicUriResolver basicURIResolver) {
        this.parentTransformInfoInterface = parentTransformInfoInterface;
        this.basicURIResolver = basicURIResolver;
    }

    public Source resolve(String href, String base) throws TransformerException {
        try {
            final StringMaker stringBuffer = new StringMaker();

            final TransformInfoHttpStoreInterface transformInfoHttpStoreInterface =
                (TransformInfoHttpStoreInterface) this.parentTransformInfoInterface;

            stringBuffer.append(URLGLOBALS.getMainPath()).append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH)
                .append(transformInfoHttpStoreInterface.getStoreName())
                .append(AbPathData.getInstance().SEPARATOR).append(href);

            final AbPath fileAbPath = (AbPath) new AbFilePath(
                stringBuffer.toString());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XMLLOGGING)) {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(this.uriResolverStrings.ATTEMPT);
                stringBuffer.append(href);
                stringBuffer.append(this.uriResolverStrings.BASE);
                stringBuffer.append(base);
                stringBuffer.append(this.uriResolverStrings.NEW_PATH);
                stringBuffer.append(fileAbPath.toString());
                stringBuffer.append(this.uriResolverStrings.NOTE);
                stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
                stringBuffer.append(this.uriResolverStrings.URL_GLOBAL);
                stringBuffer.append(this.uriResolverStrings.REQUIRED_EXTENSION);
                stringBuffer.append(this.basicURIResolver.getExtension());

                this.logUtil.putF(stringBuffer.toString(), this, this.uriResolverStrings.RESOLVE);
            }

            return new StreamSource(new CryptFileReader(
                TransformInfoTemplateData.getInstance().UNCRYPTED_EXTENSION,
                TransformInfoTemplateData.getInstance().ENCRYPTED_EXTENSION).getInputStream(fileAbPath));
        } catch (TransformerException e) {
            throw e;
        } catch (Exception e) {
            throw new TransformerException(e);
        }
    }

    public String toString() {
        try {
            return URLGLOBALS.getMainPath() + FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH
                + "{Store Name if Any}/{import url}";
        } catch (Exception e) {
            //Log Error
            return "StoreUriResolver - Does not work without webapp path should be changed";
        }
    }
}
