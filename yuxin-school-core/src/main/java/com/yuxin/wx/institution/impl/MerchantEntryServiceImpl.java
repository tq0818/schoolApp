package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.MerchantEntryService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.institution.mapper.MerchantEntryMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MerchantEntryServiceImpl extends BaseServiceImpl implements MerchantEntryService {
    @Autowired
    private MerchantEntryMapper merchantEntryMapper;

    @Override
    public Integer merchantEntryCount() {
        return merchantEntryMapper.merchantEntryCount();
    }
}
