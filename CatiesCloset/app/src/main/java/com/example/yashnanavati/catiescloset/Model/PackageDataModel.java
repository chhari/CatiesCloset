package com.example.yashnanavati.catiescloset.Model;

/**
 * Created by Game of Threads
 */

// Package Data class object that stores package variables to be sent to the fireabse.
// This class helps distinguish packages that are delivered to donation center and schools.
public class PackageDataModel {

    String name;
    String type;
    String version_number;
    String feature;

    public PackageDataModel(String name, String type, String version_number, String feature ) {
        this.name=name;
        this.type=type;
        this.version_number=version_number;
        this.feature=feature;

    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getVersion_number() {
        return version_number;
    }

    public String getFeature() {
        return feature;
    }

}
