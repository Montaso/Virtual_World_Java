package virtual_world_package.Plants;

import virtual_world_package.Defines;
import virtual_world_package.Organism;
import virtual_world_package.World;

import java.awt.*;

public abstract class Plant extends Organism {
    public Plant(int strength, Point pos, World world, char symbol)
    {
        super(strength, Defines.PLANT_INITIATIVE, pos, world, symbol);
    }
    public Plant(int strength, World world, char symbol)
    {
        super(strength, Defines.PLANT_INITIATIVE, world, symbol);
    }
    public Plant(int strength, boolean canPlay, World world, char symbol)
    {
        super(strength, Defines.PLANT_INITIATIVE, canPlay, world, symbol);
    }
    public Plant(int strength, boolean canPlay, Point pos, World world, char symbol)
    {
        super(strength, Defines.PLANT_INITIATIVE, canPlay, pos, world, symbol);
    }

    protected abstract char Symbol();

    protected abstract Color GetColor();
    @Override
    public String GetName()
    {
        return "Roslina";
    }
    @Override
    public void Action()
    {
        if (this.GetCanPlay() == false)
        {
            if (this.GetAge() == 0 && this.GetPosX() != Defines.OUT_OF_MAP)
            {
                this.SetCanPlay(true);
            }
            return;
        }
        //try to spread
        if ((int)(Math.random() * 5) == 0)
        {
            //spread successful
            Point newPos = this.FindNeighbourPos(this);
            if (newPos.x == Defines.OUT_OF_MAP)
            {
                //no available positions so no spread
                return;
            }
            else
            {
                this.AddOrganism(this.SpawnNew(newPos));
            }
        }
        else
        {
            //spread unsuccessful
            return;
        }
    }
    @Override
    public void Collision(Organism other)
    {
        return;
    }
}
