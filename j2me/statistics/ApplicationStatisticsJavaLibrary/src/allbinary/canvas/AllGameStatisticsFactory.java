package allbinary.canvas;

import abcs.logic.basic.string.StringMaker;

public class AllGameStatisticsFactory
{
    private static final AllGameStatisticsFactory instance = new AllGameStatisticsFactory();

    public static AllGameStatisticsFactory getInstance()
    {
        return instance;
    }
    
    private StringMaker stringBuffer = new StringMaker();
    
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
