package zooapi.zooerp2.Domain.Factories;

import org.springframework.stereotype.Component;
import zooapi.zooerp2.Domain.Entities.FeedingSchedule;
import zooapi.zooerp2.Domain.Enums.FoodType;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Component
public class FeedingScheduleFactory {
    int lastId = 0;

    public FeedingSchedule createFeedingSchedule(UUID animalId,  Date startTime, Duration delta, FoodType foodType) {
        return new FeedingSchedule(UUID.fromString(Integer.toString(++lastId)), animalId,  startTime, delta, foodType);
    }
}
