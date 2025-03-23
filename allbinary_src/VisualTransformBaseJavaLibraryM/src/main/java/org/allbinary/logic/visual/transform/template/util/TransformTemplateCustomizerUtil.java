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
package org.allbinary.logic.visual.transform.template.util;

import org.allbinary.string.CommonSeps;
import java.util.HashMap;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.regex.replace.Replace;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.globals.GLOBALS2;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.visual.transform.info.CustomizerTransformInfoData;
import org.allbinary.logic.visual.transform.template.customizer.bodies.BodyData;
import org.allbinary.logic.visual.transform.template.customizer.widgets.title.TitleData;

public class TransformTemplateCustomizerUtil
{
    private static final TransformTemplateCustomizerUtil instance = new TransformTemplateCustomizerUtil();

    public static TransformTemplateCustomizerUtil getInstance()
    {
        return instance;
    }

    
    private final String CUSTOMIZER_NAME;
    private final String BODY_VIEWNAMEKEY;
    private final String TITLE_VIEWNAMEKEY;
    private final String GLOBALS_NEW;
    private final String GLOBALS_INSERT;
    private final String GLOBALS_DELETE;
    private final String GLOBALS_EDIT;
    private final String GLOBALS_UPDATE;

    private TransformTemplateCustomizerUtil()
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        
        final String SPACE = CommonSeps.getInstance().SPACE;

        CUSTOMIZER_NAME = SPACE + CustomizerTransformInfoData.NAME;

        BODY_VIEWNAMEKEY = SPACE + BodyData.getInstance().VIEWNAMEKEY;

        TITLE_VIEWNAMEKEY = SPACE + TitleData.getInstance().VIEWNAMEKEY;

        GLOBALS_NEW = GLOBALS2.NEW + SPACE;
        GLOBALS_INSERT = commonStrings.INSERT + SPACE;
        GLOBALS_DELETE = commonStrings.DELETE + SPACE;
        GLOBALS_EDIT = GLOBALS2.EDIT + SPACE;
        GLOBALS_UPDATE = commonStrings.UPDATE + SPACE;
    }

    public String getPageNameHack(String viewName, String storeName)
    {
        final String SPACE = CommonSeps.getInstance().SPACE;

        //Current ViewName: storeName + SPACE + nextViewPrefix + SPACE + nextPage + SPACE + nViewTypes + SPACE + CustomizerTransformInfoData.NAME
        //Convert to: nextPage

        final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;

        HashMap hashMap = new HashMap();
        //hashMap.put(selectedTemplate + SPACE, EMPTY_STRING);
        hashMap.put(viewName + SPACE, EMPTY_STRING);
        hashMap.put(storeName + SPACE, EMPTY_STRING);

        //Remove N # of Types
        hashMap.put(CUSTOMIZER_NAME, EMPTY_STRING); //Customizer
        hashMap.put(BODY_VIEWNAMEKEY, EMPTY_STRING); //Body
        hashMap.put(TITLE_VIEWNAMEKEY, EMPTY_STRING); //Title

        //Remove Prefix
        hashMap.put(GLOBALS_NEW, EMPTY_STRING);
        hashMap.put(GLOBALS_INSERT, EMPTY_STRING);
        hashMap.put(GLOBALS_DELETE, EMPTY_STRING);
        hashMap.put(GLOBALS_EDIT, EMPTY_STRING);
        hashMap.put(GLOBALS_UPDATE, EMPTY_STRING);

        Replace replace = new Replace(hashMap);

        String rootViewName = replace.all(viewName);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance("RootViewName for ObjectConfig: " + rootViewName, this, "getPageNameHack"));
        }

        return rootViewName;
    }
}
