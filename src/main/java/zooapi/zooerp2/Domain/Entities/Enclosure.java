package zooapi.zooerp2.Domain.Entities;

import lombok.Getter;
import zooapi.zooerp2.Domain.Enums.AnimalType;
import zooapi.zooerp2.Domain.Enums.EnclosureStatus;
import zooapi.zooerp2.Domain.ValueObjects.AnimalNumber;
import zooapi.zooerp2.Domain.ValueObjects.Size3D;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Getter
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

    public Optional<Animal> checkAnimalInside(UUID animalId) {
        for (Animal animal : animals) {
            if (animal.getId().equals(animalId)) {
                return Optional.of(animal);
            }
        }

        return Optional.empty();
    }

    public Animal removeAnimal(UUID animalId) {
        Animal[] ret = {null};
        var t = animals.stream().filter(enclosure -> enclosure.getId() == animalId).findFirst();
        t.ifPresent(enclosure -> {ret[0] = enclosure; animals.remove(enclosure);});
        return ret[0];
    }

    public void Clear() {
        status = EnclosureStatus.TIDY;
    }

}
