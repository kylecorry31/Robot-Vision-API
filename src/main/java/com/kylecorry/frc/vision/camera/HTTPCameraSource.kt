package com.kylecorry.frc.vision.camera

import edu.wpi.cscore.HttpCamera

/**
 * Created by Kylec on 5/16/2017.
 */
class HTTPCameraSource(url: String) : CameraSource(HttpCamera("cam-" + url, url))
