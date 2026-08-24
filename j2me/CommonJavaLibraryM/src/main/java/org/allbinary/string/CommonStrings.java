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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class CommonStrings
{
    private static final CommonStrings instance = new CommonStrings();
    
    @JsMethod
    public static CommonStrings getInstance()
    {
        return CommonStrings.instance;
    }

    @JsProperty
    public final String EMPTY = "Empty";
    
    @JsProperty
    public final String CREATE_IMAGE = "createImage";
    
    @JsProperty
    public final String PLEASE_WAIT = "Please Wait";
    @JsProperty
    public final String PLEASE_WAIT_FOR_SERVER = "Please Wait Getting Data From Server...";
    @JsProperty
    public final String PERIODS = "...";
    @JsProperty
    public final String LOADING = "Loading...";
    
    //CommonStrings.getInstance()
    @JsProperty
    public final String CONSTRUCTOR = "Constructor";
    @JsProperty
    public final String START = "Start";
    @JsProperty
    public final String END = "End";
    @JsProperty
    public final String EXCEPTION = "Exception";
    @JsProperty
    public final String EXCEPTION_LABEL = "Exception: ";
    
    @JsProperty
    public final String CLEANUP = "cleanup";
    
    @JsProperty
    public final String INIT = "init";
    @JsProperty
    public final String CLOSE = "close";

    @JsProperty
    public final String UP = "up";
    @JsProperty
    public final String DOWN = "down";
    @JsProperty
    public final String LEFT = "left";
    @JsProperty
    public final String RIGHT = "right";
    
    @JsProperty
    public final String[] DIRECTION_NAME = {
        this.DOWN,
        this.LEFT,
        this.RIGHT,
        this.UP
    };
    
    @JsProperty
    public final String CREATE = "Create";
    @JsProperty
    public final String REMOVE = "Remove";
    @JsProperty
    public final String UPDATE = "Update";
    @JsProperty
    public final String DELETE = "Delete";
    
    @JsProperty
    public final String delete = "delete";
    @JsProperty
    public final String LOAD = "load";
    @JsProperty
    public final String SAVE = "save";
    
    @JsProperty
    public final String ADD = "Add";
    @JsProperty
    public final String DROP = "Drop";
    @JsProperty
    public final String INSERT = "Insert";

    @JsProperty
    public final String VISIT = "visit";

    @JsProperty
    public final String START_METHOD_NAME = this.START; //"start";
    @JsProperty
    public final String END_METHOD_NAME = "end";

    @JsProperty
    public final String GET = "get";
    @JsProperty
    public final String PROCESS = "process";
    @JsProperty
    public final String GET_INSTANCE = "getInstance";

    @JsProperty
    public final String GET_LIST = "getList";

    @JsProperty
    public final String RUN = "run";
    @JsProperty
    public final String START_RUNNABLE = "Start Runnable";
    @JsProperty
    public final String RUNNING = "Running";
    @JsProperty
    public final String END_RUNNABLE = "End Runnable";
    
    @JsProperty
    public final String DISABLE = "disable";
    @JsProperty
    public final String ENABLE = "enable";
    
    @JsProperty
    public final String SUCCESS = "Command Success";
    @JsProperty
    public final String FAILURE = "Command Failed";
    
    @JsProperty
    public final String NOT_IMPLEMENTED = "Not Implemented";
    @JsProperty
    public final String UNKNOWN = "Unknown";
    
    @JsProperty
    public final String ON_EVENT = "onEvent";

    @JsProperty
    public final String IS_VALID = "isValid";
    @JsProperty
    public final String TOSTRING = "toString";
    
    @JsProperty
    public final String ADD_LISTENER = "addListener";
    @JsProperty
    public final String REMOVE_LISTENER = "removeListener";
    
}
