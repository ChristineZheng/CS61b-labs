package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature {
    private String name = "clorus";
    private int r = 34;
    private int g = 0;
    private int b = 231;
    private double energy;

    public Clorus(double e){
        super("clorus");
        energy = e;
    }

    /* public Clorus(){
        this(1);
    } */

    public double energy(){
        return energy;
    }
    public Color color(){
        return color(r, g, b);
    }
    public void attack(Creature c){
        energy += c.energy();
    }
    public void move(){
        energy -= 0.03;
    }
    public void stay(){
        energy -= 0.01;
    }

    public Clorus replicate(){
        energy = 0.5 * energy;
        return new Clorus(energy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // rule 1
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        // rule 2
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if (plips.size() > 0) {
            Direction moveDir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, moveDir);
        }

        // rule 3
        if (energy >= 1) {
            Direction movedir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, movedir);
        }
        // rule 4
        Direction movedirection = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, movedirection);
    }

}







