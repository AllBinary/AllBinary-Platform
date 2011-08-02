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
package abcs.logic.communication.log.config.type;

public class LogConfigType
{
    private String name;
    private String description;

    //public static BasicArrayList availableLogConfigTypes = new BasicArrayList();
    public LogConfigType(String name, String description)
    {
        this.name = name;
        this.description = description;
        //availableLogConfigTypes.add(this);
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setName(String value)
    {
        this.name = value;
    }

    public void setDescription(String value)
    {
        this.description = value;
    }
}
