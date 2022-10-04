package com.raphaelframos.refii.scrap.service;

import com.raphaelframos.refii.scrap.data.*;
import com.raphaelframos.refii.utils.SoupUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class FundsExplorerServiceImpl implements FundsExplorerService {

    private static String URL = "https://www.fundsexplorer.com.br";

    private Document document(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    @Override
    public FundsDTO listFunds() {
        FundsDTO fundsDTO = new FundsDTO();
        try {
            Elements elements = document(URL + "/funds").getElementsByClass("item");
            fundsDTO.setTotal(elements.size());
            for (Element ads: elements) {
                saveFund(fundsDTO, ads);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fundsDTO;
    }

    private void saveFund(FundsDTO fundsDTO, Element ads) {
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

    @Override
    public DetailFundDTO details(String fund) {

        DetailFundDTO detailFundDTO = new DetailFundDTO();
        try {
            Document document = document(URL + fund);
            saveIndicator(detailFundDTO, document);
            saveStock(detailFundDTO, document);
            saveInformation(detailFundDTO, document);
            saveDividends(detailFundDTO, document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detailFundDTO;
    }

    private void saveDividends(DetailFundDTO detailFundDTO, Document document) {
        Element table = document.select("table").get(0);
        Elements rows = table.select("tr");
        Element header = rows.get(0);
        Elements headers = header.getElementsByTag("th");
        ArrayList<DividendFundDTO> dividends = new ArrayList<>();
        headers.forEach(h-> dividends.add(new DividendFundDTO(SoupUtils.text(h))));

        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            for(int j = 0; j < cols.size(); j++){
                if(i == 1) {
                    dividends.get(j).setValue(SoupUtils.text(cols.get(j)));
                }else{
                    dividends.get(j).setPercent(SoupUtils.text(cols.get(j)));
                }
            }
        }
        detailFundDTO.setDividends(dividends);
    }

    private void saveInformation(DetailFundDTO detailFundDTO, Document document) {
        Elements information = document.getElementsByClass("text-wrapper");
        information.forEach(info -> {
            Element titleElement = info.getElementsByClass("title").first();
            Element descriptionElement = info.getElementsByClass("description").first();
            Element contentElement = info.getElementsByClass("content").first();

            InfoFundDTO infoFundDTO = new InfoFundDTO(SoupUtils.text(titleElement), SoupUtils.text(descriptionElement), SoupUtils.text(contentElement));
            detailFundDTO.add(infoFundDTO);
        });
    }

    private void saveStock(DetailFundDTO detailFundDTO, Document document) {
        Element element = document.getElementById("stock-price");
        if(element != null){
            Element priceElement = element.getElementsByClass("price").first();
            Element percentElement = element.getElementsByClass("percentage").first();
            String price = SoupUtils.text(priceElement);
            String percent = SoupUtils.text(percentElement);
            detailFundDTO.setStock(new StockFundDTO(price, percent));
        }
    }

    private void saveIndicator(DetailFundDTO detailFundDTO, Document document) {
        Elements elements = document.getElementsByClass("carousel-cell");
        elements.forEach(e -> {
            String title = SoupUtils.text(e.getElementsByClass("indicator-title").first());
            String value = SoupUtils.text(e.getElementsByClass("indicator-value").first());
            if(SoupUtils.isValid(title, value)){
                detailFundDTO.add(new IndicatorFundDTO(title, value));
            }
        });
    }

    @Override
    public ArrayList<FundRankingDTO> ranking() {
        return convertValuesInFund(valuesRanking());
    }

    @Override
    public ArrayList<FundRankingDTO> ranking(String type, String category) {
        return null;
    }

    private ArrayList<FundRankingDTO> convertValuesInFund(ArrayList<ArrayList<String>> values) {
        ArrayList<FundRankingDTO> result = new ArrayList<>();
        for(ArrayList<String> row : values){
            try{
                FundRankingDTO fund = new FundRankingDTO();
                fund.setCode(row.get(0));
                fund.setSector(row.get(1));
                fund.setCurrentPrice(row.get(2));
                fund.setDailyLiquidity(row.get(3));
                fund.setDividend(row.get(4));
                fund.setDividendYield(row.get(5));
                fund.setDy3(row.get(6));
                fund.setDy6(row.get(7));
                fund.setDy12(row.get(8));
                fund.setDy3Average(row.get(9));
                fund.setDy6Average(row.get(10));
                fund.setDy12Average(row.get(11));
                fund.setDyYear(row.get(12));
                fund.setPriceVariation(row.get(13));
                fund.setPeriodProfitability(row.get(14));
                fund.setAccumulatedProfitability(row.get(15));
                fund.setProperty(row.get(16));
                fund.setVpa(row.get(17));
                fund.setpVpa(row.get(18));
                fund.setDyPatrimonial(row.get(19));
                fund.setDyVariation(row.get(20));
                fund.setPeriodPracticedProfitability(row.get(21));
                fund.setAccumulatedPracticedProfitability(row.get(22));
                fund.setPhysicalVacancy(row.get(23));
                fund.setFinanceVacancy(row.get(24));
                fund.setAmountAssets(row.get(25));
                result.add(fund);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    private ArrayList<ArrayList<String>> valuesRanking(){
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        try {
            Elements table = document(URL + "/ranking").getElementsByClass("table");
            Elements rows = table.select("tr");
            ArrayList<String> rowList;
            for (int i = 1; i < rows.size(); i++) {
                rowList = new ArrayList<>();
                Element row = rows.get(i);
                Elements cols = row.select("td");
                for (Element col : cols) {
                    rowList.add(SoupUtils.text(col));
                }
                result.add(rowList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
