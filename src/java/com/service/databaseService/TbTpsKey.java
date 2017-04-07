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
@Table(name = "tb_tps_key")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbTpsKey.findAll", query = "SELECT t FROM TbTpsKey t"),
    @NamedQuery(name = "TbTpsKey.findByTpsKeyId", query = "SELECT t FROM TbTpsKey t WHERE t.tpsKeyId = :tpsKeyId"),
    @NamedQuery(name = "TbTpsKey.findByTpsSumKey", query = "SELECT t FROM TbTpsKey t WHERE t.tpsSumKey = :tpsSumKey")})
public class TbTpsKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tps_key_id")
    private Integer tpsKeyId;
    @Basic(optional = false)
    @Lob
    @Column(name = "tps_key")
    private String tpsKey;
    @Basic(optional = false)
    @Column(name = "tps_sum_key")
    private int tpsSumKey;

    public TbTpsKey() {
    }

    public TbTpsKey(Integer tpsKeyId) {
        this.tpsKeyId = tpsKeyId;
    }

    public TbTpsKey(Integer tpsKeyId, String tpsKey, int tpsSumKey) {
        this.tpsKeyId = tpsKeyId;
        this.tpsKey = tpsKey;
        this.tpsSumKey = tpsSumKey;
    }

    public Integer getTpsKeyId() {
        return tpsKeyId;
    }

    public void setTpsKeyId(Integer tpsKeyId) {
        this.tpsKeyId = tpsKeyId;
    }

    public String getTpsKey() {
        return tpsKey;
    }

    public void setTpsKey(String tpsKey) {
        this.tpsKey = tpsKey;
    }

    public int getTpsSumKey() {
        return tpsSumKey;
    }

    public void setTpsSumKey(int tpsSumKey) {
        this.tpsSumKey = tpsSumKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tpsKeyId != null ? tpsKeyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbTpsKey)) {
            return false;
        }
        TbTpsKey other = (TbTpsKey) object;
        if ((this.tpsKeyId == null && other.tpsKeyId != null) || (this.tpsKeyId != null && !this.tpsKeyId.equals(other.tpsKeyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service.databaseService.TbTpsKey[ tpsKeyId=" + tpsKeyId + " ]";
    }
    
}
