package com.wipro.digital.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.wipro.digital.domain.WebPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by ramesh on 2/19/2017.
 * This is a Main controller for the WebCrawler Application
 */

@RestController
public class WebCrawlerController {



    public static Set<String> crawledLinks = new HashSet<String>();
    public static String domainName=null;
    public static String domainNameWithoutBackSlash=null;
    /**
     * This method gets called when the /webCrawler path is invoked.
     * @param domainName
     */
    @RequestMapping("/webCrawler")
    public WebPage webCrawl(@RequestParam(value="domain", defaultValue="http://wiprodigital.com/") String domainName) throws IOException {
        //WebPage rootWebPage = new WebPage("f:\\test.html");
        this.domainName = domainName;
        this.domainNameWithoutBackSlash = domainName.substring(0,domainName.length()-1);
        WebPage rootWebPage = new WebPage(domainName);
        processWebPageInternalCall(rootWebPage);
        return rootWebPage;
    }

    /**
     * This method recurcisvely crawls through webpages and retrieves the respective information.
     * @param rootWebPage
     */
    public static void processWebPageInternalCall(WebPage rootWebPage)throws IOException {

        Document doc = Jsoup.connect(rootWebPage.getLinkName()).get();
        crawledLinks.add(rootWebPage.getLinkName());
        //File input = new File(rootWebPage.getLinkName());
        //Document doc = Jsoup.parse(input, "UTF-8");
        //get all links
        Elements links = doc.select("a[href]");
        Elements images = doc.select("img[src]");
        List<WebPage> childLinks = links.parallelStream().filter(link -> link.attr("href").startsWith(domainName)).map(s -> new WebPage(s.attr("href"))).collect(Collectors.toList());
        List<String> imagesList = images.parallelStream().map(s -> s.attr("src")).collect(Collectors.toList());
        List<String> externalLinks = links.parallelStream().filter(link -> (!link.attr("href").startsWith(domainNameWithoutBackSlash) && !link.attr("href").startsWith("#")) ).map(s -> s.attr("href")).collect(Collectors.toList());
        rootWebPage.setExternalLinks(externalLinks);
        rootWebPage.setImages(imagesList);
        if(!childLinks.isEmpty()) {
            rootWebPage.setChildLinks(childLinks);
            for (WebPage webPage : rootWebPage.getChildLinks()) {
                if(!crawledLinks.contains(webPage.getLinkName()))
                {
                    processWebPageInternalCall(webPage);
                }
            }
        }
    }



}
