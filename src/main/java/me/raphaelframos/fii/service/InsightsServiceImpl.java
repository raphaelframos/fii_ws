package me.raphaelframos.fii.service;

import me.raphaelframos.fii.data.InsightDTO;
import me.raphaelframos.fii.data.TopFundDTO;
import me.raphaelframos.fii.motor.InsightsMotor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsightsServiceImpl implements InsightsService{

    @Autowired
    private InsightsMotor insightsMotor;

    public InsightsServiceImpl(InsightsMotor insightsMotor){
        this.insightsMotor = insightsMotor;
    }

    @Override
    public InsightDTO makeBy(Long id) {
        TopFundDTO topFundDTO = new TopFundDTO();
        topFundDTO.setTitle("Os top 5 em dividendos no último mês.");
        topFundDTO.setClassificationFunds(insightsMotor.fundsByDividends());
        InsightDTO insightDTO = new InsightDTO();
        insightDTO.setTip("Olá Eduardo, vi que você possui 5 fundos em sua carteira, sabia que uma maior diversidade te protege de eventuais problemas? Um número entre 10 e 15 é uma boa solução!");
        insightDTO.setTopFund(topFundDTO);
        return insightDTO;
    }
}
