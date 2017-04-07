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
@Table(name = "tb_ktp_key")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbKtpKey.findAll", query = "SELECT t FROM TbKtpKey t"),
    @NamedQuery(name = "TbKtpKey.findByKtpKeyId", query = "SELECT t FROM TbKtpKey t WHERE t.ktpKeyId = :ktpKeyId"),
    @NamedQuery(name = "TbKtpKey.findByKtpSumKey", query = "SELECT t FROM TbKtpKey t WHERE t.ktpSumKey = :ktpSumKey")})
public class TbKtpKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ktp_key_id")
    private Integer ktpKeyId;
    @Basic(optional = false)
    @Lob
    @Column(name = "ktp_key")
    private String ktpKey;
    @Basic(optional = false)
    @Column(name = "ktp_sum_key")
    private int ktpSumKey;

    public TbKtpKey() {
    }

    public TbKtpKey(Integer ktpKeyId) {
        this.ktpKeyId = ktpKeyId;
    }

    public TbKtpKey(Integer ktpKeyId, String ktpKey, int ktpSumKey) {
        this.ktpKeyId = ktpKeyId;
        this.ktpKey = ktpKey;
        this.ktpSumKey = ktpSumKey;
    }

    public Integer getKtpKeyId() {
        return ktpKeyId;
    }

    public void setKtpKeyId(Integer ktpKeyId) {
        this.ktpKeyId = ktpKeyId;
    }

    public String getKtpKey() {
        return ktpKey;
    }

    public void setKtpKey(String ktpKey) {
        this.ktpKey = ktpKey;
    }

    public int getKtpSumKey() {
        return ktpSumKey;
    }

    public void setKtpSumKey(int ktpSumKey) {
        this.ktpSumKey = ktpSumKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ktpKeyId != null ? ktpKeyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbKtpKey)) {
            return false;
        }
        TbKtpKey other = (TbKtpKey) object;
        if ((this.ktpKeyId == null && other.ktpKeyId != null) || (this.ktpKeyId != null && !this.ktpKeyId.equals(other.ktpKeyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service.databaseService.TbKtpKey[ ktpKeyId=" + ktpKeyId + " ]";
    }
    
}
