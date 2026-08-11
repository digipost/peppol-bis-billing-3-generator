/**
 * Copyright (C) Posten Norge AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package peppol.bis.invoice3.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Focused XML operations needed by the billing generator, backed by JDK DOM. */
public final class Xml {

    private Xml() {
    }

    public static Element el(String name) {
        Document document = newDocument();
        Element element = document.createElement(name);
        document.appendChild(element);
        return element;
    }

    public static Element el(QName name, Content... contents) {
        Document document = newDocument();
        String qualifiedName = name.getPrefix().isEmpty()
            ? name.getLocalPart()
            : name.getPrefix() + ":" + name.getLocalPart();
        Element element = document.createElementNS(name.getNamespaceURI(), qualifiedName);
        document.appendChild(element);
        Arrays.stream(contents).forEach(content -> content.apply(element));
        return element;
    }

    public static Content text(String value) {
        return element -> element.appendChild(element.getOwnerDocument().createTextNode(value));
    }

    public static Content attr(String name, String value) {
        return element -> element.setAttribute(name, value);
    }

    public static Element attr(Element element, String name, String value) {
        element.setAttribute(name, value);
        return element;
    }

    public static String attributeOrNull(Element element, String name) {
        return element.hasAttribute(name) ? element.getAttribute(name) : null;
    }

    public static Element append(Element parent, Element child) {
        Node adopted = parent.getOwnerDocument().adoptNode(child);
        parent.appendChild(adopted != null ? adopted : parent.getOwnerDocument().importNode(child, true));
        return parent;
    }

    public static void declareNamespaces(Element element, Namespace... namespaces) {
        for (Namespace namespace : namespaces) {
            String attributeName = namespace.getPrefix().isEmpty()
                ? XMLConstants.XMLNS_ATTRIBUTE
                : XMLConstants.XMLNS_ATTRIBUTE + ":" + namespace.getPrefix();
            element.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, attributeName, namespace.getUri());
        }
    }

    public static Document parse(String xml) {
        try {
            DocumentBuilder builder = documentBuilderFactory().newDocumentBuilder();
            builder.setErrorHandler(new DefaultHandler() {
                @Override
                public void error(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    throw exception;
                }
            });
            return builder.parse(new InputSource(new StringReader(xml)));
        } catch (Exception exception) {
            throw new IllegalArgumentException("Unable to parse XML", exception);
        }
    }

    public static List<Element> find(Document document, String... path) {
        return find(document.getDocumentElement(), path);
    }

    public static List<Element> find(Element root, String... path) {
        List<Element> matches = Collections.singletonList(root);
        for (String name : path) {
            List<Element> children = new ArrayList<>();
            for (Element parent : matches) {
                NodeList childNodes = parent.getChildNodes();
                for (int index = 0; index < childNodes.getLength(); index++) {
                    Node child = childNodes.item(index);
                    if (child instanceof Element && name.equals(localName((Element) child))) {
                        children.add((Element) child);
                    }
                }
            }
            matches = children;
        }
        return matches;
    }

    public static Element single(List<Element> elements) {
        if (elements.size() != 1) {
            throw new IllegalStateException("Expected exactly one XML element, found " + elements.size());
        }
        return elements.get(0);
    }

    public static String toXml(Document document) {
        return transform(new DOMSource(document), false);
    }

    public static String toXml(Element element) {
        return transform(new DOMSource(element), false);
    }

    public static String toIndentedXml(Element element) {
        return transform(new DOMSource(element), true);
    }

    private static String localName(Element element) {
        if (element.getLocalName() != null) {
            return element.getLocalName();
        }
        String nodeName = element.getNodeName();
        int separator = nodeName.indexOf(':');
        return separator < 0 ? nodeName : nodeName.substring(separator + 1);
    }

    private static String transform(DOMSource source, boolean indent) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            if (indent) {
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            }
            StringWriter output = new StringWriter();
            transformer.transform(source, new StreamResult(output));
            return output.toString();
        } catch (TransformerException exception) {
            throw new IllegalStateException("Unable to serialize XML", exception);
        }
    }

    private static Document newDocument() {
        try {
            return documentBuilderFactory().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException exception) {
            throw new IllegalStateException("Unable to create XML document", exception);
        }
    }

    private static DocumentBuilderFactory documentBuilderFactory() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setXIncludeAware(false);
        factory.setExpandEntityReferences(false);
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        return factory;
    }

    @FunctionalInterface
    public interface Content {
        void apply(Element element);
    }
}
