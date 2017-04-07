/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.service.databaseService.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Long
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.service.databaseService.service.CoreResource.class);
        resources.add(com.service.databaseService.service.TbAreaFacadeREST.class);
        resources.add(com.service.databaseService.service.TbKtpFacadeREST.class);
        resources.add(com.service.databaseService.service.TbKtpKeyFacadeREST.class);
        resources.add(com.service.databaseService.service.TbPengawasKeyFacadeREST.class);
        resources.add(com.service.databaseService.service.TbProvinsiFacadeREST.class);
        resources.add(com.service.databaseService.service.TbRawDataFacadeREST.class);
        resources.add(com.service.databaseService.service.TbTpsKeyFacadeREST.class);
    }
    
}
