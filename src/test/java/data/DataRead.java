package data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataRead {
	public HashMap<String,String> getDataFromJsonToMap() throws IOException
	{
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir")+"\\src\\test\\java\\data\\dataFile.json"),
				StandardCharsets.UTF_8);
		//convert it into string variable
		//then convert string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> data = mapper.readValue
				(jsonContent, new TypeReference<HashMap<String, String>>(){});
		return data;
	}

}
