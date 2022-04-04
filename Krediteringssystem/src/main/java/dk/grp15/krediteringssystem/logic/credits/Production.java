package dk.grp15.krediteringssystem.logic.credits;

import dk.grp15.krediteringssystem.logic.user.User;

import java.util.*;

public class Production {

    private String title;
    private int lengthHours;
    private int lengthMinutes;
    private Date releaseDate;
    private String resume = "";
    private int owner_id;
    private boolean visible = false;

    private List<Contributor> contributors = new ArrayList<>();
    private Map<Integer, List<String>> characters = new HashMap<>();

    public Production(String title, int lengthHours, int lengthMinutes, Date releaseDate, int owner_id) {
        this.title = title;
        this.lengthHours = lengthHours;
        this.lengthMinutes = lengthMinutes;
        this.releaseDate = releaseDate;
        this.resume = resume;
        this.owner_id = owner_id;
    }

    public Production(String title, int lengthHours, int lengthMinutes, Date releaseDate) {
    }

    public void addContributor(Contributor contributor, List<String> characterList) {
        contributors.add(contributor);
        characters.put(contributor.getId(), characterList);
    }

    public void exportData() {

    }

    public String toString() {
        String contributorString = "\n";

        for(Contributor contributor : contributors) {
            contributorString += "  " + contributor.getName() + "\n";
        }

        return    "Title:        " + title +
                "\nLength:       H:" + lengthHours + " M:" + lengthMinutes +
                "\nRelease Date: " + releaseDate.toString() +
                "\nResume:       " + resume +
                "\nContributors: " + contributorString;
    }

    public void addCharacter(int id, String name) {
        if (characters.containsKey(id)) {
            characters.get(id).add(name);
        }
        else {
            List<String> characterNames = new ArrayList<>();
            characterNames.add(name);
            characters.put(id, characterNames);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLengthHours() {
        return lengthHours;
    }

    public void setLengthHours(int lengthHours) {
        this.lengthHours = lengthHours;
    }

    public int getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(int lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }
}
