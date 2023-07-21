package virtual_world_package.Animals;

import virtual_world_package.Defines;
import virtual_world_package.Organism;
import virtual_world_package.Pair;
import virtual_world_package.World;

import java.awt.*;

public class Antelope extends Animal{
    public Antelope(Point pos, World world)
    {
        super(Defines.ANTELOPE_STRENGTH, Defines.ANTELOPE_INITIATIVE, pos, world, Defines.ANTELOPE_SIGN);
    }
    public Antelope(World world)
    {
        super(Defines.ANTELOPE_STRENGTH, Defines.ANTELOPE_INITIATIVE, world, Defines.ANTELOPE_SIGN);
    }
    public Antelope(boolean canPlay, World world)
    {
        super(Defines.ANTELOPE_STRENGTH, Defines.ANTELOPE_INITIATIVE, canPlay, world, Defines.ANTELOPE_SIGN);
    }
    public Antelope(boolean canPlay, Point pos, World world)
    {
        super(Defines.ANTELOPE_STRENGTH, Defines.ANTELOPE_INITIATIVE, canPlay, pos, world, Defines.ANTELOPE_SIGN);
    }
    @Override
    public boolean DefendedAttack(Organism attacker)
    {
        if ((int)(Math.random() * 2)  == 0)
        {
            Point newPos = this.FindNeighbourPos(this);
            if (newPos.x == Defines.OUT_OF_MAP)
            {
                return false;
            }
            this.ChangePosition(newPos);
            return true;
        }
        return false;
    }
    @Override
    public Pair<Point, Integer> RandomDirection()
    {
        int direction = (int)(Math.random() * 4);
        Pair<Point, Integer> newPos = new Pair<>(new Point(Defines.OUT_OF_MAP, Defines.OUT_OF_MAP), Defines.NO_DIRECTION);
        switch (direction)
        {
            case Defines.LEFT:
                newPos = new Pair<>(new Point(GetPosX() - 2, GetPosY()), Defines.LEFT);
                break;
            case Defines.RIGHT:
                newPos = new Pair<>(new Point(GetPosX() + 2, GetPosY()), Defines.RIGHT);
                break;
            case Defines.UP:
                newPos = new Pair<>(new Point(GetPosX(), GetPosY() - 2), Defines.UP);
                break;
            case Defines.DOWN:
                newPos = new Pair<>(new Point(GetPosX(), GetPosY() + 2), Defines.DOWN);
                break;
        }
        return newPos;
    }
    @Override
    public void Collision(Organism other)
    {
        if (other == null) return;
        else if (this.getClass() == other.getClass())
        {
            //collision of the same organisms - BREED

            //checking for available positions to spawn new organism
            Point respawnPos = this.FindNeighbourPos(other);
            if (respawnPos.x == Defines.OUT_OF_MAP)
            {
                respawnPos = this.FindNeighbourPos(this);
                if (respawnPos.x == Defines.OUT_OF_MAP) return;
            }
            this.AddOrganism(this.SpawnNew(respawnPos));
        }
	else
        {
            if ((int)(Math.random() * 2) == 0)
            {
                Point newPossPos = other.FindNeighbourPos(other);
                if (newPossPos.x == Defines.OUT_OF_MAP)
                {
                    super.Collision(other);
                    return;
                }
                this.ChangePosition(newPossPos);
                return;
            }
            else
            {
                if (other.DefendedAttack(this))
                {
                    return;
                }
                //collision of different organisms - fight
                if (this.GetStrength() >= other.GetStrength())
                {
                    Point newPos = other.GetPosition();
                    other.Kill();
                    this.ChangePosition(newPos);
                }
			else
                {
                    this.Kill();
                }
            }
        }
    }
    @Override
    protected char Symbol()
    {
        return Defines.ANTELOPE_SIGN;
    }
    @Override
    public String GetName()
    {
        return Defines.ANTELOPE_NAME;
    }
    @Override
    protected Color GetColor()
    {
        return Color.blue;
    }
    @Override
    protected Antelope SpawnNew(Point pos)
    {
        return new Antelope(pos, this.world);
    }
}
