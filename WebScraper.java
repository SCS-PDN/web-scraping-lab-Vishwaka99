import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScraper {

    public static void main(String[] args) {
        String url = "https://bbc.com"; // You can change this to any URL you like

        try {
            // Fetch the document
            Document doc = Jsoup.connect(url).get();

            // Title of the webpage
            String title = doc.title();
            System.out.println("Title: " + title);
            System.out.println();

            // Headings (h1 to h6)
            System.out.println("Headings:");
            for (int i = 1; i <= 6; i++) {
                Elements headings = doc.select("h" + i);
                for (Element heading : headings) {
                    System.out.println("h" + i + ": " + heading.text());
                }
            }

            System.out.println();

            // All links
            System.out.println("Links:");
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                System.out.println(link.attr("abs:href") + " -> " + link.text());
            }

        } catch (IOException e) {
            System.out.println("Error fetching the URL: " + e.getMessage());
        }
    }
}
