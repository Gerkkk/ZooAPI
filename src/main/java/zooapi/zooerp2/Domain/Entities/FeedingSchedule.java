package zooapi.zooerp2.Domain.Entities;

import zooapi.zooerp2.Domain.Enums.FoodType;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

public class FeedingSchedule {
    private UUID animalId;
    private Date startTime;
    private Date nextTime;
    private Duration delta;
    private FoodType foodType;

    public FeedingSchedule(UUID animalId, Date startTime, Duration delta, FoodType foodType) {
        this.animalId = animalId;
        this.startTime = startTime;
        this.delta = delta;
        this.foodType = foodType;
        this.nextTime = startTime;
    }

    public void completeFeeding() {
        nextTime = Date.from(nextTime.toInstant().plus(delta));
    }

    public void changeDelta(Duration delta) {
        this.delta = delta;
    }
}
