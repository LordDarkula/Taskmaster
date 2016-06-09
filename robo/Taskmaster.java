package robo;

import robocode.*;

/**
 * A modular bot adhering to the RoboPart Interface.
 *
 * @author Aubhro Sengupta, Aditya Kuppili
 * @version 5/11/16
 *
 * @author Period - 2
 * @author Assignment - Taskmaster
 *
 * @author Sources - none
 */
public class Taskmaster extends AdvancedRobot {
    public AdvancedEnemyBot enemy = new AdvancedEnemyBot();
    private RobotPart[] parts = new RobotPart[3]; // make three parts
    RobotFunctions robotFunctions = new RobotFunctions(this);
    RobotConstants robotConstants = new RobotConstants();
    private final static int RADAR = 0;
    private final static int GUN = 1;
    private final static int TANK = 2;
    private TaskRadar taskRadar = new TaskRadar(this);
    private TaskGun taskGun = new TaskGun(this);
    private TaskTank taskTank = new TaskTank(this);


    public void run() {
        parts[RADAR] = new Radar();
        parts[GUN] = new Gun();
        parts[TANK] = new Tank();

        // initialize each part
        for (RobotPart part : parts) {
            // behold, the magic of polymorphism
            part.init();
        }

        // iterate through each part, moving them as we go
        for ( int i = 0; true; i = ( i + 1 ) % parts.length )
        {
            // polymorphism galore!
            parts[i].move();
            if ( i == 0 ) {
                execute();
            }
        }
    }

    public void onScannedRobot( ScannedRobotEvent e ) {
        Radar radar = (Radar)parts[RADAR];
        if ( taskRadar.shouldTrack( e ) )
        enemy.update( e, this );
        // Do not add any more code here
    }

    public void onRobotDeath( RobotDeathEvent e ) {
        Radar radar = (Radar)parts[RADAR];
        if ( taskRadar.wasTracking( e ) )
            enemy.reset();
    }

    // ... declare the RobotPart interface and classes that implement it here
    // They will be _inner_ classes.
    interface RobotPart {
        void init();
        void move();
    }

    private class Radar implements RobotPart {
        public void init() {
            taskRadar.init();
        }

        public void move() {
            taskRadar.move();
        }
    }

    private class Gun implements RobotPart {
        public void init() {
            taskGun.init();
        }

        public void move() {
            taskGun.move();
        }
    }

    private class Tank implements RobotPart {
        public void init() {
            taskTank.init();
        }

        public void move() {
            taskTank.move();
        }
    }
}
