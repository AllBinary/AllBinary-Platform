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
package abcs.business.installer;

import java.util.HashMap;

public class Portion
{

    private final Integer current;// = Integer.valueOf(0);
    private final Integer total;// = Integer.valueOf(1);

    public Portion(HashMap hashMap)
    {
        this.current = (Integer) hashMap.get("current");
        this.total = (Integer) hashMap.get("total");
    }

    public Integer getCurrent()
    {
        return current;
    }

    public Integer getTotal()
    {
        return total;
    }
}
