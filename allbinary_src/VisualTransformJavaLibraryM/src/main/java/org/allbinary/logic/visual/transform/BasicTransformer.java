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
package org.allbinary.logic.visual.transform;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.transform.URIResolver;

import org.allbinary.logic.io.AbFileInputStream;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.AbKeys;
import org.allbinary.data.tree.dom.BasicUriResolver;
import org.allbinary.logic.control.crypt.jcehelper.AbCrypt;
import org.allbinary.logic.control.crypt.jcehelper.KeySpecFactory;
import org.allbinary.logic.io.path.PathUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.template.TransformInfoTemplateData;

public class BasicTransformer extends AbTransformer {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final StreamUtil streamUtil = StreamUtil.getInstance();

    //private InputStream inputStream;
    private final AbeClientInformationInterface abeClientInformation;

    public BasicTransformer(final AbeClientInformationInterface abeClientInformation, final TransformInfoInterface transformInfoInterface) throws Exception {
        super(transformInfoInterface);

        this.abeClientInformation = abeClientInformation;

        this.setTemplateAsInputStream();
    }

    //private InputStream getEncryptedTemplateFileAsInputStream(File file)
    private void setEncryptedTemplateFileAsInputStream(final AbFile file) throws Exception {
        ByteArrayOutputStream outputStream = null;
        AbFileInputStream inputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            inputStream = new AbFileInputStream(file);

            outputStream = (ByteArrayOutputStream) this.streamUtil.get(inputStream, outputStream, new byte[16384]);

            final AbCrypt abCrypt = new AbCrypt(KeySpecFactory.DESEDE, AbKeys.getInstance().getKey(abeClientInformation, file.getAbsolutePath()));
            final byte[] decrypted = abCrypt.decrypt(outputStream.toByteArray());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                logUtil.put(
                    "Decrypted Template: \n" + new String(decrypted),
                    this, "setEncryptedTemplateFileAsInputStream(file)");
            }

