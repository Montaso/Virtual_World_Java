package virtual_world_package.Animals;

import virtual_world_package.Defines;
import virtual_world_package.Organism;
import virtual_world_package.Pair;
import virtual_world_package.World;

import java.awt.*;

public abstract class Animal extends Organism {
    protected Point prevPos;

    public Animal(int strength, int initiative, Point pos, World world, char symbol) {
        super(strength, initiative, pos, world, symbol);
        this.prevPos = new Point(-1, -1);
    }

    public Animal(int strength, int initiative, World world, char symbol) {
        super(strength, initiative, world, symbol);
        this.prevPos = new Point(-1, -1);
    }

    public Animal(int strength, int initiative, boolean canPlay, World world, char symbol) {
        super(strength, initiative, canPlay, world, symbol);
        this.prevPos = new Point(-1, -1);
    }

    public Animal(int strength, int initiative, Boolean canPlay, Point pos, World world, char symbol) {
        super(strength, initiative, canPlay, pos, world, symbol);
        this.prevPos = new Point(-1, -1);
    }

    public Point GetPrevPos()
    {
        return this.prevPos;
    }

    public void SetPrevPos(Point newPos)
    {
        this.prevPos = newPos;
    }
    protected abstract char Symbol();

    protected abstract Color GetColor();
    @Override
    public String GetName()
    {
        return "Zwierze";
    }

    protected Pair<Point, Integer> RandomDirection()
    {
        int direction = (int) (Math.random() * 4);
        Pair<Point, Integer> newPos = new Pair<>(new Point(Defines.OUT_OF_MAP, Defines.OUT_OF_MAP), Defines.NO_DIRECTION);
        switch (direction)
        {
            case Defines.LEFT:
                newPos = new Pair<>(new Point(GetPosX() - 1, GetPosY()), Defines.LEFT);
                break;
            case Defines.RIGHT:
                newPos = new Pair<>(new Point(GetPosX() + 1, GetPosY()), Defines.RIGHT);
                break;
            case Defines.UP:
                newPos = new Pair<>(new Point(GetPosX(), GetPosY() - 1), Defines.UP);
                break;
            case Defines.DOWN:
                newPos = new Pair<>(new Point(GetPosX(), GetPosY() + 1), Defines.DOWN);
                break;
        }
        return newPos;
    }

    public void ChangePosition(Point newPos)
    {
        this.SetPrevPos(this.GetPosition());
        this.SetPosition(newPos);
        this.SetWorldField(this.GetPosition(), this.Symbol());
        this.SetWorldOrgField(this.GetPosition(), this);

        if (this.GetPrevPos().x != Defines.OUT_OF_MAP)
        {
            this.SetWorldField(this.GetPrevPos(), Defines.EMPTY_SIGN);
            this.SetWorldOrgField(this.GetPrevPos(), null);
        }
    }

    public Point ChooseDirection()
    {
        boolean[] possiblePosTaken = { false, false, false, false };
        //Position newPossPos;
        //int direction;
        Pair<Point, Integer> newPossPos;

        while (possiblePosTaken[0] == false || possiblePosTaken[1] == false
                || possiblePosTaken[2] == false || possiblePosTaken[3] == false)
        {
            newPossPos = RandomDirection();
            switch (newPossPos.getSecond())
            {
                case Defines.LEFT:
                    possiblePosTaken[Defines.LEFT] = true;
                    break;
                case Defines.RIGHT:
                    possiblePosTaken[Defines.RIGHT] = true;
                    break;
                case Defines.UP:
                    possiblePosTaken[Defines.UP] = true;
                    break;
                case Defines.DOWN:
                    possiblePosTaken[Defines.DOWN] = true;
                    break;
            }
            if (this.GetWorld().IsInBounds(newPossPos.getFirst()))
            {
                return newPossPos.getFirst();
            }
        }
        return new Point(Defines.OUT_OF_MAP, Defines.OUT_OF_MAP);
    }



    @Override
    protected void Action()
    {
        //checks if animal can play round
        if (this.GetCanPlay() == false)
        {
            //if he is newborn then we set, so he can play next round
            if (this.GetAge() == 0 && this.GetPosX() != Defines.OUT_OF_MAP)
            {
                this.SetCanPlay(true);
            }
            return;
        }
        //int direction = ChooseDirection();
        Point newPos = ChooseDirection();
        if (this.GetWorld().GetField(newPos.x, newPos.y) == Defines.EMPTY_SIGN)
        {
            this.ChangePosition(newPos);
        }
	    else
        {
            if (newPos.x == this.GetPosX() && newPos.y == this.GetPosY())
            {
                return;
            }
            this.Collision(this.GetWorld().FindOrgByPos(newPos));
        }
    }

    @Override
    protected void Collision(Organism other)
    {
        String log;
        if (other == null) return;
        else if (this.getClass() == other.getClass())
        {
            //collision of the same organisms - BREED

            //checking for available positions to spawn new organism
            Point respawnPos = this.FindNeighbourPos(other);
            if (respawnPos.x == Defines.OUT_OF_MAP)
            {
                respawnPos = this.FindNeighbourPos(this);
                if (respawnPos.x == Defines.OUT_OF_MAP) return;
            }
            this.AddOrganism(this.SpawnNew(respawnPos));
        }
	else
        {
            if (other.DefendedAttack(this))
            {
                return;
            }
            //collision of different organisms - fight
            if (this.GetStrength() >= other.GetStrength())
            {
                Point newPos = other.GetPosition();
                other.Kill();
                this.ChangePosition(newPos);
                log = this.GetName() + " zabija: " + other.GetName() + " na pozycji (" +
                    this.GetPosX() + "; " + this.GetPosY() + ")";
                this.AddLog(log);
            }
		else
            {
                this.Kill();
                log = this.GetName() + " ginie atakujac: " + other.GetName() + " na pozycji (" +
                    this.GetPosX() + "; " + this.GetPosY() + ")";
                this.AddLog(log);
            }
        }
    }
}
