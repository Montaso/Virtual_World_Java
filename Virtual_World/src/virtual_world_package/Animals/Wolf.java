package virtual_world_package.Animals;

import virtual_world_package.Defines;
import virtual_world_package.World;

import java.awt.*;

public class Wolf extends Animal{
    @Override
    protected char Symbol()
    {
        return Defines.WOLF_SIGN;
    }
    @Override
    public String GetName()
    {
        return Defines.WOLF_NAME;
    }
    @Override
    protected Color GetColor()
    {
        return new Color(81, 0, 14);
    }
    public Wolf(Point pos, World world)
    {
        super(Defines.WOLF_STRENGTH, Defines.WOLF_INITIATIVE, pos, world, Defines.WOLF_SIGN);
    }
    public Wolf(World world)
    {
        super(Defines.WOLF_STRENGTH, Defines.WOLF_INITIATIVE, world, Defines.WOLF_SIGN);
    }
    public Wolf(boolean canPlay, World world)
    {
        super(Defines.WOLF_STRENGTH, Defines.WOLF_INITIATIVE, canPlay, world, Defines.WOLF_SIGN);
    }
    public Wolf(boolean canPlay, Point pos, World world)
    {
        super(Defines.WOLF_STRENGTH, Defines.WOLF_INITIATIVE, canPlay, pos, world, Defines.WOLF_SIGN);
    }


    @Override
    protected Wolf SpawnNew(Point pos)
    {
        return new Wolf(pos, this.world);
    }
}
