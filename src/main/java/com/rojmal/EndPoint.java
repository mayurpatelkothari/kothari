package com.rojmal;

public class EndPoint {

	public final static String INSERT_REGISATION= "/data/regisation"; // post1
	
	public final static String UPDATE_REGISATION= "/data/regisation/{id}/update"; // post1
	
	public final static String GET_REGISATION= "/data/regisation/{id}/get"; // post1




	
	public final static String INSERT_BIRTH_REGISATION= "/data/birthregister";
	
	public final static String UPDATE_BIRTH_REGISATION= "/data/birthregister/{id}/update";
	
	public final static String DELETE_BIRTH_REGISATION= "/data/birthregister/{id}/delete";

	public final static String GET_BIRTH_REGISATION= "/data/birthregister/{id}/get";
	
	public final static String GETALL_BIRTH_REGISATION= "/data/birthregister/getAll";


	
	public final static String INSERT_TRANSACTION= "/data/transaction"; // post
	public final static String UPDATE_TRANSACTION= "/data/{regisationId}/transaction/{id}/update"; // put
	
	public final static String DELETE_TRANSACTION= "/data/{regisationId}/transaction/{id}/delete"; // delete
	public final static String GET_TRANSACTION= "/data/{regisationId}/transaction/{id}/get"; // get
	public final static String GETS_TRANSACTION= "/data/{regisationId}/transaction/{id}/gets"; // delete




	
	public final static String LOGIN = "/login";
	
	public final static String GET_BY_USERNAME_REGISATION= "/data/regisation"; // get

	
	
	
	
	
public final static String SCHEDULE_JOB_TEST = "/data/schedule/job/test"; // GET
	
	// Login
	public final static String INDEX = "/"; // GET
	public final static String HOME = "/home"; // GET
	public final static String SINUP = "/signup"; // GET
	public final static String VIEW = "/view"; // GET
	public final static String FORGOTPASSWORD = "/forgotpassword"; // GET
	public final static String REGISATION = "/signup"; // GET
	public final static String OFFER = "/offer"; // GET
	public final static String PRICING = "/pricing"; // GET
	public final static String CONTACT = "/contact"; // GET
	public final static String GROUP = "/group"; // GET
	public final static String FORGOT_PASSWORD = "/forgotpassword"; // GET





	

	// signup
	public final static String SIGN_UP = "/data/signup"; // POST
	
	// Contact
	public  final static String CREATE_CONTACT="/data/contact";//POST
	public  final static String UPDATE_OR_DELETE_CONTACT="/data/contact/{contactId}";//PUT
	public  final static String GET_CONTACT="/data/get/contact";//PUT
	
	//Group
	public final static String CREATE_OR_GET_GROUP="/data/group";
	public final static String UPDATE_OR_DELETE_GROUP="/data/group/{groupId}";

	
	//schedule api
	public final static String CREATE_SCHEDULE = "/data/create"; // POST
	
	public final static String UPDATE_SCHEDULE = "/data/update/{scheduleId}"; // PUT
	
	public final static String DELETE_SCHEDULE = "/data/delete/{scheduleId}"; // DELETE
	
	
	
	// view controller URLS
	
	public final static String ERROR_GENERAL = "/data/login-error"; // GET


}
