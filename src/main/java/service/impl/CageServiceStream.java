package service.impl;

import model.Cage;
import model.enums.Type;
import service.CageService;

import java.util.Set;
import java.util.stream.Collectors;

public class CageServiceStream implements CageService {
    private Cage cage;

    public Cage getCage() {
        return cage;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
    }

    public CageServiceStream(Cage cage) {
        this.cage = cage;
    }


    @Override
    public Set<Type> allTypes(){
        return cage.getAnimals().stream().map(animal -> animal.getType()).collect(Collectors.toSet());
    }

}
