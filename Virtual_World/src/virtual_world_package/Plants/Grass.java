package virtual_world_package.Plants;

import virtual_world_package.Defines;
import virtual_world_package.World;

import java.awt.*;

public class Grass extends Plant {
    public Grass(Point pos, World world)
    {
        super(Defines.PLANT_STRENGTH, pos, world, Defines.GRASS_SIGN);
    }
    public Grass(World world)
    {
        super(Defines.PLANT_STRENGTH, world, Defines.GRASS_SIGN);
    }
    public Grass(boolean canPlay, World world)
    {
        super(Defines.PLANT_STRENGTH, true, world, Defines.GRASS_SIGN);
    }
    public Grass(boolean canPlay, Point pos, World world)
    {
        super(Defines.PLANT_STRENGTH, canPlay, pos, world, Defines.GRASS_SIGN);
    }

    @Override
    protected char Symbol()
    {
        return Defines.GRASS_SIGN;
    }

    @Override
    public String GetName()
    {
        return Defines.GRASS_NAME;
    }

    @Override
    protected Color GetColor()
    {
        return Color.green;
    }

    @Override
    protected Grass SpawnNew(Point pos)
    {
        return new Grass(pos, this.world);
    }

}
