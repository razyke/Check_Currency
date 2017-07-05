package com.puper.asuper.checkcurrency;

import android.os.AsyncTask;
import android.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Daniil Smirnov on 03.07.2017.
 * All copy registered MF.
 */
public class GetFromInternet extends AsyncTask<String,Void,Double> {
    /**
     *
     * @param aDouble - Принимаем данные в формате double
     *
     *   В данном классе обращаемся по заданным параметрам на XML от ЦБ и получаем нужную нам валюту по ID.
     *   На приём должны приниматься данные по дате в формате - dd/mm/yyyy
     */
    @Override
    protected void onPostExecute(Double aDouble) {
        super.onPostExecute(aDouble);
    }

    @Override
    protected Double doInBackground(String... Url) {


        try {
            String cbURL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req="+Url[0];


            URL url = new URL(cbURL);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Valute");

            for (int i=0; i<nodeList.getLength();i++){
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE==node.getNodeType()){
                    Element element = (Element) node;
                    if (element.getAttribute("ID").equals("R01235")) {
                        String elementFromXML = element.getElementsByTagName("Value").item(0).getTextContent();
                        Log.i(Constants.INFO, "[GetFromInternet] С xml страницы ЦБ получаем $ = " + elementFromXML);
                        Log.i(Constants.INFO,"Наше супер округление = "+getRoundigOff(elementFromXML));
                        return getRoundigOff(elementFromXML);
                    }
                }
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(Constants.ERRORS,"[GetFromInternet] - Не указан протокол");
            return 0.0;
        } catch (ParserConfigurationException e) {
            Log.e(Constants.ERRORS,"[GetFromInternet] - Запрашиваемая функциональность не доступна");
            e.printStackTrace();
            return 0.0;
        } catch (SAXException e) {
            Log.e(Constants.ERRORS,"[GetFromInternet] - Последовательное чтение с XML не удалось");
            e.printStackTrace();
            return 0.0;
        } catch (IOException e) {
            Log.e(Constants.ERRORS,"[GetFromInternet] - Не найдены входные данные");
            e.printStackTrace();
            return 0.0;
        }

        return null;
    }


    /**
     *
     * @param chislo - Значение принимаемое с ЦБ в формате **.****
     * @return возвращаем округлённое число до **.*
     */
    private double getRoundigOff(String chislo){

        StringBuilder sb = new StringBuilder(chislo);
        sb.replace(2,3,".");
        chislo = sb.toString();

        char costil[] = chislo.toCharArray();
        String[] array = new String[costil.length];
        for (int i=0;i<costil.length;i++){
            array[i] = String.valueOf(costil[i]);
        }

        for (int i=0;i<4;i++){
            if (Integer.parseInt(array[array.length-1-i])>=5){
                if (i==3){
                    if (array[array.length-1-i].equals("10")){
                        array[array.length-1-i]="0";
                        array[1] = String.valueOf(Integer.parseInt(array[1])+1);
                        if (array[1].equals("10")){
                            array[1]="0";
                            array[0] = String.valueOf(Integer.parseInt(array[0])+1);
                        }
                    }
                }else {
                    if (array[array.length - 1 - i].equals("10")) {
                        array[array.length - 1 - i] = "0";
                    }
                    array[array.length - 2 - i] = String.valueOf(Integer.parseInt(array[array.length - 2 - i]) + 1);
                }
            }
        }
        StringBuilder go = new StringBuilder("");
        for (int i=0;i<4;i++){
            go.append(array[i]);
        }
        double res = Double.parseDouble(go.toString());
        return res;
    }

}
