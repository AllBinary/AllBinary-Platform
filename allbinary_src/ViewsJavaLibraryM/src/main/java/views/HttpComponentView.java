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
package views;


import java.util.Vector;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;
import org.allbinary.logic.visual.transform.BasicTransformer;
import org.allbinary.logic.visual.transform.TransformInterface;
import org.allbinary.logic.visual.transform.data.TransformDocumentInterface;
import org.allbinary.logic.visual.transform.data.TransformHttpRequestDocumentFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpComposite;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class HttpComponentView extends TransformInfoHttpComposite
    implements TransformInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();
    
    private Vector domNodeInterfaceVector;
    private TransformDocumentInterface transformDocumentInterface;

    public HttpComponentView(TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        super(transformInfoInterface);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("View Name: " + transformInfoInterface.getName(), this, "ComponentView()");
        }

        this.domNodeInterfaceVector = new Vector();

        this.setTransformDocumentInterface(
            TransformHttpRequestDocumentFactory.getInstance(
            this.getPageContext(), this.getWeblisketSession()));
    }

    public int NO_TYPE = 0;
    
    public int getTypeId()
    {
 	   return NO_TYPE;
    }

    public TransformDocumentInterface getTransformDocumentInterface()
    {
        return transformDocumentInterface;
    }

    public void setTransformDocumentInterface(TransformDocumentInterface transformDocumentInterface)
    {
        this.transformDocumentInterface = transformDocumentInterface;
    }

    public void addDomNodeInterface(DomNodeInterface domNodeInterface)
    {
        this.domNodeInterfaceVector.add(domNodeInterface);
    }

    public Document toXmlDoc() throws Exception
    {
        try
        {
            final int size = domNodeInterfaceVector.size();
            for(int index = 0; index < size; index++)
            {
                DomNodeInterface domNodeInterface = (DomNodeInterface) domNodeInterfaceVector.get(index);
                this.transformDocumentInterface.getBaseNode().appendChild(
                    domNodeInterface.toXmlNode(this.transformDocumentInterface.getDoc()));
            }
            return this.getTransformDocumentInterface().getDoc();
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "toXmlDoc()", e);
            }
            throw e;
        }
    }

    public Document getDoc() throws Exception
    {
        Document document = this.getTransformInfoInterface().getDataDocument();

        Node node = DomNodeHelper.getFirstChildElement(document);

        if (node != null)
        {
            Node dataNode = this.getTransformDocumentInterface().getDoc().importNode(
                node, true);

            if (dataNode != null)
            {
                this.getTransformDocumentInterface().getBaseNode().appendChild(dataNode);
            }
        }

        return this.getTransformDocumentInterface().getDoc();
    }

    public String view() throws Exception
    {
        try
        {

            this.toXmlDoc();
            String success = DomDocumentHelper.toString(this.getDoc());

            String result = new BasicTransformer(this.abeClientInformation,
                this.getTransformInfoInterface()).translate(success);

            return result;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "view()", e);
            }
            throw e;
        }
    }
}
