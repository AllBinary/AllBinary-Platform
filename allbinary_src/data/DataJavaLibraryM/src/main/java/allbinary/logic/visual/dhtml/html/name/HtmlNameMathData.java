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
package allbinary.logic.visual.dhtml.html.name;

public class HtmlNameMathData
{
	private static final HtmlNameMathData instance = new HtmlNameMathData();
	
	   public static HtmlNameMathData getInstance() {
			return instance;
		}
	
   private HtmlNameMathData()
   {
   }
   
   public String EQUALS = "_EQUALS_";
   public String PLUS = "_PLUS_";
   public String MINUS = "_MINUS_";
   public String DIVIDE = "_DIVIDE_";
   public String MULTIPLY = "_MULTIPLY_";
}
