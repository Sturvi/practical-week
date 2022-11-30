public abstract class Player {
    int hp;
    int level;
    String type;
    String weapon;

    public Player(int hp, int level, String type, String weapon) {
        this.hp = hp;
        this.level = level;
        this.type = type;
        this.weapon = weapon;
    }

    abstract String getFullInfo();
    abstract void levelUp();
    abstract boolean doDamage();
}
