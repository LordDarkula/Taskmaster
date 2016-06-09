package robo;

import java.awt.*;

public class TaskTank {
    private Taskmaster taskmaster;

    public TaskTank(Taskmaster taskmaster) {
        this.taskmaster = taskmaster;
    }

    public void init()
    {
        taskmaster.setColors(Color.WHITE, Color.WHITE, Color.BLACK);
    }

    public void move() {
        //if (enemy.getDistance() < robotConstants.RAM_DISTANCE)
        //{
        //setTurnRight(enemy.getBearing() + 90);
        //setAhead(20);
        //}
        //else {
        taskmaster.setTurnRight(taskmaster.enemy.getBearing());
        taskmaster.setAhead(taskmaster.enemy.getDistance());
        //}
    }
}