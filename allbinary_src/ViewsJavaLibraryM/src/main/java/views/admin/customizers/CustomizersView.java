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
package views.admin.customizers;

import java.util.Arrays;
import java.util.Vector;

import org.allbinary.data.tables.transform.info.TransformInfoEntityBuilder;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.globals.GLOBALS2;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.sort.StringComparator;
import org.allbinary.logic.visual.transform.info.CustomizerTransformInfoData;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.template.customizer.TransformTemplateCustomizerData;
import org.allbinary.logic.visual.transform.template.customizer.TransformTemplateCustomizersData;
import org.allbinary.logic.visual.transform.template.customizer.bodies.BodyData;
import org.allbinary.logic.visual.transform.template.customizer.widgets.title.TitleData;
import org.allbinary.logic.visual.transform.template.util.TransformTemplateCustomizerUtil;
import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import views.business.context.modules.storefront.HttpStoreComponentView;

public class CustomizersView extends HttpStoreComponentView implements DomNodeInterface {
    protected final LogUtil logUtil = LogUtil.getInstance();


    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    protected final Vector customizersVector;

    public CustomizersView(TransformInfoInterface transformInfoInterface) throws Exception {
        super(transformInfoInterface);

        //String storeName = ;
        this.customizersVector
                = TransformInfoEntityBuilder.getInstance().getNames(this.getWeblisketSession().getStoreName());
    }

    public Node toXmlNode(final Document document) {
        try {
            final Node node = document.createElement(TransformTemplateCustomizersData.NAME);

            final Vector unsortedCustomizerViewVector = new Vector();

            final int size = customizersVector.size();
            for (int index = 0; index < size; index++) {
                final String viewName = (String) customizersVector.get(index);

                if (viewName.indexOf(CustomizerTransformInfoData.NAME) > 0
                        && viewName.indexOf(GLOBALS2.EDIT) > 0
                        && viewName.indexOf(BodyData.getInstance().VIEWNAMEKEY) > 0
                        && viewName.indexOf(TitleData.getInstance().VIEWNAMEKEY) > 0) //TransformTemplateCustomizerData.EDITNAMEKEY
                {
                    unsortedCustomizerViewVector.add(viewName);
                }
            }

            final Object[] objectArray = (Object[]) unsortedCustomizerViewVector.toArray();

            //String[] customizerViews = (String[]) objectArray;
            Arrays.sort(objectArray, new StringComparator());
            for (int index = 0; index < objectArray.length; index++) {
                final String viewName = (String) objectArray[index];

                final Node viewNameNode
                        = ModDomHelper.createNameValueNodes(
                                document, TransformTemplateCustomizerData.NAME, viewName,
                                TransformTemplateCustomizerUtil.getInstance().getPageNameHack(
                                        viewName,
                                        this.getWeblisketSession().getStoreName()));
                //+ " " + TransformInfoObjectConfigData.BODY

                node.appendChild(viewNameNode);
            }

            return node;
        } catch (Exception e) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XSLLOGGINGERROR)) {
                logUtil.put(this.commonStrings.FAILURE, this, "toXmlNode", e);
            }
            return null;
        }
    }

    public void addDomNodeInterfaces() {
        this.addDomNodeInterface((DomNodeInterface) this);
    }

    public String view() throws Exception {
        try {
            this.addDomNodeInterfaces();
            return super.view();
        } catch (Exception e) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR)) {
                logUtil.put(commonStrings.EXCEPTION, this, "view()", e);
            }
            throw e;
        }
    }
}
