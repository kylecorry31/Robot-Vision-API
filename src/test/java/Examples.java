import com.kylecorry.frc.vision.CameraSource;
import edu.wpi.cscore.UsbCamera;
import org.junit.Test;

/**
 * Created by Kylec on 2/12/2017.
 */
public class Examples {

    public void testCameraSource(){
        CameraSource camera = new CameraSource(new UsbCamera("cam", 0));
        camera.setExposure(0);
        camera.setBrightness(0);
        camera.setResolution(640, 480);
        camera.start();
    }
}
