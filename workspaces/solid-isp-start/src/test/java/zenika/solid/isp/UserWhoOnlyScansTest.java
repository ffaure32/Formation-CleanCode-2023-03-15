package zenika.solid.isp;

import static org.junit.jupiter.api.Assertions.*;

class UserWhoOnlyScansTest {

    @org.junit.jupiter.api.Test
    void scanDocument() {
        Machine scanner = getMachine();
        UserWhoOnlyScans user = new UserWhoOnlyScans(scanner);
        user.scanDocument();
    }

    private Machine getMachine() {
        Machine scanner = new SimplePrinter();
        return scanner;
    }
}