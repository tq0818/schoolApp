package com.yuxin.wx.institution.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.institution.MerchantEntryService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.institution.mapper.MerchantEntryMapper;
import com.yuxin.wx.model.institution.DimQueryMerchantVo;
import com.yuxin.wx.model.institution.MerchantEntryVo;

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
	public List<MerchantEntryVo> queryMerchantEntry(MerchantEntryVo merchantEntryVo) {
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

	@Override
	public Integer dimMerchantEntryCount(DimQueryMerchantVo merchantEntryVo) {
		return merchantEntryMapper.dimMerchantEntryCount(merchantEntryVo);
	}

	@Override
	public List<MerchantEntryVo> dimMerchantEntry(DimQueryMerchantVo merchantEntryVo) {
		return merchantEntryMapper.dimMerchantEntry(merchantEntryVo);
	}

	@Override
	public void updateMerchanrEntryNote(MerchantEntryVo merchantEntryVo) {
		merchantEntryMapper.updateMerchanrEntryNote(merchantEntryVo);
	}


}
