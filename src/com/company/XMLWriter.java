package com.company;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class XMLWriter {

    private static final XMLOutputFactory FACTORY = XMLOutputFactory.newInstance();

    public static void XMLWrite(List<AquariumComponent> list) {
        int sum = Aquarium.getTotalPrice(list);
        try {
            XMLStreamWriter writer = FACTORY.createXMLStreamWriter(
                    new FileWriter("Output.xml"));

            writer.writeStartDocument();
            writer.writeCharacters("\n");
            writer.writeStartElement("result");
            writer.writeCharacters("\n\t");
            writer.writeStartElement("aquariumComposition");
            writer.writeCharacters("\n\t");
            for (AquariumComponent component :
                    list) {
                writer.writeCharacters(component.getName());
                writer.writeCharacters("\n\t");
            }
            writer.writeEndElement();
            writer.writeCharacters("\n\t");
            writer.writeStartElement("totalPrice");
            writer.writeCharacters("\s" + sum + "\s");
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeEndDocument();

            writer.flush();
            writer.close();

        } catch (
                XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

}
