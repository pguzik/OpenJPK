package pl.pguzik.jpk.domain.jpk

import spock.lang.Specification

class XmlToUtf8EncoderSpec extends Specification {

    def "should encode xml file to utf8"() {
        given:
            XmlToUtf8Encoder encoder = new XmlToUtf8Encoder()
            ClassLoader classLoader = getClass().getClassLoader();
            File xmlFile = new File(classLoader.getResource("testFile.xml").getFile());

        when:
            String encodedToUtf8 = encoder.encodeToUtf8(xmlFile)

        then:
            encodedToUtf8.getBytes("UTF-8") != null
    }
}
