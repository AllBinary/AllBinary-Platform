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
package org.allbinary.business.user.commerce.inventory.item.download;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.data.generator.ProductIdGenerator;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.time.TimeUtil;

public class DownloadableItem
{

    private final String id;
    private final String basicItemId;
    private final String enabled;
    private final String system;
    private final String platform;
    private final String specialName;
    private final String version;
    private final String changes;
    private final String licenseFile;
    private final String file;
    private final Long size;
    private final Long validTime;
    private final String retries;
    //private final String timeEntered;
    //private final String lastModified;

    public DownloadableItem(String id) throws Exception
    {
        final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;

        this.id = new ProductIdGenerator().getNext();

        this.basicItemId = id;

        this.enabled = EntryData.getInstance().YES;

        this.system = "Java";

        this.platform = "Android";

        this.specialName = EMPTY_STRING;

        this.version = EMPTY_STRING;

        this.changes = EMPTY_STRING;

        this.licenseFile = EMPTY_STRING;

        this.file = EMPTY_STRING;

        this.size = Long.valueOf(0);

        this.validTime = Long.valueOf(0);

        this.retries = EMPTY_STRING;
    }

    public DownloadableItem(HashMap hashMap)
    {
        this.id = (String) hashMap.get(DownloadItemData.ID);

        this.basicItemId = (String) hashMap.get(BasicItemData.ID);

        this.enabled = (String) hashMap.get(EntryData.getInstance().ENABLE);

        this.system = (String) hashMap.get(DownloadItemData.SYSTEM);

        this.platform = (String) hashMap.get(DownloadItemData.PLATFORM);

        this.specialName = (String) hashMap.get(DownloadItemData.SPECIAL_NAME);

        this.version = (String) hashMap.get(DownloadItemData.VERSION);

        this.changes = (String) hashMap.get(DownloadItemData.CHANGES);

        this.licenseFile = (String) hashMap.get(DownloadItemData.LICENSE_FILE);

        this.file = (String) hashMap.get(DownloadItemData.FILE);

        String value = (String) hashMap.get(DownloadItemData.SIZE);
        
        if (!StringValidationUtil.getInstance().isEmpty(value))
        {
            this.size = Long.valueOf(value);
        } else
        {
            this.size = new Long(0);
        }

        String validTime = (String) hashMap.get(DownloadItemData.VALID_TIME);

        if (!StringValidationUtil.getInstance().isEmpty(validTime))
        {
            this.validTime = Long.valueOf(validTime);
        } else
        {
            String totalYears = (String) hashMap.get(DownloadItemData.VALID_TIME_YEARS);
            String totalMonths = (String) hashMap.get(DownloadItemData.VALID_TIME_MONTHS);
            String totalDays = (String) hashMap.get(DownloadItemData.VALID_TIME_DAYS);
            String totalHours = (String) hashMap.get(DownloadItemData.VALID_TIME_HOURS);
            String totalMinutes = (String) hashMap.get(DownloadItemData.VALID_TIME_MINUTES);
            String totalSeconds = (String) hashMap.get(DownloadItemData.VALID_TIME_SECONDS);

            long totalTime = TimeUtil.getInstance().getTotalTime(
                totalYears, totalMonths, totalDays,
                totalHours, totalMinutes, totalSeconds);

            this.validTime = new Long(totalTime);
        }

        this.retries = (String) hashMap.get(DownloadItemData.RETRIES);

        //this.timeEntered = itemInterface.getTimeEntered();
        //this.lastModified = itemInterface.getLastModified();
    }

    public Vector toVector()
    {
        Calendar calendar = Calendar.getInstance();
        String time = new Long(calendar.getTimeInMillis()).toString();
        Vector values = new Vector();

        values.add(getId());

        values.add(this.basicItemId);

        values.add(this.enabled);

        values.add(this.getSpecialName());

        values.add(this.getVersion());

        values.add(this.getChanges());

        values.add(this.getSystem());

        values.add(this.getPlatform());

        values.add(this.getLicenseFile());
        values.add(this.getFile());
        values.add(this.getSize().toString());
        values.add(this.getValidTime().toString());
        values.add(this.getRetries());

        values.add(time);
        values.add(time);

        return values;
    }

    public HashMap toHashMap()
    {
        HashMap values = new HashMap();

        values.put(DownloadItemData.ID, this.getId());

        values.put(BasicItemData.ID, this.basicItemId);

        values.put(EntryData.getInstance().ENABLE, this.enabled);

        values.put(DownloadItemData.SYSTEM, this.getSystem());

        values.put(DownloadItemData.PLATFORM, this.getPlatform());

        values.put(DownloadItemData.SPECIAL_NAME, this.getSpecialName());

        values.put(DownloadItemData.VERSION, this.getVersion());

        values.put(DownloadItemData.CHANGES, this.getChanges());

        values.put(DownloadItemData.LICENSE_FILE, this.getLicenseFile());
        values.put(DownloadItemData.FILE, this.getFile());
        values.put(DownloadItemData.SIZE, this.getSize().toString());

        values.put(DownloadItemData.VALID_TIME, this.getValidTime().toString());

        values.put(DownloadItemData.RETRIES, this.getRetries());

        Calendar calendar = Calendar.getInstance();
        String time = new String(new Long(calendar.getTimeInMillis()).toString());

        values.put(EntryData.getInstance().LASTMODIFIED, time);

        return values;
    }

    public String getId()
    {
        return id;
    }

    /**
     * @return the system
     */
    public String getSystem()
    {
        return system;
    }

    /**
     * @return the platform
     */
    public String getPlatform()
    {
        return platform;
    }

    /**
     * @return the specialName
     */
    public String getSpecialName()
    {
        return specialName;
    }

    /**
     * @return the version
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * @return the changes
     */
    public String getChanges()
    {
        return changes;
    }

    /**
     * @return the licenseFile
     */
    public String getLicenseFile()
    {
        return licenseFile;
    }

    /**
     * @return the file
     */
    public String getFile()
    {
        return file;
    }

    /**
     * @return the size
     */
    public Long getSize()
    {
        return size;
    }

    /**
     * @return the validTime
     */
    public Long getValidTime()
    {
        return validTime;
    }

    /**
     * @return the retries
     */
    public String getRetries()
    {
        return retries;
    }
}
