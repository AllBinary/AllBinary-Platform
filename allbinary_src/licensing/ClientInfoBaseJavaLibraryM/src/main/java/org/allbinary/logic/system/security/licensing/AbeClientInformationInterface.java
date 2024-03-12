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

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.system.os.OperatingSystemInterface;

public interface AbeClientInformationInterface
{
    void init();

    OperatingSystemInterface getOperatingSystemInterface();

    String getName();

    String getSpecialName();

    String getVersion();

    String getLicenseId();

    BasicArrayList getLicenseServers();

    String getLicenseServer(int index);

    int getNumberOfLicenseServers();

    Hashtable toHashtable();

    boolean isSameId(String alicenseId);

    boolean isLargerOrDifferentServerList(BasicArrayList vector);

    boolean isHardSale();
    
    String toShortString();
}