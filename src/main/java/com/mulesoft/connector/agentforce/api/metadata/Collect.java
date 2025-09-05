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

  public void setTargetType(String targetType) {
    this.targetType = targetType;
  }

  public String getTargetProperty() {
    return targetProperty;
  }

  public void setTargetProperty(String targetProperty) {
    this.targetProperty = targetProperty;
  }

  public CollectData getData() {
    return data;
  }

  public void setData(CollectData data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "Collect{" +
        "targetType='" + targetType + '\'' +
        ", targetProperty='" + targetProperty + '\'' +
        ", data=" + data +
        '}';
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

    public void setType(String type) {
      this.type = type;
    }

    public String getProperty() {
      return property;
    }

    public void setProperty(String property) {
      this.property = property;
    }

    public List<SearchResult> getValue() {
      return value;
    }

    public void setValue(List<SearchResult> value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return "CollectData{" +
          "type='" + type + '\'' +
          ", property='" + property + '\'' +
          ", value=" + value +
          '}';
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

    public void setId(String id) {
      this.id = id;
    }

    public SObjectInfo getSObjectInfo() {
      return sObjectInfo;
    }

    public void setSObjectInfo(SObjectInfo sObjectInfo) {
      this.sObjectInfo = sObjectInfo;
    }

    public String getRecordTypeId() {
      return recordTypeId;
    }

    public void setRecordTypeId(String recordTypeId) {
      this.recordTypeId = recordTypeId;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public RecordData getData() {
      return data;
    }

    public void setData(RecordData data) {
      this.data = data;
    }

    @Override
    public String toString() {
      return "SearchResult{" +
          "id='" + id + '\'' +
          ", sObjectInfo=" + sObjectInfo +
          ", recordTypeId='" + recordTypeId + '\'' +
          ", title='" + title + '\'' +
          ", data=" + data +
          '}';
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

    public void setApiName(String apiName) {
      this.apiName = apiName;
    }

    public String getLabel() {
      return label;
    }

    public void setLabel(String label) {
      this.label = label;
    }

    @Override
    public String toString() {
      return "SObjectInfo{" +
          "apiName='" + apiName + '\'' +
          ", label='" + label + '\'' +
          '}';
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

    public void setType(RecordField type) {
      Type = type;
    }

    public RecordField getId() {
      return Id;
    }

    public void setId(RecordField id) {
      Id = id;
    }

    public RecordField getName() {
      return Name;
    }

    public void setName(RecordField name) {
      Name = name;
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

    @Override
    public String toString() {
      return "RecordData{" +
          "Type=" + Type +
          ", Id=" + Id +
          ", Name=" + Name +
          '}';
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

    public void setDisplayValue(String displayValue) {
      this.displayValue = displayValue;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
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

    @Override
    public String toString() {
      return "RecordField{" +
          "displayValue='" + displayValue + '\'' +
          ", value='" + value + '\'' +
          '}';
    }
  }
}
