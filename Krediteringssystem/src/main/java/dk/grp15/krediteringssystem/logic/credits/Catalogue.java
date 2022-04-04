package dk.grp15.krediteringssystem.logic.credits;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Catalogue {

    private static final Catalogue INSTANCE = new Catalogue();

    private final List<Production> productions = new ArrayList<>();
    private final List<Contributor> contributors = new ArrayList<>();

    private Catalogue() {
    }

    public void createProduction(String title, int lengthHours, int lengthMinutes, Date releaseDate, int owner_id) {
        Production newProduction = new Production(title, lengthHours, lengthMinutes, releaseDate, owner_id);
        productions.add(newProduction);
    }

    public void showProduction(Production production) {

    }

    public void updateProduction(Production production) {

    }

    public void removeProduction(Production production) {
        //removes the chosen production from the ArrayList containing Productions
        productions.remove(production);
        //Make sure it is connected to the persistence (in the user)
    }

    public void exportProduction(Production production) {

    }

    public Production getProduction(int id) {
        return null;
    }

    public static Catalogue getInstance() {
        return INSTANCE;
    }

    public List<Production> getProductions() {
        return productions;
    }
}
