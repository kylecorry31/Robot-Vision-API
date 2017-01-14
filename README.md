# Robot Vision API
The Robot Vision API supports detecting multiple targets asynchronously. The vision library uses the builder pattern to create detectors and camera sources. Through this API, the robot is able to detect targets, groups of targets, or even multiple kinds of targets asynchronously. 

### CameraSource
The CameraSource class is used to grab images from a camera and pass them to a detector if supplied. Once the start method is called, the CameraSource will asynchronously pass the detector an image to process until the stop method is called. 

### TargetDetector
The TargetDetector class specializes in finding vision targets in an image given a target specification. 

### Example
```Java
TargetGroupDetector pegDetector = new TargetGroupDetector.Builder()
	.setTargetSpecs(new PegSingleRetroreflectiveSpecs())
	.setTargetGroupSpecs(new PegGroupSpecs())
    .setProcessor((targets)->{
        // do something
    })
	.build();
    
CameraSource camera = new CameraSource.Builder(pegDetector)
    .setType(CameraSource.USB_CAMERA)
    .build();
```
    
    
