package allbinary.game.configuration.feature;

/**
 *
 * @author user
 */
public class HTMLFeatureFactory
{
    private static final HTMLFeatureFactory instance = new HTMLFeatureFactory();

    public static HTMLFeatureFactory getInstance()
    {
        return instance;
    }

    private HTMLFeatureFactory()
    {

    }
    
    public final Feature HTML = new Feature("HTML");
}
