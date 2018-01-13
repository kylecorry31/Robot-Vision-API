//package sample;
//
//import com.kylecorry.frc.vision.targetDetection.TargetDetector;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.videoio.VideoCapture;
//
//import com.kylecorry.frc.vision.Target;
//import com.kylecorry.frc.vision.TargetDetector;
//import com.kylecorry.frc.vision.TargetGroup;
//import com.kylecorry.frc.vision.TargetGroupDetector;
//import com.kylecorry.frc.vision.TargetGroupScorer;
//
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferByte;
//import java.util.List;
//
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//public class Test {
//
//	public static Mat image;
//	public static ImageIcon imageIcon;
//	public static JFrame frame;
//
//	public static void main(String[] args) {
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//		imageIcon = new ImageIcon();
//
//		try {
//
//			frame = new JFrame("Image");
//			frame.setPreferredSize(new Dimension(640, 480));
//			frame.getContentPane().setLayout(new FlowLayout());
//
//			frame.getContentPane().add(new JLabel(imageIcon));
//			frame.pack();
//			frame.setVisible(true);
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		image = new Mat();
//
//		VideoCapture cap = new VideoCapture(0);
//
//		TargetDetector detector = new TargetDetector(new ExampleSpecs());
//
//		while (true) {
//
//			cap.read(image);
//
//			List<Target> targets = detector.detect(image);
//
//			processTargets(targets);
//		}
//
////		TargetGroupDetector detector2 = new TargetGroupDetector(new ExampleSpecs(), new ExampleGroupSpecs());
////
////		// List<Target> targets = detector.detect(image);
////		List<TargetGroup> targetGroups = detector2.detect(image);
//
//		// processTargets(targets);
//		// processTargetGroups(targetGroups);
//	}
//
//	public static void processTargetGroups(List<TargetGroup> groups) {
//
//		TargetGroup bestGroup = null;
//		double bestGroupScore = 0;
//
//		for (TargetGroup group : groups) {
//			if (group.getIsTargetGroupProbability() > bestGroupScore) {
//				bestGroupScore = group.getIsTargetGroupProbability();
//				bestGroup = group;
//			}
//		}
//
//		if (bestGroup != null) {
//			double angle = 56.5;
//			System.out.println("Group probability: " + bestGroupScore);
//			System.out.println("Group distance: " + bestGroup.computeDistance(5, angle));
//			System.out.println("Group angle: " + bestGroup.computeAngle(angle));
//			Rect boundary = new Rect(MathExt.roundToInt(bestGroup.getPosition().x),
//					MathExt.roundToInt(bestGroup.getPosition().y), MathExt.roundToInt(bestGroup.getWidth()),
//					MathExt.roundToInt(bestGroup.getHeight()));
//			ImageEditor.drawRectangleToMat(image, boundary, new Scalar(0, 255, 0));
//			Imgproc.drawMarker(image,
//					new org.opencv.core.Point(bestGroup.getCenterPosition().x, bestGroup.getCenterPosition().y),
//					new Scalar(0, 255, 0));
//		}
//		display(image);
//	}
//
//	public static void processTargets(List<Target> targets) {
//
//		if (targets.isEmpty())
//			return;
//
//		Target target = targets.get(0);
//
//		Rect boundary = new Rect(MathExt.roundToInt(target.getPosition().x), MathExt.roundToInt(target.getPosition().y),
//				MathExt.roundToInt(target.getWidth()), MathExt.roundToInt(target.getHeight()));
//		System.out.println("Distance: " + target.computeDistance(-1, 60.0));
//		System.out.println("Target probability: " + target.getIsTargetProbability());
//		System.out.println();
//		ImageEditor.drawRectangleToMat(image, boundary, new Scalar(0, 0, 255));
//
//		Imgproc.drawMarker(image, new org.opencv.core.Point(target.getCenterPosition().x, target.getCenterPosition().y),
//				new Scalar(0, 255, 0));
//
//		display(image);
//	}
//
//	public static void display(Mat image) {
//		imageIcon.setImage(toBufferedImage(image));
//		frame.revalidate();
//		frame.repaint();
//	}
//
//	public static BufferedImage toBufferedImage(Mat m) {
//		int type = BufferedImage.TYPE_BYTE_GRAY;
//		if (m.channels() > 1) {
//			type = BufferedImage.TYPE_3BYTE_BGR;
//		}
//		int bufferSize = m.channels() * m.cols() * m.rows();
//		byte[] b = new byte[bufferSize];
//		m.get(0, 0, b); // get all the pixels
//		BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
//		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//		System.arraycopy(b, 0, targetPixels, 0, b.length);
//		return image;
//
//	}
//
//}
