import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("Connection successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Menu menu = new Menu();
		
		menu.display();
		
		
	}

}
