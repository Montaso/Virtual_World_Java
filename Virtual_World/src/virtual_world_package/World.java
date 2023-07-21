package virtual_world_package;

import virtual_world_package.Animals.*;
import virtual_world_package.Plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class World {
    private final Point worldSize;
    private final JFrame frame;
    private JButton[][] field;
    private final JButton nextTurnBtn;
    private final JButton saveBtn;
    private final JButton loadBtn;
    private final JButton powerBtn;
    private JLabel[] logsLabel;
    private final JLabel[] legendLabel;
    private JPanel boardButtonsPanel;
    private JPanel logsPanel;
    private JPanel functionalButtonsPanel;
    private JPanel legendPanel;

    private Vector<Organism> organisms;
    private Vector<String> roundLogs;
    private char[][] charField;
    private int rounds;
    private Organism[][] orgBoard;
    private Organism player;

    public World(int sizeX, int sizeY) {
        frame = new JFrame("Virtual World 193428");

        boardButtonsPanel = new JPanel();
        logsPanel = new JPanel();
        functionalButtonsPanel = new JPanel();
        legendPanel = new JPanel();

        legendLabel = new JLabel[10];
        logsLabel = new JLabel[sizeY];

        nextTurnBtn = new JButton("Next Turn");
        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");
        powerBtn = new JButton("Activate Power");

        field = new JButton[sizeY][sizeX];
        charField = new char[sizeY][sizeX];
        orgBoard = new Organism[sizeY][sizeX];

        worldSize = new Point(sizeX, sizeY);
        rounds = 0;
        organisms = new Vector<>();
        roundLogs = new Vector<>();

        SetPanels();
        SetLegend();
        SetFunctionalButtons();
        SetFrame();

        //logsLabel.setBounds(sizeX * (buttonSize+1), 0, sizeX * (buttonSize - 1), sizeY * buttonSize + 100);
        //frame.add(logsLabel);

        for (int i = 0; i < sizeY; i++) {
            logsLabel[i] = new JLabel();
            //logsLabel[i].setBounds(sizeX * (Defines.BUTTON_SIZE+1), i*Defines.BUTTON_SIZE, sizeX * (Defines.BUTTON_SIZE-1), Defines.BUTTON_SIZE);
            logsLabel[i].setBackground(Color.white);
            logsPanel.add(logsLabel[i]);
            for (int j = 0; j < sizeX; j++) {
                charField[i][j] = Defines.EMPTY_SIGN;
                orgBoard[i][j] = null;

                field[i][j] = new JButton();
                //field[i][j].setBounds(j * Defines.BUTTON_SIZE, i * Defines.BUTTON_SIZE, Defines.BUTTON_SIZE,Defines.BUTTON_SIZE);
                field[i][j].setBackground(Color.gray);
                field[i][j].addActionListener(new ButtonClickListener(j, i, field[i][j], this));
                field[i][j].setFocusable(false);
                //field[i][j].setFont(new Font("Arial", Font.BOLD, 16));

                boardButtonsPanel.add(field[i][j]);
            }
        }
        {
            organisms.addElement(new Human(new Point(1, 0), this));
            for (int i = 0; i < Defines.WOLFS_START; i++) {
                organisms.addElement(new Wolf(true, this));
            }
            for (int i = 0; i < Defines.SHEEP_START; i++) {
                organisms.addElement(new Sheep(true, this));
            }
            for (int i = 0; i < Defines.FOX_START; i++) {
                organisms.addElement(new Fox(true, this));
            }
            for (int i = 0; i < Defines.TURTLE_START; i++) {
                organisms.addElement(new Turtle(true, this));
            }
            for (int i = 0; i < Defines.ANTELOPE_START; i++) {
                organisms.addElement(new Antelope(true, this));
            }
            for (int i = 0; i < Defines.GRASS_START; i++) {
                organisms.addElement(new Grass(true, this));
            }
            for (int i = 0; i < Defines.DANDELION_START; i++) {
                organisms.addElement(new Dandelion(true, this));
            }
            for (int i = 0; i < Defines.GUARANA_START; i++) {
                organisms.addElement(new Guarana(true, this));
            }
            for (int i = 0; i < Defines.WOLFBERRY_START; i++) {
                organisms.addElement(new WolfBerry(true, this));
            }
            for (int i = 0; i < Defines.GIANTHOGWEED_START; i++) {
                organisms.addElement(new Hogweed(true, this));
            }
        }
        this.SortOrganisms();
        this.Draw();

        frame.setVisible(true);
        frame.setExtendedState(JFrame.NORMAL);
        frame.toFront();
        frame.requestFocusInWindow();
    }

    private void SetPanels()
    {
        boardButtonsPanel.setBounds(0, 0, GetSizeX() * Defines.BUTTON_SIZE, GetSizeY() * Defines.BUTTON_SIZE);
        boardButtonsPanel.setBackground(Color.red);
        boardButtonsPanel.setLayout(new GridLayout(GetSizeY(), GetSizeX()));
        boardButtonsPanel.setFocusable(false);

        logsPanel.setBounds((GetSizeX() + 1) * (Defines.BUTTON_SIZE), 0, (GetSizeX() - 1) * (Defines.BUTTON_SIZE), GetSizeY() * Defines.BUTTON_SIZE);
        logsPanel.setBackground(Color.cyan);
        logsPanel.setLayout(new GridLayout(GetSizeY(), 1));
        logsPanel.setFocusable(false);

        functionalButtonsPanel.setBounds(0, (GetSizeY() + 1) * (Defines.BUTTON_SIZE), GetSizeX() * Defines.BUTTON_SIZE, Defines.BUTTON_SIZE);
        functionalButtonsPanel.setBackground(Color.darkGray);
        functionalButtonsPanel.setLayout(new GridLayout(1, 4));
        functionalButtonsPanel.setFocusable(false);

        legendPanel.setBounds(0, (GetSizeY() + 3) * (Defines.BUTTON_SIZE), GetSizeX() * Defines.BUTTON_SIZE * 2 + Defines.BUTTON_SIZE, Defines.BUTTON_SIZE*2);
        legendPanel.setBackground(Color.darkGray);
        legendPanel.setLayout(new GridLayout(2, 5));
        legendPanel.setFocusable(false);
    }
    private void SetLegend()
    {

        for(int i = 0; i < 10; i++)
        {
            legendLabel[i]= new JLabel();
            legendLabel[i].setOpaque(true);
            legendPanel.add(legendLabel[i]);
        }
        legendLabel[0].setText("Wilk");
        legendLabel[0].setBackground(new Color(81, 0, 14));
        legendLabel[1].setText("Owca");
        legendLabel[1].setBackground(Color.white);
        legendLabel[2].setText("Lis");
        legendLabel[2].setBackground(Color.cyan);
        legendLabel[3].setText("Zolw");
        legendLabel[3].setBackground(new Color(39, 119, 0));
        legendLabel[4].setText("Antylopa");
        legendLabel[4].setBackground(Color.blue);
        legendLabel[5].setText("Trawa");
        legendLabel[5].setBackground(Color.green);
        legendLabel[6].setText("Mlecz");
        legendLabel[6].setBackground(Color.yellow);
        legendLabel[7].setText("Guarana");
        legendLabel[7].setBackground(Color.red);
        legendLabel[8].setText("Wilcze Jagody");
        legendLabel[8].setBackground(new Color(108, 0, 209));
        legendLabel[9].setText("Barszcz sosnowskiego");
        legendLabel[9].setBackground(new Color(59, 158, 249));
    }
    private void SetFunctionalButtons()
    {
        nextTurnBtn.addActionListener(new NextTurnListener(this));
        nextTurnBtn.setFocusable(false);
        functionalButtonsPanel.add(nextTurnBtn);

        saveBtn.addActionListener(new SaveBtnListener(this));
        saveBtn.setFocusable(false);
        functionalButtonsPanel.add(saveBtn);

        loadBtn.addActionListener(new LoadBtnListener(this));
        loadBtn.setFocusable(false);
        functionalButtonsPanel.add(loadBtn);

        powerBtn.addActionListener(new PowerBtnListener(this));
        powerBtn.setFocusable(false);
        functionalButtonsPanel.add(powerBtn);
    }
    private void SetFrame()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setFocusable(true);
        //frame.requestFocusInWindow();
        frame.add(boardButtonsPanel);
        frame.add(logsPanel);
        frame.add(functionalButtonsPanel);
        frame.add(legendPanel);
        frame.getContentPane().setBackground(Color.pink);
        frame.setSize(GetSizeX() * Defines.BUTTON_SIZE * 2 + Defines.BUTTON_SIZE, (GetSizeY() + 7) * Defines.BUTTON_SIZE);
        frame.setBackground(Color.pink);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    SetPlayerInput(Defines.LEFT);
                    //System.out.println("Pressed left arrow");
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    SetPlayerInput(Defines.DOWN);
                    //System.out.println("Pressed down arrow");
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    SetPlayerInput(Defines.RIGHT);
                    //System.out.println("Pressed right arrow");
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    SetPlayerInput(Defines.UP);
                    //System.out.println("Pressed up arrow");
                }
                else
                {
                    return;
                }
                DoRound();
            }
        });
    }

    public JButton GetButton(int x, int y) {
        return field[y][x];
    }

    public JFrame GetFrame() {
        return frame;
    }

    public JButton GetNextTurnBtn() {
        return nextTurnBtn;
    }

    public JButton GetSaveBtn() {
        return saveBtn;
    }

    public JButton GetLoadBtn() {
        return loadBtn;
    }
    public JButton GetPowerBtn()
    {
        return powerBtn;
    }

    public char GetField(int x, int y) {
        return this.charField[y][x];
    }

    public Organism GetOrgField(Point pos)
    {
        return this.orgBoard[pos.y][pos.x];
    }


    public int GetSizeX() {
        return this.worldSize.x;
    }

    public int GetSizeY() {
        return this.worldSize.y;
    }

    public int GetRounds() {
        return this.rounds;
    }

    public int GetOrganismsSize()
    {
        return organisms.size();
    }

    public Organism GetPlayer()
    {
        return this.player;
    }
    public boolean GetPlayerPowerActivated()
    {
        if (this.player != null)
        {
            Human player = (Human)(this.player);
            return player.GetPowerActivated();
        }
	    else return false;
    }
    public int GetPlayerCooldown()
    {
        if (this.player != null)
        {
            Human player = (Human)(this.player);
            return player.GetCooldown();
        }
	    else return 0;
    }

    public void SetField(int x, int y, char sign) {
        this.charField[y][x] = sign;
    }

    public void SetRounds(int newRounds) {
        this.rounds = newRounds;
    }

    public void SetPlayer(Organism newPlayer)
    {
        this.player = newPlayer;
    }
    public void SetPlayerInput(int input)
    {
        Human tmp = (Human)(this.player);
        if(tmp != null)
        {
            tmp.SetInputDirection(input);
        }
    }

    public void SetOrgField(Point pos, Organism org)
    {
        orgBoard[pos.y][pos.x] = org;
    }

    public void TurnOnPlayerAbility()
    {
        Human player = (Human)(this.player);
        player.SetPower(true);
        player.SetCooldown(Defines.ABILITY_COOLDOWN + Defines.ABILITY_DURATION);
    }

    public void SetSizeX(int newSize)
    {
        this.worldSize.x = newSize;
    }
    public void SetSizeY(int newSize)
    {
        this.worldSize.y = newSize;
    }
    public boolean IsInBounds(Point pos)
    {
        if (pos.x >= this.GetSizeX() || pos.x < 0)
        {
            return false;
        }
	    else if (pos.y >= this.GetSizeY() || pos.y < 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void AddOrganism(Organism newOrganism)
    {
        this.organisms.add(newOrganism);
        String log = "Pojawia sie nowy organizm: " + newOrganism.GetName() + ", na pozycji (" + newOrganism.GetPosX()
                + ";" + newOrganism.GetPosY() + ")";
        this.AddLog(log);
    }
    public void AddLog(String newLog)
    {
        this.roundLogs.addElement(newLog);
    }

    public void SortOrganisms()
    {
        Collections.sort(organisms, new Comparator<Organism>() {
            @Override
            public int compare(Organism o1, Organism o2) {
                // Compare by strength

                int strengthComparison = Integer.compare(o2.GetInitiative(), o1.GetInitiative());
                if (strengthComparison != 0) {
                    return strengthComparison;
                }

                // Compare by initiative if strength is equal
                return Integer.compare(o2.GetStrength(), o1.GetStrength());
            }
        });
    }

    public Organism FindOrgByPos(Point pos)
    {
        return this.GetOrgField(pos);
    }
    public void DoRound()
    {
        this.ClearLogs();
        String roundNmb = "Runda " + this.GetRounds();
        this.AddLog(roundNmb);

        int addedOrganisms = this.GetOrganismsSize();

        for (int i = 0; i < organisms.size(); i++) {
            organisms.get(i).Action();
            organisms.get(i).SetAge(organisms.get(i).GetAge() + 1);
            //System.out.println("organism Pos: " + organisms.get(i).pos.x + ", " + organisms.get(i).pos.y);
        }

        addedOrganisms = this.GetOrganismsSize() - addedOrganisms;
        this.ClearDefeatedOrganisms();

        if(addedOrganisms > 0)
        {
            this.SortOrganisms();
        }
        this.SetRounds(this.GetRounds() + 1);
        //System.out.println("Organisms size: " + organisms.size());
        this.Draw();
        this.PrintLogs();
    }
    public void Draw()
    {
        for(int i = 0; i < this.GetSizeY(); i++)
        {
            for(int j = 0; j < this.GetSizeX(); j++)
            {
                if(this.GetOrgField(new Point(j, i)) != null)
                {
                    this.GetButton(j, i).setBackground(this.GetOrgField(new Point(j, i)).GetColor());
                    //this.GetButton(j, i).setText(String.valueOf(this.GetOrgField(new Point(j, i)).Symbol()));
                }
                else {
                    this.GetButton(j, i).setBackground(Color.gray);
                    //this.GetButton(j, i).setText("");
                }
            }
        }
    }

    public void PrintLogs()
    {
        for(int i = 0; i < GetSizeY() && i < roundLogs.size(); i++)
        {
            logsLabel[i].setText(roundLogs.get(i));
        }
    }


    public void ClearDefeatedOrganisms()
    {
        organisms.removeIf(organism -> !(organism.GetCanPlay()) && organism.GetPosX() == Defines.OUT_OF_MAP);
    }

    public void ClearLogs()
    {
        if(this.roundLogs != null)
        {
            this.roundLogs.removeAllElements();
        }
    }

    public void ClearWorld()
    {
        charField = null;
        orgBoard = null;
        organisms.removeAllElements();
        roundLogs.removeAllElements();
        this.SetRounds(0);
        this.SetSizeX(0);
        this.SetSizeY(0);
        this.SetPlayer(null);
    }
    public void ResetBoard(int newSizeX, int newSizeY)
    {
        frame.remove(logsPanel);
        frame.remove(functionalButtonsPanel);
        frame.remove(boardButtonsPanel);
        frame.remove(legendPanel);
        frame.setSize(newSizeX * Defines.BUTTON_SIZE * 2 + Defines.BUTTON_SIZE, (newSizeY + 7) * Defines.BUTTON_SIZE);

        boardButtonsPanel = new JPanel();
        logsPanel = new JPanel();
        functionalButtonsPanel = new JPanel();
        legendPanel = new JPanel();

        SetPanels();

        functionalButtonsPanel.add(nextTurnBtn);
        functionalButtonsPanel.add(saveBtn);
        functionalButtonsPanel.add(loadBtn);
        functionalButtonsPanel.add(powerBtn);
        powerBtn.setEnabled(false);

        for(int i = 0; i < 10; i++)
        {
            legendPanel.add(legendLabel[i]);
        }

        logsLabel = new JLabel[newSizeY];
        field = new JButton[newSizeY][newSizeX];
        charField = new char[newSizeY][newSizeX];
        orgBoard = new Organism[newSizeY][newSizeX];
        for(int i = 0; i < newSizeY; i++)
        {
            logsLabel[i] = new JLabel();
            logsLabel[i].setBackground(Color.white);
            logsPanel.add(logsLabel[i]);
            for(int j = 0; j < newSizeX; j++)
            {
                charField[i][j] = Defines.EMPTY_SIGN;
                orgBoard[i][j] = null;

                field[i][j] = new JButton();
                //field[i][j].setBounds(j * Defines.BUTTON_SIZE, i * Defines.BUTTON_SIZE, Defines.BUTTON_SIZE,Defines.BUTTON_SIZE);
                field[i][j].setBackground(Color.gray);
                field[i][j].addActionListener(new ButtonClickListener(j, i, field[i][j], this));
                field[i][j].setFocusable(false);
                //field[i][j].setFont(new Font("Arial", Font.BOLD, 16));

                boardButtonsPanel.add(field[i][j]);
            }
        }
        frame.add(boardButtonsPanel);
        frame.add(logsPanel);
        frame.add(functionalButtonsPanel);
        frame.add(legendPanel);
    }

    public int SaveWorld() {
        try {
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(Defines.SAVE_BOARD_FILE_NAME));

            outputFile.write(GetSizeX() + " " + GetSizeY() + " " + rounds + " " + organisms.size() + "\n");

            for (int i = 0; i < organisms.size(); i++) {
                outputFile.write(
                        organisms.get(i).Symbol() + " " +
                                organisms.get(i).GetStrength() + " " +
                                organisms.get(i).GetInitiative() + " " +
                                organisms.get(i).GetAge() + " " +
                                organisms.get(i).GetCanPlay() + " " +
                                organisms.get(i).GetPosX() + " " +
                                organisms.get(i).GetPosY() + " "
                );


                if(organisms.get(i) instanceof Animal)
                {
                    Animal tmp = (Animal) organisms.get(i);
                    if (tmp != null) {
                        outputFile.write(tmp.GetPrevPos().x + " " + tmp.GetPrevPos().y + " ");
                    }
                }
                if (organisms.get(i) instanceof Human) {
                    Human tmp = (Human) organisms.get(i);
                    outputFile.write(
                            tmp.GetInputDirection() + " " +
                                    tmp.GetPowerActivated() + " " +
                                    tmp.GetCooldown() + " "
                    );
                }
                outputFile.write("\n");
            }

            outputFile.write(roundLogs.size() + "\n");
            for (int i = 0; i < roundLogs.size(); i++) {
                outputFile.write(roundLogs.get(i) + "\n");
            }

            outputFile.close();
            return Defines.LOADSAVE_COMPLETE;
        } catch (IOException e) {
            e.printStackTrace();
            return Defines.SAVING_ERROR;
        }
    }
    public int LoadWorld() {
        try {
            BufferedReader inputFile = new BufferedReader(new FileReader(Defines.SAVE_BOARD_FILE_NAME));

            this.ClearWorld();

            int newSizeX, newSizeY, newRounds, orgSize;
            char symbol;
            int newStrength, newInitiative, newAge;
            boolean newCanPlay, newPowerActivated;
            Point newPos, prevPos;
            int newInputDir, newCooldown;

            int newLogSize;
            String log;

            String[] worldInfo = inputFile.readLine().split(" ");
            newSizeX = Integer.parseInt(worldInfo[0]);
            newSizeY = Integer.parseInt(worldInfo[1]);
            newRounds = Integer.parseInt(worldInfo[2]);
            orgSize = Integer.parseInt(worldInfo[3]);

            this.SetSizeX(newSizeX);
            this.SetSizeY(newSizeY);
            this.SetRounds(newRounds);

            this.ResetBoard(newSizeX, newSizeY);

            for (int i = 0; i < orgSize; i++) {
                String[] organismInfo = inputFile.readLine().split(" ");
                symbol = organismInfo[0].charAt(0);
                newStrength = Integer.parseInt(organismInfo[1]);
                newInitiative = Integer.parseInt(organismInfo[2]);
                newAge = Integer.parseInt(organismInfo[3]);
                newCanPlay = Boolean.parseBoolean(organismInfo[4]);
                newPos = new Point(Integer.parseInt(organismInfo[5]), Integer.parseInt(organismInfo[6]));

                switch (symbol) {
                    case Defines.HUMAN_SIGN:
                        organisms.add(new Human(newPos, this));
                        newInputDir = Integer.parseInt(organismInfo[7]);
                        newPowerActivated = Boolean.parseBoolean(organismInfo[8]);
                        newCooldown = Integer.parseInt(organismInfo[9]);
                        Human tmp = (Human) organisms.get(i);
                        tmp.SetInputDirection(newInputDir);
                        tmp.SetPower(newPowerActivated);
                        tmp.SetCooldown(newCooldown);
                        this.SetPlayer(organisms.get(i));

                        if(newCooldown == 0) powerBtn.setEnabled(true);
                        break;
                    case Defines.WOLF_SIGN:
                        organisms.add(new Wolf(newPos, this));
                        break;
                    case Defines.SHEEP_SIGN:
                        organisms.add(new Sheep(newPos, this));
                        break;
                    case Defines.FOX_SIGN:
                        organisms.add(new Fox(newPos, this));
                        break;
                    case Defines.TURTLE_SIGN:
                        organisms.add(new Turtle(newPos, this));
                        break;
                    case Defines.ANTELOPE_SIGN:
                        organisms.add(new Antelope(newPos, this));
                        break;
                    case Defines.GRASS_SIGN:
                        organisms.add(new Grass(newPos, this));
                        break;
                    case Defines.DANDELION_SIGN:
                        organisms.add(new Dandelion(newPos, this));
                        break;
                    case Defines.WOLFBERRY_SIGN:
                        organisms.add(new WolfBerry(newPos, this));
                        break;
                    case Defines.GUARANA_SIGN:
                        organisms.add(new Guarana(newPos, this));
                        break;
                    case Defines.GIANTHOGWEED_SIGN:
                        organisms.add(new Hogweed(newPos, this));
                        break;
                }

                if (organisms.get(i) instanceof Animal tmp) {
                    prevPos = new Point(Integer.parseInt(organismInfo[7]), Integer.parseInt(organismInfo[8]));
                    tmp.SetPrevPos(prevPos);
                }

                organisms.get(i).SetAge(newAge);
                organisms.get(i).SetCanPlay(newCanPlay);
                organisms.get(i).SetStrength(newStrength);

                this.SetOrgField(newPos, organisms.get(i));
            }
            newLogSize = Integer.parseInt(inputFile.readLine());
            for (int i = 0; i < newLogSize; i++) {
                log = inputFile.readLine();
                if (log.equals("")) {
                    i--;
                    continue;
                }
                roundLogs.add(log);
            }

            inputFile.close();
            return Defines.LOADSAVE_COMPLETE;
        } catch (IOException e) {
            e.printStackTrace();
            return Defines.LOADING_ERROR;
        }
    }

    private record LoadBtnListener(World world) implements ActionListener {
        public void actionPerformed(ActionEvent event)
        {
            //world.GetFrame().setVisible(false);
            world.LoadWorld();

            world.GetFrame().revalidate();
            world.GetFrame().repaint();

            world.Draw();
            world.PrintLogs();
        }
    }
    private record SaveBtnListener(World world) implements ActionListener {
        public void actionPerformed(ActionEvent event)
        {
            world.SaveWorld();
            System.out.println("World saved!");
        }
    }
    private record PowerBtnListener(World world) implements ActionListener {
        public void actionPerformed(ActionEvent event)
        {
            if(world.player != null && world.GetPlayerCooldown() == 0)
            {
                world.TurnOnPlayerAbility();
                world.powerBtn.setEnabled(false);
            }

        }
    }

    private record ButtonClickListener(int x, int y, JButton pressedBtn, World world) implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            System.out.println("Button clicked at position: (" + x + ", " + y + ")");
            //pressedBtn.setBackground(Color.magenta);
            if(world.GetOrgField(new Point(x, y)) == null)
            {
                JPopupMenu popupMenu = new JPopupMenu();

                String[] menuItems = {"Wolf", "Sheep", "Fox", "Turtle", "Antelope",
                        "Grass", "Dandelion", "Guarana", "WolfBerry", "Sosnowsky's Hogweed"};

                for (String item : menuItems) {
                    JMenuItem menuItem = new JMenuItem(item);
                    menuItem.addActionListener(new PopupMenuItemListener(item, x, y, world));
                    popupMenu.add(menuItem);
                }

                popupMenu.show(pressedBtn, 0, pressedBtn.getHeight());
            }
        }
    }

    private record PopupMenuItemListener(String item, int x, int y, World world) implements ActionListener {
        public void actionPerformed(ActionEvent event) {
                System.out.println("Selected: " + item);

            switch (item) {
                case "Wolf" -> world.organisms.addElement(new Wolf(true, new Point(x, y), world));
                case "Sheep" -> world.organisms.addElement(new Sheep(true, new Point(x, y), world));
                case "Fox" -> world.organisms.addElement(new Fox(true, new Point(x, y), world));
                case "Turtle" -> world.organisms.addElement(new Turtle(true, new Point(x, y), world));
                case "Antelope" -> world.organisms.addElement(new Antelope(true, new Point(x, y), world));
                case "Grass" -> world.organisms.addElement(new Grass(true, new Point(x, y), world));
                case "Dandelion" -> world.organisms.addElement(new Dandelion(true, new Point(x, y), world));
                case "Guarana" -> world.organisms.addElement(new Guarana(true, new Point(x, y), world));
                case "WolfBerry" -> world.organisms.addElement(new WolfBerry(true, new Point(x, y), world));
                case "Sosnowsky's Hogweed" -> world.organisms.addElement(new Hogweed(true, new Point(x, y), world));
            }
                world.SortOrganisms();
                world.Draw();
            }
        }
    private record NextTurnListener(World world) implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {
            world.DoRound();
        }
    }
}
