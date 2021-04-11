package mbh.com.hutodolist.ui.main;

public class Todo {
    public final static String LIFE = "life";
    public final static String ENTERTAINMENT = "entertainment";
    public final static String STUDY = "study";

    protected String title = "";
    protected String description = "";
    protected String dueDate = "";
    protected String type = "";
    protected String information = "";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    protected long id = 0;

    @Override
    public String toString() {
        return "Todo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", type='" + type + '\'' +
                ", information='" + information + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }


}
