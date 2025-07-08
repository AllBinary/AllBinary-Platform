package org.allbinary.thread;

public class ThreadObjectUtil {

    private static final ThreadObjectUtil instance = new ThreadObjectUtil();

    public static ThreadObjectUtil getInstance() {
        return instance;
    }

    public final PriorityRunnable NULL_PRIORITY_RUNNABLE = new PriorityRunnable() {

        public int getPriority() {
            return 0;
        }

        public boolean isDone() {
            return true;
        }

        public void reset() {

        }

        public void run() {

        }

    };

    public void notifyObject(Object object) {
    }

    public void waitObject(Object object)
        throws Exception {
    }

    public void waitObject(Object object, int delay)
        throws Exception {
    }

}
