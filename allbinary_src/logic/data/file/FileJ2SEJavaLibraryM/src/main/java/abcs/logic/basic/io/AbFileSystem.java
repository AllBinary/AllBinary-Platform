/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abcs.logic.basic.io;

/**
 *
 * @author user
 */
public class AbFileSystem {

    private static final AbFileSystem instance = new AbFileSystem();

    /**
     * @return the instance
     */
    public static AbFileSystem getInstance()
    {
        return instance;
    }

    private final String type = "java.io";

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    public boolean isType(String type)
    {
        if(this.type.compareTo(type) == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
