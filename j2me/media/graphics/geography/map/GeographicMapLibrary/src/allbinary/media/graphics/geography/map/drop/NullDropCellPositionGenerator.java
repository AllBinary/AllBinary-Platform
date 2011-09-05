package allbinary.media.graphics.geography.map.drop;

public class NullDropCellPositionGenerator
extends BaseDropCellPositionGenerator
{
    private static final NullDropCellPositionGenerator instance = 
            new NullDropCellPositionGenerator();

    public static NullDropCellPositionGenerator getInstance()
    {
        return instance;
    }
    
}
