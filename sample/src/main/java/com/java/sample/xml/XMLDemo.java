package com.java.sample.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

//參考文章 https://www.796t.com/post/ZGwwcQ==.html

public class XMLDemo {
    public static void main(String[] args) throws Exception  {
        JAXBContext  jc  =JAXBContext.newInstance(Customer.class);

        XMLInputFactory  xif=XMLInputFactory.newFactory();
        xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        xif.setProperty(XMLInputFactory.SUPPORT_DTD, true);
        XMLStreamReader xsr =xif.createXMLStreamReader(new StreamSource("src/main/java/com/java/sample/xml/input.xml"));
        //Unmarshaller 為 將xml 反序列化 to java
        Unmarshaller unmarshaller =jc.createUnmarshaller();
        Customer customer =(Customer)unmarshaller.unmarshal(xsr);

        Marshaller marshaller =jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(customer, System.out);

    }
}
