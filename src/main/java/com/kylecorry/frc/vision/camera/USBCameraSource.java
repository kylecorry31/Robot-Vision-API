package com.kylecorry.frc.vision.camera;

import edu.wpi.cscore.UsbCamera;

/**
 * Created by Kylec on 5/16/2017.
 */
public class USBCameraSource extends CameraSource {

    public USBCameraSource(int port) {
        super(new UsbCamera("cam" + port, port));
    }

}
