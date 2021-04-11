package mbh.com.restlistrecyclerassignment6.ui.main;

public class Rest {
    public String name;
    public String location;
    public String type;
    public String cost;
    public Rest() {
        super();
    }
    public Rest(String name,  String type, String location, String cost) {
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

}
