package virtual_world_package.Animals;

import virtual_world_package.Defines;
import virtual_world_package.Organism;
import virtual_world_package.World;

import java.awt.*;

public class Turtle extends Animal{
    public Turtle(Point pos, World world)
    {
        super(Defines.TURTLE_STRENGTH, Defines.TURTLE_INITIATIVE, pos, world, Defines.TURTLE_SIGN);
    }
    public Turtle(World world)
    {
        super(Defines.TURTLE_STRENGTH, Defines.TURTLE_INITIATIVE, world, Defines.TURTLE_SIGN);
    }
    public Turtle(boolean canPlay, World world)
    {
        super(Defines.TURTLE_STRENGTH, Defines.TURTLE_INITIATIVE, canPlay, world, Defines.TURTLE_SIGN);
    }
    public Turtle(boolean canPlay, Point pos, World world)
    {
        super(Defines.TURTLE_STRENGTH, Defines.TURTLE_INITIATIVE, canPlay, pos, world, Defines.TURTLE_SIGN);
    }

    public boolean DefendedAttack(Organism attacker)
    {
        if (attacker.GetStrength() < Defines.TURTLE_DEFENSE) return true;
        return false;
    }
    @Override
    public void Action()
    {
        if (this.GetCanPlay() == false)
        {
            //if he is newborn then we set so he can play next round
            if (this.GetAge() == 0 && this.GetPosX() != Defines.OUT_OF_MAP)
            {
                this.SetCanPlay(true);
            }
            return;
        }
        if (((int) (Math.random() * 4)) == 0) super.Action();
    }
    @Override
    protected char Symbol()
    {
        return Defines.TURTLE_SIGN;
    }
    @Override
    public String GetName()
    {
        return Defines.TURTLE_NAME;
    }
    @Override
    protected Color GetColor()
    {
        return new Color(39, 119, 0);
    }
    @Override
    protected Turtle SpawnNew(Point pos)
    {
        return new Turtle(pos, this.world);
    }

}
