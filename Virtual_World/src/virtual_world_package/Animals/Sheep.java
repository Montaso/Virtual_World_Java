package virtual_world_package.Animals;

import virtual_world_package.Defines;
import virtual_world_package.World;

import java.awt.*;

public class Sheep extends Animal{

    @Override
    protected char Symbol()
    {
        return Defines.SHEEP_SIGN;
    }
    @Override
    public String GetName()
    {
        return Defines.SHEEP_NAME;
    }
    @Override
    protected Color GetColor()
    {
        return Color.white;
    }
    public Sheep(Point pos, World world)
    {
        super(Defines.SHEEP_STRENGTH, Defines.SHEEP_INITIATIVE, pos, world, Defines.SHEEP_SIGN);
    }
    public Sheep(World world)
    {
        super(Defines.SHEEP_STRENGTH, Defines.SHEEP_INITIATIVE, world, Defines.SHEEP_SIGN);
    }
    public Sheep(boolean canPlay, World world)
    {
        super(Defines.SHEEP_STRENGTH, Defines.SHEEP_INITIATIVE, canPlay, world, Defines.SHEEP_SIGN);
    }
    public Sheep(boolean canPlay, Point pos, World world)
    {
        super(Defines.SHEEP_STRENGTH, Defines.SHEEP_INITIATIVE, canPlay, pos, world, Defines.SHEEP_SIGN);
    }

    @Override
    protected Sheep SpawnNew(Point pos)
    {
        return new Sheep(pos, this.world);
    }

}
