package robo;

public class TaskGun {
    private Taskmaster taskmaster;

    TaskGun(Taskmaster taskmaster) {
        this.taskmaster = taskmaster;
    }

    void init()
    {
        taskmaster.setAdjustGunForRobotTurn(true);
    }

    void move() {
        // Sets time at which the bullet will hit the enemy
        long time = taskmaster.robotConstants.INITIAL_TIME;

        // Gets where enemy will be at that time
        double futureX = taskmaster.enemy.getFutureX(time);
        double futureY = taskmaster.enemy.getFutureY(time);


        //  calculate gun turn toward enemy
        double absDeg = taskmaster.robotFunctions.absoluteBearing(taskmaster.getX(), taskmaster.getY(), futureX, futureY);


        // Finds distance to that point
        double futureDistance = taskmaster.robotFunctions.pythagoreanDistance(taskmaster.getX(), taskmaster.getY(), futureX, futureY);

        // normalize the turn to take the shortest path there
        taskmaster.setTurnGunRight(taskmaster.robotFunctions.normalizeBearing(absDeg - taskmaster.getGunHeading()));

        // Calculate enemy distance left after gun has turned
        double enemyDistanceLeft = taskmaster.robotFunctions.pythagoreanDistance(taskmaster.enemy.getX(), taskmaster.enemy.getY(), futureX, futureY);
        double bulletSpeed;

        // If the enemy is not at rest
        if (taskmaster.enemy.getVelocity() > taskmaster.robotConstants.ENEMY_REST_VELOCITY ) {

            // Calculate the time it will take for the enemy to reach the destination
            time = (long) (enemyDistanceLeft / taskmaster.enemy.getVelocity());

            // Sets bullet speed at a velocity that will ensure that it will hit the enemy at the destination
            bulletSpeed = Math.max(taskmaster.robotConstants.MIN_BULLET_VELOCITY, Math.min(taskmaster.robotConstants.MAX_BULLET_VELOCITY, (futureDistance / time)));
        } else {

            // If the enemy is at bullet speed can be the minimum
            bulletSpeed = taskmaster.robotConstants.MIN_BULLET_VELOCITY;
        }

        double firePower;

        // If robot is not being rammed
        if (taskmaster.enemy.getDistance() > taskmaster.robotConstants.RAM_DISTANCE) {

            // Calculate firepower necessary for the bullet to have the needed velocity
            firePower = (20 - bulletSpeed) / 3;
        } else {

            // If robot is being rammed fire with highest available firepower
            firePower = taskmaster.robotConstants.MAX_FIREPOWER;
        }


        // if the gun is cool and we're pointed at the target, shoot!
        //if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < robotConstants.MAX_TURN_REMAINING)
        //{
        taskmaster.setFire(firePower);

        //}

    }
}