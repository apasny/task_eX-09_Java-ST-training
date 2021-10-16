package com.company;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {

    public static void parseXML(String filePath) {
        List<AquariumComponent> aquariumList = new ArrayList<>();
        AquariumComponent aquariumComponent = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "fish" -> {
                            aquariumComponent = new Fish();
                            //Get the 'id' attribute from AquariumComponent element
                            Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                            if (idAttr != null) {
                                aquariumComponent.setId(Integer.parseInt(idAttr.getValue()));
                            }
                        }
                        case "reptile" -> {
                            aquariumComponent = new Reptile();
                            //Get the 'id' attribute from AquariumComponent element
                            Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                            if (idAttr != null) {
                                aquariumComponent.setId(Integer.parseInt(idAttr.getValue()));
                            }
                        }
                        case "crustacean" -> {
                            aquariumComponent = new Crustacean();
                            //Get the 'id' attribute from AquariumComponent element
                            Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                            if (idAttr != null) {
                                aquariumComponent.setId(Integer.parseInt(idAttr.getValue()));
                            }
                        }
                        case "accessory" -> {
                            aquariumComponent = new Accessory();
                            //Get the 'id' attribute from AquariumComponent element
                            Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                            if (idAttr != null) {
                                aquariumComponent.setId(Integer.parseInt(idAttr.getValue()));
                            }
                        }
                        //set the other variables from xml elements
                        case "price" -> {
                            xmlEvent = xmlEventReader.nextEvent();
                            assert aquariumComponent != null;
                            aquariumComponent.setPrice(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        }
                        case "name" -> {
                            xmlEvent = xmlEventReader.nextEvent();
                            assert aquariumComponent != null;
                            aquariumComponent.setName(xmlEvent.asCharacters().getData());
                        }
                    }
                }
                //if AquariumComponent end element is reached, add AquariumComponent object to list
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    switch (endElement.getName().getLocalPart()) {
                        case "fish", "reptile", "crustacean", "accessory" -> aquariumList.add(aquariumComponent);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        XMLWriter.XMLWrite(aquariumList);
    }

}

