/**
 * 
 */
package icap.columbia.org.mz.service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.zkoss.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import icap.columbia.org.mz.service.util.ObjectMapperProvider;

/**
 * This class is used to call multiple ENDPOINTS, all predefined API to use a
 * invocation method you need to register the SiosService instance with your
 * credentials (username and password) and build the PATH
 * 
 * @author Simone Fernando, 19/01/2021
 *
 */
public class SiosService {

	private Client client;
	private Invocation.Builder invocationBuilder;
	public String credentials = null;
	private ObjectMapperProvider objectMapperProvider;

	public static final String BASE_SIOS_URL = "https://dhis.ins.gov.mz";

	public static final String SIOS_TRACKED_ENTITY_PATH = "/api/33/trackedEntityInstances.json";
	public static final String SIOS_ORGANIZATION_UNITY_PATH = "/api/33/organisationUnits/";
	public static final String SIOS_DATA_ELEMENT_BY_ID_PATH = "/api/33/dataElements/";

	private static final Logger logger = LogManager.getLogger(SiosService.class);

	/**
	 * use this method to register your new SIOS Service instance
	 * 
	 * @param username
	 * @param password Used Basic base64encode(username:password)
	 * @throws UnsupportedEncodingException
	 */
	public void register(String username, String password) throws UnsupportedEncodingException {

		objectMapperProvider = new ObjectMapperProvider();
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.property(ClientProperties.READ_TIMEOUT, 0);
		clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 30000);

		client = ClientBuilder.newClient(clientConfig);
		credentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes("UTF-8"));

		logger.info("Service registered successfuly");
	}

	/**
	 * Build the initial service path for Request
	 * 
	 * @param path
	 */
	public void build(String path) {
		logger.info("Prepare to call URI: " + (BASE_SIOS_URL + path));

		WebTarget webTarget = client.target(BASE_SIOS_URL + path);

		invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,
				"Basic " + credentials);

	}

	/**
	 * 
	 * @param <T>
	 * @param t
	 * @return a T<Class> tha represent the result of response, you need to pass the
	 *         correct Mapping class to the Mapper if Response code is not 200 then
	 *         the T object should be: JSONObject {status:BOOLEAN<TRUE|FALSE>,
	 *         statusMessage:String<exception>, response:String<the response error>}
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public <T> Object invoke(Class<T> t) throws JsonMappingException, JsonProcessingException {
		logger.info("Invocation Class T: " + t);
		Response response = invocationBuilder.get();

		if (response.getStatus() == 200) {

			return objectMapperProvider.getContext(t).readValue(response.readEntity(String.class), t);
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", false);
			jsonObject.put("statusMessage", "exception:" + response.getStatus());
			jsonObject.put("response", response.readEntity(String.class));
			return jsonObject;
		}

	}

	/**
//	 * 
//	 * @param username
//	 * @param password
//	 * @param path
//	 * @return
//	 * @throws ClientProtocolException
//	 * @throws IOException
//	 */
//	public String getData(String username, String password, String path) throws ClientProtocolException, IOException {
//		HttpClient client = HttpClientBuilder.create().build();
//		HttpGet request = new HttpGet(BASE_SIOS_URL + path);
//
//		String credentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes("UTF-8"));
//
//		request.addHeader("accept", "application/json");
//		request.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + credentials);
//
//		HttpResponse response = client.execute(request);
//
//		return IOUtils.toString(response.getEntity().getContent());
//	}

//	public JsonNode parseToJsonNode(String nodeText) throws JsonMappingException, JsonProcessingException {
//		return objectMapperProvider.getContext(String.class).readTree(nodeText);
//	}

}
