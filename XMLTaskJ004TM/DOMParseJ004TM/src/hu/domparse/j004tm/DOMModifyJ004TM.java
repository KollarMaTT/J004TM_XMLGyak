package hu.domparse.j004tm;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModifyJ004TM {

	public static void main(String[] args){
		
		try {
		File xmlFile = new File("src/XMLJ004TM.xml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
				
		Document doc = dBuilder.parse(xmlFile);

		doc.getDocumentElement().normalize();
		
		
		//V�gigmegy a vend�g elemeken for ciklussal �s a felt�telben megadott
		//nev� �s �rt�k� adatokat �t�rja a k�rt tartalomm�
		NodeList nList = doc.getElementsByTagName("vendeg");
		
		for(int i = 0; i < nList.getLength(); i++) {
			Node vendeg = doc.getElementsByTagName("vendeg").item(i);
	        
	        NodeList list = vendeg.getChildNodes();
	        
	        for (int temp = 0; temp < list.getLength(); temp++) {
	           Node node = list.item(temp);
	           if (node.getNodeType() == Node.ELEMENT_NODE) {
	              Element eElement = (Element) node;
	              if ("eletkor".equals(eElement.getNodeName())) {
	                 if("21".equals(eElement.getTextContent())) {
	                    eElement.setTextContent("22");
	                 }
	              }
	              if ("nev".equals(eElement.getNodeName())) {
		                 if("Elon Musk".equals(eElement.getTextContent())) {
		                    eElement.setTextContent("Mr. Elon Musk");
		                 }
		          }
	           }
	        }
		}
		
		
		
		// �t�rja az adott gyakornok attrib�tum�t a megadott �rt�kre
		Node gyakornok = doc.getElementsByTagName("gyakornok").item(2);
        
        NamedNodeMap attr = gyakornok.getAttributes();
        Node nodeAttr = attr.getNamedItem("e_gy");
        nodeAttr.setTextContent("e2");
        
        
        
        //Kit�rli az �sszes rendel�s elemet az xml f�jlb�l
        Node vendeglatas = doc.getFirstChild();
        NodeList childNodes = vendeglatas.getChildNodes();
        
        for(int count = 0; count < childNodes.getLength(); count++) {
           Node node = childNodes.item(count);
           
           if("rendeles".equals(node.getNodeName()))
        	   vendeglatas.removeChild(node);
        }
        
        
        //Ki�ratja a m�dos�tott xml f�jlt a konzolra
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        System.out.println("-----------Modified File-----------");
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
		
		}catch (Exception e) {
			e.printStackTrace();
	    }
	}

}
