/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public final String PRIMARY_KEY = "PRIMARY KEY(";
    public final String END = ") )";
    
    public final String EQUAL_QUOTE = " = \"";
    public final String CLOSE_QUOTE = "\"";
    public final String EQUAL_QUOTE_NO_SPACE = "=\"";
    
    public final String SQL_STATEMENT_LABEL = "SQL Statement: ";
    public final String COLUMN_VALUE = "\nColumn Value: ";
    public final String CREATE_RETURN = " Created Successfully";
    public final String FIELD_VALUE = "\nField Value: ";
}
