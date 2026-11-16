package org.allbinary.android;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class ToastUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();


	private static final ToastUtil instance = new ToastUtil();

	public static ToastUtil getInstance() {
		return ToastUtil.instance;
	}

	public void makeToast(final View view, final Context context, String message, final int time)
	{
	    view.post(new MakeToast(context, message, time));
	}

	//A member class instead of a local class using the method parameters since most of the converted languages do not have local classes.
	private static class MakeToast implements Runnable
	{
	    private final Context context;
	    private final String string;
	    private final int time;

	    public MakeToast(final Context context, final String string, final int time)
	    {
	        this.context = context;
	        this.string = string;
	        this.time = time;
	    }

	    @Override
	    public void run()
	    {
	        final LogUtil logUtil = LogUtil.getInstance();
	        try
	        {
	            //logUtil.putF("Trying to Toast: "+ string, this, this.commonStrings.RUN);
	            //PreLogUtil.put("Trying to Toast: "+ string, this, this.commonStrings.RUN);

	            Toast.makeText(this.context, this.string, this.time).show();
	        }
	        catch(Exception e)
	        {
	            final CommonStrings commonStrings = CommonStrings.getInstance();
	            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
	        }
	    }
	}
}
