package org.bouncycastle.jce.provider;

import java.security.Provider;

public final class BouncyCastleProvider extends Provider
{
    private static String info = "BouncyCastle Security Provider v1.41";

    public static String PROVIDER_NAME = "BC";

    public BouncyCastleProvider()
    {
        super(PROVIDER_NAME, 1.41, info);
    }

    
    public void setParameter(String parameterName, Object parameter)
    {

    }
}
