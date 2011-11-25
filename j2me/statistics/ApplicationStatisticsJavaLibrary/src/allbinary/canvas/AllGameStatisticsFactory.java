package allbinary.canvas;

public class AllGameStatisticsFactory
{
    private static final AllGameStatisticsFactory instance = new AllGameStatisticsFactory();

    public static AllGameStatisticsFactory getInstance()
    {
        return instance;
    }
    
    private StringBuilder stringBuffer = new StringBuilder();
    
    public void add(String string)
    {
        if(this.stringBuffer.length() > 12000)
        {
            this.stringBuffer.delete(0, this.stringBuffer.length());
            this.stringBuffer.append("Old Stats Cleared");
        }
        this.stringBuffer.append(string);
    }
    
    public String toString()
    {
        return this.stringBuffer.toString();
    }
}
