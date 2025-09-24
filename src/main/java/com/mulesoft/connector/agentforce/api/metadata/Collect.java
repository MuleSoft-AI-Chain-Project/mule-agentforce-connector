package com.mulesoft.connector.agentforce.api.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Collect implements Serializable {

  @JsonProperty("targetType")
  private String targetType;
  @JsonProperty("targetProperty")
  private String targetProperty;
  @JsonProperty("data")
  private CollectData data;

  public String getTargetType() {
    return targetType;
  }

  public String getTargetProperty() {
    return targetProperty;
  }

  public CollectData getData() {
    return data;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class CollectData implements Serializable {

    @JsonProperty("type")
    private String type;
    @JsonProperty("property")
    private String property;
    @JsonProperty("value")
    private List<SearchResult> value;

    public String getType() {
      return type;
    }

    public String getProperty() {
      return property;
    }

    public List<SearchResult> getValue() {
      return value;
    }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class SearchResult implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("sObjectInfo")
    private SObjectInfo sObjectInfo;
    @JsonProperty("recordTypeId")
    private String recordTypeId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("data")
    private RecordData data;

    public String getId() {
      return id;
    }

    public SObjectInfo getSObjectInfo() {
      return sObjectInfo;
    }

    public String getRecordTypeId() {
      return recordTypeId;
    }

    public String getTitle() {
      return title;
    }

    public RecordData getData() {
      return data;
    }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class SObjectInfo implements Serializable {

    @JsonProperty("apiName")
    private String apiName;
    @JsonProperty("label")
    private String label;

    public String getApiName() {
      return apiName;
    }

    public String getLabel() {
      return label;
    }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class RecordData implements Serializable {

    @JsonProperty("Type")
    private RecordField Type;
    @JsonProperty("Id")
    private RecordField Id;
    @JsonProperty("Name")
    private RecordField Name;

    public RecordField getType() {
      return Type;
    }

    public RecordField getId() {
      return Id;
    }

    public RecordField getName() {
      return Name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof RecordData))
        return false;
      RecordData that = (RecordData) o;
      return Objects.equals(getType(), that.getType()) &&
          Objects.equals(getId(), that.getId()) &&
          Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getType(), getId(), getName());
    }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class RecordField implements Serializable {

    @JsonProperty("displayValue")
    private String displayValue;
    @JsonProperty("value")
    private String value;

    public String getDisplayValue() {
      return displayValue;
    }

    public String getValue() {
      return value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof RecordField))
        return false;
      RecordField that = (RecordField) o;
      return Objects.equals(getDisplayValue(), that.getDisplayValue()) &&
          Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getDisplayValue(), getValue());
    }
  }
}
