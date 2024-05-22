import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;
import org.json.JSONObject;

public class XMLReader {
    public static void main(String[] args) {
        try {
            File inputFile = new File("input.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the fields you want to display (comma separated): ");
            String[] fields = scanner.nextLine().split(",");

            NodeList nodeList = doc.getElementsByTagName("record");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    JSONObject jsonObject = new JSONObject();
                    for (String field : fields) {
                        field = field.trim();
                        try {
                            jsonObject.put(field, element.getElementsByTagName(field).item(0).getTextContent());
                        } catch (Exception e) {
                            jsonObject.put(field, "Not Found");
                        }
                    }
                    System.out.println(jsonObject.toString(4)); // Pretty print with an indent of 4 spaces
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
