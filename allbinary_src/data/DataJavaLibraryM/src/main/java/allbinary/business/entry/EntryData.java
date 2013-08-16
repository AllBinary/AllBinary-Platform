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
package allbinary.business.entry;

public class EntryData
{
    private static final EntryData instance = new EntryData();

    public static EntryData getInstance()
    {
        return instance;
    }

    private EntryData()
    {
    }
    public final String ID = "ENTRY_ID";
    public final String ENCRYPTION = "ENTRY_ENCRYPTION";
    public final String TIMECREATED = "ENTRY_TIMECREATED";
    public final String LASTMODIFIED = "ENTRY_LASTMODIFIED";
    public final String DEFAULT = "ENTRY_DEFAULT";
    public final String SPECIAL = "ENTRY_SPECIAL";
    public final String ENABLE = "ENTRY_ENABLE";

    public final String YES = "Yes";
    public final String NO = "No";
}
