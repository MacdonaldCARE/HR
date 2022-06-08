package com.tmgreyhat.api.fundCode;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundCodeService {

    private final  FundCodeRepository fundCodeRepository;

    public FundCodeService(FundCodeRepository fundCodeRepository) {
        this.fundCodeRepository = fundCodeRepository;
    }

    public FundCode createFundCode(FundCode fundCode){
        return fundCodeRepository.save(fundCode);
    }

    public FundCode getFundCode(Long id){
        return fundCodeRepository.findById(id).orElse(null);
    }
    public List<FundCode> getAllFundCodes(){
        return fundCodeRepository.findAll();
    }

}
