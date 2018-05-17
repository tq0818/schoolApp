package com.yuxin.wx.institution.mapper;

import java.util.List;

import com.yuxin.wx.model.institution.DimQueryMerchantVo;
import com.yuxin.wx.model.institution.MerchantEntryVo;

public interface MerchantEntryMapper {

    /**
     * 商家入驻申请数量
     * @return
     */
    Integer merchantEntryCount();
    /**
     * 查询商家入驻申请
     * @return
     */
    List<MerchantEntryVo> queryMerchantEntry(MerchantEntryVo merchantEntryVo);
    /**
     * 更新商家入驻信息
     * @param merchantEntryVo
     */
    void updateMerchanrEntry(MerchantEntryVo merchantEntryVo);

    /**
     * 未处理的申请量
     * @return
     */
    Integer queryCount();
    
    /**
     * 模糊查询
     * @param merchantEntryVo
     * @return
     */
    List<MerchantEntryVo> dimMerchantEntry(DimQueryMerchantVo merchantEntryVo);
    
    /**
     * 模糊查询数量
     * @param merchantEntryVo
     * @return
     */
    Integer dimMerchantEntryCount(DimQueryMerchantVo merchantEntryVo);
}
