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
package abcs.logic.basic.math.permutations;

public class OrderedIntegerArrayPermutationUtil
{
   private OrderedIntegerArrayPermutationUtil()
   {
   }
      
   public static void getNext(int[] data)
   {
      int length = data.length;
      int i = length - 1;
      
      while (data[i-1] >= data[i])
      {
         i = i-1;
      }
      
      int j = length;
      
      while (data[j-1] <= data[i-1])
      {
         j = j-1;
      }
      
      OrderedIntegerArrayPermutationUtil.swap(data,i-1, j-1);
      
      i++;
      j = length;
      
      while (i < j)
      {
         OrderedIntegerArrayPermutationUtil.swap(data,i-1, j-1);
         i++;
         j--;
      }
   }   
   
   public static void swap(int[] data, int a, int b)
   {
      int temp = data[a];
      data[a] = data[b];
      data[b] = temp;
   }
      
   public static int factorial(int dataLength)
   {
      int temp = 1;
      if (dataLength > 1)
      {
         for (int i = 1; i <= dataLength; i++)
         {
            temp *= i;
         }
      }      
      return temp;
   }
   
   public static void print(int[] data)
   {
      int size = data.length;      
      for (int i = 0; i < size; i++)
      {
         System.out.print("" + data[i] + " ");
      }      
      System.out.println("");      
   }

   public static void generate(int[] data)
   {
      System.out.print("Original Data: ");
      OrderedIntegerArrayPermutationUtil.print(data);
      int iterations = OrderedIntegerArrayPermutationUtil.factorial(data.length);
      System.out.println("Total Iterations: " + iterations);
      for (int count = 0; count < iterations - 1; count++)
      {
         OrderedIntegerArrayPermutationUtil.getNext(data);
         OrderedIntegerArrayPermutationUtil.print(data);
      }
      System.out.println("\nFINISHED");
   }
   
   public static void main(String args[])
   {
      int[] data = new int[4];
      
      for (int i = 0; i < 4; i++)
      {
         data[i] = i;
      }
      OrderedIntegerArrayPermutationUtil.generate(data);
   }      
}
