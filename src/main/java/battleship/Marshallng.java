package battleship;

import java.io.FileOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Marshallng {
	public void gameToJSON(Game game) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream("resources/savedgames.json"), game);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
