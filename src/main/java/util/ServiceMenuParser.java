package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.MenuContent;
import model.MenuNode;

public class ServiceMenuParser {
	public static int maxDepth;
	public static int nRighe = 1;
	public static List<String> valoriCol = Arrays.asList("ServiceId", "NodeName", "NodeType", "GroupType", "FlowType",
			"ResourceId");

	public void deserializzaJson_CreaxlsFile() {
		Gson gson = new Gson();
		// Letto il file di properties per stabilire input e output
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("prop.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			// Deserializzazione del .json
			MenuContent mc = gson.fromJson(new FileReader(prop.getProperty("input")), MenuContent.class);
			// Creazione excel
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Menu " + mc.getVersion());
			XSSFRow row;

			profondita(mc.getNodes(), 0);

			// Creazione header
			row = sheet.createRow(0);
			int lunghezza = maxDepth + 1;
			for (int i = 0; i <= maxDepth; i++) {
				row.createCell(i).setCellValue(i);
			}
			for (int i = lunghezza, j = 0; i <= valoriCol.size() + maxDepth; i++, j++) {
				row.createCell(i).setCellValue(valoriCol.get(j));
			}
			// Popolamento file excel
			creaRiga(mc.getNodes(), sheet);

			// Output file
			FileOutputStream out = new FileOutputStream(new File(prop.getProperty("output")));
			workbook.write(out);
			out.close();

		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			e.printStackTrace();
		}

	}

	public void profondita(List<MenuNode> listNode, int depth) {
		// Controllo se la profondità è quella massima
		if (depth > maxDepth) {
			maxDepth = depth;
		}
		// Per ogni nodo nella lista se non è un nodo foglia richiama il metodo
		// profondità aggiornando la depth aggiungendo 1
		for (MenuNode mn : listNode) {
			mn.setDepth(depth);
			if (mn.getNodes() != null) {
				profondita(mn.getNodes(), depth + 1);
			}
		}
	}

	private static String getValoreCella(String colonna, MenuNode menuNode) {
		// Controlli sulla presenza e correttezza dei valori.
		// Non in tutti i nodi sono presenti i valori da ritornare nel file excel.
		// Se questi mancano o non sono quelli corretti ritornare uno spazio vuoto.
		switch (colonna) {
		case "ServiceId":
			return menuNode.getNodeType().equals("service") ? "" + menuNode.getNodeId() : "";
		case "NodeName":
			return menuNode.getNodeName();
		case "NodeType":
			return menuNode.getNodeType();
		case "GroupType":
			return menuNode.getGroupType() != null ? menuNode.getFlowType() : "";
		case "FlowType":
			return menuNode.getFlowType() != null ? menuNode.getFlowType() : "";
		case "ResourceId":
			return menuNode.getResource() != null ? "" + menuNode.getResource().getId() : "";

		default:
			return null;
		}
	}

	private static void creaRiga(List<MenuNode> listaNodi, XSSFSheet sheet) {
		for (MenuNode nodo : listaNodi) {
			XSSFRow row = sheet.createRow(nRighe);
			// Inserisce la X nel file excel in base alla profondità
			for (int k = 0; k <= maxDepth; k++) {
				row.createCell(k).setCellValue(nodo.getDepth() == k ? "X" : "");
			}
			// Inserisce i valori richiesti dopo l'inserimento della profondità
			for (int j = maxDepth + 1; j <= valoriCol.size() + maxDepth; j++) {
				row.createCell(j).setCellValue(getValoreCella(valoriCol.get(j - maxDepth - 1), nodo));
			}
			nRighe++;
			// Metodo ricorsivo per scorrere tutti i nodi
			if (nodo.getNodes() != null) {
				creaRiga(nodo.getNodes(), sheet);
			}
		}
	}
}
