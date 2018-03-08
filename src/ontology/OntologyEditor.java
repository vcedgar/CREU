package ontology;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.impl.OntModelImpl;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDFS;

public class OntologyEditor {

	static OntModel model;
	
	public static void loadOntology(String file) throws FileNotFoundException {		
		model = new OntModelImpl(new OntModelSpec(null, null, null, "http://www.w3.org/2002/07/owl#"));
		model.read(file);
	}
	
	public static void saveOntology(String file) throws FileNotFoundException {	
		model.write(new FileOutputStream(file));
	}
	
	public static void addCity(String city) {
		
	}
	
	public static void addIndicator(String indexP, String factorP, String indicatorP) {
		String subIndex = indexP +"SubIndex";
		String factor = factorP+indexP + "Factor";
		String parentIndicator = factorP + indexP + "Indicator";
		String indicator = indicatorP + "_" + parentIndicator;
		
		String uriSelf = "http://www.semanticweb.org/123theone/ontologies/2017/10/SustainabilityOntology#" + indicator;
		String uriParent = "http://www.semanticweb.org/123theone/ontologies/2017/10/SustainabilityOntology#" + parentIndicator;
		
		OntClass parent = model.getOntClass(uriParent);
		OntClass self = (OntClass) model.createClass(uriSelf);
		self.addSuperClass(parent);
	} 
	
	public static void addData(String indicator, String source, String data) {
		
	}
}
