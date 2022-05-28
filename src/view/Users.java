package view;

public class Users {
    private int account;
    private String key;
    private int points;
    private String id;

    public Users(int account, String key, int points, String id) {
        this.account = account;
        this.key = key;
        this.points = points;
        this.id = id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
