package robo;

public class RobotFunctions {

    Taskmaster taskmaster;

    public RobotFunctions(Taskmaster taskmaster) {
        this.taskmaster = taskmaster;
    }

    private double futeureX;
    private double futureY;

    // ... put normalizeBearing and absoluteBearing methods here
    // normalizes a bearing to between +180 and -180
    public double normalizeBearing(double angle)
    {
        while (angle >  180)
            angle -= 360;
        while (angle < -180)
            angle += 360;
        return angle;
    }

    // computes the absolute bearing between two points
    double absoluteBearing(double x1, double y1, double x2, double y2)
    {
        return Math.toDegrees(Math.atan2(x2-x1, y2-y1));
    }

    double pythagoreanDistance(double x1, double y1, double x2, double y2 )
    {
        return Math.sqrt( Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }



}
