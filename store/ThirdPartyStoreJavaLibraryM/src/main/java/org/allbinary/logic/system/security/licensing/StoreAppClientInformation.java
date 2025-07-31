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

import org.allbinary.thirdparty.store.LongArrayIdentifierInterface;

/**
 *
 * @author user
 */
public class StoreAppClientInformation
extends AbeClientInformation
implements LongArrayIdentifierInterface
{
    private final long[] longArrayIdentifier;

    public StoreAppClientInformation(
        final String name, final String version, final String specialName, final String shortName, long[] longArrayIdentifier)
    {
        super(name, version, specialName, shortName);

        this.longArrayIdentifier = longArrayIdentifier;
    }

    /**
     * @return the longArrayIdentifier
     */
    @Override
    public long[] getLongArrayIdentifier()
    {
        return longArrayIdentifier;
    }
}
