package robo;

import robocode.*;


/**
 * Record the state of an enemy bot.
 *
 * @author Aubhro Sengupta, Aditya Kuppili
 * @author Period - 2
 * @author Assignment - EnemyBot
 * @author Sources - none
 * @version 5/10/16
 */
public class EnemyBot {
    /**
     * The Bearing the the Robot
     */
    private double bearing;
    /**
     * The distance to the Robot
     */
    private double distance;

    private double heading;

    private double velocity;

    private String name;


    /**
     * Constructor for enemyBot
     */
    public EnemyBot() {
        reset();
    }


    /**
     * Gets bearing
     *
     * @return bearing
     */
    public double getBearing() {
        return bearing;
    }


    /**
     * Gets distance
     *
     * @return distance
     */
    public double getDistance() {
        return distance;
    }


    /**
     * Gets heading
     *
     * @return heading
     */
    public double getHeading() {
        return heading;
    }


    /**
     * Gets velocity
     *
     * @return velocity
     */
    public double getVelocity() {
        return velocity;
    }


    /**
     * Gets name
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Updates data
     *
     * @param srEvt it is the event that triggers method
     */
    public void update(ScannedRobotEvent srEvt) {
        bearing = srEvt.getBearing();
        distance = srEvt.getDistance();
        heading = srEvt.getHeading();
        velocity = srEvt.getVelocity();
        name = srEvt.getName();
    }


    /**
     * Resets data
     */
    public void reset() {
        bearing = 0.0;
        distance = 0.0;
        heading = 0.0;
        velocity = 0.0;
        name = "";
    }


    /**
     * Finds out if a robot is detected.
     *
     * @return the length of the name
     */
    public boolean none() {
        return name.length() == 0;
    }
}