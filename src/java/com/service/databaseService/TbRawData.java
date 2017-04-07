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
@Table(name = "tb_raw_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbRawData.findAll", query = "SELECT t FROM TbRawData t"),
    @NamedQuery(name = "TbRawData.findByRawDataId", query = "SELECT t FROM TbRawData t WHERE t.rawDataId = :rawDataId"),
    @NamedQuery(name = "TbRawData.findByTimestamp", query = "SELECT t FROM TbRawData t WHERE t.timestamp = :timestamp")})
public class TbRawData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "raw_data_id")
    private Integer rawDataId;
    @Basic(optional = false)
    @Lob
    @Column(name = "raw_data")
    private String rawData;
    @Basic(optional = false)
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public TbRawData() {
    }

    public TbRawData(Integer rawDataId) {
        this.rawDataId = rawDataId;
    }

    public TbRawData(Integer rawDataId, String rawData, Date timestamp) {
        this.rawDataId = rawDataId;
        this.rawData = rawData;
        this.timestamp = timestamp;
    }

    public Integer getRawDataId() {
        return rawDataId;
    }

    public void setRawDataId(Integer rawDataId) {
        this.rawDataId = rawDataId;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
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
        hash += (rawDataId != null ? rawDataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbRawData)) {
            return false;
        }
        TbRawData other = (TbRawData) object;
        if ((this.rawDataId == null && other.rawDataId != null) || (this.rawDataId != null && !this.rawDataId.equals(other.rawDataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service.databaseService.TbRawData[ rawDataId=" + rawDataId + " ]";
    }
    
}
