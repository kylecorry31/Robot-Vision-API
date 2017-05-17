package com.kylecorry.frc.vision.camera;

import edu.wpi.cscore.HttpCamera;

/**
 * Created by Kylec on 5/16/2017.
 */
public class HTTPCameraSource extends CameraSource {
    public HTTPCameraSource(String url) {
        super(new HttpCamera("cam-" + url, url));
    }
}
