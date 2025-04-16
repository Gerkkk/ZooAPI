package zooapi.zooerp2.Application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zooapi.zooerp2.Domain.Entities.Animal;
import zooapi.zooerp2.Domain.Interfaces.Application.AnimalMoveServiceI;
import zooapi.zooerp2.Domain.Interfaces.Application.AnimalServiceI;
import zooapi.zooerp2.Domain.Interfaces.Infrastructure.EnclosureRepositoryI;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AnimalMoveService implements AnimalMoveServiceI {
    private final AnimalServiceI animalService;
    private final EnclosureRepositoryI enclosureRepository;

    @Override
    public void move(UUID animalId, UUID enclosureId) {
        var curEnclosure = enclosureRepository.getEnclosureByAnimalId(animalId);

        Animal animal;
        if (curEnclosure.isPresent()) {
            animal = curEnclosure.get().removeAnimal(animalId);
        } else {
            var t = animalService.getAnimal(animalId);
            if (t.isPresent()) {
                animal = t.get();
            } else {
                animal = null;
            }
        }

        if (animal == null) {
            throw new IllegalArgumentException("Animal with id " + animalId + " not found");
        }

        var e = enclosureRepository.getEnclosure(enclosureId);

        if (e.isEmpty()) {
            throw new IllegalArgumentException("Enclosure with id " + enclosureId + " not found");
        }

        e.ifPresent(enclosure -> enclosure.addAnimal(animal));
    }
}
