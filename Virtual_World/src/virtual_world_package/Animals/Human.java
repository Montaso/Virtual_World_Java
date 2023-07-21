package virtual_world_package.Animals;

import virtual_world_package.Defines;
import virtual_world_package.Organism;
import virtual_world_package.World;

import java.awt.*;

public class Human extends Animal{
    private int inputDirection;
    private boolean powerActivated;
    private int cooldown;

    public Human(World world)
    {
        super(Defines.HUMAN_STRENGTH, Defines.HUMAN_INITIATIVE, true, world, Defines.HUMAN_SIGN);
        this.SetWorldPlayer(this);
        this.inputDirection = Defines.NO_DIRECTION;
        this.powerActivated = false;
        this.cooldown = 0;
    };

    public Human(Point pos, World world)
    {
        super(Defines.HUMAN_STRENGTH, Defines.HUMAN_INITIATIVE, true, pos, world, Defines.HUMAN_SIGN);
        this.SetWorldPlayer(this);
        this.inputDirection = Defines.NO_DIRECTION;
        this.powerActivated = false;
        this.cooldown = 0;
    };

    public int GetInputDirection()
    {
        return this.inputDirection;
    }

    public int GetCooldown()
    {
        return this.cooldown;
    }

    public boolean GetPowerActivated()
    {
        return this.powerActivated;
    }

    public Point ChooseDirection()
    {
        switch (this.GetInputDirection())
        {
            case Defines.LEFT:
                if (this.GetPosX() > 0)
            {
                //return LEFT;
                return new Point(this.GetPosX() - 1, GetPosY());
            }
            break;

            case Defines.RIGHT:
                if (this.GetPosX() < this.GetWorld().GetSizeX() - 1)
            {
                //return RIGHT;
                return new Point(this.GetPosX() + 1, GetPosY());
            }
            break;
            case Defines.UP:
                if (this.GetPosY() > 0)
            {
                //return UP;
                return new Point(this.GetPosX(), GetPosY() - 1);
            }
            break;
            case Defines.DOWN:
                if (this.GetPosY() < this.GetWorld().GetSizeY() - 1)
            {
                //return DOWN;
                return new Point(this.GetPosX(), GetPosY() + 1);
            }
            break;
        }
        return new Point(this.GetPosX(), GetPosY());
    }

    public boolean DefendedAttack(Organism attacker)
    {
        if (this.GetPowerActivated() == true)
        {
            Animal animal = (Animal) (attacker);
            Point newPos;
            do
            {
                newPos = animal.ChooseDirection();
            } while (newPos.x == this.GetPosX() && newPos.y == this.GetPosY());
            if (newPos.x == Defines.OUT_OF_MAP)
            {
                return false;
            }
            else
            {
                if (this.GetWorld().GetOrgField(newPos) == null)
                {
                    animal.ChangePosition(newPos);
                }
			else
                {
                    animal.Collision(this.GetWorld().GetOrgField(newPos));
                }
                //animal.Action();
                AddLog("Moc czlowieka odbila atak: " + animal.GetName());
                return true;
            }

        }
	else
        {
            return false;
        }
    }
    @Override
    protected void Action()
    {
        if (this.GetCooldown() > 0)
        {
            if (this.GetCooldown() <= Defines.ABILITY_COOLDOWN && this.GetPowerActivated() == true)
            {
                this.SetPower(false);
                System.out.println("turned power off");
            }
            this.SetCooldown(GetCooldown() - 1);
            //System.out.println("Cooldown: " + GetCooldown());
        }
        else if(this.GetCooldown() == 0 && this.world.GetPowerBtn().isEnabled() == false)
        {
            this.world.GetPowerBtn().setEnabled(true);
        }
        super.Action();
    }

    @Override
    protected char Symbol()
    {
        return Defines.HUMAN_SIGN;
    }

    @Override
    public String GetName()
    {
        return Defines.HUMAN_NAME;
    }

    @Override
    protected Color GetColor()
    {
        return Color.BLACK;
    }

    public void SetInputDirection(int newDir)
    {
        this.inputDirection = newDir;
    }

    public void SetPower(boolean isOn)
    {
        this.powerActivated = isOn;
    }

    public void SetCooldown(int newCooldown)
    {
        this.cooldown = newCooldown;
    }

    @Override
    public Human SpawnNew(Point pos)
    {
        return null;
    }

    @Override
    public void Kill()
    {
        this.world.GetPowerBtn().setEnabled(false);
        super.Kill();
        this.SetWorldPlayer(null);
    }
}
