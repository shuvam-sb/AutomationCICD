package winnerXD.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	public List<Map<String, String>> getJsonDatatoMap() throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "/src/test/java/winnerXD/data/purchaseOrder.json"),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		// List<Map<String,String>> list = mapper.readValue(jsonContent, List.class);
		// List<Map<String,String>> list = mapper.readValue(jsonContent, new TypeReference<List<Map<String, String>>());
		List<Map<String, String>> dataList = mapper.readValue(jsonContent, new TypeReference<List<Map<String, String>>>() {
		});
		return dataList;
	}

}
