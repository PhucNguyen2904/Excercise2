package com.javaweb.repository.Entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")

public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "ward")
    private String ward;
    @Column(name = "street")
    private String street;
    @Column(name = "districtid")
    private Long districtId;
    @Column(name = "numberofnasement")
    private Integer numberOfBasement;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "floorarea")
    private Integer floorArea;
    @Column(name = "rentarea")
    private String rentArea;
    @Column(name = "remeinarea")
    private Integer remeinArea;
    @Column(name = "rentprice")
    private Integer rentPrice;
    @Column(name = "servicefee")
    private Integer serviceFee;
    @Column(name = "brokeragefee")
    private Integer brokerageFee;

    @ManyToOne
    @JoinColumn(name = "districtid")
    private DistrictEntity district;

    @OneToMany(mappedBy = "building",fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentAreas = new ArrayList<>();
    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

    public List<RentAreaEntity> getRentAreas() {
        return rentAreas;
    }

    public void setRentAreas(List<RentAreaEntity> rentAreas) {
        this.rentAreas = rentAreas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public Integer getRemeinArea() {
        return remeinArea;
    }

    public void setRemeinArea(Integer remeinArea) {
        this.remeinArea = remeinArea;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Integer getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Integer getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Integer brokerageFee) {
        this.brokerageFee = brokerageFee;
    }
}
