package dk.grp15.krediteringssystem.logic.user;

import dk.grp15.krediteringssystem.logic.credits.Catalogue;
import dk.grp15.krediteringssystem.logic.credits.Production;
import dk.grp15.krediteringssystem.persistence.DataController;

import java.util.Date;

public abstract class User {

    private int id;
    public String password;
    public String username;
    public Permission permission;
    private static final DataController dataController = new DataController();

    public User(String username, String password, Permission permission) {
        this.username = username;
        this.id = id;
        this.permission = permission;
        this.password = password;
    }

    public static User login(String text, String text1) {
        return null;
    }

    public void createProduction(String title, int lengthHours, int lengthMinutes, Date releaseDate) {
        Catalogue.getInstance().createProduction(title, lengthHours, lengthMinutes, releaseDate, id);
        Production newProduction = new Production(title, lengthHours, lengthMinutes, releaseDate, id);
        dataController.createProduction(newProduction);
    }

    public void updateProduction(int id) {

    }

    public void showProduction(int id) {

    }

    public void removeProduction(int id) {

    }

    public void createUser(String username, String password, Permission permission) {

    }

    public void showUser(int id) {

    }

    public void updateUser(int id) {

    }

    public void exportProduction(int id) {

    }

    public static User login(String username, String password, Permission permission) {
        return dataController.login(username, password, permission);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
    public String toString() {
        return "(id " + id + " ,username " + username + " ,permission " + permission +")";
    }
}


