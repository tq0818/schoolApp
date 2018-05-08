package com.yuxin.wx.controller.institution;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lym_gxm on 18/5/6.
 */
@Controller
@RequestMapping("/InsInfo")
public class InstitutionBaseController {

    @RequestMapping(value = "/organizationIndex")
    public String findInsInfoList(){
        return "institution/organizationIndex";
    }


}
