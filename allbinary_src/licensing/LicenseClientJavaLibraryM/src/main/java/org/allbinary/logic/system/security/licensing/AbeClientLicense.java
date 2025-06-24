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
package org.allbinary.logic.system.security.licensing;

import java.util.Hashtable;
import java.util.Vector;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;

public class AbeClientLicense implements AbeLicenseInterface
{
    private Hashtable hashtable;
    private String id;
    private BasicArrayList servers;
    private String special;
    private LicenseType licenseType;

    public AbeClientLicense(Hashtable hashtable)
    {
        this.hashtable = new Hashtable();

        AbeClientInformationData abeClientInformationData = 
            AbeClientInformationData.getInstance();

        Object keyValue = hashtable.get(abeClientInformationData.KEY);
        if (keyValue != null)
        {
            if (keyValue instanceof String)
            {
                this.hashtable.put(abeClientInformationData.KEY, keyValue);
            }
        }

        /*
         * Set set = hashtable.keySet(); iter = set;
         * while(iter.hasNext()) { String nextKey = (String) iter.next(); Object
         * keyValue = hashtable.get(nextKey);
         * 
         * if(keyValue instanceof String) { //this.hashtable.put(new
         * String(nextKey),new String(keyValue)); this.hashtable.put(nextKey,
         * keyValue); } }
         */

        this.id = (String) hashtable.get(
                abeClientInformationData.LICENSEID);
        
        Vector vector = (Vector)
            hashtable.get(abeClientInformationData.LICENSESERVERS);
        
        this.servers = new BasicArrayList();
        
        int size = vector.size();
        for(int index = 0; index < size; index++)
        {
            this.servers.add(vector.get(index));
        }
        
        final StringUtil stringUtil = StringUtil.getInstance();
        
        this.setSpecial(stringUtil.getInstance((String) hashtable
                .get(abeClientInformationData.SPECIAL)));

        String licenseTypeString = 
            stringUtil.getInstance((String) hashtable
                .get(abeClientInformationData.LICENSE_TYPE));
        this.licenseType = LicenseTypeFactory.getInstance().getInstance(licenseTypeString);
    }

    // Check to see if xml from server makes sense with what is needed
    // Should only fail is server is not working correctly
    public static boolean hasRequiredKeys(Hashtable resultHashtable)
    {
        AbeClientInformationData abeClientInformationData = 
            AbeClientInformationData.getInstance();
        
        if (resultHashtable.containsKey(abeClientInformationData.LICENSEID)
                && resultHashtable
                        .containsKey(abeClientInformationData.LICENSESERVERS)
                && resultHashtable.containsKey(abeClientInformationData.KEY)
                && resultHashtable.containsKey(abeClientInformationData.LICENSE_TYPE))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean hasKey()
    {
        return this.isValid();
    }

    public String getKey(String keyName)
    {
        return (String) hashtable.get(keyName);
    }

    public String getLicenseId()
    {
        return this.id;
    }

    public BasicArrayList getServers()
    {
        return this.servers;
    }

    public boolean isValid()
    {
        final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if (!stringValidationUtil.isEmpty(
                this.getKey(AbeClientInformationData.getInstance().KEY)))
        {
            return true;
        }
        return false;
    }

    /*
     * public boolean isNew() { String isNew = (String)
     * keyHashtable.get(AbeClientInformation.NEWLICENSEKEY);
     * 
     * if(isNew.compareTo(AbeClientInformation.ISNEW)==0) return true; else
     * return false; }
     */
    /*
     * public void setKey(String keyType, String keyValue) {
     * this.keyHashtable.put(keyType,keyValue); }
     */

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        // getKey(String keyName);
        
        final String BREAK = "<br/>";
        
        stringBuffer.append("License Id: ");
        stringBuffer.append(this.getLicenseId());
        stringBuffer.append(BREAK);
        
        stringBuffer.append("Is Valid: ");
        stringBuffer.append(this.isValid());
        stringBuffer.append(BREAK);

        stringBuffer.append("Keys: ");
        stringBuffer.append(this.hashtable.toString());
        stringBuffer.append(BREAK);

        BasicArrayList serverVector = this.getServers();
            
        int size = serverVector.size();
        for(int index = 0; index < size; index++)
        {
            String nextServerString = (String) serverVector.get(index);
            stringBuffer.append("Server: ");
            stringBuffer.append(nextServerString);
            stringBuffer.append(BREAK);
        }
        return stringBuffer.toString();
    }

    private void setSpecial(String special)
    {
        this.special = special;
    }

    public String getSpecial()
    {
        return special;
    }

    public LicenseType getLicenseType()
    {
        return licenseType;
    }
}
