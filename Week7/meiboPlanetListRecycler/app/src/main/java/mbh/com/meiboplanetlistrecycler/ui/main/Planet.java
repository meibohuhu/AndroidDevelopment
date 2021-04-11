package mbh.com.meiboplanetlistrecycler.ui.main;

public class Planet {
    public String name;
    public String location;
    public String type;
    public String cost;
    public Planet() {
        super();
    }
    public Planet(String name,  String type, String location, String cost) {
        super();
        this.name = name;
        this.location = location;
        this.type = type;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getCost() {
        return cost;
    }
//    public int logo;
//    public String name;
//    public String type;
//
//    public Planet() {
//        super();
//    }
//
//    public Planet(int logo, String name, String type) {
//        super();
//        this.logo = logo;
//        this.name = name;
//        this.type = type;
//    }
//
//    public int getLogo() {
//        return logo;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getType() {
//        return type;
//    }
}
