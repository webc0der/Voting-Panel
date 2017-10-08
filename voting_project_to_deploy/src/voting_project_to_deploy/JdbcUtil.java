package voting_project_to_deploy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

		static{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}
		}
			public static Connection connMysql()throws SQLException{
			
				String url="jdbc:mysql://votingdb.cuu3vp3sqbhd.ap-south-1.rds.amazonaws.com:3306/voting_db?autoReconnect=true&useSSL=false";
				Connection con= DriverManager.getConnection(url,"manish","munnamanish");
			
			return con;
			}
			
			public static void cleanUp(ResultSet rs, Statement s){
				try {
					if(rs!= null)rs.close();
					if(s!=null)s.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
}

