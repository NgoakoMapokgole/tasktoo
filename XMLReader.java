import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;

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
                System.out.println("\nCurrent Element: " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    for (String field : fields) {
                        field = field.trim();
                        try {
                            System.out.println(field + ": " + element.getElementsByTagName(field).item(0).getTextContent());
                        } catch (Exception e) {
                            System.out.println(field + ": Not Found");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
