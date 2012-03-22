package allbinary.graphics;

public class ScreenCaptureFactory
{
    private static final ScreenCapture instance = ScreenCaptureNoneFactory.getInstance();

    public static ScreenCapture getInstance()
    {
        return instance;
    }
}
