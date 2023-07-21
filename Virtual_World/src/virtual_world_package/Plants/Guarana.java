package virtual_world_package.Plants;

import virtual_world_package.Defines;
import virtual_world_package.Organism;
import virtual_world_package.World;

import java.awt.*;

public class Guarana extends Plant{
    public Guarana(Point pos, World world)
    {
        super(Defines.PLANT_STRENGTH, pos, world, Defines.GUARANA_SIGN);
    }
    public Guarana(World world)
    {
        super(Defines.PLANT_STRENGTH, world, Defines.GUARANA_SIGN);
    }
    public Guarana(boolean canPlay, World world)
    {
        super(Defines.PLANT_STRENGTH, true, world, Defines.GUARANA_SIGN);
    }
    public Guarana(boolean canPlay, Point pos, World world)
    {
        super(Defines.PLANT_STRENGTH, canPlay, pos, world, Defines.GUARANA_SIGN);
    }
    public boolean DefendedAttack(Organism attacker)
    {
        attacker.SetStrength(attacker.GetStrength() + Defines.GUARANA_BONUS_ATTACK);
        return false;
    }

    @Override
    protected char Symbol()
    {
        return Defines.GUARANA_SIGN;
    }

    @Override
    public String GetName()
    {
        return Defines.GUARANA_NAME;
    }

    @Override
    protected Color GetColor()
    {
        return Color.red;
    }

    @Override
    protected Guarana SpawnNew(Point pos)
    {
        return new Guarana(pos, this.world);
    }
}
