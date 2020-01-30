package com.senacor.tecco.ilms.katas.e01_value;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by fsubasi on 17.02.2016.
 *
 * Spring facilitates a mapping from configuration properties to objects
 *
 * the set methods in this controller will be initialized with
 * properties defined in the prefix object in application.properties.
 * Please note that lists and maps defined in the configuration are set as well.
 */
@RestController
@ConfigurationProperties("e01configurationproperties")
public class ConfigurationPropertiesController {

    //these values are injected by Spring using the setters
    private String stringProp1;
    private String stringProp2;
    private Integer intProp1;
    private List<String> listProp;
    private Map<String, String> mapProp;

    @RequestMapping("/configuration/properties")
    public String getProperties(){
        return toString();
    }

    //Getter/Setter

    public void setStringProp1(String stringProp1) {
        this.stringProp1 = stringProp1;
    }

    public void setStringProp2(String stringProp2) {
        this.stringProp2 = stringProp2;
    }

    public void setIntProp1(Integer intProp1) {
        this.intProp1 = intProp1;
    }

    public void setListProp(List<String> listProp) {
        this.listProp = listProp;
    }

    public void setMapProp(Map<String, String> mapProp) {
        this.mapProp = mapProp;
    }

    public String getStringProp1() {
        return stringProp1;
    }

    public String getStringProp2() {
        return stringProp2;
    }

    public Integer getIntProp1() {
        return intProp1;
    }

    public List<String> getListProp() {
        return listProp;
    }

    public Map<String, String> getMapProp() {
        return mapProp;
    }

    @Override
    public String toString() {
        return "ConfigurationPropertiesController{" +
                "stringProp1='" + stringProp1 + '\'' +
                ", stringProp2='" + stringProp2 + '\'' +
                ", intProp1=" + intProp1 +
                ", listProp=" + listProp +
                ", mapProp=" + mapProp +
                '}';
    }
}
