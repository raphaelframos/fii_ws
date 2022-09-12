package me.raphaelframos.fii.insights;

import me.raphaelframos.fii.data.InsightDTO;

public interface InsightsService {
    InsightDTO makeBy(Long id);

    String today(Long id);
}
