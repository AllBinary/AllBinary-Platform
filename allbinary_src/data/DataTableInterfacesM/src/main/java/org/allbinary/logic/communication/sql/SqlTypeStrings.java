package org.allbinary.logic.communication.sql;

import org.allbinary.string.CommonSeps;

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
    public final String INT_COLUMN = "VARCHAR";
    public final String MAX_SIZE = "(255)";
    public final String SIXTY_SIZE = "(60)";
    public final String NOT_NULL = "NOT NULL";
    
    public final String ONE_SIZE = "(1)";
    public final String TWO_SIZE = "(2)";
    public final String THREE_SIZE = "(3)";
    public final String SIX_SIZE = "(6)";
    public final String ELEVEN_SIZE = "(11)";
    public final String TWELVE_SIZE = "(12)";
    
    public final String ONE_KB_CHAR_COLUMN = new StringBuilder()
            .append(CommonSeps.getInstance().SPACE)
            .append(CHAR_COLUMN)
            .append("(1024)")
            .append(CommonSeps.getInstance().COMMA_SEP)
            .toString();

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
    
    public final String SIXTY_CHAR_COLUMN_NOT_NULL = new StringBuilder()
            .append(CommonSeps.getInstance().SPACE)
            .append(CHAR_COLUMN)
            .append(SIXTY_SIZE)
            .append(NOT_NULL)
            .append(CommonSeps.getInstance().COMMA_SEP)
            .toString();

    public final String ONE_CHAR_COLUMN_NOT_NULL = new StringBuilder()
            .append(CommonSeps.getInstance().SPACE)
            .append(CHAR_COLUMN)
            .append(ONE_SIZE)
            .append(NOT_NULL)
            .append(CommonSeps.getInstance().COMMA_SEP)
            .toString();

    public final String SIX_CHAR_COLUMN_NOT_NULL = new StringBuilder()
            .append(CommonSeps.getInstance().SPACE)
            .append(CHAR_COLUMN)
            .append(SIX_SIZE)
            .append(NOT_NULL)
            .append(CommonSeps.getInstance().COMMA_SEP)
            .toString();
    
    public final String TWELVE_CHAR_COLUMN_NOT_NULL = new StringBuilder()
            .append(CommonSeps.getInstance().SPACE)
            .append(CHAR_COLUMN)
            .append(this.TWELVE_SIZE)
            .append(NOT_NULL)
            .append(CommonSeps.getInstance().COMMA_SEP)
            .toString();

    public final String TWO_INT_NOT_NULL = " INT(2) NOT NULL, ";
    public final String THREE_INT_NOT_NULL = " INT(3) NOT NULL, ";
    public final String FOUR_INT_NOT_NULL = " INT(4) NOT NULL, ";
    public final String MAX_INT_NOT_NULL = " INT(11) NOT NULL, ";
    public final String MAX_INT_UNSIGNED = " INT(11) UNSIGNED, ";
    public final String MAX_INT_UNSIGNED_NOT_NULL = " INT(11) UNSIGNED NOT NULL, ";
    
    public final String MAX_BIG_INT_NOT_NULL = " BIGINT(19) NOT NULL, ";
    public final String MAX_BIG_INT_UNSIGNED = " BIGINT(19) UNSIGNED, ";
    public final String MAX_BIG_INT_UNSIGNED_NOT_NULL = " BIGINT(19) UNSIGNED NOT NULL, ";
    public final String MAX_BIG_INT_UNSIGNED_AUTO_INCREMENT_NOT_NULL = " BIGINT(19) UNSIGNED AUTO_INCREMENT NOT NULL,";
    
    public final String LONG_BLOB = " LONGBLOB, ";
    public final String BLOB = " BLOB, ";
    public final String BLOB_NOT_NULL = " BLOB NOT NULL, ";
}