            this.setInputStream((InputStream) new ByteArrayInputStream(decrypted));
            this.setURIResolver((URIResolver) new BasicUriResolver(
                TransformInfoTemplateData.getInstance().ENCRYPTED_EXTENSION));
        } catch (Exception e) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR)) {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Failed to get Encrypted File: ");
                stringBuffer.append(file.getPath());
                stringBuffer.append(" \nName:");
                stringBuffer.append(file.getName());

                logUtil.put(stringBuffer.toString(), this, "setEncryptedTemplateFileAsInputStream()", e);
            }
            throw e;
        } finally {
            this.streamUtil.close(outputStream);
            this.streamUtil.close(inputStream);
        }
    }

    private void setEncryptedTemplateAsInputStream() throws Exception {
        ByteArrayOutputStream outputStream = null;
        ByteArrayInputStream inputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            inputStream = new ByteArrayInputStream(
                this.getTransformInfoInterface().getTemplate().getBytes());

            outputStream = (ByteArrayOutputStream) this.streamUtil.get(inputStream, outputStream, new byte[16384]);

            final AbCrypt abCrypt = new AbCrypt(KeySpecFactory.DESEDE, AbKeys.getInstance().getKey(abeClientInformation, this.getTransformInfoInterface().getName()));

            final byte[] decrypted = abCrypt.decrypt(outputStream.toByteArray());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                logUtil.put(
                    "Decrypted Template: \n" + decrypted.toString(), this, "setEncryptedTemplateAsInputStream()");
            }

            this.setInputStream((InputStream) new ByteArrayInputStream(decrypted));
            this.setURIResolver((URIResolver) new BasicUriResolver(TransformInfoTemplateData.getInstance().ENCRYPTED_EXTENSION));
        } catch (Exception e) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR)) {
                logUtil.put(
                    "Failed to set with template: "
                    + this.getTransformInfoInterface().getTemplateFilePath(),
                    this, "setEncryptedTemplateFileAsInputStream()", e);
            }
            throw e;
        } finally {
            this.streamUtil.close(outputStream);
            this.streamUtil.close(inputStream);
        }
    }

    //If Templatefilename is provided then load the file and return it as a input stream
    //otherwise return the Template as an inputstream
    private void setTemplateAsInputStream() throws Exception {
        AbFileInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;

        try {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                logUtil.put(
                    this.getTransformInfoInterface().log(), this, "setTemplateAsInputStream()");
            }

            final TransformInfoTemplateData transformInfoTemplateData =
                TransformInfoTemplateData.getInstance();

            final PathUtil pathUtil = PathUtil.getInstance();

            if (this.getTransformInfoInterface().getTemplateFilePath() != null) {
                final String extension = pathUtil.getExtension(
                    this.getTransformInfoInterface().getTemplateFilePath());
                final String filePath = pathUtil.getWithoutExtension(
                    this.getTransformInfoInterface().getTemplateFilePath());

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                    logUtil.put(
                        this.getTransformInfoInterface().log(), this, "setTemplateAsInputStream()");
                }

                if (extension.compareTo(transformInfoTemplateData.UNCRYPTED_EXTENSION) == 0) {
                    //attempt to load an encrypted version

                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append(filePath);
                    stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
                    stringBuffer.append(transformInfoTemplateData.ENCRYPTED_EXTENSION);

                    final AbFile encFile = new AbFile(stringBuffer.toString());

                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                        logUtil.put(
                            this.getTransformInfoInterface().log(), this, "setTemplateAsInputStream()");
                    }

                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                        stringBuffer = new StringBuffer();
                        //stringBuffer.delete(0, stringBuffer.length());

                        stringBuffer.append("Encrypted Template File isFile=");
                        stringBuffer.append(encFile.isFile());
                        stringBuffer.append("\nEncTemplateFilePath: ");
                        stringBuffer.append(encFile.getPath());

                        logUtil.put(
                            stringBuffer.toString(), this, "setTemplateAsInputStream()");
                    }

                    if (encFile.isFile()) {
                        this.setEncryptedTemplateFileAsInputStream(encFile);
                        return;
                    } else {
                        final AbFile file =
                            new AbFile(this.getTransformInfoInterface().getTemplateFilePath());

                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                            logUtil.put(
                                this.getTransformInfoInterface().log(), this, "setTemplateAsInputStream()");
                        }

                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                            logUtil.put(
                                "Template File isFile=" + file.isFile(), this, "setTemplateAsInputStream()");
                        }

                        if (file.isFile()) {
                            inputStream = new AbFileInputStream(file);
                            outputStream = new ByteArrayOutputStream();

                            outputStream = (ByteArrayOutputStream) this.streamUtil.get(inputStream, outputStream, new byte[16384]);

                            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                                logUtil.put(
                                    "Template: " + outputStream.toString(), this, "getTemplateAsInputStream()");
                            }

                            this.setInputStream((InputStream) new ByteArrayInputStream(outputStream.toByteArray()));

                            this.setURIResolver((URIResolver) new BasicUriResolver(
                                transformInfoTemplateData.UNCRYPTED_EXTENSION));
                            return;
                        }
                    }
                } else if (extension.compareTo(transformInfoTemplateData.ENCRYPTED_EXTENSION) == 0) {
                    final StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append(filePath);
                    stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
                    stringBuffer.append(transformInfoTemplateData.ENCRYPTED_EXTENSION);

                    final AbFile file = new AbFile(stringBuffer.toString());

                    if (file.isFile()) {
                        this.setEncryptedTemplateFileAsInputStream(file);
                    }
                } else {
                    throw new Exception(
                        "View Template File Type Is Not Recognized: "
                        + this.getTransformInfoInterface().getTemplateFilePath());
                }
            } else {
                this.setEncryptedTemplateAsInputStream();
            }
        } catch (Exception e) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR)) {
                logUtil.put(
                    "Failed to get Template Data", this, "setTemplateAsInputStream()", e);
            }
            throw e;
        } finally {
            this.streamUtil.close(outputStream);
            this.streamUtil.close(inputStream);
        }
        throw new Exception("Error setTemplateAsInputStream()");
    }
}
