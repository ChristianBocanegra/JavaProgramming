import java.util.Date;

public class Singers {
    private int id;
    private String name;
    private String address;
    private Date dateOfBirth;
    private int albumsPublished;

    public Singers() {
        this.id = 100;
        this.name = "Unknown";
        this.address = "Unknown";
        this.dateOfBirth = null;
        this.albumsPublished = 0;
    }

    public Singers(int id) {
        this.id = id;
    }

    public Singers(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Singers(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Singers(int id, String name, String address, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public Singers(int id, String name, String address, Date dateOfBirth, int albumsPublished) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.albumsPublished = albumsPublished;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAlbumsPublished(int albumsPublished) {
        this.albumsPublished = albumsPublished;
    }

    public void setAllDetails(int id, String name, String address, Date dateOfBirth, int albumsPublished) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.albumsPublished = albumsPublished;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAlbumsPublished() {
        return albumsPublished;
    }

    public void displaySingerInfo() {
        System.out.println("Singer details: ");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("SAddress: " + address);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Number of Albums Published: " + albumsPublished);
    }
}
