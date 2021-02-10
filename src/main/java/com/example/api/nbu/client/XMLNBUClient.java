package com.example.api.nbu.client;

import com.example.api.model.CurrencyRate;
import com.example.api.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

@Component
public class XMLNBUClient implements ApplicationRunner {
    private String XML_DAILY_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchanges";
    private String CODE = "r030";
    private String TXT = "txt";
    private String RATE = "rate";
    private String CC = "cc";

    private ArrayList<CurrencyRate> rates = new ArrayList<>();
    private Document document;
    private RateRepository rateRepository;

    @Autowired
    public XMLNBUClient(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        document = getCurrencyRate();
        saveInfoAboutAllChildNodes(document, CODE);
        saveInfoAboutAllChildNodes(document, TXT);
        saveInfoAboutAllChildNodes(document, RATE);
        saveInfoAboutAllChildNodes(document, CC);

        long count = rateRepository.count();
        if (count == 0) {
            for (CurrencyRate cr : rates) {
                rateRepository.save(cr);
            }
        }
    }

    private void saveInfoAboutAllChildNodes(Document document, String element) {

        NodeList matchedElementsList = document.getElementsByTagName(element);
        if (!(matchedElementsList.getLength() == 0)) {
            for (int i = 0; i < matchedElementsList.getLength(); i++) {
                Node attributes = matchedElementsList.item(i);
                CurrencyRate currencyRate = new CurrencyRate();

                String x = attributes.getFirstChild().getNodeValue();
                String s = attributes.getNodeValue();
                switch (element) {
                    case "r030":
                        currencyRate.setCode(attributes.getFirstChild().getNodeValue());
                        rates.add(currencyRate);
                        break;
                    case "rate":
                        rates.get(i).setRate(Double.parseDouble(attributes.getFirstChild().getNodeValue()));
                        break;
                    case "txt":
                        rates.get(i).setTxt(attributes.getFirstChild().getNodeValue());
                        break;
                    case "cc":
                        rates.get(i).setCc(attributes.getFirstChild().getNodeValue());
                        break;

                }

            }

        }

    }

    public Document getCurrencyRate() throws ParserConfigurationException, SAXException, IOException {
        try {
            String pre_apiURL = XML_DAILY_URL;
            URL url = new URL(pre_apiURL);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(url.openStream());
            return doc;


        } catch (Exception e) {
            return null;
        }
    }
}
