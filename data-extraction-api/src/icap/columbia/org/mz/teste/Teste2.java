/**
 * 
 */
package icap.columbia.org.mz.teste;

import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.zkoss.json.JSONObject;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import icap.columbia.org.mz.mapper.Data;
import icap.columbia.org.mz.service.util.Constants;
import icap.columbia.org.mz.service.util.PoiXLSUtil;
import icap.columbia.org.mz.service.util.SIOSUtil;

/**
 * @author Simone Fernando
 *
 */
public class Teste2 {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		// aggregado, delete all Data from Organisation Unit
		Client client;
		Invocation.Builder invocationBuilder;
		String credentials = null;

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.property(ClientProperties.READ_TIMEOUT, 0);
		clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 30000);

		client = ClientBuilder.newClient(clientConfig);
		credentials = Base64.getEncoder()
				.encodeToString(("sfc2124@cumc.columbia.edu" + ":" + "Icap2020.").getBytes("UTF-8"));

		FileReader filereader = new FileReader("C:\\Users\\Intuser\\organisationunitid_aggregado.csv");

		CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

		List<String[]> allData = csvReader.readAll();

		for (String[] data : allData) {
			String uri = "https://agregado.columbia.org.mz/api/maintenance/dataPruning/organisationUnits/"+ data[0];
			WebTarget webTarget = client.target(uri);

			invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,
					"Basic " + credentials);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("uid", "");
			Response response = invocationBuilder.post(Entity.entity(jsonObject.toJSONString(), MediaType.APPLICATION_JSON));

			if (response.getStatus() == 200 || response.getStatus()==204) {
				System.out.println("OK");
			}else {
				System.out.println("ERROR:"+response.readEntity(String.class));
			}
		}

	}

}
