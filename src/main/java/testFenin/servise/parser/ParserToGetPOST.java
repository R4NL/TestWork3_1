package testFenin.servise.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import testFenin.entity.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// not nullable safe!!!!!!!
// some problems with not english letters (java change them)

public class ParserToGetPOST {
    private ParserToGetPOST parserToGetPOST;

    public ParserToGetPOST() {
    }

    public ParserToGetPOST(ParserToGetPOST parserToGetPOST) {
        this.parserToGetPOST = parserToGetPOST;
    }

    public static ParserToGetPOST get() {
        return new ParserToGetPOST();
    }

    public Post parse(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String a = "";
            List<Element> elementList = new ArrayList<>();
            Elements answers = document.getElementsByClass("post-content");
            for (Element answerer : answers) {
                for (Node node : answerer.childNodes()) {
                    if (node.getClass().equals(Element.class)) {
                        elementList.add((Element) node);
                    }
                }
            }
            String[] classes = {"post-data", "mistape_caption", "custom-related-posts", "wp-block-image"};
            for (Element element : elementList) {
                if (!Arrays.asList(classes).contains(element.className())) {
                    a += element.text() + System.lineSeparator();
                }
            }
            Post post = new Post();
            post.setAuthor(document.select("#post-content .author_name").text());
            post.setData(document.select("#post-content .post-date").text());
            post.setText(a);
            return post;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
