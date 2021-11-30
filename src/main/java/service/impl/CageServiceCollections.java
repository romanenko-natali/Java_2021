package service.impl;

import model.Animal;
import model.Cage;
import model.enums.Type;
import service.CageService;

import java.util.HashSet;
import java.util.Set;

public class CageServiceCollections implements CageService {
    private Cage cage;

    public Cage getCage() {
        return cage;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
    }

    public CageServiceCollections(Cage cage) {
        this.cage = cage;
    }

    @Override
    public Set<Type> allTypes(){
        Set<Type> types = new HashSet<>();
        for (Animal animal : cage.getAnimals()){
            types.add(animal.getType());
        }
        return types;
    }


}
