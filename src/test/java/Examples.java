import com.kylecorry.frc.vision.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import org.junit.Test;

import java.util.List;

/**
 * Created by Kylec on 2/12/2017.
 */
public class Examples {

    public void testCameraSource(){
        UsbCamera usbCamera = CameraServer.getInstance().startAutomaticCapture(0);
        usbCamera.setExposureManual(0);
        usbCamera.setBrightness(0);
        usbCamera.setWhiteBalanceManual(10000);
        usbCamera.setResolution(160, 120);

        CameraSource cameraSource = new CameraSource(usbCamera);

        TargetGroupDetector detector = new TargetGroupDetector(new ExampleSpecs(), new ExampleGroupSpecs());

        List<TargetGroup> targets = detector.detect(cameraSource.getPicture());

        if(!targets.isEmpty()){
            TargetGroup bestTarget = targets.get(0);

            double angle = bestTarget.computeAngle(160, CameraSpecs.MicrosoftLifeCam.HORIZONTAL_VIEW_ANGLE);
            double distance = bestTarget.computeDistance(160, new ExampleGroupSpecs().getGroupWidth(), CameraSpecs.MicrosoftLifeCam.HORIZONTAL_VIEW_ANGLE);

            double centerX = bestTarget.getCenterPosition().x;

            double confidence = bestTarget.getIsTargetGroupProbability(); // 0 - 1

            if(confidence > 0.75){
                // do something
            }
        }

    }


}
