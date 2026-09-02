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

import java.util.Calendar;
import java.util.HashMap;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.business.user.commerce.money.MoneyException;
import org.allbinary.data.generator.ProductIdGenerator;
import org.allbinary.logic.StdUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonPhoneStrings;
import org.allbinary.string.CommonStrings;

public class BasicItem implements ItemInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final BasicItemData basicItemData = BasicItemData.getInstance();
    
    protected String itemId;
    protected String number;
    protected String inBaskets;
    protected String weight;
    protected String enabled;
    protected String newOrUsed;
    protected String summary;
    protected String distributor;
    protected String idUsedByDistributor;
    protected String producedBy;
    protected String productionDate;
    protected String startProductionDate;
    protected String description;
    protected String keywords;
    protected String category;
    protected String type;
    protected String smallImage;
    protected String mediumImage;
    protected String largeImage;
    protected Money price;
    protected String comment;
    protected String customs;
    private String downloads;
    protected String groups;
    protected String options;
    protected String permissions;
    protected String specials;
    private boolean downloadable;
    protected String timeEntered;
    protected String lastModified;

    public BasicItem(HashMap itemHashMap) throws MoneyException
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
        {
            this.logUtil.putF(this.commonStrings.START, this,"Constructor(HashMap)");
        }

        this.itemId = (String) itemHashMap.get(basicItemData.ID);

        this.number = (String) itemHashMap.get(basicItemData.NUMBER);

        /*
         * String numberString = (String) itemHashMap.get(basicItemData.NUMBER);
         * if(!StringValidationUtil.isEmpty(numberString)) { this.number = new
         * Long(0).valueOf(numberString); } else { this.number }
         */

        this.inBaskets = (String) itemHashMap.get(basicItemData.INBASKETS);
        this.weight = (String) itemHashMap.get(basicItemData.WEIGHT);
        this.enabled = (String) itemHashMap.get(EntryData.getInstance().ENABLE);
        this.newOrUsed = (String) itemHashMap.get(basicItemData.NEWORUSED);
        this.summary = (String) itemHashMap.get(basicItemData.SUMMARY);
        this.distributor = (String) itemHashMap.get(basicItemData.DISTRIBUTOR);
        this.idUsedByDistributor = (String) itemHashMap.get(basicItemData.IDUSEDBYDISTRIBUTOR);
        this.producedBy = (String) itemHashMap.get(basicItemData.PRODUCEDBY);
        this.productionDate = (String) itemHashMap.get(basicItemData.PRODUCTIONDATE);
        this.startProductionDate = (String) itemHashMap.get(basicItemData.STARTPRODUCTIONDATE);
        this.description = (String) itemHashMap.get(basicItemData.DESCRIPTION);
        this.keywords = (String) itemHashMap.get(basicItemData.KEYWORDS);
        this.category = (String) itemHashMap.get(basicItemData.CATEGORY);
        this.type = (String) itemHashMap.get(basicItemData.TYPE);
        this.smallImage = (String) itemHashMap.get(basicItemData.SMALLIMAGE);
        this.mediumImage = (String) itemHashMap.get(basicItemData.MEDIUMIMAGE);
        this.largeImage = (String) itemHashMap.get(basicItemData.LARGEIMAGE);
        this.timeEntered = (String) itemHashMap.get(EntryData.getInstance().TIMECREATED);
        this.lastModified = (String) itemHashMap.get(EntryData.getInstance().LASTMODIFIED);

        this.price = new Money((String) itemHashMap.get(basicItemData.PRICE));

        this.comment = (String) itemHashMap.get(basicItemData.COMMENT);
        this.customs = (String) itemHashMap.get(basicItemData.CUSTOMS);
        this.setDownloads((String) itemHashMap.get(basicItemData.DOWNLOADS));
        this.groups = (String) itemHashMap.get(basicItemData.GROUPS);
        this.options = (String) itemHashMap.get(basicItemData.OPTIONS);
        this.permissions = (String) itemHashMap.get(basicItemData.PERMISSIONS);
        this.specials = (String) itemHashMap.get(basicItemData.SPECIALS);
    }

    public BasicItem() throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
        {
            this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
        }

        String EMPTY = StringUtil.getInstance().EMPTY_STRING;
        this.itemId = new ProductIdGenerator().getNext();
        this.number = CommonPhoneStrings.getInstance().ZERO;
        this.inBaskets = EMPTY;
        this.weight = EMPTY;
        this.enabled = EMPTY;
        this.newOrUsed = EMPTY;
        this.summary = EMPTY;
        this.distributor = EMPTY;
        this.idUsedByDistributor = EMPTY;
        this.producedBy = EMPTY;
        this.productionDate = EMPTY;
        this.startProductionDate = EMPTY;
        this.description = EMPTY;
        this.keywords = EMPTY;
        this.category = EMPTY;
        this.type = EMPTY;
        this.smallImage = EMPTY;
        this.mediumImage = EMPTY;
        this.largeImage = EMPTY;
        this.timeEntered = EMPTY;
        this.lastModified = EMPTY;

        this.price = new Money();
        this.comment = EMPTY;
        this.customs = EMPTY;
        this.setDownloads(EMPTY);
        this.groups = EMPTY;
        this.options = EMPTY;
        this.permissions = EMPTY;
        this.specials = EMPTY;
    }

    public void setId(String itemId)
    {
        this.itemId = itemId;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public void setInBaskets(String value)
    {
        this.inBaskets = value;
    }

    public void setWeight(String value)
    {
        this.weight = value;
    }

    public void setNewOrUsed(String value)
    {
        this.newOrUsed = value;
    }

    public void setSummary(String value)
    {
        this.summary = value;
    }

    public void setDistributor(String value)
    {
        this.distributor = value;
    }

    public void setIdUsedByDistributor(String value)
    {
        this.idUsedByDistributor = value;
    }

    public void setProducedBy(String value)
    {
        this.producedBy = value;
    }

    public void setProductionDate(String value)
    {
        this.productionDate = value;
    }

    public void setStartProductionDate(String value)
    {
        this.startProductionDate = value;
    }

    public void setDescription(String value)
    {
        this.description = value;
    }

    public void setKeywords(String value)
    {
        this.keywords = value;
    }

    public void setCategory(String value)
    {
        this.category = value;
    }

    public void setType(String value)
    {
        this.type = value;
    }

    public void setSmallImage(String value)
    {
        this.smallImage = value;
    }

    public void setMediumImage(String value)
    {
        this.mediumImage = value;
    }

    public void setLargeImage(String value)
    {
        this.largeImage = value;
    }

    public void setTimeEntered(String value)
    {
        this.timeEntered = value;
    }

    public void setLastModified(String value)
    {
        this.lastModified = value;
    }

    public void setPrice(Money value)
    {
        this.price = value;
    }

    public void setComment(String value)
    {
        this.comment = value;
    }

    public void setCustoms(String value)
    {
        this.customs = value;
    }

    public void setDownloads(String value)
    {
        this.downloads = value;

        if (!StringValidationUtil.getInstance().isEmpty(this.downloads))
        {
            Integer downloadInteger = Integer.valueOf(this.downloads);
            if (downloadInteger.intValue() != 0)
            {
                this.setDownloadable(true);
            }
        }
    }

    public void setGroups(String value)
    {
        this.groups = value;
    }

    public void setOptions(String value)
    {
        this.options = value;
    }

    public void setPermissions(String value)
    {
        this.permissions = value;
    }

    public void setSpecials(String value)
    {
        this.specials = value;
    }

    public void setEnabled(String value)
    {
        this.enabled = value;
    }

    public String getId()
    {
        return this.itemId;
    }

    public String getNumber()
    {
        return this.number;
    }

    public String getInBaskets()
    {
        return this.inBaskets;
    }

    public String getWeight()
    {
        return this.weight;
    }

    public String getNewOrUsed()
    {
        return this.newOrUsed;
    }

    public String getSummary()
    {
        return this.summary;
    }

    public String getDistributor()
    {
        return this.distributor;
    }

    public String getIdUsedByDistributor()
    {
        return this.idUsedByDistributor;
    }

    public String getProducedBy()
    {
        return this.producedBy;
    }

    public String getProductionDate()
    {
        return this.productionDate;
    }

    public String getStartProductionDate()
    {
        return this.startProductionDate;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getKeywords()
    {
        return this.keywords;
    }

    public String getCategory()
    {
        return this.category;
    }

    public String getType()
    {
        return this.type;
    }

    public String getSmallImage()
    {
        return this.smallImage;
    }

    public String getMediumImage()
    {
        return this.mediumImage;
    }

    public String getLargeImage()
    {
        return this.largeImage;
    }

    public String getTimeEntered()
    {
        return this.timeEntered;
    }

    public String getLastModified()
    {
        return this.lastModified;
    }

    public Money getPrice()
    {
        return this.price;
    }

    public String getComment()
    {
        return this.comment;
    }

    public String getCustoms()
    {
        return this.customs;
    }

    public String getDownloads()
    {
        return this.downloads;
    }

    public String getGroups()
    {
        return this.groups;
    }

    public String getOptions()
    {
        return this.options;
    }

    public String getPermissions()
    {
        return this.permissions;
    }

    public String getSpecials()
    {
        return this.specials;
    }

    public void setDownloadable(boolean downloadable)
    {
        this.downloadable = downloadable;
    }

    public boolean isDownloadable()
    {
        return this.downloadable;
    }

    public String getEnabled()
    {
        return this.enabled;
    }

    public Money getTotal()
    {
        // add = total is number * items price
        Money itemTotal = new Money(this.price);
        itemTotal.multiply(new Integer(this.number).intValue());
        return itemTotal;
    }

    public HashMap toHashMap()
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
        {
            this.logUtil.putF(this.commonStrings.START, this, "toHashMap");
        }

        HashMap hashMap = StdUtil.getInstance().createHashMap();

        hashMap.put(basicItemData.ID, this.itemId);
        hashMap.put(basicItemData.NUMBER, this.number);
        hashMap.put(basicItemData.INBASKETS, this.inBaskets);
        hashMap.put(basicItemData.WEIGHT, this.weight);
        hashMap.put(EntryData.getInstance().ENABLE, this.enabled);
        hashMap.put(basicItemData.NEWORUSED, this.newOrUsed);
        hashMap.put(basicItemData.SUMMARY, this.summary);
        hashMap.put(basicItemData.DISTRIBUTOR, this.distributor);
        hashMap.put(basicItemData.IDUSEDBYDISTRIBUTOR,
            this.idUsedByDistributor);
        hashMap.put(basicItemData.PRODUCEDBY, this.producedBy);
        hashMap.put(basicItemData.PRODUCTIONDATE, this.productionDate);
        hashMap.put(basicItemData.STARTPRODUCTIONDATE,
            this.startProductionDate);
        hashMap.put(basicItemData.DESCRIPTION, this.description);
        hashMap.put(basicItemData.KEYWORDS, this.keywords);
        hashMap.put(basicItemData.CATEGORY, this.category);
        hashMap.put(basicItemData.TYPE, this.type);
        // hashMap.put(basicItemData.IMAGE,EMPTY);

        hashMap.put(basicItemData.SMALLIMAGE, this.smallImage);
        hashMap.put(basicItemData.MEDIUMIMAGE, this.mediumImage);
        hashMap.put(basicItemData.LARGEIMAGE, this.largeImage);

        // hashMap.put(EntryData.getInstance().TIMECREATED,this.timeEntered);

        // update time
        Calendar calendar = Calendar.getInstance();
        String time = new Long(calendar.getTimeInMillis()).toString();
        hashMap.put(EntryData.getInstance().LASTMODIFIED, time);

        // if(this.price.isValid())
        hashMap.put(basicItemData.PRICE, this.price.toString());

        hashMap.put(basicItemData.COMMENT, this.comment);
        hashMap.put(basicItemData.CUSTOMS, this.customs);
        hashMap.put(basicItemData.DOWNLOADS, this.getDownloads());
        hashMap.put(basicItemData.GROUPS, this.groups);
        hashMap.put(basicItemData.OPTIONS, this.options);
        hashMap.put(basicItemData.PERMISSIONS, this.permissions);
        hashMap.put(basicItemData.SPECIALS, this.specials);

        return hashMap;
    }

    public BasicArrayList toVector()
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
        {
            this.logUtil.putF(this.commonStrings.START, this, "toVector");
        }

        BasicArrayList values = new BasicArrayListD();
        values.add(this.itemId);
        values.add(this.number);
        values.add(this.inBaskets);
        values.add(this.weight);
        values.add(this.enabled);
        values.add(this.newOrUsed);
        values.add(this.summary);
        values.add(this.distributor);
        values.add(this.idUsedByDistributor);
        values.add(this.producedBy);
        values.add(this.productionDate);
        values.add(this.startProductionDate);
        values.add(this.description);
        values.add(this.keywords);
        values.add(this.category);
        values.add(this.type);

        values.add(this.getSmallImage());
        values.add(this.getMediumImage());
        values.add(this.getLargeImage());

        Calendar calendar = Calendar.getInstance();
        String time = new Long(calendar.getTimeInMillis()).toString();
        values.add(time);
        values.add(time);

        values.add(this.price.toString());

        values.add(this.comment);

        values.add(this.customs);
        values.add(this.getDownloads());
        values.add(this.groups);
        values.add(this.options);
        values.add(this.permissions);
        values.add(this.specials);

        return values;
    }

    public Object getKey() throws Exception
    {
        return (Object) this.getId();
    }
}
