package org.allbinary.logic.communication.sql;

/**
 *
 * @author user
 */
public class SqlStrings
{
    private static final SqlStrings instance = new SqlStrings();

    /**
     * @return the instance
     */
    public static SqlStrings getInstance()
    {
        return instance;
    }
    
    public final String CREATE_DATABASE = "CREATE DATABASE ";
    public final String DROP_TABLE = "DROP TABLE ";
    public final String CREATE_TABLE = "CREATE TABLE ";
    public final String START = " (";
    public final String SELECT = "SELECT ";
    public final String FROM = " FROM ";
    public final String SELECT_ALL = "SELECT *";
    public final String WHERE = " WHERE ";
    public final String AND = " AND ";
    public final String UPDATE = "UPDATE ";
    public final String SET = " SET ";
    public final String SELECT_ALL_FROM = this.SELECT_ALL + this.FROM;
    
    public final String DELETE = "DELETE";
    public final String LIKE_QUOTE = " LIKE \"";
    public final String INSERT_INTO = "INSERT INTO ";
    public final String VALUES = " VALUES ('";
    
    public final String ORDER_BY = "ORDER BY";
    public final String COUNT = "COUNT(";
    public final String ASC = "ASC";
    public final String DESC = "DESC";
    
    public final String PRIMARY_KEY = "PRIMARY KEY(";
    public final String END = ") )";
    
    public final String EQUAL_QUOTE = " = \"";
    public final String CLOSE_QUOTE = "\"";
    public final String EQUAL_QUOTE_NO_SPACE = "=\"";
    
    public final String ESCAPE = "\\";
    public final String DOUBLE_ESCAPE = "\\\\";
    public final String MORE_THAN_QUOTE = " > \"";
    public final String LESS_THAN_QUOTE = " < \"";
    public final String SINGLE_QUOTE_COMMA_SEP = "','";

    public final String ID = "ID";
    
    public final String SQL_STATEMENT_LABEL = "SQL Statement: ";
    public final String COLUMN_VALUE = "\nColumn Value: ";
    public final String CREATE_RETURN = " Created Successfully";
    public final String FIELD_VALUE = "\nField Value: ";    
}
