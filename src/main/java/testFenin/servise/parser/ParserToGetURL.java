package testFenin.servise.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// not nullable safe

public class ParserToGetURL {
    public ParserToGetURL() {
    }

    public static ParserToGetURL get() {
        return new ParserToGetURL();
    }

    public String getPostURL(String tagURL) {
        try {
            Document document = Jsoup.connect(tagURL).get();
            List<Element> elementList = new ArrayList<>();
            Elements answers = document.getElementsByClass("posts-list");
            for (Element answerer : answers) {
                for (Node node : answerer.childNodes()) {
                    if (node.getClass().equals(Element.class)) {
                        elementList.add((Element) node);
                    }
                }
            }
            return elementList.get(0).getElementsByClass("post-link with-labels ").get(0).attributes().get("href");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
