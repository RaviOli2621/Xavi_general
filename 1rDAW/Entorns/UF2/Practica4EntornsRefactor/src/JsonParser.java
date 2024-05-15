import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class JsonParser {
    public void parseJsonFile(String filePath) { // separar el recorre la array amb inicialitzar els objectes
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader(filePath)) {

            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            recorrerJSONArray(jsonArray);

        }catch (FileNotFoundException f)
        {
            System.out.println("La ruta del JSON no es la correcta");
        }catch (ClassCastException c){
            System.out.println("El JSON no es troba ben estructurat");
        } catch(IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public static void recorrerJSONArray(JSONArray jsonArray)// els canvis fetes al recorregut del jsonArray serveixen perque si peta una dada pugui seguir lleguint les seguents
    {
        String name = "";
        long age;
        int indexABorrar = 0;
        try {
            for (Object o : jsonArray) {
                name = "";
                JSONObject jsonObject = (JSONObject) o;
                name = (String) jsonObject.get("name");
                age = (long) jsonObject.get("age");
                System.out.println("Name: " + name + ", Age: " + age);
                indexABorrar++;
            }
        }catch (ClassCastException c)
        {
            if(name.isEmpty()) System.out.println("El formato del nombre ("
                    + ((JSONObject)(jsonArray.get(indexABorrar))).get("name")
                    + ") en el dato " + (indexABorrar + 1) + " es incorrecto");
            else System.out.println("El formato de la edad ("
                    + ((JSONObject)(jsonArray.get(indexABorrar))).get("age")
                    + ") en el dato " + (indexABorrar + 1) + " es incorrecto: ");
            while (indexABorrar >= 0)
            {
                jsonArray.remove(indexABorrar);
                indexABorrar--;
            }
            recorrerJSONArray(jsonArray);
        }
    }
}