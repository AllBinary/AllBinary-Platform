package abcs.logic.system.security.licensing;

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import abcs.logic.system.os.OperatingSystemInterface;

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
}