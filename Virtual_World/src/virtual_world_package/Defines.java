package virtual_world_package;

public class Defines {

    public static final int BUTTON_SIZE = 20;

    public static final int WOLFS_START = 10;
    public static final int SHEEP_START = 2;
    public static final int FOX_START = 2;
    public static final int TURTLE_START = 2;
    public static final int ANTELOPE_START = 2;
    public static final int GRASS_START = 2;
    public static final int DANDELION_START = 2;
    public static final int GUARANA_START = 2;
    public static final int WOLFBERRY_START = 2;
    public static final int GIANTHOGWEED_START = 1;

    public static final char EMPTY_SIGN = '.';
    public static final char WOLF_SIGN = 'W';
    public static final char SHEEP_SIGN = 'O';
    public static final char FOX_SIGN = 'L';
    public static final char TURTLE_SIGN = 'Z';
    public static final char ANTELOPE_SIGN = 'A';
    public static final char GRASS_SIGN = 'T';
    public static final char DANDELION_SIGN = 'M';
    public static final char GUARANA_SIGN = 'G';
    public static final char WOLFBERRY_SIGN = 'J';
    public static final char GIANTHOGWEED_SIGN = 'B';
    public static final char HUMAN_SIGN = 'C';


    public static final int OUT_OF_MAP = -1;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final int NO_DIRECTION = -1;

    public static final String WOLF_NAME = "Wilk";
    public static final int WOLF_STRENGTH = 9;
    public static final int WOLF_INITIATIVE = 5;

    public static final String SHEEP_NAME = "Owca";
    public static final int SHEEP_STRENGTH = 4;
    public static final int SHEEP_INITIATIVE = 4;

    public static final String HUMAN_NAME = "Czlowiek";
    public static final int HUMAN_STRENGTH = 5;
    public static final int HUMAN_INITIATIVE = 4;
    public static final int ABILITY_COOLDOWN = 5;
    public static final int ABILITY_DURATION = 5;

    public static final String FOX_NAME = "Lis";
    public static final int FOX_STRENGTH = 3;
    public static final int FOX_INITIATIVE = 7;

    public static final String TURTLE_NAME = "Zolw";
    public static final int TURTLE_STRENGTH = 2;
    public static final int TURTLE_INITIATIVE = 1;
    public static final int TURTLE_DEFENSE = 5;

    public static final String ANTELOPE_NAME = "Antylopa";
    public static final int ANTELOPE_STRENGTH = 3;
    public static final int ANTELOPE_INITIATIVE = 4;

    public static final int PLANT_STRENGTH = 0;
    public static final int PLANT_INITIATIVE = 0;

    public static final String GRASS_NAME = "Trawa";
    public static final String DANDELION_NAME = "Mlecz";
    public static final String GUARANA_NAME = "Guarana";
    public static final int GUARANA_BONUS_ATTACK = 3;

    public static final String WOLFBERRY_NAME = "Wilcza jagoda";
    public static final int WOLFBERRY_STRENGTH = 99;

    public static final String GIANTHOGWEED_NAME = "Barszcz Sosnowskiego";
    public static final int GIANTHOGWEED_STRENGTH = 10;

    public static final String SAVE_BOARD_FILE_NAME = "save.txt";
    public static final int SAVING_ERROR = 0;
    public static final int LOADING_ERROR = 0;
    public static final int LOADSAVE_COMPLETE = 1;
}
