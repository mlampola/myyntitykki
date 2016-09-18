/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lampola.myyntitykki.controller;

import com.lampola.myyntitykki.repository.HenkiloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Markus
 */
@Controller
public class DefaultController {

    @Autowired
    private HenkiloRepository henkiloRepository;

    @RequestMapping(value="*", method = RequestMethod.GET)
    public String home(Model model) {
        return "index";
    }

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("henkilot", henkiloRepository.findAll());
        return "signup";
    }
}
