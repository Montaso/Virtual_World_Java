package virtual_world_package.Animals;

import virtual_world_package.Defines;
import virtual_world_package.Pair;
import virtual_world_package.World;

import java.awt.*;

public class Fox extends Animal{

    public Fox(Point pos, World world)
    {
        super(Defines.FOX_STRENGTH, Defines.FOX_INITIATIVE, pos, world, Defines.FOX_SIGN);
    }
    public Fox(World world)
    {
        super(Defines.FOX_STRENGTH, Defines.FOX_INITIATIVE, world, Defines.FOX_SIGN);
    }
    public Fox(boolean canPlay, World world)
    {
        super(Defines.FOX_STRENGTH, Defines.FOX_INITIATIVE, canPlay, world, Defines.FOX_SIGN);
    }
    public Fox(boolean canPlay, Point pos, World world)
    {
        super(Defines.FOX_STRENGTH, Defines.FOX_INITIATIVE, canPlay, pos, world, Defines.FOX_SIGN);
    }

    @Override
    protected char Symbol()
    {
        return Defines.FOX_SIGN;
    }
    @Override
    public String GetName()
    {
        return Defines.FOX_NAME;
    }
    @Override
    protected Color GetColor()
    {
        return Color.cyan;
    }
    @Override
    public Point ChooseDirection()
    {
        boolean[] takenPos = { false, false, false, false };
        //int direction;

        while (takenPos[0] != true || takenPos[1] != true || takenPos[2] != true || takenPos[3] != true)
        {
            //Position newPossPos = Animal::ChooseDirection();
            Pair<Point, Integer> newPossPos = RandomDirection();
            switch (newPossPos.getSecond())
            {
                case Defines.LEFT:
                    takenPos[Defines.LEFT] = true;
                    break;
                case Defines.RIGHT:
                    takenPos[Defines.RIGHT] = true;
                    break;
                case Defines.UP:
                    takenPos[Defines.UP] = true;
                    break;
                case Defines.DOWN:
                    takenPos[Defines.DOWN] = true;
                    break;
            }
            if (!this.GetWorld().IsInBounds(newPossPos.getFirst()))
            {
                continue;
            }
            if (this.GetWorld().GetField(newPossPos.getFirst().x, newPossPos.getFirst().y) == Defines.EMPTY_SIGN)
            {
                return newPossPos.getFirst();
            }
		else if (this.GetWorld().FindOrgByPos(newPossPos.getFirst()).GetStrength() <= this.GetStrength())
            {
                return newPossPos.getFirst();
            }

        }
        return new Point(GetPosX(), GetPosY());
    }

    @Override
    protected Fox SpawnNew(Point pos)
    {
        return new Fox(pos, this.world);
    }

}
