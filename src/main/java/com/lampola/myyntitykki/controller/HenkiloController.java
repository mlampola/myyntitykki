/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lampola.myyntitykki.controller;

import com.lampola.myyntitykki.domain.Henkilo;
import com.lampola.myyntitykki.googleplaces.GeocodingResponse;
import com.lampola.myyntitykki.repository.HenkiloRepository;
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
@RequestMapping("/henkilot")
public class HenkiloController {

    @Autowired
    private HenkiloRepository henkiloRepository;

    @Autowired
    private GeoRestClient geoRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("henkilot", henkiloRepository.findAll());
        return "signup";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Henkilo henkilo) {
        String address = henkilo.getKatuosoite() + "," + henkilo.getKaupunki() + "," + henkilo.getMaa();
        address.replace(' ', '+');
        GeocodingResponse resp = geoRepository.findByAddress(address);
        henkilo.setLongitudi(resp.getResults().get(0).getGeometry().getLocation().getLng());
        henkilo.setLatitudi(resp.getResults().get(0).getGeometry().getLocation().getLat());
        
        henkiloRepository.save(henkilo);
        return "redirect:/henkilot";
    }
}
