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

import java.util.HashMap;

import org.allbinary.globals.GLOBALS2;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.regex.replace.Replace;
import org.allbinary.logic.visual.transform.info.CustomizerTransformInfoData;
import org.allbinary.logic.visual.transform.template.customizer.bodies.BodyData;
import org.allbinary.logic.visual.transform.template.customizer.widgets.title.TitleData;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;

public class TransformTemplateCustomizerUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final TransformTemplateCustomizerUtil instance = new TransformTemplateCustomizerUtil();

    public static TransformTemplateCustomizerUtil getInstance()
    {
        return TransformTemplateCustomizerUtil.instance;
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

        this.CUSTOMIZER_NAME = SPACE + CustomizerTransformInfoData.NAME;

        this.BODY_VIEWNAMEKEY = SPACE + BodyData.getInstance().VIEWNAMEKEY;

        this.TITLE_VIEWNAMEKEY = SPACE + TitleData.getInstance().VIEWNAMEKEY;

        this.GLOBALS_NEW = GLOBALS2.NEW + SPACE;
        this.GLOBALS_INSERT = commonStrings.INSERT + SPACE;
        this.GLOBALS_DELETE = commonStrings.DELETE + SPACE;
        this.GLOBALS_EDIT = GLOBALS2.EDIT + SPACE;
        this.GLOBALS_UPDATE = commonStrings.UPDATE + SPACE;
    }

    public String getPageNameHack(String viewName, String storeName)
    {
        final String SPACE = CommonSeps.getInstance().SPACE;

        //Current ViewName: storeName + SPACE + nextViewPrefix + SPACE + nextPage + SPACE + nViewTypes + SPACE + CustomizerTransformInfoData.NAME
        //Convert to: nextPage

        final String[] ONE_EMPTY_STRING_ARRAY = StringUtil.getInstance().ONE_EMPTY_STRING_ARRAY;

        HashMap hashMap = new HashMap();
        //hashMap.put(selectedTemplate + SPACE, EMPTY_STRING);
        hashMap.put(new String[] {viewName + SPACE}, ONE_EMPTY_STRING_ARRAY);
        hashMap.put(new String[] {storeName + SPACE}, ONE_EMPTY_STRING_ARRAY);

        //Remove N # of Types
        hashMap.put(new String[] {CUSTOMIZER_NAME}, ONE_EMPTY_STRING_ARRAY); //Customizer
        hashMap.put(new String[] {BODY_VIEWNAMEKEY}, ONE_EMPTY_STRING_ARRAY); //Body
        hashMap.put(new String[] {TITLE_VIEWNAMEKEY}, ONE_EMPTY_STRING_ARRAY); //Title

        //Remove Prefix
        hashMap.put(new String[] {GLOBALS_NEW}, ONE_EMPTY_STRING_ARRAY);
        hashMap.put(new String[] {this.GLOBALS_INSERT}, ONE_EMPTY_STRING_ARRAY);
        hashMap.put(new String[] {this.GLOBALS_DELETE}, ONE_EMPTY_STRING_ARRAY);
        hashMap.put(new String[] {this.GLOBALS_EDIT}, ONE_EMPTY_STRING_ARRAY);
        hashMap.put(new String[] {this.GLOBALS_UPDATE}, ONE_EMPTY_STRING_ARRAY);

        Replace replace = null; //new Replace(hashMap);

        String rootViewName = replace.all(viewName);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            this.logUtil.putF("RootViewName for ObjectConfig: " + rootViewName, this, "getPageNameHack");
        }

        return rootViewName;
    }
}
