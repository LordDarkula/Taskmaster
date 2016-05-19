package robo;

import robocode.*;

/**
 * Record the advanced state of an enemy bot.
 *
 * @author Aubhro Sengupta, Aditya Kuppili
 * @version 5/11/16
 *
 * @author Period - 2
 * @author Assignment - AdvancedEnemyBot
 *
 * @author Sources - none
 */
public class AdvancedEnemyBot extends EnemyBot
{
    private double x;
    private double y;
    private byte scanDirection = 1;

    public AdvancedEnemyBot()
    {
        reset();
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void update( ScannedRobotEvent e, Robot robot )
    {
        super.update(e);
        double absBearingDeg = (robot.getHeading() + e.getBearing());
        if (absBearingDeg < 0)
        {
            absBearingDeg += 360;
        }

        x = robot.getX() + Math.sin(Math.toRadians(absBearingDeg)) * e.getDistance();

        y = robot.getY() + Math.cos(Math.toRadians(absBearingDeg)) * e.getDistance();

        scanDirection *= -1; // changes value from 1 to -1
    }

    public double getFutureX( long when )
    {
        return x + Math.sin(Math.toRadians(getHeading())) * getVelocity() * when;
    }

    public double getFutureY( long when )
    {

        return y + Math.cos(Math.toRadians(getHeading())) * getVelocity() * when;
    }

    public byte getScanDirection()
    {
        return scanDirection;
    }

    public void reset()
    {
        super.reset();
        x = 0.0;
        y = 0.0;
    }

}