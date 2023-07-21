package virtual_world_package;

import java.awt.*;

public abstract class Organism {
    protected int strength;
    protected final int initiative;
    protected int age;
    protected boolean canPlay;
    protected Point pos;
    protected final World world;

    public Organism(int strength, int initiative, Point pos, World world, char symbol) {
        this.strength = strength;
        this.initiative = initiative;
        this.age = 0;
        this.canPlay = false;
        this.world = world;
        this.pos = new Point(pos.x, pos.y);
        world.SetField(pos.x, pos.y, symbol);
        world.SetOrgField(pos, this);
    }

    public Organism(int strength, int initiative, World world, char symbol) {
        this.strength = strength;
        this.initiative = initiative;
        this.age = 0;
        this.canPlay = false;
        this.world = world;

        do {
            pos = new Point();
            this.pos.x = (int) (Math.random() * world.GetSizeX());
            this.pos.y = (int) (Math.random() * world.GetSizeY());
        } while (world.GetField(pos.x, pos.y) != Defines.EMPTY_SIGN);

        world.SetField(pos.x, pos.y, symbol);
        world.SetOrgField(pos, this);
    }

    public Organism(int strength, int initiative, boolean canPlay, World world, char symbol) {
        this.strength = strength;
        this.initiative = initiative;
        this.age = 0;
        this.canPlay = canPlay;
        this.world = world;

        do {
            pos = new Point();
            this.pos.x = (int) (Math.random() * world.GetSizeX());
            this.pos.y = (int) (Math.random() * world.GetSizeY());
        } while (world.GetField(pos.x, pos.y) != Defines.EMPTY_SIGN);

        world.SetField(pos.x, pos.y, symbol);
        world.SetOrgField(this.pos, this);
    }

    public Organism(int strength, int initiative, boolean canPlay, Point pos, World world, char symbol) {
        this.strength = strength;
        this.initiative = initiative;
        this.age = 0;
        this.canPlay = canPlay;
        this.pos = new Point(pos.x, pos.y);
        this.world = world;

        world.SetField(pos.x, pos.y, symbol);
        world.SetOrgField(pos, this);
    }

    public int GetStrength() {
        return strength;
    }

    public int GetInitiative() {
        return initiative;
    }

    public int GetAge() {
        return age;
    }

    public Point GetPosition() {
        return pos;
    }

    public int GetPosX() {
        return pos.x;
    }

    public int GetPosY() {
        return pos.y;
    }

    public World GetWorld() {
        return world;
    }

    public boolean GetCanPlay() {
        return canPlay;
    }

    public String GetName() {
        return "Organism";
    }

    protected abstract char Symbol();
    protected abstract Color GetColor();

    public void SetPosition(Point newPos) {
        this.pos = newPos;
    }

    public void SetX(int newX) {
        this.pos.x = newX;
    }

    public void SetY(int newY) {
        this.pos.y = newY;
    }

    public void SetWorldField(Point pos, char newChar) {
        this.world.SetField(pos.x, pos.y, newChar);
    }

    public void SetWorldOrgField(Point pos, Organism newOrg) {
        this.world.SetOrgField(pos, newOrg);
    }

    public void SetWorldPlayer(Organism newPlayer)
    {
        this.world.SetPlayer(newPlayer);
    }
    public void SetAge(int newAge)
    {
        this.age = newAge;
    }

    public void SetCanPlay(boolean newCanPlay)
    {
        this.canPlay = newCanPlay;
    }

    public void SetStrength(int newStrength)
    {
        this.strength = newStrength;
    }


    public void AddOrganism(Organism newOrganism)
    {
        this.world.AddOrganism(newOrganism);
    }

    public void AddLog(String newLog)
    {
        this.world.AddLog(newLog);
    }

//    bool Organism::CompareCondition(Organism* left, Organism* right)
//    {
//        if (left->GetInitiative() == right->GetInitiative())
//        {
//            return left->GetAge() > right->GetAge();
//        }
//        else
//        {
//            return left->GetInitiative() > right->GetInitiative();
//        }
//    }

    public boolean DefendedAttack(Organism attacker)
    {
        return false;
    }

    protected abstract void Action();
    protected abstract void Collision(Organism other);
    protected abstract Organism SpawnNew(Point pos);
    public void Kill()
    {
        this.SetCanPlay(false);
        this.SetWorldField(this.GetPosition(), Defines.EMPTY_SIGN);
        this.SetWorldOrgField(this.GetPosition(), null);
        this.SetPosition(new Point(Defines.OUT_OF_MAP, Defines.OUT_OF_MAP));
    }

    public Point FindNeighbourPos(Organism org)
    {
        boolean[] possiblePosTaken = {false, false, false, false};

        while (possiblePosTaken[0] == false || possiblePosTaken[1] == false
                || possiblePosTaken[2] == false || possiblePosTaken[3] == false)
        {
            int newPossPos = (int) (Math.random() * 4);
            switch (newPossPos)
            {
                case Defines.LEFT:
                    if (possiblePosTaken[Defines.LEFT] == false)
                    {
                        if (org.GetPosX() != 0 && org.GetWorld().GetField(org.GetPosX() - 1, org.GetPosY()) == Defines.EMPTY_SIGN)
                        {
                            return new Point(org.GetPosX() - 1, org.GetPosY());
                            //return LEFT;
                        }
                        else
                        {
                            possiblePosTaken[Defines.LEFT] = true;
                        }
                    }
                    break;
                case Defines.RIGHT:
                    if (possiblePosTaken[Defines.RIGHT] == false)
                    {
                        if (org.GetPosX() != this.GetWorld().GetSizeX() - 1 && org.GetWorld().GetField(org.GetPosX() + 1, org.GetPosY()) == Defines.EMPTY_SIGN)
                        {
                            return new Point(org.GetPosX() + 1, org.GetPosY());
                            //return RIGHT;
                        }
				else
                        {
                            possiblePosTaken[Defines.RIGHT] = true;
                        }
                    }
                    break;
                case Defines.UP:
                    if (possiblePosTaken[Defines.UP] == false)
                    {
                        if (org.GetPosY() != 0 && org.GetWorld().GetField(org.GetPosX(), org.GetPosY() - 1) == Defines.EMPTY_SIGN)
                        {
                            return new Point(org.GetPosX(), org.GetPosY() - 1);
                            //return UP;
                        }
                        else
                        {
                            possiblePosTaken[Defines.UP] = true;
                        }
                    }
                    break;
                case Defines.DOWN:
                    if (possiblePosTaken[Defines.DOWN] == false)
                    {
                        if (org.GetPosY() != this.GetWorld().GetSizeY() - 1 && org.GetWorld().GetField(org.GetPosX(), org.GetPosY() + 1) == Defines.EMPTY_SIGN)
                        {
                            return new Point(org.GetPosX(), org.GetPosY() + 1);
                            //return DOWN;
                        }
				else
                        {
                            possiblePosTaken[Defines.DOWN] = true;
                        }
                    }
                    break;
            }
        }
        return new Point(Defines.OUT_OF_MAP,Defines.OUT_OF_MAP);
    }
}
