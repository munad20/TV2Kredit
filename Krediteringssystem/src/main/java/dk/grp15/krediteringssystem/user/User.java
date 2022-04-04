package dk.grp15.krediteringssystem.user;

import dk.grp15.krediteringssystem.logic.credits.Catalogue;
import dk.grp15.krediteringssystem.logic.credits.Production;
import dk.grp15.krediteringssystem.logic.user.Permission;

import java.util.Date;

public abstract class User {

    private int id;
    private Permission permission;


    public User(int id, Permission permission) {
        this.id = id;
        this.permission = permission;
    }

    public void createProduction(String title, int lengthHours, int lengthMinutes, Date releaseDate) {
        Production newProduction = new Production(title, lengthHours, lengthMinutes, releaseDate);
        Catalogue.getInstance().getProductions().add(newProduction);
    }

    public void updateProduction(int id) {

    }

    public void showProduction(int id) {

    }

    public int getID() {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission (Permission permission) {
        this.permission = permission;
    }
}

