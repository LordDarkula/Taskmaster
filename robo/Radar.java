package robo;

import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

public class Radar implements Taskmaster.RobotPart {

    public Taskmaster taskmaster;

    public Radar(Taskmaster taskmaster) {
        this.taskmaster = taskmaster;
    }
    public void init() {
        taskmaster.setAdjustRadarForGunTurn(true);

    }

    public void move() {

        taskmaster.setTurnRadarRight(360 * taskmaster.enemy.getScanDirection());

    }

    boolean shouldTrack( ScannedRobotEvent e ) {
        // track if we have no enemy, the one we found is significantly
        // closer, or we scanned the one we've been tracking.
        return ( taskmaster.enemy.none() || e.getDistance() < taskmaster.enemy.getDistance() - 70 || e.getName()
                .equals( taskmaster.enemy.getName() ) );
    }

    boolean wasTracking( RobotDeathEvent e )
    {
        return e.getName().equals( taskmaster.enemy.getName() );
    }
}