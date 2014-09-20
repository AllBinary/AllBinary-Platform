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
package org.allbinary.game.ai;

import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class NumberInSameGroupSequence
{
    public static NumberInSameGroupSequence getInstance()
    {
        return new NumberInSameGroupSequence();
    }

    public Integer NUMBER_ON_SAME_TEAM_SEQUENCE_ZERO;
    public Integer NUMBER_ON_SAME_TEAM_SEQUENCE_ONE;
    public Integer NUMBER_ON_SAME_TEAM_SEQUENCE_TWO;
    public Integer NUMBER_ON_SAME_TEAM_SEQUENCE_THREE;
    public Integer NUMBER_ON_SAME_TEAM_SEQUENCE_FOUR;
    public Integer NUMBER_ON_SAME_TEAM_SEQUENCE_FIVE;
    public Integer NUMBER_ON_SAME_TEAM_SEQUENCE_SIX;
    public Integer NUMBER_ON_SAME_TEAM_SEQUENCE_SEVEN;
    public Integer NUMBER_ON_SAME_TEAM_SEQUENCE_EIGHT;
    public Integer NUMBER_ON_SAME_TEAM_SEQUENCE_NINE;

    public Integer[] NUMBER_ON_SAME_TEAM_SEQUENCE;
     
    private NumberInSameGroupSequence()
    {
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        NUMBER_ON_SAME_TEAM_SEQUENCE_ZERO =
            smallIntegerSingletonFactory.getInstance(100);
         NUMBER_ON_SAME_TEAM_SEQUENCE_ONE =
            smallIntegerSingletonFactory.getInstance(101);
         NUMBER_ON_SAME_TEAM_SEQUENCE_TWO =
            smallIntegerSingletonFactory.getInstance(102);
         NUMBER_ON_SAME_TEAM_SEQUENCE_THREE =
            smallIntegerSingletonFactory.getInstance(103);
         NUMBER_ON_SAME_TEAM_SEQUENCE_FOUR =
            smallIntegerSingletonFactory.getInstance(104);
         NUMBER_ON_SAME_TEAM_SEQUENCE_FIVE =
            smallIntegerSingletonFactory.getInstance(105);
         NUMBER_ON_SAME_TEAM_SEQUENCE_SIX =
            smallIntegerSingletonFactory.getInstance(106);
         NUMBER_ON_SAME_TEAM_SEQUENCE_SEVEN =
            smallIntegerSingletonFactory.getInstance(107);
         NUMBER_ON_SAME_TEAM_SEQUENCE_EIGHT =
            smallIntegerSingletonFactory.getInstance(108);
         NUMBER_ON_SAME_TEAM_SEQUENCE_NINE =
            smallIntegerSingletonFactory.getInstance(109);
         
         NUMBER_ON_SAME_TEAM_SEQUENCE = new Integer[]
            {NUMBER_ON_SAME_TEAM_SEQUENCE_ZERO,
            NUMBER_ON_SAME_TEAM_SEQUENCE_ONE,
            NUMBER_ON_SAME_TEAM_SEQUENCE_THREE,
            NUMBER_ON_SAME_TEAM_SEQUENCE_FOUR,
            NUMBER_ON_SAME_TEAM_SEQUENCE_FIVE,
            NUMBER_ON_SAME_TEAM_SEQUENCE_SIX,
            NUMBER_ON_SAME_TEAM_SEQUENCE_SEVEN,
            NUMBER_ON_SAME_TEAM_SEQUENCE_EIGHT,
            NUMBER_ON_SAME_TEAM_SEQUENCE_NINE};
    }
}
