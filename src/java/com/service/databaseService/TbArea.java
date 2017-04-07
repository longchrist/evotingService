/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.service.databaseService;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Long
 */
@Entity
@Table(name = "tb_area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbArea.findAll", query = "SELECT t FROM TbArea t"),
    @NamedQuery(name = "TbArea.findByAreaId", query = "SELECT t FROM TbArea t WHERE t.areaId = :areaId"),
    @NamedQuery(name = "TbArea.findByProvinceId", query = "SELECT t FROM TbArea t WHERE t.provinceId = :provinceId"),
    @NamedQuery(name = "TbArea.findByAreaNumber", query = "SELECT t FROM TbArea t WHERE t.areaNumber = :areaNumber")})
public class TbArea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "area_id")
    private Integer areaId;
    @Basic(optional = false)
    @Column(name = "province_id")
    private int provinceId;
    @Basic(optional = false)
    @Column(name = "area_number")
    private int areaNumber;
    @Basic(optional = false)
    @Lob
    @Column(name = "area_name")
    private String areaName;

    public TbArea() {
    }

    public TbArea(Integer areaId) {
        this.areaId = areaId;
    }

    public TbArea(Integer areaId, int provinceId, int areaNumber, String areaName) {
        this.areaId = areaId;
        this.provinceId = provinceId;
        this.areaNumber = areaNumber;
        this.areaName = areaName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(int areaNumber) {
        this.areaNumber = areaNumber;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (areaId != null ? areaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbArea)) {
            return false;
        }
        TbArea other = (TbArea) object;
        if ((this.areaId == null && other.areaId != null) || (this.areaId != null && !this.areaId.equals(other.areaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service.databaseService.TbArea[ areaId=" + areaId + " ]";
    }
    
}
