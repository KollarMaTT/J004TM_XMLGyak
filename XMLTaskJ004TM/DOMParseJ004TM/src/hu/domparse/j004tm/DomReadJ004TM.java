package hu.domparse.j004tm;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomReadJ004TM {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException{
		
		//Ebb�l a f�jlb�l olvas
		File xmlFile = new File("src/XMLJ004TM.xml");
		
		//A DocumentBuilderFactory-b�l megkapjuk a DocumentBuildert
		//A DocumentBuilder tartalmazza az API-t a DOM-dokumentum p�ld�nyok XML-dokumentumb�l val� beszerz�s�hez
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//A parse() met�dus elemzi az XML f�jlt �s lek�rem a documentbe
		Document doc = dBuilder.parse(xmlFile);
		
		//A dokumentum normaliz�l�sa seg�t a helyes eredm�nyek el�r�s�ben
		doc.getDocumentElement().normalize();
		
		//Ki�ratjuk a dokumentum gy�k�relem�t
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		
		//A getElementsByTagname() met�dus seg�ts�g�vel megkapjuk az etterem elem NodeListj�t
		NodeList nList = doc.getElementsByTagName("etterem");
		
		//V�gigmegy�nk rajta egy for ciklussal
		for(int i = 0; i < nList.getLength(); i++) {
			
			//Lek�rj�k a lista aktu�lis elem�t
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				//Elementt� konvert�ljuk az aktu�lis elemet
				Element elem = (Element) nNode;
				
				//Lek�rj�k az aktu�lis elem attrib�tum�nak tartalm�t
				String id = elem.getAttribute("ekod");
				
				//Lek�rj�k az aktu�lis elem gyerekelemeinke tartalm�t
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("varos").item(0);
				String city = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("utca").item(0);
				String street = node3.getTextContent();
				
				Node node4 = elem.getElementsByTagName("hazszam").item(0);
				String number = node4.getTextContent();
				
				Node node5 = elem.getElementsByTagName("csillag").item(0);
				String stars = node5.getTextContent();
				
				
				//A c�m gyerekelemeinek �sszerak�sa egy stringbe
				String adr = city + ", " + street + " utca " + number + ".";
				
				
				//Form�zva ki�ratjuk a lek�rt inform�ci�kat az adott elemr�l
				System.out.println("�tterem ID: " + id);
				System.out.println("N�v: " + name);
				System.out.println("C�m: " + adr);
				System.out.println("Csillag: " + stars);
				
			}
		}
		
		nList = doc.getElementsByTagName("foszakacs");
		
		for(int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				String id = elem.getAttribute("fkod");
				String eid = elem.getAttribute("e_f");
				
				String work = "Ez a f�szak�cs a(z) " + eid + " azonos�t�j� �tteremben dolgozik." ;
				
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("eletkor").item(0);
				String age = node2.getTextContent();
				
				Node node3;
				String edu = "";
				
				
				//T�bb v�gzetts�ge is lehet egy szak�csnak, �gy ezt a form�zott ki�rat�st csin�ltam,
				//hogy helyesen �rassa ki a v�gzetts�gek sz�m�t�l f�ggetlen�l
				for(int j=0;j<elem.getElementsByTagName("vegzettseg").getLength();j++) {
					node3 = elem.getElementsByTagName("vegzettseg").item(j);
					if(j == elem.getElementsByTagName("vegzettseg").getLength()-1) {
						edu += node3.getTextContent();
					}else {
					edu += node3.getTextContent() + ", ";
					}
				}
				
				
				System.out.println("F�szak�cs ID: " + id);
				System.out.println("N�v: " + name);
				System.out.println("Kor: " + age);
				System.out.println("V�gzetts�gek: " + edu);
				System.out.println(work);
			}
		}
		
		nList = doc.getElementsByTagName("szakacs");
		
		for(int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				String id = elem.getAttribute("szkod");
				String eid = elem.getAttribute("e_sz");
				
				String work = "Ez a szak�cs a(z) " + eid + " azonos�t�j� �tteremben dolgozik." ;
				
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("reszleg").item(0);
				String department = node2.getTextContent();
				
				Node node3;
				String edu = "";
				
				for(int j=0;j<elem.getElementsByTagName("vegzettseg").getLength();j++) {
					node3 = elem.getElementsByTagName("vegzettseg").item(j);
					if(j == elem.getElementsByTagName("vegzettseg").getLength()-1) {
						edu += node3.getTextContent();
					}else {
					edu += node3.getTextContent() + ", ";
					}
				}

				
				System.out.println("Szak�cs ID: " + id);
				System.out.println("N�v: " + name);
				System.out.println("R�szleg: " + department);
				System.out.println("V�gzetts�gek: " + edu);
				System.out.println(work);
			}
		}
		
		nList = doc.getElementsByTagName("gyakornok");
		
		for(int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
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
				
				Node node4;
				String shift = "";
				
				
				//T�bb m�szakban is dolgozhat egy gyakornok, �gy ezt a form�zott ki�rat�st csin�ltam,
				//hogy helyesen �rassa ki a m�szakok sz�m�t�l f�ggetlen�l
				for(int j=0;j<elem.getElementsByTagName("muszak").getLength();j++) {
					node4 = elem.getElementsByTagName("muszak").item(j);
					if(j == elem.getElementsByTagName("muszak").getLength()-1) {
						shift += node4.getTextContent();
					}else {
						shift += node4.getTextContent() + ", ";
					}
				}
				
				
				System.out.println("Gyakornok ID: " + id);
				System.out.println("N�v: " + name);
				System.out.println("Gyakorlat " + practical);
				System.out.println("M�szak: " + shift);
				System.out.println(work);
			}
		}
		
		nList = doc.getElementsByTagName("vendeg");
		
		for(int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				String id = elem.getAttribute("vkod");
				
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("eletkor").item(0);
				String age = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("varos").item(0);
				String city = node3.getTextContent();
				
				Node node4 = elem.getElementsByTagName("utca").item(0);
				String street = node4.getTextContent();
				
				Node node5 = elem.getElementsByTagName("hazszam").item(0);
				String number = node5.getTextContent();
				
				String adr = city + " " + street + " utca " + number + ".";
				
				System.out.println("Vend�g ID: " + id);
				System.out.println("N�v: " + name);
				System.out.println("Kor: " + age);
				System.out.println("C�m: " + adr);
				
			}
		}
		
		nList = doc.getElementsByTagName("rendeles");
		
		for(int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				String eid = elem.getAttribute("e_v_e");
				String vid = elem.getAttribute("e_v_v");
				
				String dinner = "A(z) " + eid + " azonos�t�val rendelkez� �tteremb�l rendelt a(z) " + vid + " azonos�t�j� vend�g.";
				
				Node node1 = elem.getElementsByTagName("osszeg").item(0);
				String price = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("etel").item(0);
				String food = node2.getTextContent();
				
				System.out.println(dinner);
				System.out.println("�sszeg: " + price);
				System.out.println("�tel: " + food);
				
			}
		}
	}
}
