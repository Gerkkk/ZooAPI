package zooapi.zooerp2.Domain.Entities;

import zooapi.zooerp2.Domain.Enums.FoodType;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

public class Feeding {
    private UUID animalId;
    private Date startTime;
    private FoodType foodType;

    public Feeding(UUID animalId, Date startTime, FoodType foodType) {
        this.animalId = animalId;
        this.startTime = startTime;
        this.foodType = foodType;
    }
}
