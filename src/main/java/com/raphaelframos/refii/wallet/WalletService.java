package com.raphaelframos.refii.wallet;

import com.raphaelframos.refii.common.entity.FundWallet;
import com.raphaelframos.refii.wallet.data.BalanceResponse;
import com.raphaelframos.refii.wallet.data.FundFeed;
import com.raphaelframos.refii.wallet.data.FundResponse;
import com.raphaelframos.refii.wallet.data.WalletResponse;
import com.raphaelframos.refii.wallet.repository.FundWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {

    @Autowired
    private final FundWalletRepository repository;

    public WalletService(FundWalletRepository repository) {
        this.repository = repository;
    }

    public ArrayList<FundResponse> findBy(Long userId) {
        ArrayList<FundResponse> response = new ArrayList<>();
        try{
            List<Object[]> funds = repository.findWalletBy(userId);
            double totalPrice = getTotalPrice(userId);
            funds.forEach( f -> {
                FundFeed fundFeed = new FundFeed(f, totalPrice);
                response.add(new FundResponse(fundFeed));
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    private double getTotalPrice(Long userId) {
        double totalPrice;
        try {
            totalPrice = repository.totalPrice(userId);
        }catch (Exception e){
            totalPrice = 0.0;
        }

        return totalPrice;
    }

    public BalanceResponse balance(Long userId) {
        double total;
        try{
            total = repository.totalPrice(userId);
        }catch (Exception e){
            total = 0;
        }

        return new BalanceResponse("Total", total);
    }

    public List<WalletResponse> historic(Long fundId, Long userId) {
        List<FundWallet> funds = repository.findByIdAndUser(fundId, userId);
        return funds.stream().map(WalletResponse::new).collect(Collectors.toList());
    }
}
