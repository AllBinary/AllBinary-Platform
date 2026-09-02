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
package org.allbinary.business.user.commerce.inventory.item;

public class BasicItemData {

    private static final BasicItemData instance = new BasicItemData();

    /**
     * @return the instance
     */
    public static BasicItemData getInstance() {
        return instance;
    }

    public final String ITEM = "BASICITEM";

    public final String TOTAL = "BASICITEM_TOTAL";

    public final String ID = "BASICITEM_ID";
    public final String NUMBER = "BASICITEM_NUMBER";

    public final String INBASKETS = "BASICITEM_IN_BASKETS";
    public final String WEIGHT = "BASICITEM_WEIGHT";

    public final String NEWORUSED = "BASICITEM_NEW_OR_USED";
    public final String SUMMARY = "BASICITEM_SUMMARY";
    public final String DISTRIBUTOR = "BASICITEM_DISTRIBUTOR";
    public final String IDUSEDBYDISTRIBUTOR = "BASICITEM_IDUSEDBYDISTRIBUTOR";
    public final String PRODUCEDBY = "BASICITEM_PRODUCEDBY";
    public final String PRODUCTIONDATE = "BASICITEM_PRODUCTIONDATE";
    public final String STARTPRODUCTIONDATE = "BASICITEM_STARTPRODUCTIONDATE";
    public final String DESCRIPTION = "BASICITEM_DESCRIPTION";
    public final String KEYWORDS = "BASICITEM_KEYWORDS";
    public final String CATEGORY = "BASICITEM_CATEGORY";
    public final String TYPE = "BASICITEM_TYPE";

    public final String IMAGE = "BASICITEM_IMG";
    public final String SMALLIMAGE = "BASICITEM_SMALL_IMG";
    public final String MEDIUMIMAGE = "BASICITEM_MEDIUM_IMG";
    public final String LARGEIMAGE = "BASICITEM_LARGE_IMG";

    public final String PRICE = "BASICITEM_PRICE";

    public final String COMMENT = "BASICITEM_COMMENT";

    public final String CUSTOMS = "BASICITEM_CUSTOMS";
    public final String DOWNLOADS = "BASICITEM_DOWNLOADS";
    public final String GROUPS = "BASICITEM_GROUPS";
    public final String OPTIONS = "BASICITEM_OPTIONS";
    public final String PERMISSIONS = "BASICITEM_PERMISSIONS";
    public final String SPECIALS = "BASICITEM_SPECIALS";
}
