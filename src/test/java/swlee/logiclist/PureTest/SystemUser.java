package swlee.logiclist.PureTest;

import org.junit.jupiter.api.Test;

public class SystemUser {
    @Test
    void test(){
        System.out.println(System.getProperty("user.name"));
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("os = " + os);

    }
}
