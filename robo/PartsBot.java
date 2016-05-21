package robo;

import robocode.*;
import java.awt.Color;

/**
 * A modular bot adhering to the RoboPart Interface.
 *
 * @author Aubhro Sengupta, Aditya Kuppili
 * @version 5/11/16
 *
 * @author Period - 2
 * @author Assignment - PartsBot
 *
 * @author Sources - none
 */
public class PartsBot extends AdvancedRobot
{
    private AdvancedEnemyBot enemy = new AdvancedEnemyBot();
    private RobotPart[] parts = new RobotPart[3]; // make three parts
    private final static int RADAR = 0;
    private final static int GUN = 1;
    private final static int TANK = 2;


    public void run()
    {
        parts[RADAR] = new Radar();
        parts[GUN] = new Gun();
        parts[TANK] = new Tank();

        // initialize each part
        for ( int i = 0; i < parts.length; i++ )
        {
            // behold, the magic of polymorphism
            parts[i].init();
        }

        // iterate through each part, moving them as we go
        for ( int i = 0; true; i = ( i + 1 ) % parts.length )
        {
            // polymorphism galore!
            parts[i].move();
            if ( i == 0 )
                execute();
        }
    }

    public void onScannedRobot( ScannedRobotEvent e )
    {
        Radar radar = (Radar)parts[RADAR];
        if ( radar.shouldTrack( e ) )
        enemy.update( e, this );
        // Do not add any more code here
    }

    public void onRobotDeath( RobotDeathEvent e )
    {
        Radar radar = (Radar)parts[RADAR];
        if ( radar.wasTracking( e ) )
            enemy.reset();
    }

    // ... put normalizeBearing and absoluteBearing methods here
    // normalizes a bearing to between +180 and -180
    private double normalizeBearing(double angle)
    {
        while (angle >  180)
            angle -= 360;
        while (angle < -180)
            angle += 360;
        return angle;
    }

    // computes the absolute bearing between two points
    private double absoluteBearing(double x1, double y1, double x2, double y2)
    {
        return Math.toDegrees(Math.atan2(x2-x1, y2-y1));
    }


    public double lawOfCos( double b, double c, double angle)
    {
        return Math.sqrt(Math.pow(b, 2) + Math.pow(c, 2) - 2*b*c*Math.cos(Math.toRadians(angle)));
    }

    public double pythagoreanDistance(double x1, double y1, double x2, double y2 )
    {
        return Math.sqrt( Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    // ... declare the RobotPart interface and classes that implement it here
    // They will be _inner_ classes.
    public interface RobotPart
    {
        public void init();

        public void move();
    }

    private class Radar implements RobotPart
    {
        public void init()
        {
            setAdjustRadarForGunTurn(true);

        }

        public void move()
        {

            setTurnRadarRight(360 * enemy.getScanDirection());

        }

        public boolean shouldTrack( ScannedRobotEvent e )
        {
            // track if we have no enemy, the one we found is significantly
            // closer, or we scanned the one we've been tracking.
            return ( enemy.none() || e.getDistance() < enemy.getDistance() - 70 || e.getName()
                    .equals( enemy.getName() ) );
        }

        public boolean wasTracking( RobotDeathEvent e )
        {
            return e.getName().equals( enemy.getName() );
        }
    }

    private class Gun implements RobotPart
    {
        public void init()
        {
            setAdjustGunForRobotTurn(true);
        }

        public void move()
        {

            double futureX = enemy.getFutureX(1);
            double futureY = enemy.getFutureY(1);
            double futureDistance = lawOfCos(enemy.getDistance(), pythagoreanDistance(getX(), getY(), futureX, futureY), enemy.getHeading() + 90);

            double firePower = Math.min(400 / futureDistance, 3);
            // calculate speed of bullet
            double bulletSpeed = 20 - firePower * 3;

            // distance = rate * time, solved for time

            long time = (long)(futureDistance / bulletSpeed);

            futureX = enemy.getFutureX(time);
            futureY = enemy.getFutureY(time);

            double absDeg = absoluteBearing(getX(), getY(), futureX, futureY);
            //  calculate gun turn toward enemy


            // normalize the turn to take the shortest path there
            setTurnGunRight(normalizeBearing(absDeg - getGunHeading()));

            // if the gun is cool and we're pointed at the target, shoot!
            if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10)
            {
                setFire(firePower);
            }

        }
    }

    private class Tank implements RobotPart
    {
        public void init()
        {
            setColors(Color.BLACK, Color.BLACK, Color.BLACK);
        }

        public void move()
        {
            setTurnRight(enemy.getBearing());
            setAhead(enemy.getDistance());
        }
    }
}
