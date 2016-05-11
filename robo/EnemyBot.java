package robo;

import robocode.*;


/**
 * Record the state of an enemy bot.
 *
 * @author Aubhro Sengupta, Aditya Kuppili
 * @version 5/10/16
 *
 * @author Period - 2
 * @author Assignment - EnemyBot
 *
 * @author Sources - none
 */
public class EnemyBot
{
    private double bearing;

    private double distance;

    private double energy;

    private double heading;

    private double velocity;

    private String name;


    /**
     * Constructor for enemyBot
     */
    public EnemyBot()
    {
        reset();
    }


    /**
     * Gets bearing
     * @return bearing
     */
    public double getBearing()
    {
        return bearing;
    }


    /**
     * Gets distance
     * @return distance
     */
    public double getDistance()
    {
        return distance;
    }


    /**
     * Gets energy
     * @return energy
     */
    public double getEnergy()
    {
        return energy;
    }


    /**
     * Gets heading
     * @return heading
     */
    public double getHeading()
    {
        return heading;
    }


    /**
     * Gets velocity
     * @return velocity
     */
    public double getVelocity()
    {
        return velocity;
    }


    /**
     * Gets name
     * @return name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Updates data
     * @param srEvt it is the event that triggers method
     */
    public void update( ScannedRobotEvent srEvt )
    {
        bearing = srEvt.getBearing();
        distance = srEvt.getDistance();
        energy = srEvt.getEnergy();
        heading = srEvt.getHeading();
        velocity = srEvt.getVelocity();
        name = srEvt.getName();
    }


    /**
     * Resets data
     */
    public void reset()
    {
        bearing = 0.0;
        distance = 0.0;
        energy = 0.0;
        heading = 0.0;
        velocity = 0.0;
        name = "";
    }


    /**
     * Finds out if a robot is detected.
     * @return name
     */
    public boolean none()
    {
        return name.length() == 0;
    }
}