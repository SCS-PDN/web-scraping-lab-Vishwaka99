package com.example.scraper;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ScrapeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");
        String[] optionsArray = request.getParameterValues("option");

        Set<String> options = new HashSet<>();
        if (optionsArray != null) {
            options.addAll(Arrays.asList(optionsArray));
        }

        Map<String, List<String>> scrapedData = ScraperUtil.scrape(url, options);

        
        Gson gson = new Gson();
        String jsonOutput = gson.toJson(scrapedData);

        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Scrape Results</title></head><body>");
        out.println("<h2>Scraped Results</h2>");
        out.println("<table border='1'><tr><th>Type</th><th>Data</th></tr>");

        for (Map.Entry<String, List<String>> entry : scrapedData.entrySet()) {
            out.println("<tr><td>" + entry.getKey() + "</td><td><ul>");
            for (String data : entry.getValue()) {
                out.println("<li>" + data + "</li>");
            }
            out.println("</ul></td></tr>");
        }

        out.println("</table>");
        out.println("<h3>JSON Output</h3>");
        out.println("<pre>" + jsonOutput + "</pre>");
        out.println("</body></html>");
    }
}
