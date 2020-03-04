
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;

    public void setName(String name, String nationality, String team, int goals, int assists) {
        this.name = name;
        this.nationality = nationality;
        this.team = team;
        this.goals = goals;
        this.assists = assists;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    @Override
    public String toString() {
        return name + "\t" + team + "\t" + goals + " + " + assists + " = " + (goals + assists);
    }

    @Override
    public int compareTo(Player p) {
        return (p.getGoals() + p.getAssists()) - (this.goals + this.assists);
    }

}
