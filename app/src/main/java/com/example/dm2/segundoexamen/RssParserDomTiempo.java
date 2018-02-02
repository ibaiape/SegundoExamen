package com.example.dm2.segundoexamen;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RssParserDomTiempo {
    private URL rssURL;

    public RssParserDomTiempo(String url){
        try{
            this.rssURL =new URL(url);
        }catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String parse() {
        //Instanciamos la fabrica para DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String str = "";
        try {
            //Creamos un nuevo parser DOM
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Realizamos la lectura completa del XML
            Document dom = builder.parse(this.getInputStream());
            //Nos posicionamos en el nodo principal del árbol (<rss>)
            Element root = dom.getDocumentElement();
            //Localizamos todos los elemntos <item>
            NodeList items = root.getElementsByTagName("hora");
            //Recorremos la lista de noticias
            Node item = items.item(0);

            NodeList datosDia = item.getChildNodes();

            for (int i=0; i<datosDia.getLength(); i++){
                Node dato = datosDia.item(i);
                try{
                    if(dato.getNodeName().equals("hora_datos")){
                        str+="Hora: "+obtenerTexto(dato);
                    }
                    if(dato.getNodeName().equals("temperatura")){
                        str+="Temperatura: "+obtenerTexto(dato);
                    }
                    if(dato.getNodeName().equals("texto")){
                        str+="Estado del cielo: "+obtenerTexto(dato);
                    }

                    str += "\n";
                }catch (ClassCastException e){
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return str;
    }
    public String obtenerTexto (Node dato) {

        StringBuilder texto = new StringBuilder();
        NodeList fragmentos = dato.getChildNodes();

        for (int k=0; k<fragmentos.getLength(); k++) {
            texto.append(fragmentos.item(k).getNodeValue());
        }

        return texto.toString();

    }
    private InputStream getInputStream() {
        try {
            return rssURL.openConnection().getInputStream();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}