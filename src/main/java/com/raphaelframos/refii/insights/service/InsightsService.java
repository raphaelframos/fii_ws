package com.raphaelframos.refii.insights.service;


import com.raphaelframos.refii.scrap.data.InsightDTO;

public interface InsightsService {
    InsightDTO makeBy(Long id);

    String today(Long id);
}
