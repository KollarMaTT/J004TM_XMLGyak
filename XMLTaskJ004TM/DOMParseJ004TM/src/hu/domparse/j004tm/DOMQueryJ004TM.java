package hu.domparse.j004tm;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryJ004TM {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

			File xmlFile = new File("src/XMLJ004TM.xml");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName() + "\n");
			
			
			//Ki�ratom azokat a szak�csokat, akiknek a v�gzetts�geik k�z�tt van szakk�z�piskola
			System.out.println("Azok a szak�csok, akiknek a v�gzetts�geik k�z�tt van szakk�z�piskola:\n");
			
			NodeList nodeList = doc.getElementsByTagName("szakacs");
			
			for(int i = 0; i < nodeList.getLength(); i++) {
					
				Node nNode = nodeList.item(i);
					
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) nNode;
						
					Node node3;
					
					//V�gign�zem a v�gzetts�geit, hogy van-e k�zte szakk�z�piskola
					for(int j=0;j<elem.getElementsByTagName("vegzettseg").getLength();j++) {
						node3 = elem.getElementsByTagName("vegzettseg").item(j);
						String edu1 = node3.getTextContent();
						//Ha igen, akkor ki�ratom az adatait
						if("Szakk�z�piskola".equals(edu1)) {
							String id = elem.getAttribute("szkod");
							String eid = elem.getAttribute("e_sz");
								
							String work = "Ez a szak�cs a(z) " + eid + " azonos�t�j� �tteremben dolgozik." ;
							
							Node node1 = elem.getElementsByTagName("nev").item(0);
							String name = node1.getTextContent();
								
							Node node2 = elem.getElementsByTagName("reszleg").item(0);
							String department = node2.getTextContent();
								
							String edu2 = "";
								
							for(int k=0;k<elem.getElementsByTagName("vegzettseg").getLength();k++) {
								node3 = elem.getElementsByTagName("vegzettseg").item(k);
								if(k == elem.getElementsByTagName("vegzettseg").getLength()-1) {
									edu2 += node3.getTextContent();
								}else {
									edu2 += node3.getTextContent() + ", ";
								}
							}
							
							System.out.println("Szak�cs ID: " + id);
							System.out.println("N�v: " + name);
							System.out.println("R�szleg: " + department);
							System.out.println("V�gzetts�gek: " + edu2);
							System.out.println(work + "\n");
						}
					}
				}
			}
			
			System.out.println("-----------------------");
			
			//Ki�ratom azokat az �ttermeket, amik �t csillagosak
			System.out.println("Azok az �ttermek, amik �t csillagosak:\n");
			
			nodeList = doc.getElementsByTagName("etterem");
			
			for(int i = 0; i < nodeList.getLength(); i++) {
					
				Node nNode = nodeList.item(i);
					
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) nNode;
					
					Node node5 = elem.getElementsByTagName("csillag").item(0);
					String stars = node5.getTextContent();
					
					if("5".equals(stars)) {
						String id = elem.getAttribute("ekod");

						Node node1 = elem.getElementsByTagName("nev").item(0);
						String name = node1.getTextContent();
						
						Node node2 = elem.getElementsByTagName("varos").item(0);
						String city = node2.getTextContent();
						
						Node node3 = elem.getElementsByTagName("utca").item(0);
						String street = node3.getTextContent();
						
						Node node4 = elem.getElementsByTagName("hazszam").item(0);
						String number = node4.getTextContent();
						
						String adr = city + ", " + street + " utca " + number + ".";
						
						System.out.println("�tterem ID: " + id);
						System.out.println("N�v: " + name);
						System.out.println("C�m: " + adr);
						System.out.println("Csillag: " + stars + "\n");
					}
				}
			}
			
			System.out.println("-----------------------");
			
			//Ki�ratom azokat a gyakornokokat, akik be vannak osztva d�lut�nra
			System.out.println("Azok a gyakornokok, akik be vannak osztva d�lut�nra:\n");
			
			nodeList = doc.getElementsByTagName("gyakornok");
			
			for(int i = 0; i < nodeList.getLength(); i++) {
					
				Node nNode = nodeList.item(i);
					
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) nNode;
						
					
					Node node4;
					
					for(int j=0;j<elem.getElementsByTagName("muszak").getLength();j++) {
						node4 = elem.getElementsByTagName("muszak").item(j);
						String shift1 = node4.getTextContent();
						if("D�lut�n".equals(shift1)) {
							String id = elem.getAttribute("gykod");
							String eid = elem.getAttribute("e_gy");
							
							String work = "Ez a gyakornok a(z) " + eid + " azonos�t�j� �tteremben dolgozik." ;
							
							Node node1 = elem.getElementsByTagName("nev").item(0);
							String name = node1.getTextContent();
							
							Node node2 = elem.getElementsByTagName("kezdete").item(0);
							String start = node2.getTextContent();
							
							Node node3 = elem.getElementsByTagName("idotartama").item(0);
							String duration = node3.getTextContent();
							
							String practical = "kezdete: " + start + ", id�tartama: " + duration;
							
							String shift2 = "";
							
							for(int k=0;k<elem.getElementsByTagName("muszak").getLength();k++) {
								node4 = elem.getElementsByTagName("muszak").item(k);
								if(k == elem.getElementsByTagName("muszak").getLength()-1) {
									shift2 += node4.getTextContent();
								}else {
									shift2 += node4.getTextContent() + ", ";
								}
							}
							
							
							System.out.println("Gyakornok ID: " + id);
							System.out.println("N�v: " + name);
							System.out.println("Gyakorlat " + practical);
							System.out.println("M�szak: " + shift2);
							System.out.println(work + "\n");
						}
					}
				}
			}
	}
}

