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
@Table(name = "tb_provinsi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbProvinsi.findAll", query = "SELECT t FROM TbProvinsi t"),
    @NamedQuery(name = "TbProvinsi.findByProvinsiId", query = "SELECT t FROM TbProvinsi t WHERE t.provinsiId = :provinsiId")})
public class TbProvinsi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "provinsi_id")
    private Integer provinsiId;
    @Basic(optional = false)
    @Lob
    @Column(name = "provinsi")
    private String provinsi;
    @Basic(optional = false)
    @Lob
    @Column(name = "ibukota")
    private String ibukota;

    public TbProvinsi() {
    }

    public TbProvinsi(Integer provinsiId) {
        this.provinsiId = provinsiId;
    }

    public TbProvinsi(Integer provinsiId, String provinsi, String ibukota) {
        this.provinsiId = provinsiId;
        this.provinsi = provinsi;
        this.ibukota = ibukota;
    }

    public Integer getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(Integer provinsiId) {
        this.provinsiId = provinsiId;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getIbukota() {
        return ibukota;
    }

    public void setIbukota(String ibukota) {
        this.ibukota = ibukota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provinsiId != null ? provinsiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbProvinsi)) {
            return false;
        }
        TbProvinsi other = (TbProvinsi) object;
        if ((this.provinsiId == null && other.provinsiId != null) || (this.provinsiId != null && !this.provinsiId.equals(other.provinsiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service.databaseService.TbProvinsi[ provinsiId=" + provinsiId + " ]";
    }
    
}
