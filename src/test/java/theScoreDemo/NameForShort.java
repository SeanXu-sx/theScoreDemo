package theScoreDemo;

//Created by: Sean Xu
//This NameForShort class contains a method that return the abbreviation of the league name and team name.
//But it is currently ONLY for a small number of names as I only tested these names for the demo assignment.

public class NameForShort {

    public static String covertNameToShort(String name) {
        String nameForShort = "";
        switch (name){
            case "Chelsea":
                nameForShort = "CHE";
                break;
            case "Big 3" :
                nameForShort = name;
                break;
            case "NASCAR Racing" :
                nameForShort = "NASCAR";
                break;
            case "NHL Hockey" :
                nameForShort = "NHL";
                break;
        }
        return nameForShort;
    }
}
