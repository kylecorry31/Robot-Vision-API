import com.kylecorry.frc.vision.camera.CameraSource
import com.kylecorry.frc.vision.camera.CameraSpecs
import com.kylecorry.frc.vision.detection.TargetGroup
import com.kylecorry.frc.vision.detection.TargetGroupDetector
import edu.wpi.cscore.UsbCamera
import edu.wpi.first.wpilibj.CameraServer

/**
 * Created by Kylec on 2/12/2017.
 */
class Examples {

    fun testCameraSource() {
        val usbCamera = CameraServer.getInstance().startAutomaticCapture(0)
        usbCamera.setExposureManual(0)
        usbCamera.brightness = 0
        usbCamera.setWhiteBalanceManual(10000)
        usbCamera.setResolution(160, 120)

        val cameraSource = CameraSource(usbCamera)

        val detector = TargetGroupDetector(ExampleSpecs(), ExampleGroupSpecs())

        val targets = detector.detect(cameraSource.picture)

        if (!targets.isEmpty()) {
            val bestTarget = targets[0]

            val angle = bestTarget.computeAngle(CameraSpecs.MicrosoftLifeCam.HORIZONTAL_VIEW_ANGLE)
            val distance = bestTarget.computeDistance(ExampleGroupSpecs().groupWidth, CameraSpecs.MicrosoftLifeCam.HORIZONTAL_VIEW_ANGLE)

            val centerX = bestTarget.centerPosition.x

            val confidence = bestTarget.probability // 0 - 1

            if (confidence > 0.75) {
                // do something
            }
        }

    }


}
