# FRC Robot Vision API
The Robot Vision API allows FRC teams to specify some key parameters about the target they are looking for, and receive the location of the target in a given image. Developed through FRC Team 5112, The Gongoliers.


## Installation
Download the jar file from the [releases page](https://github.com/kylecorry31/Robot-Vision-API/releases) add it to your project.

### FRC 2019 (alpha - official support coming after kickoff)
Move the jar to a libs folder in your project. In your `build.gradle` file, add the following line to the dependencies section:

`compile fileTree(dir: 'libs', include: '*.jar')`

Here is an examples [build.gradle file](https://github.com/kylecorry31/FRC-Sample-Robot-2019/blob/master/build.gradle).


### FRC 2018 and older
Move the jar to a libs folder in your project. Add the libs folder to your `build.properties` file like so, at the end of the file:

`userLibs = libs`

Then add the jar to your build path in Eclipse.
    



## Usage
```Java

// This image will come from the camera using WPILib
Mat image = ...;

// Fill in with your own parameters
double minBrightness = 200;
double maxBrightness = 255;

Range area = new Range(0.03, 100);
Range fullness = new Range(0, 100);
Range aspectRatio = new Range(0, 100);

FOV fov = new FOV(60, 60);
Resolution resolution = new Resolution(640, 480);
boolean cameraInverted = false;

int imageArea = resolution.getArea();



// An HSV filter may be better for FRC target detection
TargetFilter filter = new BrightnessFilter(minBrightness, maxBrightness);
ContourFilter contourFilter = new StandardContourFilter(area, fullness, aspectRatio, imageArea);
CameraSettings cameraSettings = new CameraSettings(cameraInverted, fov, resolution);
TargetFinder targetFinder = new TargetFinder(cameraSettings, filter, contourFilter, TargetGrouping.SINGLE);

// Get the image from the camera using WPILib
List<Target> targets = targetFinder.findTargets(image);


// Draw the targets on the image
for(Target target: targets){
    // Get the bounding box of the target as a rotated rectangle
    RotatedRect boundary = target.getBoundary();
    
    // Convert the rotated rect to a contour list (OpenCV stuff to draw the rectangle)
    Point[] points = new Point[4];
    boundary.points(points);
    MatOfPoint contour = new MatOfPoint(points);
    List<MatOfPoint> contours = Arrays.asList(contour);
    
    // Draw it on the image
    Imgproc.drawContours(image, contours, 0, new Scalar(0, 255, 0));
    Imgproc.drawMarker(image, target.getBoundary().center, new Scalar(255, 0, 0));
}
```

## Contributing
Please fork this repo and submit a pull request to contribute. I will review all changes and respond if they are accepted or rejected (as well as reasons, so it will be accepted).

## License
This project is published under the [GPL-3.0 license](LICENSE).

