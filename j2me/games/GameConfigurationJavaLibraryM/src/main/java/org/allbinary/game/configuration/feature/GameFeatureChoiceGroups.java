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

public class GameFeatureChoiceGroups {

	private static GameFeatureChoiceGroups gameFeatureChoiceGroupsExclusive = 
		new GameFeatureChoiceGroups();
	private static GameFeatureChoiceGroups gameFeatureChoiceGroupsMultiple = 
		new GameFeatureChoiceGroups();
	
	private Hashtable hashtable  = new Hashtable();
	
	private GameFeatureChoiceGroups()
	{
		
	}
	
	public static GameFeatureChoiceGroups getExclusiveInstance()
	{
		return gameFeatureChoiceGroupsExclusive;
	}

	public static GameFeatureChoiceGroups getMultipleInstance()
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
