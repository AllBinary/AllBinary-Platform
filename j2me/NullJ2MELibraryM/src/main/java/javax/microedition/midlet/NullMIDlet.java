package javax.microedition.midlet;

public class NullMIDlet extends MIDlet {

    public static final NullMIDlet NULL_MIDLET = new NullMIDlet();
    
    @Override
    protected void startApp() throws MIDletStateChangeException {
    }

    @Override
    protected void pauseApp() {
    }

    @Override
    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
    }

}
