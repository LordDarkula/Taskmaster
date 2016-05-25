package robo;


import java.util.Random;

public class RobotConstants {

    public final int RAM_DISTANCE = 30;

    public final int ENEMY_REST_VELOCITY = 0;

    public final int MAX_FIREPOWER = 3;

    public final int MAX_TURN_REMAINING = 10;

    public final long INITIAL_TIME = 7;

    public final double MAX_BULLET_VELOCITY = 19.7D;

    public final double MIN_BULLET_VELOCITY = 14D;

    public Random rand = new Random();

    public RobotConstants() {

    }

    public int getRandomDistance()
    {
        return rand.nextInt(100) + 75;
    }

}
