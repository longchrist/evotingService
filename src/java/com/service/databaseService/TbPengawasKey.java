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
@Table(name = "tb_pengawas_key")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbPengawasKey.findAll", query = "SELECT t FROM TbPengawasKey t"),
    @NamedQuery(name = "TbPengawasKey.findByPengawasKeyId", query = "SELECT t FROM TbPengawasKey t WHERE t.pengawasKeyId = :pengawasKeyId"),
    @NamedQuery(name = "TbPengawasKey.findByPengawasSumKey", query = "SELECT t FROM TbPengawasKey t WHERE t.pengawasSumKey = :pengawasSumKey")})
public class TbPengawasKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pengawas_key_id")
    private Integer pengawasKeyId;
    @Basic(optional = false)
    @Lob
    @Column(name = "pengawas_key")
    private String pengawasKey;
    @Basic(optional = false)
    @Column(name = "pengawas_sum_key")
    private int pengawasSumKey;

    public TbPengawasKey() {
    }

    public TbPengawasKey(Integer pengawasKeyId) {
        this.pengawasKeyId = pengawasKeyId;
    }

    public TbPengawasKey(Integer pengawasKeyId, String pengawasKey, int pengawasSumKey) {
        this.pengawasKeyId = pengawasKeyId;
        this.pengawasKey = pengawasKey;
        this.pengawasSumKey = pengawasSumKey;
    }

    public Integer getPengawasKeyId() {
        return pengawasKeyId;
    }

    public void setPengawasKeyId(Integer pengawasKeyId) {
        this.pengawasKeyId = pengawasKeyId;
    }

    public String getPengawasKey() {
        return pengawasKey;
    }

    public void setPengawasKey(String pengawasKey) {
        this.pengawasKey = pengawasKey;
    }

    public int getPengawasSumKey() {
        return pengawasSumKey;
    }

    public void setPengawasSumKey(int pengawasSumKey) {
        this.pengawasSumKey = pengawasSumKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pengawasKeyId != null ? pengawasKeyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbPengawasKey)) {
            return false;
        }
        TbPengawasKey other = (TbPengawasKey) object;
        if ((this.pengawasKeyId == null && other.pengawasKeyId != null) || (this.pengawasKeyId != null && !this.pengawasKeyId.equals(other.pengawasKeyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service.databaseService.TbPengawasKey[ pengawasKeyId=" + pengawasKeyId + " ]";
    }
    
}
