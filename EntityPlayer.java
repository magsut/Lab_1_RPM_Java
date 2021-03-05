package Lab1;

public class EntityPlayer extends Entity
{
    private String name;

    public EntityPlayer(String title, double posX, double posZ, int maxHealth, int health, int attackDamage, String name) {
        super(title, posX, posZ, false, maxHealth, health, attackDamage);
        this.name = name;
    }

    public EntityPlayer(String title, double posX, double posZ, int maxHealth, int attackDamage, String name) {
        super(title, posX, posZ, false, maxHealth, attackDamage);
        this.name = name;
    }

    @Override
    public void update() {
        super.update();
        if(GameServer.getInstance().getCounterOfUpdates()%2 == 0)
        {
            if(this.health < this.maxHealth)
            {
                this.health++;
            }
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "EntityPlayer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", posX=" + posX +
                ", posZ=" + posZ +
                ", agressive=" + agressive +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                ", attackDamage=" + attackDamage +
                ", name='" + name + '\'' +
                '}';
    }
}
