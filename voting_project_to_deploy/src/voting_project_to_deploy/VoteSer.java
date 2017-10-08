package voting_project_to_deploy;

//import vooting_proj.Voting_db;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

public class VoteSer extends HttpServlet {
	Voting_db vdb= null;
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final String CLIENT_ID ="1067440508780-4sohls7be8loq4fc61g54pp4211j63h8.apps.googleusercontent.com";

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JSON_FACTORY)
				.setAudience(Collections.singletonList(CLIENT_ID))
				// Or, if multiple clients access the backend:
				//.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				.build();

		String idTokenString= request.getParameter("idToken");
		String vote_submitted_for=request.getParameter("vote_sub");
		String email = null;
		String name= null;
		String massage= null;
		vdb= new Voting_db();

		GoogleIdToken idToken= null;
		if(idTokenString.length() > 10){

			try {
				idToken = verifier.verify(idTokenString);
			} 
			catch (GeneralSecurityException e) {
				e.printStackTrace();
			}
		}
		else {
			//			System.out.println("idToken is null");
			massage="Sorry You Can't Vote Without SignIn";
			request.setAttribute("msg",massage);
			request.getRequestDispatcher("error.jsp").forward(request, response); 
			//			response.sendRedirect("error.jsp");
		}

		if (idToken != null) {
			Payload payload = idToken.getPayload();
			email = payload.getEmail();

			//	Print user identifier
			//	String userId = payload.getSubject();
			//	System.out.println("User ID: " + userId);

			//				boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			name = (String) payload.get("name");
		} 

		else {
			System.out.println("Invalid ID token.");
		}

		if(vdb.voterValidate(email)) {
			try {
				if(vdb.voteSubmit(email, vote_submitted_for)) {
					
					massage="Thank You "+name+" For Submitting Your Vote To "+vote_submitted_for;
					request.setAttribute("msg",massage);
					request.getRequestDispatcher("success.jsp").forward(request, response);
				}
				else {
					
					massage="Sorry You Have Already submitted your vote";
					request.setAttribute("msg",massage);
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			
			massage="Sorry You Are Not An Eligible Voter";
			request.setAttribute("msg",massage);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
