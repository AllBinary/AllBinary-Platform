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
package org.allbinary.logic.string.tokens;

import java.util.HashSet;
import java.util.Vector;

import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.regex.replace.Replace;
import org.allbinary.string.CommonPhoneStrings;
import org.allbinary.string.CommonSeps;
import org.allbinary.util.BasicArrayList;

public class UniqueTokens
{
	private final Vector specialCharacters = new Vector();
	
   public UniqueTokens()
   {
	   specialCharacters.add("!");
	   specialCharacters.add("@");
	   specialCharacters.add(CommonPhoneStrings.getInstance().POUND);
	   specialCharacters.add("$");
	   specialCharacters.add("%");
	   specialCharacters.add("^");
	   specialCharacters.add(CommonSeps.getInstance().AMP);
	   specialCharacters.add(CommonPhoneStrings.getInstance().STAR);
	   specialCharacters.add("(");
	   specialCharacters.add(")");
	   specialCharacters.add("-");
	   specialCharacters.add("_");
	   specialCharacters.add("+");
	   specialCharacters.add("=");
	   specialCharacters.add("\\");
	   specialCharacters.add("|");
	   specialCharacters.add(AbPathData.getInstance().EXTENSION_SEP);
	   specialCharacters.add(",");
	   specialCharacters.add("<");
	   specialCharacters.add(">");
	   specialCharacters.add("?");
	   specialCharacters.add("/");
	   specialCharacters.add("~");
	   specialCharacters.add("`");
   }
   
   public HashSet getWhithoutDashesAndSkipNumberOnlyTokens(Vector stringVector) throws Exception
   {
      try
      {         
         HashSet hashSet = new HashSet();
         int index = 0;
         
         CommonSeps commonSeps = CommonSeps.getInstance();
         
         while(index < stringVector.size())
         {
            String keywords = (String) stringVector.elementAt(index);
            Tokenizer tokenizer = new Tokenizer(commonSeps.COMMA);
            BasicArrayList keywordVector = tokenizer.getTokens(keywords, new BasicArrayList());
                        
            for(int forIndex=0;forIndex<keywordVector.size();forIndex++)
            {
               String cleanString = (String) keywordVector.get(forIndex);
               
               cleanString=cleanString.trim();               
               if(cleanString.indexOf('-')!=-1)
                  cleanString = new Replace("-", commonSeps.SPACE).all(cleanString);               
               
               hashSet.add(cleanString);
               if(cleanString.indexOf(' ')!=-1)
               {
                  Tokenizer spaceTokenizer = new Tokenizer(commonSeps.SPACE);
                  BasicArrayList subKeywordVector = spaceTokenizer.getTokens(cleanString, new BasicArrayList());
                  for(int spaceIndex=0;spaceIndex<subKeywordVector.size();spaceIndex++)                  
                  {                     
                     String subCleanString = (String) subKeywordVector.get(spaceIndex);
                     if(!isSpecialCharacter(subCleanString) && !numberOnly(subCleanString))                  
                     {
                        hashSet.add(subCleanString);
                     }
                  }
               }                              
            }
            index++;
         }
         return hashSet;
      }catch(Exception e)
      {
         throw e;
      }
   }
   
   private boolean numberOnly(String subCleaningString)
   {
      try
      {
         Integer.parseInt(subCleaningString);
         return true;
      }catch(NumberFormatException e)
      {
         return false;
      }
   }
      
   private boolean isSpecialCharacter(String subCleaningString)
   {      
      for(int index=0; index< specialCharacters.size(); index++)
      {
         if(subCleaningString.compareTo((String) specialCharacters.elementAt(index))==0)
            return true;
      }
      return false;
   }
}
