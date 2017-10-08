package voting_project_to_deploy;

import voting_project_to_deploy.JdbcUtil;
import java.sql.*;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;



public class Voting_db {


	boolean voterValidate(String gid) {
		ResultSet rs= null;
		Connection con= null;
		Statement st= null;
		boolean b = false;
		try {
			con= JdbcUtil.connMysql();
			
			String query="select * from voters_list where gmailid="+"'"+gid+"'";
			st= con.createStatement();
			rs= st.executeQuery(query);
			
			if(rs.next()) {
				b= true;
			}
			else {
				b= false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			JdbcUtil.cleanUp(rs,st);
			return b;
		}
	}
	
	boolean voteSubmit(String gid, String can) throws SQLException {
		Connection con= null;
		Statement st= null;
		boolean b = false;
		int i=0;
		try {
			con= JdbcUtil.connMysql();
			
			String query="insert into vote_submit values("+"'"+gid+"',"+"'"+can+"'"+")";
			
			st= con.createStatement();
			i=st.executeUpdate(query);
			
			if(i==1) {
				b=true;
			}
			else {
				b=false;
			}
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			b= false;
		}
		
		finally {
			JdbcUtil.cleanUp(null,st);
			return b;
		}
	}
}
