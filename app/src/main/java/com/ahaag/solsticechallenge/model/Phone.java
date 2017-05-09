package com.ahaag.solsticechallenge.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Phone model class generated via http://www.jsonschema2pojo.org/
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "work",
        "home",
        "mobile"
})
public class Phone {

    @JsonProperty("work")
    private String work;
    @JsonProperty("home")
    private String home;
    @JsonProperty("mobile")
    private String mobile;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("work")
    public String getWork() {
        return work;
    }

    @JsonProperty("work")
    public void setWork(String work) {
        this.work = work;
    }

    @JsonProperty("home")
    public String getHome() {
        return home;
    }

    @JsonProperty("home")
    public void setHome(String home) {
        this.home = home;
    }

    @JsonProperty("mobile")
    public String getMobile() {
        return mobile;
    }

    @JsonProperty("mobile")
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}