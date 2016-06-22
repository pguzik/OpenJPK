package pl.pguzik.jpk.domain.jpk;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.w3c.dom.Document;
import pl.pguzik.jpk.OpenJpkApplication;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

public class XmlToUtf8Encoder {

    String encodeToUtf8(File xmlFile) {
        String result;
        try {
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            String originalXmlFileName = xmlFile.getName();
            String removeXmlEtensionFromFileName = originalXmlFileName.replaceAll(".xml", "");
            String encodedXmlFileName = OpenJpkApplication.ROOT + "/" + removeXmlEtensionFromFileName + "asUtf8.xml";

            FileOutputStream out = new FileOutputStream(encodedXmlFileName);

            DOMSource domSource = new DOMSource(doc);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(domSource, new StreamResult(out));
            out.flush();
            out.close();
            result = Files.toString(new File(encodedXmlFileName), Charsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
