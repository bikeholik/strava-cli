/*
 * Strava API v3
 * Strava API
 *
 * OpenAPI spec version: 3.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * MetaClub
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-03-12T15:31:53.751+01:00")
public class MetaClub {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("resource_state")
  private Integer resourceState = null;

  @SerializedName("name")
  private String name = null;

  public MetaClub id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * The club&#39;s unique identifier.
   * @return id
  **/
  @ApiModelProperty(value = "The club's unique identifier.")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public MetaClub resourceState(Integer resourceState) {
    this.resourceState = resourceState;
    return this;
  }

   /**
   * Resource state, indicates level of detail. Possible values: 1 -&gt; \&quot;meta\&quot;, 2 -&gt; \&quot;summary\&quot;, 3 -&gt; \&quot;detail\&quot;
   * @return resourceState
  **/
  @ApiModelProperty(value = "Resource state, indicates level of detail. Possible values: 1 -> \"meta\", 2 -> \"summary\", 3 -> \"detail\"")
  public Integer getResourceState() {
    return resourceState;
  }

  public void setResourceState(Integer resourceState) {
    this.resourceState = resourceState;
  }

  public MetaClub name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The club&#39;s name.
   * @return name
  **/
  @ApiModelProperty(value = "The club's name.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetaClub metaClub = (MetaClub) o;
    return Objects.equals(this.id, metaClub.id) &&
        Objects.equals(this.resourceState, metaClub.resourceState) &&
        Objects.equals(this.name, metaClub.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resourceState, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetaClub {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceState: ").append(toIndentedString(resourceState)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

