package com.yuxin.wx.api.institution;

import java.util.List;

import com.yuxin.wx.model.institution.DimQueryMerchantVo;
import com.yuxin.wx.model.institution.MerchantEntryVo;

public interface MerchantEntryService {

    /**
     * 商家入驻数量
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
     * 总申请量
     * @return
     */
    Integer queryCount();
    /**
     * 模糊查询数量
     * @param merchantEntryVo
     * @return
     */
    Integer dimMerchantEntryCount(DimQueryMerchantVo merchantEntryVo);
    
    /**
     * 模糊查询
     * @param merchantEntryVo
     * @return
     */
    List<MerchantEntryVo> dimMerchantEntry(DimQueryMerchantVo merchantEntryVo);
}
