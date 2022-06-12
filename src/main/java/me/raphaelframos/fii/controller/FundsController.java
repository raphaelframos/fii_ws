package me.raphaelframos.fii.controller;

import me.raphaelframos.fii.data.FundDTO;
import me.raphaelframos.fii.data.FundsDTO;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("funds")
public class FundsController {

    @RequestMapping("/")
    public void scrapping(){
        try {
            Document document = Jsoup.connect("https://www.fundsexplorer.com.br/funds").get();
            Elements elements = document.getElementsByClass("item");
            FundsDTO fundsDTO = new FundsDTO(elements.size());
            for (Element ads: elements) {
                try{
                    Elements elementsSymbol = ads.getElementsByClass("symbol");
                    String symbol = Objects.requireNonNull(elementsSymbol.first()).text();
                    Elements elementsName = ads.getElementsByClass("name");
                    String name = Objects.requireNonNull(elementsName.first()).text();
                    Elements elementsAdmin = ads.getElementsByClass("admin");
                    String admin = Objects.requireNonNull(elementsAdmin.first()).text();
                    String href = ads.getElementsByTag("a").attr("href");
                    FundDTO fundDTO = new FundDTO(name, admin, symbol);
                    fundsDTO.add(fundDTO);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
