package service;

import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;



public class Parser{

	private static String className=null;
	private static String view=null;
	
	public static String path=null;
	
	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		Parser.path = path;
	}

	public static void setClassName(String className) {
		Parser.className = className;
	}

	public static void setView(String view) {
		Parser.view = view;
	}

	public static String getClassName() {
		return className;
	}

	public static String getView() {
		return view;
	}



	public static boolean P(String module) {

		Document xmlDoc=getDomObj(getPath());		 // -->> path
		System.out.println(xmlDoc.getDocumentElement().getNodeName());
		System.out.println("-------------------------");

		
		NodeList list=xmlDoc.getElementsByTagName("mvc");

		for(int i=0;i<list.getLength();i++)
			{
					Node node=list.item(i);

			Element e=(Element)node;

				if(e.getAttribute("id").equals(module))
			{

			System.out.println(e.getAttribute("id"));
			System.out.println(e.getElementsByTagName("ctrl").item(0).getTextContent());
			System.out.println(e.getElementsByTagName("view").item(0).getTextContent());
			
			className=e.getElementsByTagName("ctrl").item(0).getTextContent();
			view=e.getElementsByTagName("view").item(0).getTextContent();

			System.out.println("-------------------------");
			
			
			
			
			return true;

		}
		
	}
		return false;

	}



	private static Document getDomObj(String s1){

		try{
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder=factory.newDocumentBuilder();

			return builder.parse(new InputSource(s1));

		}

		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}
}