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
package org.allbinary.logic.visual.dhtml.html;

import java.util.Vector;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.visual.dhtml.html.input.HtmlInput;
import org.allbinary.logic.visual.dhtml.html.table.HtmlTable;

public class HtmlForm
{
   private final String FORMBEGIN = "<form ";
   private final String END = ">";
   private final String FORMEND = "</form>";
   private final String ACTION = "ACTION=\"";
   private final String GET = "GET\"";
   private final String POST = "POST\"";
   private final String[] METHODS = {this.GET,this.POST};   
   private final String METHOD = "METHOD=\"";
   //private final String ENCTYPE
   
   private String method;
   private String action;
   Vector inputs;
   
   public HtmlForm(String action)
   {
      this.action = action;
      this.inputs = new Vector();
   }

   public void setPost()
   {
      this.method = this.POST;
   }

   public void setGet()
   {
      this.method = this.GET;
   }

   public void addInput(HtmlInput htmlInput)
   {
      this.inputs.add(htmlInput);
   }

   public void addTable(HtmlTable htmlTable)
   {
      this.inputs.add(htmlTable);
   }
   
   public String toString()
   {
      final StringUtil stringUtil = StringUtil.getInstance();
      String result = stringUtil.EMPTY_STRING;
      Object[] inputArray = this.inputs.toArray();
      int inputSize = inputArray.length;
      result = this.FORMBEGIN;      
      result += this.METHOD;
      result += this.method;
      result += "\" ";
      result += this.ACTION;
      result += this.action;
      result += "\" ";
      result += this.END;
      
      for (int i = 0; i < inputSize; i++)
      {
         result += inputArray[i].toString();
         result += " ";
      }
            
      result += this.FORMEND;      
      return result;
   }
   
}
