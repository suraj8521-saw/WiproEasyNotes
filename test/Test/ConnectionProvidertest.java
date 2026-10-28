package Test; // Change this if your file is in a different package

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConnectionProvidertest {

    @Test
    public void testConnection() {
        // Get the connection from your utility class
        Connection con = com.easynotes.util.ConnectionProvider.getConnection();
        
        // Assert that the connection is not null
        assertNotNull("The database connection should not be null. Check your MySQL server and credentials.", con);
    }
}