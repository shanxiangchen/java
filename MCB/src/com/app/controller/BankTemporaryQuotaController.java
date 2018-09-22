package com.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankTemporaryQuota;
import com.app.listener.BankTemporaQutaExcelView;
import com.app.service.BankTemporaryQuotaService;

@Controller
@RequestMapping(value = "quota")
public class BankTemporaryQuotaController {

    @Autowired
    private BankTemporaryQuotaService bankTemporaryQuotaService;

    @RequestMapping()
    public ModelAndView selBankUer(Model model, BankTemporaryQuota temporaryQuota, HttpServletRequest request, ModelMap modelMap) {
        ModelAndView mv = new ModelAndView();
        List<BankTemporaryQuota> list = bankTemporaryQuotaService.temporaryQuotaPageList(temporaryQuota);
        modelMap.put("list", list);
        model.addAttribute("temporaryQuota", temporaryQuota);
        mv.setViewName("temporaryQuota/quotaList");
        return mv;
    }

    @RequestMapping(value = "/excel")
    public ModelAndView exportExcel(BankTemporaryQuota temporaryQuota, HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<String> titles = new ArrayList<String>();
        titles.add("申请日期");
        titles.add("申请时间");
        titles.add("卡号");
        titles.add("期望调升后的额度");
        titles.add("临时额度生效日期");
        titles.add("临时额度失效日期");
        titles.add("临时额度用途");
        titles.add("账户决策结果");
        titles.add("可调整金额");
        dataMap.put("titles", titles);

        List<BankTemporaryQuota> list = bankTemporaryQuotaService.exportQuota(temporaryQuota);
        dataMap.put("list", list);

        BankTemporaQutaExcelView erv = new BankTemporaQutaExcelView();
        ModelAndView mv = new ModelAndView(erv, dataMap);
        return mv;
    }

}
