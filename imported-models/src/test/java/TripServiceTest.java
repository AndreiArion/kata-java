
import org.junit.Test;

import net.courtanet.tripservice.TripService;
import net.courtanet.tripservice.User;

public class TripServiceTest {

    @Test(expected = RuntimeException.class)
    public void should_throw(){
        TripService t = new TripService();
        User user = new User();
        t.getTripsByUser(user);
    }

}