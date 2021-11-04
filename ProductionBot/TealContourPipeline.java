package org.firstinspires.ftc.teamcode.dcs15815;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;



public class TealContourPipeline extends OpenCvPipeline {

    Mat convertedInput = new Mat();
    Mat HSV = new Mat();
    private Random rng = new Random(12345);

    Mat x = new Mat();

    public int barcodePosition;

    static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(0, 0);
    static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(106, 0);
    static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(212, 0);

    static final int REGION_WIDTH = 107;
    static final int REGION_HEIGHT = 239;

    Point region1_pointA = new Point(
		  REGION1_TOPLEFT_ANCHOR_POINT.x,
		  REGION1_TOPLEFT_ANCHOR_POINT.y);
    Point region1_pointB = new Point(
		  REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
		  REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region2_pointA = new Point(
		  REGION2_TOPLEFT_ANCHOR_POINT.x,
		  REGION2_TOPLEFT_ANCHOR_POINT.y);
    Point region2_pointB = new Point(
		  REGION2_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
		  REGION2_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region3_pointA = new Point(
		  REGION3_TOPLEFT_ANCHOR_POINT.x,
		  REGION3_TOPLEFT_ANCHOR_POINT.y);
    Point region3_pointB = new Point(
		  REGION3_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
		  REGION3_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    Rect region1, region2, region3;

    public TealContourPipeline() {
//	   this.telemetry = telemetry;
	   region1 = new Rect(region1_pointA, region1_pointB);
	   region2 = new Rect(region2_pointA, region2_pointB);
	   region3 = new Rect(region3_pointA, region3_pointB);
    }

    void convertInput(Mat input) {
	   //Imgproc.cvtColor(input, convertedInput, Imgproc.COLOR_RGB2YCrCb);
	   Imgproc.cvtColor(input, convertedInput, Imgproc.COLOR_RGB2HSV);
	   //Core.extractChannel(convertedInput, x, 1);
	   //Imgproc.GaussianBlur(convertedInput, convertedInput, new Size(15.0, 15.0), 0.00);


    }

    @Override
    public void init(Mat firstFrame) {
	   convertInput(firstFrame);
    }


    @Override
    public Mat processFrame(Mat input) {
	   convertInput(input);
//		Scalar lowHSV = new Scalar(60, 70, 10);
//		Scalar highHSV = new Scalar(99, 255, 255);
//		Scalar lowHSV = new Scalar(60, 70, 30);
//		Scalar highHSV = new Scalar(99, 255, 255);
	   Scalar lowHSV = new Scalar(60, 70, 25);
	   Scalar highHSV = new Scalar(99, 255, 255);
	   Mat thresh = new Mat();

	   // We'll get a black and white image. The white regions represent the regular stones.
	   // inRange(): thresh[i][j] = {255,255,255} if mat[i][i] is within the range
	   Core.inRange(convertedInput, lowHSV, highHSV, thresh);
	   Imgproc.GaussianBlur(thresh, thresh, new Size(25.0, 25.0), 0.00);
	   Imgproc.threshold(thresh, thresh, 50, 255, Imgproc.THRESH_BINARY);

	   Mat cannyOutput = new Mat();
	   Imgproc.Canny(thresh, cannyOutput, 50, 100);

	   List<MatOfPoint> contours = new ArrayList<>();
	   Mat hierarchy = new Mat();
	   Imgproc.findContours(cannyOutput, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

	   MatOfPoint2f[] contoursPoly  = new MatOfPoint2f[contours.size()];
	   Rect[] boundRect = new Rect[contours.size()];
	   Point[] centers = new Point[contours.size()];

	   for (int i = 0; i < contours.size(); i++) {
		  contoursPoly[i] = new MatOfPoint2f();
		  Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly[i], 3, true);
		  boundRect[i] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[i].toArray()));
		  centers[i] = new Point(boundRect[i].x + boundRect[i].width / 2, boundRect[i].y + boundRect[i].height / 2);
	   }

	   Mat drawing = Mat.zeros(cannyOutput.size(), CvType.CV_8UC3);
	   List<MatOfPoint> contoursPolyList = new ArrayList<>(contoursPoly.length);
	   for (MatOfPoint2f poly : contoursPoly) {
		  contoursPolyList.add(new MatOfPoint(poly.toArray()));
	   }
	   for (int i = 0; i < contours.size(); i++) {
		  Scalar color = new Scalar(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
		  Imgproc.drawContours(drawing, contoursPolyList, i, color);
		  Imgproc.rectangle(drawing, boundRect[i].tl(), boundRect[i].br(), color, 2);
	   }

	   if (contours.size() > 0) {
		  if (region1.contains(centers[0])) {
		      barcodePosition = 1;
		  } else if (region2.contains(centers[0])) {
			 barcodePosition = 2;
		  } else if (region3.contains(centers[0])) {
			 barcodePosition = 3;
		  } else {
			 barcodePosition = 0;
		  }
	   } else {
		  barcodePosition = -1;
	   }


	   return drawing;
    }

}