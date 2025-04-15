package zooapi.zooerp2.Domain.Entities;

import zooapi.zooerp2.Domain.Enums.AnimalType;
import zooapi.zooerp2.Domain.Enums.EnclosureStatus;
import zooapi.zooerp2.Domain.ValueObjects.AnimalNumber;
import zooapi.zooerp2.Domain.ValueObjects.Size3D;

import java.util.ArrayList;
import java.util.UUID;

public class Enclosure {
    private UUID id;
    private AnimalType animalType;
    private Size3D size3D;
    private AnimalNumber maxAnimalNumber;
    private EnclosureStatus status;
    ArrayList<Animal> animals;

    public Enclosure(UUID id, AnimalType animalType, Size3D size3D, AnimalNumber maxAnimalNumber) {
        this.id = id;
        this.animalType = animalType;
        this.size3D = size3D;
        this.maxAnimalNumber = maxAnimalNumber;
        this.animals = new ArrayList<>();
        this.status = EnclosureStatus.TIDY;
    }

    public void addAnimal(Animal animal) {
        try {
            animal.moveToEnclosure(this);
        } catch (Exception e) {
            throw new IllegalArgumentException("Animal cannot be moved to enclosure");
        }

        if (maxAnimalNumber.lessThan(animals.size() + 1)) {
            throw new IllegalArgumentException("The enclosure is full");
        }

        if (!animals.contains(animal)) {
            animals.add(animal);
        }
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void Clear() {
        status = EnclosureStatus.TIDY;
    }

}
