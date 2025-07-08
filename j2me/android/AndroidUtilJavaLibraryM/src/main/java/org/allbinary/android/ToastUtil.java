package org.allbinary.android;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ToastUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();


	private static final ToastUtil instance = new ToastUtil();

	public static ToastUtil getInstance() {
		return instance;
	}

	public void makeToast(final View view, final Context context, String message, final int time)
	{
	    class MakeToast implements Runnable
	    {
	        final String string;
	        
	        public MakeToast(String string)
	        {
	            this.string = string;
	        }
	        
	        public void run()
	        {
	            try
	            {
	            	//logUtil.put("Trying to Toast: "+ string, this, commonStrings.RUN);
	            	//PreLogUtil.put("Trying to Toast: "+ string, this, commonStrings.RUN);
	                
	                Toast.makeText(context, string, time).show();
	            }
	            catch(Exception e)
	            {
                        final CommonStrings commonStrings = CommonStrings.getInstance();
	                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
	            }
	        }
	    }
	 
	    view.post(new MakeToast(message));
	}
}
