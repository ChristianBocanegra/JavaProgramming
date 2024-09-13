import java.util.Calendar;
import java.util.Date;

public class Driver {
    public void OutPut() {

        Singers singerOne = new Singers();


        System.out.println("Default Singer:");
        singerOne.displaySingerInfo();


        singerOne.setId(101);
        singerOne.setName("Carlos Alberto Vives Restrepo");
        singerOne.setAddress("6 Liberty St. Port Hope, ON L1A 2A6");
        singerOne.setDateOfBirth(new Date(61, Calendar.AUGUST, 7));
        singerOne.setAlbumsPublished(5);


        System.out.println("\nUpdated Singer:");
        singerOne.displaySingerInfo();
    }
}
