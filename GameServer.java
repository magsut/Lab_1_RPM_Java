package Lab1;

import java.util.Arrays;

public class GameServer {
    private String ip;
    private int difficulty;
    private static GameServer instance;
    private Entity[] entities;
    private int counterOfUpdates = 0;

    private void updateServer()
    {
        for (int i = 0; i < entities.length;i++)
        {
            if(entities[i] != null)
            {
                entities[i].update();
                for(int indexDethEntity = 0; indexDethEntity < entities.length;indexDethEntity++)
                {
                    if(entities[indexDethEntity] != null)
                    {
                        if(entities[indexDethEntity].deth)
                        {
                            entities[indexDethEntity] = null;
                        }
                    }
                }
            }
        }
        counterOfUpdates++;
    }

    public static void main(String[] args)
    {
        GameServer lab1 = new GameServer();
    }

    public GameServer()
    {
        instance = this;

        ip = "228.228.228";

        difficulty = 1;

        Entity Zombie1 = new Entity("Zombie1", 0, 0, true, 140, 40, 10);
        Entity Skeleton = new Entity("Skeleton", 55, 55, true, 80, 80, 10);
        Entity Pig = new Entity("Pig1", 3, 3, false, 20, 20, 0);
        Entity Human = new EntityPlayer("Human", 65, 63, 100, 100, 20, "lololoshka");
        Entity Zombie2 = new Entity("Zombie2", -2, 2, true, 40, 2);
        Entity Pig1 = new Entity("Pig2",-1,2,false,20,20,0);

        entities = new Entity[]{Zombie1, Skeleton, Pig, Human,Zombie2,Pig1
        };

        System.out.println(getInstance());

        while (counterOfUpdates<15)
        {
            updateServer();
        }

        System.out.println(getInstance());

    }

    public static GameServer getInstance() {
        return instance;
    }

    public Entity[] getEntities() {
        return entities;
    }

    public int getCounterOfUpdates() {
        return counterOfUpdates;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "ip='" + ip + '\'' +
                ", difficulty=" + difficulty +
                ", entities=" + Arrays.toString(entities) +
                ", counterOfUpdates=" + counterOfUpdates +
                '}';
    }
}
