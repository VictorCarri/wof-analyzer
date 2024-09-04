package vic.projects.wof;

/* Core Java */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.List;
import java.io.InputStream;
import java.io.File;
import java.util.ListIterator;
import java.util.Arrays;
import java.util.ArrayList;

/* Spring */
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.core.io.ClassPathResource;

/* Google */
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;

@RestController
public class WOFController {
	//private static final String APPLICATION_NAME = "WOF Stats Counter";
	private final String APPLICATION_NAME;

	//private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private final JsonFactory JSON_FACTORY;
	//private static final String TOKENS_DIRECTORY_PATH = "tokens";
	private final String TOKENS_DIRECTORY_PATH;

	/*
	* Global instance of the scopes we need.
	* Delete the tokens/ folder if I modify this.
	*/
	private final List<String> SCOPES;
	//private static final String credsFilePath = "classpath:resources/main/static/credentials.json";
	private final String credsFilePath;
	private final String spreadsheetId;
	private final ArrayList<String> ranges;

	/*
	* @desc Constructor. Creates the class and initializes our properties.
	*/
	public WOFController()
	{
		this.APPLICATION_NAME = "WOF Stats Counter";
		this.JSON_FACTORY = GsonFactory.getDefaultInstance();
		this.TOKENS_DIRECTORY_PATH = "tokens";
		//this.SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
		this.SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);
		this.credsFilePath = "static/credentials.json";
		this.spreadsheetId = "1tSL-H0RHaorufADgcG9exQEfhsNrN85ql70uomMAwFA";
		String[] strRanges = {"'Raw data'!B:B", "'Raw data'!C:C"}; 
		this.ranges = new ArrayList(Arrays.asList(strRanges)); // The date & value columns
	}

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	/*
	* @desc Creates an authorized Credential object for any of the methods that use Google Sheets data.
	* @param HTTP_TRANSPORT The network HTTP Transport.
	* @return An authorized Credential object.
	* @throws IOException If it can't find the credentials.json file.
	*/
	private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
	//	FileInputStream in = (FileInputStream)WOFController.class.getResourceAsStream(credsFilePath);
		ClassPathResource credsFileResource = new ClassPathResource(credsFilePath);
		System.out.println("Credentials file's absolute path: " + credsFileResource.getPath() + "\nWorking directory: " + System.getProperty("user.dir") + "\nClasspath: " + System.getProperty("java.class.path") + "\nResource description: "
			+ credsFileResource.getDescription()
		);
		InputStream fin = credsFileResource.getInputStream();
		System.out.println("getCredentials: fetched the resource's input stream");
		
		if (fin == null) {
			throw new FileNotFoundException("Resource not found: " + credsFilePath);
		}
		
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(fin));
		System.out.println("getCredentials: loaded a new Google Client Secrets object");

		/* Build flow and trigger user authorization request */
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
			.setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
			.setAccessType("offline")
			.build();
		System.out.println("getCredentials: created a new Google authorization code flow");
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		System.out.println("getCredentials: created a new local server receiver");
		Credential toReturn = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
		System.out.println("getCredentials: created a new credential object.");
		return toReturn;
	}

	/**
	* @desc Counts how many entries I've made in the spreadsheet.
	* @return An integral count of how many *complete* entries I've made in the spreadsheet.
	**/
	@GetMapping("/count")
	public int getRowCount() {
		int toReturn = 0;

		try
		{
			/* Load pre-authoized user credentials from the enironment */
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			System.out.println("getRowCount: created a new trusted transport");
			Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME)
				.build();
			System.out.println("getRowCount: created a new sheets service.");
			BatchGetValuesResponse readResult = service.spreadsheets().values()
				.batchGet(spreadsheetId)
				.setRanges(ranges)
				.execute();
			System.out.println("getRowCount: fetched a new batch of values.");
			List<ValueRange> valueRangeList = readResult.getValueRanges();

			for (ValueRange curRange : valueRangeList)
			{
				System.out.println("RANGE START\n\n" + curRange + "\n\nRANGE END\n\n");
			}

			ValueRange dateRange = valueRangeList.

			toReturn = 4;
		}

		catch (IOException ioe)
		{
			System.out.println("getRowCount: caught an IOException: " + ioe.getMessage());
			toReturn = 1;
		}
	
		catch (GeneralSecurityException gse)
		{
			System.out.println("getRowCount: caught a GeneralSecurityException: " + gse.getMessage());
			toReturn = 2;
		}

		finally
		{
			return toReturn;
		}
	}
}
