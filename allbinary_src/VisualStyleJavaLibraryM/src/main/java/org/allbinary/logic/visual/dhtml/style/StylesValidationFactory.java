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

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.dhtml.style.css.CssElementData;
import org.allbinary.logic.visual.dhtml.style.css.CssElementsValidationFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Vector;
import org.allbinary.logic.communication.http.request.NameSpaceRequestParamData;

public class StylesValidationFactory {

    private static final StylesValidationFactory instance = new StylesValidationFactory();

    public static StylesValidationFactory getInstance() {
        return instance;
    }

    private StylesValidationFactory() {
    }

    public Vector getInstance(Document document) throws Exception {
        //Vector styles = new Vector();

        //Get all styles nodes
        NodeList nodeList = document.getElementsByTagName(StylesData.getInstance().NAME);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW)) {
            LogUtil.put(LogFactory.getInstance("Style Present: " + DomDocumentHelper.toString(document), this, "getInstance()"));
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW)) {
            LogUtil.put(LogFactory.getInstance("Styles Present: " + nodeList.getLength(),
                this, "getInstance()"));
        }

        for (int index = 0; index < nodeList.getLength(); index++) {
            Node stylesNode = nodeList.item(index);
            //Get all style nodes
            Vector styleNodeList =
                DomSearchHelper.getAllNodes(
                    StyleData.getInstance().NAME, stylesNode.getChildNodes());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW)) {
                LogUtil.put(LogFactory.getInstance("Number Of Style Nodes Present: " + styleNodeList.size(), this, "getInstance()"));
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
                    LogUtil.put(LogFactory.getInstance("Number Of Element Nodes Present: " + cssElementStyleNodeList.size(),this, "getInstance()"));
                }

                return CssElementsValidationFactory.getInstance(cssElementStyleNodeList);
            }
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW)) {
            LogUtil.put(LogFactory.getInstance("No Style Present", this, "getInstance()"));
        }

        return new Vector();
    }

    public static Vector getInstance(HashMap hashMap) throws Exception {
        //Vector styles = new Vector();

        Document stylesDocument = (Document) hashMap.get(NameSpaceRequestParamData.DOCUMENT);

        return StylesValidationFactory.getInstance().getInstance(stylesDocument);
    }
}
