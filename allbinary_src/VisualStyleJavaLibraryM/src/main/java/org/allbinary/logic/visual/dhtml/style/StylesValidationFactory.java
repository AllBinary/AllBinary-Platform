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
package org.allbinary.logic.visual.dhtml.style;

import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.dhtml.style.css.CssElementData;
import org.allbinary.logic.visual.dhtml.style.css.CssElementsValidationFactory;
import org.allbinary.logic.communication.http.request.NameSpaceRequestParamData;
import org.allbinary.string.CommonStrings;

public class StylesValidationFactory {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final StylesValidationFactory instance = new StylesValidationFactory();

    public static StylesValidationFactory getInstance() {
        return instance;
    }

    private StylesValidationFactory() {
    }

    public Vector getInstance(Document document) throws Exception {

        final CommonStrings commonStrings = CommonStrings.getInstance();

        //Vector styles = new Vector();

        //Get all styles nodes
        NodeList nodeList = document.getElementsByTagName(StylesData.getInstance().NAME);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW)) {
            logUtil.put("Style Present: " + DomDocumentHelper.toString(document), this, commonStrings.GET_INSTANCE);
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW)) {
            logUtil.put("Styles Present: " + nodeList.getLength(),
                this, commonStrings.GET_INSTANCE);
        }

        for (int index = 0; index < nodeList.getLength(); index++) {
            Node stylesNode = nodeList.item(index);
            //Get all style nodes
            Vector styleNodeList =
                DomSearchHelper.getAllNodes(
                    StyleData.getInstance().NAME, stylesNode.getChildNodes());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW)) {
                logUtil.put("Number Of Style Nodes Present: " + styleNodeList.size(), this, commonStrings.GET_INSTANCE);
            }

            for (int styleNodesIndex = 0; styleNodesIndex < styleNodeList.size(); styleNodesIndex++) {
                //Nodes with StyleData.NAME
                Node styleNode = (Node) styleNodeList.get(styleNodesIndex);

                //Nodes with CssElementData.NAME
                Vector cssElementStyleNodeList =
                    DomSearchHelper.getAllNodes(
                        CssElementData.getInstance().NAME, styleNode.getChildNodes());

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                    org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW)) {
                    logUtil.put("Number Of Element Nodes Present: " + cssElementStyleNodeList.size(),this, commonStrings.GET_INSTANCE);
                }

                return CssElementsValidationFactory.getInstance(cssElementStyleNodeList);
            }
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW)) {
            logUtil.put("No Style Present", this, commonStrings.GET_INSTANCE);
        }

        return new Vector();
    }

    public static Vector getInstance(HashMap hashMap) throws Exception {
        //Vector styles = new Vector();

        Document stylesDocument = (Document) hashMap.get(NameSpaceRequestParamData.DOCUMENT);

        return StylesValidationFactory.getInstance().getInstance(stylesDocument);
    }
}
