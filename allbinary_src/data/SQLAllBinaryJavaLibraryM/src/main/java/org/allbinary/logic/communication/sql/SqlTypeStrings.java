/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.allbinary.logic.communication.sql;

import org.allbinary.logic.basic.string.CommonSeps;

/**
 *
 * @author user
 */
public class SqlTypeStrings
{
    private static final SqlTypeStrings instance = new SqlTypeStrings();

    /**
     * @return the instance
     */
    public static SqlTypeStrings getInstance()
    {
        return instance;
    }
    
    public final String CHAR_COLUMN = "VARCHAR";
    public final String MAX_SIZE = "(255)";
    public final String NOT_NULL = "NOT NULL";
    
    public final String MAX_CHAR_COLUMN = new StringBuilder()
            .append(CommonSeps.getInstance().SPACE)
            .append(CHAR_COLUMN)
            .append(MAX_SIZE)
            .append(CommonSeps.getInstance().COMMA_SEP)
            .toString();

    public final String MAX_CHAR_COLUMN_NOT_NULL = new StringBuilder()
            .append(CommonSeps.getInstance().SPACE)
            .append(CHAR_COLUMN)
            .append(MAX_SIZE)
            .append(CommonSeps.getInstance().SPACE)
            .append(NOT_NULL)
            .append(CommonSeps.getInstance().COMMA_SEP)
            .toString();
    
    public final String MAX_INT_NOT_NULL = " INT(11) NOT NULL, ";
    public final String MAX_INT_UNSIGNED = " INT(11) UNSIGNED, ";
    public final String MAX_INT_UNSIGNED_NOT_NULL = " INT(11) UNSIGNED NOT NULL, ";
    
    public final String MAX_BIG_INT_UNSIGNED = " BIGINT(19) UNSIGNED, ";
    public final String MAX_BIG_INT_NOT_NULL = " BIGINT(19) UNSIGNED NOT NULL, ";
    public final String MAX_BIG_INT_UNSIGNED_NOT_NULL = " BIGINT(19) UNSIGNED NOT NULL, ";
    
    public final String BLOB_NOT_NULL = " BLOB NOT NULL, ";
}
