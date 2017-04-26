package com.aa.main;

public class Login {




		String id;
		String password;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		//password 안보이게 입력
		public String getPassword() {
			return password;
	/*		String pass = "";
			Console cnsl = System.console();


		    if (cnsl != null) {




		      char[] pwd = cnsl.readPassword("Password: ");

		      System.out.println("Password is: " + pwd);

			*/
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public void chkPassword(String password){
			String pwd = getPassword();
//			if()
		}
	}


