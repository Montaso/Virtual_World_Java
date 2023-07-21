package virtual_world_package.Plants;

import virtual_world_package.Defines;
import virtual_world_package.Organism;
import virtual_world_package.World;

import java.awt.*;

public class WolfBerry extends Plant {
    public WolfBerry(Point pos, World world)
    {
        super(Defines.WOLFBERRY_STRENGTH, pos, world, Defines.WOLFBERRY_SIGN);
    }
    public WolfBerry(World world)
    {
        super(Defines.WOLFBERRY_STRENGTH, world, Defines.WOLFBERRY_SIGN);
    }
    public WolfBerry(boolean canPlay, World world)
    {
        super(Defines.WOLFBERRY_STRENGTH, true, world, Defines.WOLFBERRY_SIGN);
    }
    public WolfBerry(boolean canPlay, Point pos, World world)
    {
        super(Defines.WOLFBERRY_STRENGTH, canPlay, pos, world, Defines.WOLFBERRY_SIGN);
    }


    public boolean DefendedAttack(Organism attacker)
    {
        String log = attacker.GetName() + " zjada: " + this.GetName() + " na pozycji (" +
            this.GetPosX() + "; " + this.GetPosY() + ") i obaj gina";
        this.AddLog(log);

        this.Kill();
        attacker.Kill();
        return true;
    }

    @Override
    protected char Symbol()
    {
        return Defines.WOLFBERRY_SIGN;
    }

    @Override
    public String GetName()
    {
        return Defines.WOLFBERRY_NAME;
    }

    @Override
    protected Color GetColor()
    {
        return new Color(108, 0, 209);
    }

    @Override
    protected WolfBerry SpawnNew(Point pos)
    {
        return new WolfBerry(pos, this.world);
    }
}
