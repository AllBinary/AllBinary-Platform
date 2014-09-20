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
package org.allbinary.game.configuration.feature;

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

public class InGameFeatureChoiceGroups
{
    private static InGameFeatureChoiceGroups gameFeatureChoiceGroupsExclusive =
        new InGameFeatureChoiceGroups();
    private static InGameFeatureChoiceGroups gameFeatureChoiceGroupsMultiple =
        new InGameFeatureChoiceGroups();
    private Hashtable hashtable = new Hashtable();

    private InGameFeatureChoiceGroups()
    {
    }

    public static InGameFeatureChoiceGroups getExclusiveInstance()
    {
        return gameFeatureChoiceGroupsExclusive;
    }

    public static InGameFeatureChoiceGroups getMultipleInstance()
    {
        return gameFeatureChoiceGroupsMultiple;
    }

    public Hashtable get()
    {
        return hashtable;
    }

    public void add(String name, BasicArrayList list)
    {
        hashtable.put(name, list);
    }
}
