package allbinary.game.identification;

public class BasicGroupFactory
{
    private static final BasicGroupFactory instance = new BasicGroupFactory();
    
    public static BasicGroupFactory getInstance()
    {
        return instance;
    }
    
    public final String NAME = "GROUP_NAME";
    public final String NO_SUCH_GROUP = "No Such Group: ";

    public final Group GOOD = new Group("Good Guys", 0);
    public final Group ENEMY = new Group("Bad Guys", 1);
    public final Group NONE = new Group("Not On A Team", 2);

}
