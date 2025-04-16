package zooapi.zooerp2.Domain.Interfaces.Infrastructure;

import zooapi.zooerp2.Domain.Entities.Enclosure;
import zooapi.zooerp2.Domain.Enums.AnimalType;
import zooapi.zooerp2.Domain.ValueObjects.AnimalNumber;
import zooapi.zooerp2.Domain.ValueObjects.Size3D;

import java.util.Optional;
import java.util.UUID;

public interface EnclosureRepositoryI {
    Optional<Enclosure> addEnclosure(Enclosure e);
    void deleteEnclosure(UUID animalId);
    Optional<Enclosure> getEnclosure(UUID animalId);
    int getEnclosureCount();
    int getEnclosureCapacity();
    Optional<Enclosure> getEnclosureByAnimalId(UUID animalId);
}
