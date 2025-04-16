package zooapi.zooerp2.Domain.Interfaces.Infrastructure;

import zooapi.zooerp2.Domain.Entities.Animal;
import zooapi.zooerp2.Domain.Entities.Feeding;
import zooapi.zooerp2.Domain.Entities.FeedingSchedule;
import zooapi.zooerp2.Domain.Enums.FoodType;

import java.time.Duration;
import java.util.*;

public interface FeedingRepositoryI {
    List<Feeding> getFeedingSchedule(Date start, Date end);
    void feed(UUID scheduleId);
    Optional<FeedingSchedule> addFeedingSchedule(FeedingSchedule feedingSchedule);
    void deleteFeedingSchedule(UUID scheduleId);
    int getFeedingScheduleCount();
}
