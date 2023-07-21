package virtual_world_package.Plants;

import virtual_world_package.Animals.Animal;
import virtual_world_package.Defines;
import virtual_world_package.Organism;
import virtual_world_package.World;

import java.awt.*;

public class Hogweed extends Plant {

    void KillNeighbours()
    {
        Point curPos = this.GetPosition();
        Point left = new Point(curPos.x - 1, curPos.y);
        Point right = new Point(curPos.x + 1, curPos.y);
        Point up = new Point(curPos.x, curPos.y - 1);
        Point down = new Point(curPos.x, curPos.y + 1);

        Point[] directions = { left, right, up, down };

        for (Point pos : directions)
        {
            if (this.GetWorld().IsInBounds(pos) && this.GetWorld().GetField(pos.x, pos.y) != Defines.EMPTY_SIGN)
            {
                if (this.GetWorld().FindOrgByPos(pos) instanceof Animal)
                {
                    String log = this.GetName() + " zabija: " + this.GetWorld().FindOrgByPos(pos).GetName() + " na pozycji (" +
                        this.GetPosX() + "; " + this.GetPosY() + ") bo przyszedl za blisko";
                    this.AddLog(log);

                    this.GetWorld().FindOrgByPos(pos).Kill();
                }
            }
        }
    }
    public Hogweed(Point pos, World world)
    {
        super(Defines.GIANTHOGWEED_STRENGTH, pos, world, Defines.GIANTHOGWEED_SIGN);
    }
    public Hogweed(World world)
    {
        super(Defines.GIANTHOGWEED_STRENGTH, world, Defines.GIANTHOGWEED_SIGN);
    }
    public Hogweed(boolean canPlay, World world)
    {
        super(Defines.GIANTHOGWEED_STRENGTH, true, world, Defines.GIANTHOGWEED_SIGN);
    }
    public Hogweed(boolean canPlay, Point pos, World world)
    {
        super(Defines.GIANTHOGWEED_STRENGTH, canPlay, pos, world, Defines.GIANTHOGWEED_SIGN);
    }

    @Override
    public boolean DefendedAttack(Organism attacker)
    {
        String log = attacker.GetName() + " zjada: " + this.GetName() + " na pozycji (" +
            this.GetPosX() + "; " + this.GetPosY() + ") i obaj giną";
        this.AddLog(log);

        this.Kill();
        attacker.Kill();
        return true;
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
        this.KillNeighbours();
        super.Action();
    }

    @Override
    protected char Symbol()
    {
        return Defines.GIANTHOGWEED_SIGN;
    }

    @Override
    public String GetName()
    {
        return Defines.GIANTHOGWEED_NAME;
    }

    @Override
    protected Color GetColor()
    {
        return new Color(59, 158, 249);
    }

    @Override
    protected Hogweed SpawnNew(Point pos)
    {
        return new Hogweed(pos, this.world);
    }

}
