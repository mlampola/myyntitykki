/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lampola.myyntitykki.controller;

import com.lampola.myyntitykki.domain.Asiakas;
import com.lampola.myyntitykki.googleplaces.GeocodingResponse;
import com.lampola.myyntitykki.repository.AsiakasRepository;
import com.lampola.myyntitykki.service.GeoRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Markus
 */
@Controller
@RequestMapping("/asiakkaat")
public class AsiakasController {

    @Autowired
    private AsiakasRepository asiakasRepository;

    @Autowired
    private GeoRestClient geoRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("asiakkaat", asiakasRepository.findAll());
        return "asiakkaat";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Asiakas asiakas) {
        String address = asiakas.getKatuosoite() + "," + asiakas.getKaupunki() + "," + asiakas.getMaa();
        address.replace(' ', '+');
        GeocodingResponse resp = geoRepository.findByAddress(address);
        asiakas.setLongitudi(resp.getResults().get(0).getGeometry().getLocation().getLng());
        asiakas.setLatitudi(resp.getResults().get(0).getGeometry().getLocation().getLat());
        
        asiakasRepository.save(asiakas);
        return "redirect:/asiakkaat";
    }
}
