package main;
import java.io.*;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import americanCommunitySurvey.ACSParser;
import americanCommunitySurvey.DataCode;
import ontology.OntologyEditor;

public class Main {
	static ArrayList<DataCode> dataCodes;

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
		OntologyEditor.loadOntology("E:\\User\\Documents\\School\\CREU-2017\\Data\\OntologyXML-Practice.owl");
		OntologyEditor.addIndicator("Social", "Crime", "Murder");
		OntologyEditor.saveOntology("E:\\User\\Documents\\School\\CREU-2017\\Data\\OntologyXML-Practice.owl");
	}
}
