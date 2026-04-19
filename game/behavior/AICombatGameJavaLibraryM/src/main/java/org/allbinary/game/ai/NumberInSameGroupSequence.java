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
        
        this.NUMBER_ON_SAME_TEAM_SEQUENCE_ZERO =
            smallIntegerSingletonFactory.getInstance(100);
         this.NUMBER_ON_SAME_TEAM_SEQUENCE_ONE =
            smallIntegerSingletonFactory.getInstance(101);
         this.NUMBER_ON_SAME_TEAM_SEQUENCE_TWO =
            smallIntegerSingletonFactory.getInstance(102);
         this.NUMBER_ON_SAME_TEAM_SEQUENCE_THREE =
            smallIntegerSingletonFactory.getInstance(103);
         this.NUMBER_ON_SAME_TEAM_SEQUENCE_FOUR =
            smallIntegerSingletonFactory.getInstance(104);
         this.NUMBER_ON_SAME_TEAM_SEQUENCE_FIVE =
            smallIntegerSingletonFactory.getInstance(105);
         this.NUMBER_ON_SAME_TEAM_SEQUENCE_SIX =
            smallIntegerSingletonFactory.getInstance(106);
         this.NUMBER_ON_SAME_TEAM_SEQUENCE_SEVEN =
            smallIntegerSingletonFactory.getInstance(107);
         this.NUMBER_ON_SAME_TEAM_SEQUENCE_EIGHT =
            smallIntegerSingletonFactory.getInstance(108);
         this.NUMBER_ON_SAME_TEAM_SEQUENCE_NINE =
            smallIntegerSingletonFactory.getInstance(109);
         
         this.NUMBER_ON_SAME_TEAM_SEQUENCE = new Integer[]
            {this.NUMBER_ON_SAME_TEAM_SEQUENCE_ZERO,
            this.NUMBER_ON_SAME_TEAM_SEQUENCE_ONE,
            this.NUMBER_ON_SAME_TEAM_SEQUENCE_THREE,
            this.NUMBER_ON_SAME_TEAM_SEQUENCE_FOUR,
            this.NUMBER_ON_SAME_TEAM_SEQUENCE_FIVE,
            this.NUMBER_ON_SAME_TEAM_SEQUENCE_SIX,
            this.NUMBER_ON_SAME_TEAM_SEQUENCE_SEVEN,
            this.NUMBER_ON_SAME_TEAM_SEQUENCE_EIGHT,
            this.NUMBER_ON_SAME_TEAM_SEQUENCE_NINE};
    }
}
