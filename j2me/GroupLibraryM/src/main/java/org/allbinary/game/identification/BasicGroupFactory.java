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
package org.allbinary.game.identification;

public class BasicGroupFactory
{
    private static final BasicGroupFactory instance = new BasicGroupFactory();
    
    public static BasicGroupFactory getInstance()
    {
        return instance;
    }
    
    public final String NAME = "GROUP_NAME";
    public final String NO_SUCH_GROUP = "No Such Group: ";

    public final Group GOOD = new Group("Good Guys", (short) 0);
    public final Group ENEMY = new Group("Bad Guys", (short) 1);
    public final Group ENEMY_ON_LEVEL = new Group("Bad Guys on Level", (short) 2);
    public final Group NONE = new Group("Not On A Team", (short) 3);

    public final Group[] GOOD_ARRAY = {this.GOOD};
    public final Group[] ENEMY_ARRAY = {this.ENEMY};
    public final Group[] ENEMY_ON_LEVEL_ARRAY = {this.ENEMY, this.ENEMY_ON_LEVEL};

    public final Group[] NONE_ARRAY = {this.NONE};

}
