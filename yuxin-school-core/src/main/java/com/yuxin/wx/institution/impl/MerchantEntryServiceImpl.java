package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.MerchantEntryService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.MerchantEntryMapper;
import com.yuxin.wx.model.institution.MerchantEntryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MerchantEntryServiceImpl extends BaseServiceImpl implements MerchantEntryService {
    @Autowired
    private MerchantEntryMapper merchantEntryMapper;

    @Override
    public Integer merchantEntryCount() {
        return merchantEntryMapper.merchantEntryCount();
    }

	@Override
	public PageFinder<MerchantEntryVo> queryMerchantEntry(MerchantEntryVo merchantEntryVo) {
		return merchantEntryMapper.queryMerchantEntry(merchantEntryVo);
	}

	@Override
	public void updateMerchanrEntry(MerchantEntryVo merchantEntryVo) {
		merchantEntryMapper.updateMerchanrEntry(merchantEntryVo);
	}

	@Override
	public Integer queryCount() {
		return merchantEntryMapper.queryCount();
	}


}
