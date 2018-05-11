package com.yuxin.wx.institution.mapper;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.MerchantEntryVo;

public interface MerchantEntryMapper {

    /**
     * 商家入驻数量
     * @return
     */
    Integer merchantEntryCount();
    /**
     * 查询商家入驻申请
     * @return
     */
    PageFinder<MerchantEntryVo> queryMerchantEntry(MerchantEntryVo merchantEntryVo);
    /**
     * 更新商家入驻信息
     * @param merchantEntryVo
     */
    void updateMerchanrEntry(MerchantEntryVo merchantEntryVo);
}
