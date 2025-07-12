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
package org.allbinary.graphics;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;

public class CellPositionFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private static CellPositionFactory SINGLETON =
      new CellPositionFactory();

   public final CellPosition NONE = new CellPosition(-1, -1, -1, -1);
   
   private CellPosition[][] cellPositionArray;
   private int columns;
   private int rows;

   public static CellPositionFactory getInstance()
   {
      return SINGLETON;
   }

   private CellPositionFactory()
   {
   }

   public void init(int columns, int rows)
      
   {
      this.cellPositionArray =
         new CellPosition[columns][rows];

      this.columns = columns;
      this.rows = rows;

      final CommonStrings commonStrings = CommonStrings.getInstance();
      final StringMaker stringBuffer = new StringMaker();
      
      stringBuffer.append("Init: columns: ");
      stringBuffer.append(columns);
      stringBuffer.append(" rows: ");
      stringBuffer.append(rows);
      
      logUtil.put(stringBuffer.toString(), this, commonStrings.INIT);
      
      for (int column = 0; column < columns; column++)
      {
         // logUtil.put("Initializing Column: ").append(col, this, commonStrings.INIT);
         for (int row = 0; row < rows; row++)
         {
            // logUtil.put("Initializing Row: ").append(row, this, commonStrings.INIT);
            //CellPosition cellPosition =
               this.createInstance(column, row);
         }
      }
   }

   public CellPosition getInstance(int i_column, int i_row)
   {
      try
      {
         return cellPositionArray[i_column][i_row];
      }
      catch (Exception e)
      {
          final CommonStrings commonStrings = CommonStrings.getInstance();
         logUtil.put(new StringMaker().append("columns: ").append(this.getColumns()).append(" rows: ").append(this.getRows()).append(" col: ").append(i_column).append(" row: ").append(i_row).toString(), this, commonStrings.GET_INSTANCE, e);
         return null;
      }
   }

   public CellPosition createInstance(
      int i_column, int i_row)
      
   {

      CellPosition cellPosition =
         cellPositionArray[i_column][i_row];

      if (cellPosition == null)
      {
         cellPosition = new CellPosition(i_column, i_row, this.columns, this.rows);

         cellPositionArray[i_column][i_row] = cellPosition;
      }

      return cellPosition;
   }

   public int getColumns()
   {
      return columns;
   }

   public int getRows()
   {
      return rows;
   }
}
