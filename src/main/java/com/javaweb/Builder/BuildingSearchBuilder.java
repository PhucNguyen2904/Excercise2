package com.javaweb.Builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
    private final Long id;
    private final String name;
    private final String ward;
    private final String street;
    private final Long districtcode;
    private final Integer numberOfBasement;
    private List<String> typeCode = new ArrayList<String>();
    private final String managerName;
    private final String phoneNumber;
    private final Long floorArea;
    private final Long rentPriceFrom;
    private final Long rentPriceTo;
    private final Long areaFrom;
    private final Long areaTo;
    private final Long staffId;

    public BuildingSearchBuilder(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.ward = builder.ward;
        this.street = builder.street;
        this.districtcode = builder.districtcode;
        this.numberOfBasement = builder.numberOfBasement;
        this.typeCode = builder.typeCode;
        this.managerName = builder.managerName;
        this.phoneNumber = builder.phoneNumber;
        this.floorArea = builder.floorArea;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.areaFrom = builder.areaFrom;
        this.areaTo = builder.areaTo;
        this.staffId = builder.staffId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public Long getDistrictCode() {
        return districtcode;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Long getRentPriceTo() {
        return rentPriceTo;
    }

    public Long getAreaFrom() {
        return areaFrom;
    }

    public Long getAreaTo() {
        return areaTo;
    }

    public Long getStaffId() {
        return staffId;
    }
    public static class Builder {
        private Long id;
        private String name;
        private String ward;
        private String street;
        private Long districtcode;
        private Integer numberOfBasement;
        private List<String> typeCode = new ArrayList<String>();
        private String managerName;
        private String phoneNumber;
        private Long floorArea;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private Long areaFrom;
        private Long areaTo;
        private Long staffId;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setDistrictCode(Long districtcode) {
            this.districtcode = districtcode;
            return this;
        }

        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setFloorArea(Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Builder setRentPriceFrom(Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Builder setRentPriceTo(Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Builder setAreaFrom(Long areaFrom) {
            this.areaFrom = areaFrom;
            return this;
        }

        public Builder setAreaTo(Long areaTo) {
            this.areaTo = areaTo;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }
        public BuildingSearchBuilder build(){
            return new BuildingSearchBuilder(this);
        }
    }
}
