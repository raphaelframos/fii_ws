package me.raphaelframos.fii.controller;

import me.raphaelframos.fii.data.*;
import me.raphaelframos.fii.utils.LogUtils;
import me.raphaelframos.fii.utils.SoupUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("funds")
public class FundsController {

    private static String URL = "https://www.fundsexplorer.com.br";

    @RequestMapping("/")
    public ResponseEntity<FundsDTO> funds(){
        FundsDTO fundsDTO = new FundsDTO();
        try {
            Document document = Jsoup.connect(URL + "/funds").get();
            Elements elements = document.getElementsByClass("item");
            fundsDTO.setTotal(elements.size());
            for (Element ads: elements) {
                try{
                    Elements elementsSymbol = ads.getElementsByClass("symbol");
                    String symbol = SoupUtils.text(elementsSymbol.first());
                    Elements elementsName = ads.getElementsByClass("name");
                    String name = SoupUtils.text(elementsName.first());
                    Elements elementsAdmin = ads.getElementsByClass("admin");
                    String admin = SoupUtils.text(elementsAdmin.first());
                    String href = ads.getElementsByTag("a").attr("href");
                    FundDTO fundDTO = new FundDTO(name, admin, symbol, href);
                    fundsDTO.add(fundDTO);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(fundsDTO);
    }

    @RequestMapping("/details")
    public ResponseEntity<DetailFundDTO> details(@PathParam("href") String href){
        DetailFundDTO detailFundDTO = new DetailFundDTO();
        try {
            Document document = Jsoup.connect(URL + href).get();
            Elements elements = document.getElementsByClass("carousel-cell");
            elements.forEach(e -> {
                String title = SoupUtils.text(e.getElementsByClass("indicator-title").first());
                String value = SoupUtils.text(e.getElementsByClass("indicator-value").first());
                if(SoupUtils.isValid(title, value)){
                    detailFundDTO.add(new IndicatorFundDTO(title, value));
                }
            });

            Element element = document.getElementById("stock-price");
            if(element != null){
                Element priceElement = element.getElementsByClass("price").first();
                Element percentElement = element.getElementsByClass("percentage").first();
                String price = SoupUtils.text(priceElement);
                String percent = SoupUtils.text(percentElement);
                detailFundDTO.setStock(new StockFundDTO(price, percent));
            }

            Elements information = document.getElementsByClass("text-wrapper");
            information.forEach(info -> {
                Element titleElement = info.getElementsByClass("title").first();
                Element descriptionElement = info.getElementsByClass("description").first();
                Element contentElement = info.getElementsByClass("content").first();

                InfoFundDTO infoFundDTO = new InfoFundDTO(SoupUtils.text(titleElement), SoupUtils.text(descriptionElement), SoupUtils.text(contentElement));
                detailFundDTO.add(infoFundDTO);
            });

            Elements table = document.select("table"); //select the first table.
            LogUtils.show("table", "Tamanho " + table.size());
            table.forEach(t -> {
                LogUtils.show("tables", SoupUtils.text(t));

            });



        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(detailFundDTO);
    }
}
