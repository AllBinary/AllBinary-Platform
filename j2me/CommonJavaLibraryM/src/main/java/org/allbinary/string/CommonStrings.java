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
package org.allbinary.string;

public class CommonStrings
{
    private static final CommonStrings instance = new CommonStrings();
    
    public static CommonStrings getInstance()
    {
        return instance;
    }

    public final String EMPTY = "Empty";
    
    public final String CREATE_IMAGE = "createImage";
    
    public final String PLEASE_WAIT = "Please Wait";
    public final String PLEASE_WAIT_FOR_SERVER = "Please Wait Getting Data From Server...";
    public final String PERIODS = "...";
    public final String LOADING = "Loading...";
    
    //CommonStrings.getInstance()
    public final String CONSTRUCTOR = "Constructor";
    public final String START = "Start";
    public final String END = "End";
    public final String EXCEPTION = "Exception";
    public final String EXCEPTION_LABEL = "Exception: ";
    
    public final String INIT = "init";
    public final String CLOSE = "close";

    public final String CREATE = "Create";
    public final String REMOVE = "Remove";
    public final String UPDATE = "Update";
    public final String DELETE = "Delete";
    
    public final String ADD = "Add";
    public final String DROP = "Drop";
    public final String INSERT = "Insert";

    public final String VISIT = "visit";

    public final String START_METHOD_NAME = START; //"start";
    public final String END_METHOD_NAME = "end";

    public final String GET = "get";
    public final String PROCESS = "process";
    public final String GET_INSTANCE = "getInstance";

    public final String GET_LIST = "getList";

    public final String RUN = "run";
    public final String START_RUNNABLE = "Start Runnable";
    public final String RUNNING = "Running";
    public final String END_RUNNABLE = "End Runnable";
    
    public final String DISABLE = "disable";
    public final String ENABLE = "enable";
    
    public final String SUCCESS = "Command Success";
    public final String FAILURE = "Command Failed";
    
    public final String NOT_IMPLEMENTED = "Not Implemented";
    public final String UNKNOWN = "Unknown";
    
    public final String ON_EVENT = "onEvent";

}
