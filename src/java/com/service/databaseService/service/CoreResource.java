/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.service.databaseService.service;

import Connector.DB_Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Long
 */
@Path("generic")
public class CoreResource {

    private Connection conn = null;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public CoreResource() {
    }

    /**
     * Retrieves representation of an instance of com.service.databaseService.service.CoreResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CoreResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
    
    // TEST CONNECTION
    @POST
    @Path("getProvinceArea/")
    @Produces("text/plain")
    public String getProvinceArea() {

        String All = "";
        String provinceID = "";
        String province = "";
        String capital = "";

        Statement stmt = null;
        ResultSet rs;

        try {
            DB_Connection DC = new DB_Connection();
            conn = DC.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM tb_provinsi;";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                provinceID = rs.getString("provinsi_id");
                province = rs.getString("provinsi");
                capital = rs.getString("ibukota");
                All += "RESTful WebService Response :\n - ID : " + provinceID + "\n - Province : " + province + "\n - Capital : " + capital + "\n\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return All;
    }
    
    // TEST SERVICE 04-12-2016 09:32 WIB
    @POST
    @Path("testConnection/")
    @Produces("application/json")
    public String testWS() {
        String All = "";
        JSONObject JSONObjectRoot = new JSONObject();
        JSONObjectRoot.put("WS_Response", "1");
        All += JSONObjectRoot.toString();
        return All;
    }
    
    // KEY SERVICE 05-12-2016 13:23 WIB
    @POST
    @Path("retrieveKey/")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces("application/json")
    public String getCoreResources(final MultivaluedMap<String, String> formParams) { // key & raw data
        String voteLocation = formParams.getFirst("voteLocation");
        String All = "";
        String ktp_key = "";
        String ktp_sum_key = "";
        String tps_key = "";
        String tps_sum_key = "";
        String pengawas_key = "";
        String pengawas_sum_key = "";
        String raw_data = "";
        String timestamp_raw_data = "";

        Statement stmt = null;
        ResultSet rs;

        try {
            DB_Connection DC = new DB_Connection();
            conn = DC.getConnection();
            
            JSONObject JSONObjectRoot = new JSONObject();
            JSONArray KEY_DATA = new JSONArray();
            
            stmt = conn.createStatement();
            String query = "SELECT tb_ktp_key.ktp_key, tb_ktp_key.ktp_sum_key, tb_tps_key.tps_key, tb_tps_key.tps_sum_key, tb_pengawas_key.pengawas_key, tb_pengawas_key.pengawas_sum_key, tb_raw_data.raw_data, tb_raw_data.timestamp FROM tb_key_data LEFT JOIN tb_ktp_key ON tb_key_data.ktp_key_id = tb_ktp_key.ktp_key_id LEFT JOIN tb_tps_key ON tb_key_data.tps_key_id = tb_tps_key.tps_key_id LEFT JOIN tb_pengawas_key ON tb_key_data.pengawas_key_id = tb_pengawas_key.pengawas_key_id LEFT JOIN tb_raw_data ON tb_key_data.raw_data_id = tb_raw_data.raw_data_id WHERE tb_key_data.vote_location = '"+voteLocation+"';";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ktp_key = rs.getString("ktp_key");
                ktp_sum_key = rs.getString("ktp_sum_key");
                tps_key = rs.getString("tps_key");
                tps_sum_key = rs.getString("tps_sum_key");
                pengawas_key = rs.getString("pengawas_key");
                pengawas_sum_key = rs.getString("pengawas_sum_key");
                raw_data = rs.getString("raw_data");
                timestamp_raw_data = rs.getString("timestamp");
                JSONObject KEY_VALUES = new JSONObject();
                
                KEY_VALUES.put("ktp_key", new String(ktp_key));
                KEY_VALUES.put("ktp_sum_key", new Integer(ktp_sum_key));
                KEY_VALUES.put("tps_key", new String(tps_key));
                KEY_VALUES.put("tps_sum_key", new Integer(tps_sum_key));
                KEY_VALUES.put("pengawas_key", new String(pengawas_key));
                KEY_VALUES.put("pengawas_sum_key", new Integer(pengawas_sum_key));
                KEY_VALUES.put("raw_data", new String(raw_data));
                KEY_VALUES.put("timestamp", new String(timestamp_raw_data));
                
                KEY_DATA.put(KEY_VALUES);
//               All += "RESTful WebService Response :\n - KEY : " + ktp_key + "\n - sum_key : " + ktp_sum_key + "\n\n";
            }
            JSONObjectRoot.put("KEY_DATA", KEY_DATA);
            All += JSONObjectRoot.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return All;
    }
    
    @POST
    @Path("retrieveAllKey/")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces("application/json")
    public String getAllCoreResources(final MultivaluedMap<String, String> formParams) { // key & raw data

        String deviceIMEI = formParams.getFirst("deviceIMEI").toString();
        String isValidateDevice = formParams.getFirst("isValidateDevice").toString();
        String validateTimestamp = formParams.getFirst("validateTimestamp").toString();
        String deviceTrackerGPSProvided = formParams.getFirst("deviceTrackerGPSProvided").toString();
        String deviceTrackerGPSLatitude = formParams.getFirst("deviceTrackerGPSLatitude").toString();
        String deviceTrackerGPSLongitude = formParams.getFirst("deviceTrackerGPSLongitude").toString();
        String deviceInformationHandheld = formParams.getFirst("deviceInformationHandheld").toString();
        String deviceInformationSerialNumber = formParams.getFirst("deviceInformationSerialNumber").toString();
        
        System.out.println("deviceIMEI : "+deviceIMEI+"\nisValidateDevice : "+isValidateDevice+"\nts : "+validateTimestamp);
        System.out.println("tracker gps : "+deviceTrackerGPSProvided+" -> "+deviceTrackerGPSLatitude+","+deviceTrackerGPSLongitude);
        
        String All = "";
        String ktp_key = "";
        String ktp_sum_key = "";
        String tps_key = "";
        String tps_sum_key = "";
        String pengawas_key = "";
        String pengawas_sum_key = "";
        String raw_data = "";
        String timestamp_raw_data = "";

        Statement stmt = null;
        Statement stmtMatching = null;
        ResultSet rsMatch, rs;

        try {
            DB_Connection DC = new DB_Connection();
            conn = DC.getConnection();
            
            JSONObject JSONObjectRoot = new JSONObject();
            JSONArray KEY_DATA = new JSONArray();
            
            stmtMatching = conn.createStatement();
            String queryMatch = "SELECT tb_validate_device.validate_device_id, tb_validate_device.tps_imei, tb_validate_device.is_validate_tps, tb_validate_device.validate_tps_ts, tb_validate_device.tps_number, tb_validate_device.pengawas_number FROM tb_validate_device WHERE tb_validate_device.tps_imei = '"+deviceIMEI+"' AND tb_validate_device.is_validate_tps = "+isValidateDevice+" AND tb_validate_device.validate_tps_ts = '"+validateTimestamp+"'";
            rsMatch = stmtMatching.executeQuery(queryMatch);
            boolean foundedMatch = false;
            while(rsMatch.next()){
                foundedMatch = true;
            }
            System.out.println("query : "+queryMatch);
            System.out.println("found : "+foundedMatch);
//            if(founded){
//                foundedMatch = true;
//                System.out.println("found : "+foundedMatch);
//            } else {
//                foundedMatch = false;
//                System.out.println("found : "+foundedMatch);
//            }
            
            if(foundedMatch == true){ // if validated
                stmt = conn.createStatement();
                String query = "SELECT tb_ktp_key.ktp_key, tb_ktp_key.ktp_sum_key, tb_tps_key.tps_key, tb_tps_key.tps_sum_key, tb_pengawas_key.pengawas_key, tb_pengawas_key.pengawas_sum_key, tb_raw_data.raw_data, tb_raw_data.timestamp FROM tb_key_data LEFT JOIN tb_ktp_key ON tb_key_data.ktp_key_id = tb_ktp_key.ktp_key_id LEFT JOIN tb_tps_key ON tb_key_data.tps_key_id = tb_tps_key.tps_key_id LEFT JOIN tb_pengawas_key ON tb_key_data.pengawas_key_id = tb_pengawas_key.pengawas_key_id LEFT JOIN tb_raw_data ON tb_key_data.raw_data_id = tb_raw_data.raw_data_id;";
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ktp_key = rs.getString("ktp_key");
                    ktp_sum_key = rs.getString("ktp_sum_key");
                    tps_key = rs.getString("tps_key");
                    tps_sum_key = rs.getString("tps_sum_key");
                    pengawas_key = rs.getString("pengawas_key");
                    pengawas_sum_key = rs.getString("pengawas_sum_key");
                    raw_data = rs.getString("raw_data");
                    timestamp_raw_data = rs.getString("timestamp");
                    JSONObject KEY_VALUES = new JSONObject();

                    KEY_VALUES.put("ktp_key", new String(ktp_key));
                    KEY_VALUES.put("ktp_sum_key", new Integer(ktp_sum_key));
                    KEY_VALUES.put("tps_key", new String(tps_key));
                    KEY_VALUES.put("tps_sum_key", new Integer(tps_sum_key));
                    KEY_VALUES.put("pengawas_key", new String(pengawas_key));
                    KEY_VALUES.put("pengawas_sum_key", new Integer(pengawas_sum_key));
                    KEY_VALUES.put("raw_data", new String(raw_data));
                    KEY_VALUES.put("timestamp", new String(timestamp_raw_data));

                    KEY_DATA.put(KEY_VALUES);
                }
                JSONObjectRoot.put("MESSAGE", KEY_DATA);
                All += JSONObjectRoot.toString();
            } else {
                JSONObject RESULT_VALUES = new JSONObject();
                RESULT_VALUES.put("error_title", "Validasi Error");
                RESULT_VALUES.put("error_message", "Perangkat belum di verifikasi");
                JSONObjectRoot.put("MESSAGE", RESULT_VALUES);
                All = JSONObjectRoot.toString();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return All;
    }
    
    // VALIDATE DEVICE CORE 20.30 11/12/2016
    @POST
    @Path("validateDevice/")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces("application/json")
    public String validateDevice(final MultivaluedMap<String, String> formParams){ // key & raw data
        boolean result = false;
        String trustedKey = "";
        String deviceIMEI = formParams.getFirst("deviceIMEI").toString();
        String deviceLocation = formParams.getFirst("deviceLocation").toString();
        String deviceTPSNumber = formParams.getFirst("deviceTPSNumber").toString();
        String devicePengawasNumber = formParams.getFirst("devicePengawasNumber").toString();
        
        Date dateNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timestamp = ft.format(dateNow);
        
        System.out.print(deviceIMEI);
        
        try {
            DB_Connection DC = new DB_Connection();
            conn = DC.getConnection();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE tb_validate_device SET tps_imei = '"+deviceIMEI+"', is_validate_tps = '1', validate_tps_ts = '"+timestamp+"' WHERE tb_validate_device.vote_location = '"+deviceLocation+"' AND tb_validate_device.tps_number = '"+deviceTPSNumber+"' AND tb_validate_device.pengawas_number = '"+devicePengawasNumber+"';");
            
            if (ps.executeUpdate() > 0) {
                result = true;
            } else {
                result = false;
            }
            
            if(result == true){
                
            } else {
                
            }
            JSONObject JSONObjectRoot = new JSONObject();
            JSONObject RESULT_VALUES = new JSONObject();
            RESULT_VALUES.put("is_validate", new String(String.valueOf(result)));
            RESULT_VALUES.put("trusted_key", new String(String.valueOf(result)));
            RESULT_VALUES.put("validate_timestamp", new String(timestamp));
            JSONObjectRoot.put("response_validator", RESULT_VALUES);
            trustedKey = JSONObjectRoot.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trustedKey;
    }
    
    public String givenTrustedKey(String data){
        String key = "";
            
        return key;
    }
    
    // SAVING VOTE SELECTION CORE 20.30 11/12/2016
    @POST
    @Path("saveVotingData/")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces("application/json")
    public String saveVotingDataSelection(final MultivaluedMap<String, String> formParams){ // key & raw data
        boolean result = false;
        String All = "";
        
        try {
            String votingData = formParams.getFirst("votingData").toString();
            int votingSelection = Integer.parseInt(formParams.getFirst("votingSelection"));
            String votingLocation = formParams.getFirst("votingLocation");

            Date dateNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String timestamp = ft.format(dateNow);
        
            DB_Connection DC = new DB_Connection();
            conn = DC.getConnection();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO tb_voting_data (voting_data_id, voting_data, voting_selection, voting_timestamp, voting_location) VALUES (?,?,?,?,?)");
            ps.setString(1, null);
            ps.setString(2, votingData);
            ps.setInt(3, votingSelection);
            ps.setString(4, timestamp);
            ps.setString(5, votingLocation);
            
            JSONObject JSONObjectRoot = new JSONObject();
            JSONObject RESULT_VALUES = new JSONObject();
                
            if (ps.executeUpdate() > 0) {
                result = true;
            } else {
                result = false;
            }
            
            RESULT_VALUES.put("value", new String(String.valueOf(result)));
            JSONObjectRoot.put("RESULT", RESULT_VALUES);
            All = JSONObjectRoot.toString();
            DC.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject JSONObjectRoot = new JSONObject();
            JSONObject RESULT_VALUES = new JSONObject();
                
            result = false;
            
            RESULT_VALUES.put("value", new String(String.valueOf(result)));
            JSONObjectRoot.put("RESULT", RESULT_VALUES);
            All = JSONObjectRoot.toString();
        }
        
        return All;
    }
    
    // SAVING DATA KEHADIRAN
    @POST
    @Path("syncAttendanceData/")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces("application/json")
    public String syncAttendanceData(final MultivaluedMap<String, String> formParams){ // key & raw data
        boolean result = false;
        String All = "";
//        String votingData = formParams.getFirst("votingData").toString();
//        int votingSelection = Integer.parseInt(formParams.getFirst("votingSelection"));
//        String votingLocation = formParams.getFirst("votingLocation");
//        
//        Date dateNow = new Date();
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String timestamp = ft.format(dateNow);
//        
//        try {
//            DB_Connection DC = new DB_Connection();
//            conn = DC.getConnection();
//            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO tb_voting_data (voting_data_id, voting_data, voting_selection, voting_timestamp, voting_location) VALUES (?,?,?,?,?)");
//            ps.setString(1, null);
//            ps.setString(2, votingData);
//            ps.setInt(3, votingSelection);
//            ps.setString(4, timestamp);
//            ps.setString(5, votingLocation);
//            
//            JSONObject JSONObjectRoot = new JSONObject();
//            JSONObject RESULT_VALUES = new JSONObject();
//                
//            if (ps.executeUpdate() > 0) {
//                result = true;
//            } else {
//                result = false;
//            }
//            
//            RESULT_VALUES.put("value", new String(String.valueOf(result)));
//            JSONObjectRoot.put("RESULT", RESULT_VALUES);
//            All = JSONObjectRoot.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return All;
    }
}
