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
 * 
 */
@ApiModel(description = "")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-03-12T15:31:53.751+01:00")
public class RouteDirection {
  @SerializedName("distance")
  private Float distance = null;

  @SerializedName("action")
  private Integer action = null;

  @SerializedName("name")
  private String name = null;

  public RouteDirection distance(Float distance) {
    this.distance = distance;
    return this;
  }

   /**
   * The distance in the route at which the action applies
   * @return distance
  **/
  @ApiModelProperty(value = "The distance in the route at which the action applies")
  public Float getDistance() {
    return distance;
  }

  public void setDistance(Float distance) {
    this.distance = distance;
  }

  public RouteDirection action(Integer action) {
    this.action = action;
    return this;
  }

   /**
   * The action of this direction
   * @return action
  **/
  @ApiModelProperty(value = "The action of this direction")
  public Integer getAction() {
    return action;
  }

  public void setAction(Integer action) {
    this.action = action;
  }

  public RouteDirection name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
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
    RouteDirection routeDirection = (RouteDirection) o;
    return Objects.equals(this.distance, routeDirection.distance) &&
        Objects.equals(this.action, routeDirection.action) &&
        Objects.equals(this.name, routeDirection.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(distance, action, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RouteDirection {\n");
    
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
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

