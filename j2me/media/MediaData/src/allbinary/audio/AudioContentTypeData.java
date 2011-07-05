/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: June 4, 2006, 9:15 AM
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.audio;

public class AudioContentTypeData
{
    private final String name;

    protected AudioContentTypeData(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
