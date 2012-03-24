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
package allbinary.game;

public class GameMode
{
    public static GameMode SERVER = new GameMode("Server");
    public static GameMode CLIENT = new GameMode("Client");

    private String name;

    private GameMode(String name)
    {
        this.setName(name);
    }

    private void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public String toString()
    {
        return this.getName();
    }
}
