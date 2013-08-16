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
package allbinary.logic.control.workflow;

public class WorkFlowData
{
	private static final WorkFlowData instance = new WorkFlowData();
	
	   public static WorkFlowData getInstance() {
			return instance;
		}

   private WorkFlowData()
   {
   }

public String WORKFLOWS = "WORKFLOWS";
   
   public String WORKFLOW = "WORKFLOW";
   
   public String NAME = "WORKFLOW_NAME";

   public String DATA = "WORKFLOW_DATA";
}
