package virtual_world_package.Plants;

import virtual_world_package.Defines;
import virtual_world_package.World;

import java.awt.*;

public class Dandelion extends Plant {

    public Dandelion(Point pos, World world)
    {
        super(Defines.PLANT_STRENGTH, pos, world, Defines.DANDELION_SIGN);
    }
    public Dandelion(World world)
    {
        super(Defines.PLANT_STRENGTH, world, Defines.DANDELION_SIGN);
    }
    public Dandelion(boolean canPlay, World world)
    {
        super(Defines.PLANT_STRENGTH, true, world, Defines.DANDELION_SIGN);
    }
    public Dandelion(boolean canPlay, Point pos, World world)
    {
        super(Defines.PLANT_STRENGTH, canPlay, pos, world, Defines.DANDELION_SIGN);
    }
    @Override
    public void Action()
    {
        if (this.GetCanPlay() == false)
        {
            boolean b = GetPosX() != Defines.OUT_OF_MAP;
            if (this.GetAge() == 0 && b)
            {
                this.SetCanPlay(true);
            }
            return;
        }
	else
        {
            for (int i = 0; i < 3; i++)
            {
                super.Action();
            }
        }
    }

    @Override
    protected char Symbol()
    {
        return Defines.DANDELION_SIGN;
    }

    @Override
    public String GetName()
    {
        return Defines.DANDELION_NAME;
    }

    @Override
    protected Color GetColor()
    {
        return Color.yellow;
    }

    @Override
    protected Dandelion SpawnNew(Point pos)
    {
        return new Dandelion(pos, this.world);
    }

}
