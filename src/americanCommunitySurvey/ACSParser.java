package americanCommunitySurvey;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ACSParser {
	private static ArrayList<CityCode> cityCodes;
	private static ArrayList<DataCode> dataCodes;
	
	public static  ArrayList<CityCode> getCityCodes() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("E:\\User\\Documents\\School\\CREU-2017\\Data\\Original\\Datalist1.csv"));
		String line;
		cityCodes = new  ArrayList<CityCode>();
		for(int i = 0; i < 3; i ++) {
			reader.readLine();
		}
		while((line = reader.readLine()) != null && !line.equals(",,,,,,,,,,,")) {
			if(!line.contains("Puerto Rico")) {
				CityCode city = new CityCode(line);
				cityCodes.add(city);
			}
		}
		reader.close();
		return cityCodes;
	}
	
	public static ArrayList<DataCode> getDataCodes() throws SAXException, IOException, ParserConfigurationException {
		dataCodes = new ArrayList<DataCode>();
		File file = new File("E:\\User\\Documents\\School\\CREU-2017\\Data\\Original Data\\data-profile-variables.xml");
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		BufferedReader reader = new BufferedReader(new FileReader("E:\\User\\Documents\\School\\CREU-2017\\Data\\Variables\\Used.txt"));
		String line = "";
		String index = "";
		String factor = "";
		while((line = reader.readLine()) != null) {
			if(line.contains("-->")) { //this means the line is a Domain name
				//Something to add this data to an index
				index = line.substring(2);
			}
			else if(line.contains("---->")) { //this means the line is a Factor name
				//Something to add this data to a factor
				factor = line.substring(4);
			}
			else { //this means the line is an indicator/variable
				String[] parseLine = line.split("/");
				String indicator = parseLine[0];
				String variable = parseLine[1];
				DataCode dc = new DataCode(indicator, getDataCode(variable, doc));
				dataCodes.add(dc);
				//something to add this indicator to the ontology
			}
		}
		reader.close();
		return dataCodes;
	}
	
	public static String getDataCode(String variable, Document doc) throws SAXException, IOException, ParserConfigurationException {		
		NodeList nodes = doc.getChildNodes();
		LinkedList<Element> elements = new LinkedList<Element>();
		elements = addElements(nodes, elements);
		String dataCode = "";
		while(!elements.isEmpty()) {
			Element e = elements.removeFirst();
			String var = e.getAttribute("label");
			String conc = e.getAttribute("concept");
			if(var != null && !var.isEmpty() && var.equals(variable) && !conc.contains("PUERTO RICO")) {
				dataCode = e.getAttribute("xml:id");
				return dataCode;
			}
			addElements(e.getChildNodes(), elements);
		}
		return null;
	}
	
	private static LinkedList<Element> addElements (NodeList nodes, LinkedList<Element> queue){
		for(int i = 0; i < nodes.getLength(); i++) {
			if(nodes.item(i).getNodeType() == 1) {
				queue.add((Element)nodes.item(i));
			}
		}
		return queue;
	}
	
	/*public static void getVars() throws SAXException, IOException, ParserConfigurationException {
		File file = new File("E:\\User\\Documents\\School\\CREU-2017\\data-profile-variables.xml");

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		
		NodeList nodes = doc.getChildNodes();
		LinkedList<Element> elements = new LinkedList<Element>();
		elements = addElements(nodes, elements);
		LinkedList<String> vars = new LinkedList<String>();
		LinkedList<String> cats = new LinkedList<String>();
		
		while(!elements.isEmpty()) {
			Element e = elements.removeFirst();
			String var = e.getAttribute("label");
			String conc = e.getAttribute("concept");
			if(var != null && !var.isEmpty() && !conc.contains("PUERTO RICO")) {
				vars.add(var);
				String[] catsTemp = var.split("!!");
				if(catsTemp.length >= 2) {
					String cat = catsTemp[1];
					if(!cats.contains(cat)) {
						cats.add(cat);
					}
				}
			}
			addElements(e.getChildNodes(), elements);
		}
		
		writeThis(vars, cats);
	}
	
	private static void writeThis(LinkedList<String> vars, LinkedList<String> cats) throws IOException {		
		BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\User\\Documents\\School\\CREU-2017\\d.txt"));;
		for(String cat: cats) {		
			writer = new BufferedWriter(new FileWriter("E:\\User\\Documents\\School\\CREU-2017\\data-profile-variables-"+ cat + ".txt"));
			for(String var : vars) {
				if(var.contains(cat)) {
					writer.write("----->" + var);
					writer.newLine();
				}
			}
			writer.flush();
		}
		writer.close();
	}
*/
}
