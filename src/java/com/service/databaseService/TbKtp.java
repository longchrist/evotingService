/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.service.databaseService;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Long
 */
@Entity
@Table(name = "tb_ktp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbKtp.findAll", query = "SELECT t FROM TbKtp t"),
    @NamedQuery(name = "TbKtp.findByKtpId", query = "SELECT t FROM TbKtp t WHERE t.ktpId = :ktpId"),
    @NamedQuery(name = "TbKtp.findByKtpStatus", query = "SELECT t FROM TbKtp t WHERE t.ktpStatus = :ktpStatus"),
    @NamedQuery(name = "TbKtp.findByTimestamp", query = "SELECT t FROM TbKtp t WHERE t.timestamp = :timestamp")})
public class TbKtp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ktp_id")
    private Integer ktpId;
    @Basic(optional = false)
    @Lob
    @Column(name = "ktp_no")
    private String ktpNo;
    @Basic(optional = false)
    @Column(name = "ktp_status")
    private int ktpStatus;
    @Basic(optional = false)
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public TbKtp() {
    }

    public TbKtp(Integer ktpId) {
        this.ktpId = ktpId;
    }

    public TbKtp(Integer ktpId, String ktpNo, int ktpStatus, Date timestamp) {
        this.ktpId = ktpId;
        this.ktpNo = ktpNo;
        this.ktpStatus = ktpStatus;
        this.timestamp = timestamp;
    }

    public Integer getKtpId() {
        return ktpId;
    }

    public void setKtpId(Integer ktpId) {
        this.ktpId = ktpId;
    }

    public String getKtpNo() {
        return ktpNo;
    }

    public void setKtpNo(String ktpNo) {
        this.ktpNo = ktpNo;
    }

    public int getKtpStatus() {
        return ktpStatus;
    }

    public void setKtpStatus(int ktpStatus) {
        this.ktpStatus = ktpStatus;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ktpId != null ? ktpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbKtp)) {
            return false;
        }
        TbKtp other = (TbKtp) object;
        if ((this.ktpId == null && other.ktpId != null) || (this.ktpId != null && !this.ktpId.equals(other.ktpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service.databaseService.TbKtp[ ktpId=" + ktpId + " ]";
    }
    
}
