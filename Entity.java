package Lab1;

public class Entity {
    private static int idCounter = 1;
    protected final long id;
    protected String title;
    protected double posX;
    protected double posZ;
    protected boolean agressive;
    protected int maxHealth;
    protected int health;
    protected int attackDamage;
    protected boolean deth = false;

    public Entity(String title, double posX, double posZ, boolean agressive, int maxHealth, int health, int attackDamage) {
        this.title = title;
        this.posX = posX;
        this.posZ = posZ;
        this.agressive = agressive;
        this.maxHealth = maxHealth;
        this.health = health;
        this.attackDamage = attackDamage;
        this.id = idCounter++;
    }

    public Entity(String title, double posX, double posZ, boolean agressive, int maxHealth, int attackDamage) {
        this.title = title;
        this.posX = posX;
        this.posZ = posZ;
        this.agressive = agressive;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackDamage = attackDamage;
        this.id = idCounter++;
    }

    public void update()
    {
        int indexOfNearerEntity = -1;
        double distanceForNearerEntity = 0;
        if (this.agressive)
        {
            for(int i = 0; i < GameServer.getInstance().getEntities().length;i++)
            {
                if(GameServer.getInstance().getEntities()[i] != null && GameServer.getInstance().getEntities()[i] != this){
                    double distance = (Math.pow((GameServer.getInstance().getEntities()[i].posX-this.posX),2)+Math.pow((GameServer.getInstance().getEntities()[i].posZ-this.posZ),2));
                    if(distance <= 400 && !GameServer.getInstance().getEntities()[i].agressive)
                    {
                        if(indexOfNearerEntity == -1)
                        {
                            indexOfNearerEntity = i;
                            distanceForNearerEntity = distance;
                        }
                        else
                        {
                            if(distance < distanceForNearerEntity)
                            {
                                indexOfNearerEntity = i;
                                distanceForNearerEntity = distance;
                            }
                        }

                    }
                }
            }
            if(distanceForNearerEntity < 4 && indexOfNearerEntity != -1)
            {
                this.attack(GameServer.getInstance().getEntities()[indexOfNearerEntity]);
            }
            else if (indexOfNearerEntity != -1)
            {
                if(this.posX != GameServer.getInstance().getEntities()[indexOfNearerEntity].posX)
                {
                    this.posX += (GameServer.getInstance().getEntities()[indexOfNearerEntity].posX-this.posX)/(Math.abs(GameServer.getInstance().getEntities()[indexOfNearerEntity].posX-this.posX));
                }
                if(this.posZ != GameServer.getInstance().getEntities()[indexOfNearerEntity].posZ)
                {
                    this.posZ += (GameServer.getInstance().getEntities()[indexOfNearerEntity].posZ-this.posZ)/(Math.abs(GameServer.getInstance().getEntities()[indexOfNearerEntity].posZ-this.posZ));
                }
            }
        }
    }

    public  void attack(Entity entity)
    {
        if(entity instanceof EntityPlayer)
        {
            entity.health -= this.attackDamage + 0.5 * GameServer.getInstance().getDifficulty();
            if(entity.health > 0)
            {
                this.health -= entity.attackDamage + 0.5 * GameServer.getInstance().getDifficulty();
            }
            else
            {
                System.out.println("Player " + ((EntityPlayer) entity).getName() + " was killed by " + this.title + " on server update " + GameServer.getInstance().getCounterOfUpdates());
                entity.deth = true;
            }
            if(this.health <= 0)
            {
                System.out.println("Player " + ((EntityPlayer) entity).getName() + " killed entity " + this.title + " on server update " + GameServer.getInstance().getCounterOfUpdates());
                this.deth = true;
            }
        }
        else
        {
            entity.health -= this.attackDamage + 0.5 * GameServer.getInstance().getDifficulty();
            if(entity.health <= 0)
            {
                System.out.println("Entity " + this.title + " killed entity " + entity.title + " on server update " + GameServer.getInstance().getCounterOfUpdates());
                entity.deth = true;
            }
        }
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", posX=" + posX +
                ", posZ=" + posZ +
                ", agressive=" + agressive +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                ", attackDamage=" + attackDamage +
                '}';
    }
}
