package dk.grp15.krediteringssystem.logic.credits;

import java.util.ArrayList;
import java.util.List;

public class Contributor {

    private int id;
    private String name;
    private String description;
    private List<Role> roles;
    private Role role;
    private List<String> characters;
    private String character;

    public Contributor() {
        id = 0;
        name = "";
        roles = new ArrayList<>();
        role = Role.DEFAULT_ROLE;
        characters = new ArrayList<>();
        character = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        boolean containsRole = false;

        for (Role r : roles) {
            if (r == role) {
                containsRole = true;
                break;
            }
        }
        if (!containsRole) {
            roles.add(role);
        }
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
        boolean containsCharacter = false;

        for (String s : characters) {
            if (s.equals(character)) {
                containsCharacter = true;
                break;
            }
        }
        if (!containsCharacter) {
            characters.add(character);
        }
    }
}
