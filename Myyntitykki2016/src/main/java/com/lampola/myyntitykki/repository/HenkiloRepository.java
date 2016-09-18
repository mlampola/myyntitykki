/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lampola.myyntitykki.repository;

import com.lampola.myyntitykki.domain.Henkilo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Markus
 */
public interface HenkiloRepository extends JpaRepository<Henkilo, Long> {
    
}
