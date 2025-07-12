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
package views.generic.inventory;
import org.allbinary.logic.string.StringMaker;


/**
 *
 * @author user
 */
public class InventorySearchLoopInfo
{
    public int lastPage = -1;
    public int currentPage = -1;
    public int numberOfResultsOnCurrentPage = 0;
    public int numberOfResults = 0;

    private final String CURRENT = "current: ";
    private final String NUM = " num: ";
    private final String LAST = " last: ";

    public String toString()
    {
            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(CURRENT);
            stringBuffer.append(currentPage);
            stringBuffer.append(NUM);
            stringBuffer.append(numberOfResults);
            stringBuffer.append(LAST);
            stringBuffer.append(lastPage);

            return stringBuffer.toString();
    }
}
