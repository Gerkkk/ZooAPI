package zooapi.zooerp2.Domain.Interfaces.Application;

import zooapi.zooerp2.Domain.Entities.FeedingSchedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public interface FeedingServiceI {
    ArrayList<FeedingSchedule> getFeedingSchedule(Date start, Date end);
    void feed(UUID scheduleId);
}
