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

import org.allbinary.graph.V;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;

public class CellPosition
extends V
{
   //TWB - SmallIntegerSingletonFactory may not be needed
   private final int id;
   private final int column;
   private final int row;

   public CellPosition(int column, int row, int columns, int rows)
   {
      this.column = column;
      this.row = row;

      this.id = (row * columns) + column; 
          //SmallIntegerSingletonFactory.getInstance((row * columns) + column);
   }

   public int getColumn()
   {
      return this.column;
   }

   /*
   public void moveColumns(int column)
   {
      this.column += column;
   }
   */
   
   public int getRow()
   {
      return this.row;
   }

   /*
   public void moveRows(int row)
   {
      this.row += row;
   }
   */

   public String toString()
   {
      return CellPosition.toStringColRow(this.getColumn(), this.getRow());
   }

   public static String toStringCellPosition(CellPosition basicGeographicMapCellPosition)
   {
      return toStringColRow(basicGeographicMapCellPosition.getColumn(), basicGeographicMapCellPosition.getRow());
   }
   
   public static String toStringColRow(int i_column, int i_row)
   {
      //return "Column: " + i_column + " Row: " + i_row;
      
      StringMaker stringBuffer = new StringMaker();

      CommonSeps commonSeps = CommonSeps.getInstance();
      
      stringBuffer.append(commonSeps.PARENTHESIS_OPEN);
      stringBuffer.appendint(i_column);
      stringBuffer.append(commonSeps.COMMA);
      stringBuffer.appendint(i_row);
      stringBuffer.append(commonSeps.PARENTHESIS_CLOSE);
      
      return stringBuffer.toString();
   }

   @Override
   public int getId()
   {
      return this.id;
   }
}
