package robo;


import java.util.Random;

public class RobotConstants {

    public final int RAM_DISTANCE = 40;

    public final int ENEMY_REST_VELOCITY = 0;

    public final int MAX_FIREPOWER = 3;

    public final int MAX_TURN_REMAINING = 10;

    public final long INITIAL_TIME = 6;

    public final double MAX_BULLET_VELOCITY = 15D;

    public final double MIN_BULLET_VELOCITY = 11D;

    public RobotConstants() {

    }

    public int getRandomAngle()
    {
        Random rand = new Random();
        return rand.nextInt(20) + 20;
    }
}
