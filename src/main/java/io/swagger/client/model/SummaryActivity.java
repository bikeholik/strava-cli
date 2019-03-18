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
import io.swagger.client.model.ActivityType;
import io.swagger.client.model.LatLng;
import io.swagger.client.model.MetaActivity;
import io.swagger.client.model.MetaAthlete;
import io.swagger.client.model.PolylineMap;
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;

/**
 * SummaryActivity
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-03-12T15:31:53.751+01:00")
public class SummaryActivity {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("external_id")
  private String externalId = null;

  @SerializedName("upload_id")
  private Long uploadId = null;

  @SerializedName("athlete")
  private MetaAthlete athlete = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("distance")
  private Float distance = null;

  @SerializedName("moving_time")
  private Integer movingTime = null;

  @SerializedName("elapsed_time")
  private Integer elapsedTime = null;

  @SerializedName("total_elevation_gain")
  private Float totalElevationGain = null;

  @SerializedName("elev_high")
  private Float elevHigh = null;

  @SerializedName("elev_low")
  private Float elevLow = null;

  @SerializedName("type")
  private ActivityType type = null;

  @SerializedName("start_date")
  private OffsetDateTime startDate = null;

  @SerializedName("start_date_local")
  private OffsetDateTime startDateLocal = null;

  @SerializedName("timezone")
  private String timezone = null;

  @SerializedName("start_latlng")
  private LatLng startLatlng = null;

  @SerializedName("end_latlng")
  private LatLng endLatlng = null;

  @SerializedName("achievement_count")
  private Integer achievementCount = null;

  @SerializedName("kudos_count")
  private Integer kudosCount = null;

  @SerializedName("comment_count")
  private Integer commentCount = null;

  @SerializedName("athlete_count")
  private Integer athleteCount = null;

  @SerializedName("photo_count")
  private Integer photoCount = null;

  @SerializedName("total_photo_count")
  private Integer totalPhotoCount = null;

  @SerializedName("map")
  private PolylineMap map = null;

  @SerializedName("trainer")
  private Boolean trainer = null;

  @SerializedName("commute")
  private Boolean commute = null;

  @SerializedName("manual")
  private Boolean manual = null;

  @SerializedName("private")
  private Boolean _private = null;

  @SerializedName("flagged")
  private Boolean flagged = null;

  @SerializedName("workout_type")
  private Integer workoutType = null;

  @SerializedName("average_speed")
  private Float averageSpeed = null;

  @SerializedName("max_speed")
  private Float maxSpeed = null;

  @SerializedName("has_kudoed")
  private Boolean hasKudoed = null;

  @SerializedName("gear_id")
  private String gearId = null;

  @SerializedName("kilojoules")
  private Float kilojoules = null;

  @SerializedName("average_watts")
  private Float averageWatts = null;

  @SerializedName("device_watts")
  private Boolean deviceWatts = null;

  @SerializedName("max_watts")
  private Integer maxWatts = null;

  @SerializedName("weighted_average_watts")
  private Integer weightedAverageWatts = null;

  public SummaryActivity id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * The unique identifier of the activity
   * @return id
  **/
  @ApiModelProperty(value = "The unique identifier of the activity")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SummaryActivity externalId(String externalId) {
    this.externalId = externalId;
    return this;
  }

   /**
   * The identifier provided at upload time
   * @return externalId
  **/
  @ApiModelProperty(value = "The identifier provided at upload time")
  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public SummaryActivity uploadId(Long uploadId) {
    this.uploadId = uploadId;
    return this;
  }

   /**
   * The identifier of the upload that resulted in this activity
   * @return uploadId
  **/
  @ApiModelProperty(value = "The identifier of the upload that resulted in this activity")
  public Long getUploadId() {
    return uploadId;
  }

  public void setUploadId(Long uploadId) {
    this.uploadId = uploadId;
  }

  public SummaryActivity athlete(MetaAthlete athlete) {
    this.athlete = athlete;
    return this;
  }

   /**
   * Get athlete
   * @return athlete
  **/
  @ApiModelProperty(value = "")
  public MetaAthlete getAthlete() {
    return athlete;
  }

  public void setAthlete(MetaAthlete athlete) {
    this.athlete = athlete;
  }

  public SummaryActivity name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of the activity
   * @return name
  **/
  @ApiModelProperty(value = "The name of the activity")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SummaryActivity distance(Float distance) {
    this.distance = distance;
    return this;
  }

   /**
   * The activity&#39;s distance, in meters
   * @return distance
  **/
  @ApiModelProperty(value = "The activity's distance, in meters")
  public Float getDistance() {
    return distance;
  }

  public void setDistance(Float distance) {
    this.distance = distance;
  }

  public SummaryActivity movingTime(Integer movingTime) {
    this.movingTime = movingTime;
    return this;
  }

   /**
   * The activity&#39;s moving time, in seconds
   * @return movingTime
  **/
  @ApiModelProperty(value = "The activity's moving time, in seconds")
  public Integer getMovingTime() {
    return movingTime;
  }

  public void setMovingTime(Integer movingTime) {
    this.movingTime = movingTime;
  }

  public SummaryActivity elapsedTime(Integer elapsedTime) {
    this.elapsedTime = elapsedTime;
    return this;
  }

   /**
   * The activity&#39;s elapsed time, in seconds
   * @return elapsedTime
  **/
  @ApiModelProperty(value = "The activity's elapsed time, in seconds")
  public Integer getElapsedTime() {
    return elapsedTime;
  }

  public void setElapsedTime(Integer elapsedTime) {
    this.elapsedTime = elapsedTime;
  }

  public SummaryActivity totalElevationGain(Float totalElevationGain) {
    this.totalElevationGain = totalElevationGain;
    return this;
  }

   /**
   * The activity&#39;s total elevation gain.
   * @return totalElevationGain
  **/
  @ApiModelProperty(value = "The activity's total elevation gain.")
  public Float getTotalElevationGain() {
    return totalElevationGain;
  }

  public void setTotalElevationGain(Float totalElevationGain) {
    this.totalElevationGain = totalElevationGain;
  }

  public SummaryActivity elevHigh(Float elevHigh) {
    this.elevHigh = elevHigh;
    return this;
  }

   /**
   * The activity&#39;s highest elevation, in meters
   * @return elevHigh
  **/
  @ApiModelProperty(value = "The activity's highest elevation, in meters")
  public Float getElevHigh() {
    return elevHigh;
  }

  public void setElevHigh(Float elevHigh) {
    this.elevHigh = elevHigh;
  }

  public SummaryActivity elevLow(Float elevLow) {
    this.elevLow = elevLow;
    return this;
  }

   /**
   * The activity&#39;s lowest elevation, in meters
   * @return elevLow
  **/
  @ApiModelProperty(value = "The activity's lowest elevation, in meters")
  public Float getElevLow() {
    return elevLow;
  }

  public void setElevLow(Float elevLow) {
    this.elevLow = elevLow;
  }

  public SummaryActivity type(ActivityType type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")
  public ActivityType getType() {
    return type;
  }

  public void setType(ActivityType type) {
    this.type = type;
  }

  public SummaryActivity startDate(OffsetDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

   /**
   * The time at which the activity was started.
   * @return startDate
  **/
  @ApiModelProperty(value = "The time at which the activity was started.")
  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  public SummaryActivity startDateLocal(OffsetDateTime startDateLocal) {
    this.startDateLocal = startDateLocal;
    return this;
  }

   /**
   * The time at which the activity was started in the local timezone.
   * @return startDateLocal
  **/
  @ApiModelProperty(value = "The time at which the activity was started in the local timezone.")
  public OffsetDateTime getStartDateLocal() {
    return startDateLocal;
  }

  public void setStartDateLocal(OffsetDateTime startDateLocal) {
    this.startDateLocal = startDateLocal;
  }

  public SummaryActivity timezone(String timezone) {
    this.timezone = timezone;
    return this;
  }

   /**
   * The timezone of the activity
   * @return timezone
  **/
  @ApiModelProperty(value = "The timezone of the activity")
  public String getTimezone() {
    return timezone;
  }

  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  public SummaryActivity startLatlng(LatLng startLatlng) {
    this.startLatlng = startLatlng;
    return this;
  }

   /**
   * Get startLatlng
   * @return startLatlng
  **/
  @ApiModelProperty(value = "")
  public LatLng getStartLatlng() {
    return startLatlng;
  }

  public void setStartLatlng(LatLng startLatlng) {
    this.startLatlng = startLatlng;
  }

  public SummaryActivity endLatlng(LatLng endLatlng) {
    this.endLatlng = endLatlng;
    return this;
  }

   /**
   * Get endLatlng
   * @return endLatlng
  **/
  @ApiModelProperty(value = "")
  public LatLng getEndLatlng() {
    return endLatlng;
  }

  public void setEndLatlng(LatLng endLatlng) {
    this.endLatlng = endLatlng;
  }

  public SummaryActivity achievementCount(Integer achievementCount) {
    this.achievementCount = achievementCount;
    return this;
  }

   /**
   * The number of achievements gained during this activity
   * @return achievementCount
  **/
  @ApiModelProperty(value = "The number of achievements gained during this activity")
  public Integer getAchievementCount() {
    return achievementCount;
  }

  public void setAchievementCount(Integer achievementCount) {
    this.achievementCount = achievementCount;
  }

  public SummaryActivity kudosCount(Integer kudosCount) {
    this.kudosCount = kudosCount;
    return this;
  }

   /**
   * The number of kudos given for this activity
   * @return kudosCount
  **/
  @ApiModelProperty(value = "The number of kudos given for this activity")
  public Integer getKudosCount() {
    return kudosCount;
  }

  public void setKudosCount(Integer kudosCount) {
    this.kudosCount = kudosCount;
  }

  public SummaryActivity commentCount(Integer commentCount) {
    this.commentCount = commentCount;
    return this;
  }

   /**
   * The number of comments for this activity
   * @return commentCount
  **/
  @ApiModelProperty(value = "The number of comments for this activity")
  public Integer getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }

  public SummaryActivity athleteCount(Integer athleteCount) {
    this.athleteCount = athleteCount;
    return this;
  }

   /**
   * The number of athletes for taking part in a group activity
   * minimum: 1
   * @return athleteCount
  **/
  @ApiModelProperty(value = "The number of athletes for taking part in a group activity")
  public Integer getAthleteCount() {
    return athleteCount;
  }

  public void setAthleteCount(Integer athleteCount) {
    this.athleteCount = athleteCount;
  }

  public SummaryActivity photoCount(Integer photoCount) {
    this.photoCount = photoCount;
    return this;
  }

   /**
   * The number of Instagram photos for this activity
   * @return photoCount
  **/
  @ApiModelProperty(value = "The number of Instagram photos for this activity")
  public Integer getPhotoCount() {
    return photoCount;
  }

  public void setPhotoCount(Integer photoCount) {
    this.photoCount = photoCount;
  }

  public SummaryActivity totalPhotoCount(Integer totalPhotoCount) {
    this.totalPhotoCount = totalPhotoCount;
    return this;
  }

   /**
   * The number of Instagram and Strava photos for this activity
   * @return totalPhotoCount
  **/
  @ApiModelProperty(value = "The number of Instagram and Strava photos for this activity")
  public Integer getTotalPhotoCount() {
    return totalPhotoCount;
  }

  public void setTotalPhotoCount(Integer totalPhotoCount) {
    this.totalPhotoCount = totalPhotoCount;
  }

  public SummaryActivity map(PolylineMap map) {
    this.map = map;
    return this;
  }

   /**
   * Get map
   * @return map
  **/
  @ApiModelProperty(value = "")
  public PolylineMap getMap() {
    return map;
  }

  public void setMap(PolylineMap map) {
    this.map = map;
  }

  public SummaryActivity trainer(Boolean trainer) {
    this.trainer = trainer;
    return this;
  }

   /**
   * Whether this activity was recorded on a training machine
   * @return trainer
  **/
  @ApiModelProperty(value = "Whether this activity was recorded on a training machine")
  public Boolean isTrainer() {
    return trainer;
  }

  public void setTrainer(Boolean trainer) {
    this.trainer = trainer;
  }

  public SummaryActivity commute(Boolean commute) {
    this.commute = commute;
    return this;
  }

   /**
   * Whether this activity is a commute
   * @return commute
  **/
  @ApiModelProperty(value = "Whether this activity is a commute")
  public Boolean isCommute() {
    return commute;
  }

  public void setCommute(Boolean commute) {
    this.commute = commute;
  }

  public SummaryActivity manual(Boolean manual) {
    this.manual = manual;
    return this;
  }

   /**
   * Whether this activity was created manually
   * @return manual
  **/
  @ApiModelProperty(value = "Whether this activity was created manually")
  public Boolean isManual() {
    return manual;
  }

  public void setManual(Boolean manual) {
    this.manual = manual;
  }

  public SummaryActivity _private(Boolean _private) {
    this._private = _private;
    return this;
  }

   /**
   * Whether this activity is private
   * @return _private
  **/
  @ApiModelProperty(value = "Whether this activity is private")
  public Boolean isPrivate() {
    return _private;
  }

  public void setPrivate(Boolean _private) {
    this._private = _private;
  }

  public SummaryActivity flagged(Boolean flagged) {
    this.flagged = flagged;
    return this;
  }

   /**
   * Whether this activity is flagged
   * @return flagged
  **/
  @ApiModelProperty(value = "Whether this activity is flagged")
  public Boolean isFlagged() {
    return flagged;
  }

  public void setFlagged(Boolean flagged) {
    this.flagged = flagged;
  }

  public SummaryActivity workoutType(Integer workoutType) {
    this.workoutType = workoutType;
    return this;
  }

   /**
   * The activity&#39;s workout type
   * @return workoutType
  **/
  @ApiModelProperty(value = "The activity's workout type")
  public Integer getWorkoutType() {
    return workoutType;
  }

  public void setWorkoutType(Integer workoutType) {
    this.workoutType = workoutType;
  }

  public SummaryActivity averageSpeed(Float averageSpeed) {
    this.averageSpeed = averageSpeed;
    return this;
  }

   /**
   * The activity&#39;s average speed, in meters per second
   * @return averageSpeed
  **/
  @ApiModelProperty(value = "The activity's average speed, in meters per second")
  public Float getAverageSpeed() {
    return averageSpeed;
  }

  public void setAverageSpeed(Float averageSpeed) {
    this.averageSpeed = averageSpeed;
  }

  public SummaryActivity maxSpeed(Float maxSpeed) {
    this.maxSpeed = maxSpeed;
    return this;
  }

   /**
   * The activity&#39;s max speed, in meters per second
   * @return maxSpeed
  **/
  @ApiModelProperty(value = "The activity's max speed, in meters per second")
  public Float getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(Float maxSpeed) {
    this.maxSpeed = maxSpeed;
  }

  public SummaryActivity hasKudoed(Boolean hasKudoed) {
    this.hasKudoed = hasKudoed;
    return this;
  }

   /**
   * Whether the logged-in athlete has kudoed this activity
   * @return hasKudoed
  **/
  @ApiModelProperty(value = "Whether the logged-in athlete has kudoed this activity")
  public Boolean isHasKudoed() {
    return hasKudoed;
  }

  public void setHasKudoed(Boolean hasKudoed) {
    this.hasKudoed = hasKudoed;
  }

  public SummaryActivity gearId(String gearId) {
    this.gearId = gearId;
    return this;
  }

   /**
   * The id of the gear for the activity
   * @return gearId
  **/
  @ApiModelProperty(value = "The id of the gear for the activity")
  public String getGearId() {
    return gearId;
  }

  public void setGearId(String gearId) {
    this.gearId = gearId;
  }

  public SummaryActivity kilojoules(Float kilojoules) {
    this.kilojoules = kilojoules;
    return this;
  }

   /**
   * The total work done in kilojoules during this activity. Rides only
   * @return kilojoules
  **/
  @ApiModelProperty(value = "The total work done in kilojoules during this activity. Rides only")
  public Float getKilojoules() {
    return kilojoules;
  }

  public void setKilojoules(Float kilojoules) {
    this.kilojoules = kilojoules;
  }

  public SummaryActivity averageWatts(Float averageWatts) {
    this.averageWatts = averageWatts;
    return this;
  }

   /**
   * Average power output in watts during this activity. Rides only
   * @return averageWatts
  **/
  @ApiModelProperty(value = "Average power output in watts during this activity. Rides only")
  public Float getAverageWatts() {
    return averageWatts;
  }

  public void setAverageWatts(Float averageWatts) {
    this.averageWatts = averageWatts;
  }

  public SummaryActivity deviceWatts(Boolean deviceWatts) {
    this.deviceWatts = deviceWatts;
    return this;
  }

   /**
   * Whether the watts are from a power meter, false if estimated
   * @return deviceWatts
  **/
  @ApiModelProperty(value = "Whether the watts are from a power meter, false if estimated")
  public Boolean isDeviceWatts() {
    return deviceWatts;
  }

  public void setDeviceWatts(Boolean deviceWatts) {
    this.deviceWatts = deviceWatts;
  }

  public SummaryActivity maxWatts(Integer maxWatts) {
    this.maxWatts = maxWatts;
    return this;
  }

   /**
   * Rides with power meter data only
   * @return maxWatts
  **/
  @ApiModelProperty(value = "Rides with power meter data only")
  public Integer getMaxWatts() {
    return maxWatts;
  }

  public void setMaxWatts(Integer maxWatts) {
    this.maxWatts = maxWatts;
  }

  public SummaryActivity weightedAverageWatts(Integer weightedAverageWatts) {
    this.weightedAverageWatts = weightedAverageWatts;
    return this;
  }

   /**
   * Similar to Normalized Power. Rides with power meter data only
   * @return weightedAverageWatts
  **/
  @ApiModelProperty(value = "Similar to Normalized Power. Rides with power meter data only")
  public Integer getWeightedAverageWatts() {
    return weightedAverageWatts;
  }

  public void setWeightedAverageWatts(Integer weightedAverageWatts) {
    this.weightedAverageWatts = weightedAverageWatts;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SummaryActivity summaryActivity = (SummaryActivity) o;
    return Objects.equals(this.id, summaryActivity.id) &&
        Objects.equals(this.externalId, summaryActivity.externalId) &&
        Objects.equals(this.uploadId, summaryActivity.uploadId) &&
        Objects.equals(this.athlete, summaryActivity.athlete) &&
        Objects.equals(this.name, summaryActivity.name) &&
        Objects.equals(this.distance, summaryActivity.distance) &&
        Objects.equals(this.movingTime, summaryActivity.movingTime) &&
        Objects.equals(this.elapsedTime, summaryActivity.elapsedTime) &&
        Objects.equals(this.totalElevationGain, summaryActivity.totalElevationGain) &&
        Objects.equals(this.elevHigh, summaryActivity.elevHigh) &&
        Objects.equals(this.elevLow, summaryActivity.elevLow) &&
        Objects.equals(this.type, summaryActivity.type) &&
        Objects.equals(this.startDate, summaryActivity.startDate) &&
        Objects.equals(this.startDateLocal, summaryActivity.startDateLocal) &&
        Objects.equals(this.timezone, summaryActivity.timezone) &&
        Objects.equals(this.startLatlng, summaryActivity.startLatlng) &&
        Objects.equals(this.endLatlng, summaryActivity.endLatlng) &&
        Objects.equals(this.achievementCount, summaryActivity.achievementCount) &&
        Objects.equals(this.kudosCount, summaryActivity.kudosCount) &&
        Objects.equals(this.commentCount, summaryActivity.commentCount) &&
        Objects.equals(this.athleteCount, summaryActivity.athleteCount) &&
        Objects.equals(this.photoCount, summaryActivity.photoCount) &&
        Objects.equals(this.totalPhotoCount, summaryActivity.totalPhotoCount) &&
        Objects.equals(this.map, summaryActivity.map) &&
        Objects.equals(this.trainer, summaryActivity.trainer) &&
        Objects.equals(this.commute, summaryActivity.commute) &&
        Objects.equals(this.manual, summaryActivity.manual) &&
        Objects.equals(this._private, summaryActivity._private) &&
        Objects.equals(this.flagged, summaryActivity.flagged) &&
        Objects.equals(this.workoutType, summaryActivity.workoutType) &&
        Objects.equals(this.averageSpeed, summaryActivity.averageSpeed) &&
        Objects.equals(this.maxSpeed, summaryActivity.maxSpeed) &&
        Objects.equals(this.hasKudoed, summaryActivity.hasKudoed) &&
        Objects.equals(this.gearId, summaryActivity.gearId) &&
        Objects.equals(this.kilojoules, summaryActivity.kilojoules) &&
        Objects.equals(this.averageWatts, summaryActivity.averageWatts) &&
        Objects.equals(this.deviceWatts, summaryActivity.deviceWatts) &&
        Objects.equals(this.maxWatts, summaryActivity.maxWatts) &&
        Objects.equals(this.weightedAverageWatts, summaryActivity.weightedAverageWatts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, externalId, uploadId, athlete, name, distance, movingTime, elapsedTime, totalElevationGain, elevHigh, elevLow, type, startDate, startDateLocal, timezone, startLatlng, endLatlng, achievementCount, kudosCount, commentCount, athleteCount, photoCount, totalPhotoCount, map, trainer, commute, manual, _private, flagged, workoutType, averageSpeed, maxSpeed, hasKudoed, gearId, kilojoules, averageWatts, deviceWatts, maxWatts, weightedAverageWatts);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SummaryActivity {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
    sb.append("    uploadId: ").append(toIndentedString(uploadId)).append("\n");
    sb.append("    athlete: ").append(toIndentedString(athlete)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    movingTime: ").append(toIndentedString(movingTime)).append("\n");
    sb.append("    elapsedTime: ").append(toIndentedString(elapsedTime)).append("\n");
    sb.append("    totalElevationGain: ").append(toIndentedString(totalElevationGain)).append("\n");
    sb.append("    elevHigh: ").append(toIndentedString(elevHigh)).append("\n");
    sb.append("    elevLow: ").append(toIndentedString(elevLow)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    startDateLocal: ").append(toIndentedString(startDateLocal)).append("\n");
    sb.append("    timezone: ").append(toIndentedString(timezone)).append("\n");
    sb.append("    startLatlng: ").append(toIndentedString(startLatlng)).append("\n");
    sb.append("    endLatlng: ").append(toIndentedString(endLatlng)).append("\n");
    sb.append("    achievementCount: ").append(toIndentedString(achievementCount)).append("\n");
    sb.append("    kudosCount: ").append(toIndentedString(kudosCount)).append("\n");
    sb.append("    commentCount: ").append(toIndentedString(commentCount)).append("\n");
    sb.append("    athleteCount: ").append(toIndentedString(athleteCount)).append("\n");
    sb.append("    photoCount: ").append(toIndentedString(photoCount)).append("\n");
    sb.append("    totalPhotoCount: ").append(toIndentedString(totalPhotoCount)).append("\n");
    sb.append("    map: ").append(toIndentedString(map)).append("\n");
    sb.append("    trainer: ").append(toIndentedString(trainer)).append("\n");
    sb.append("    commute: ").append(toIndentedString(commute)).append("\n");
    sb.append("    manual: ").append(toIndentedString(manual)).append("\n");
    sb.append("    _private: ").append(toIndentedString(_private)).append("\n");
    sb.append("    flagged: ").append(toIndentedString(flagged)).append("\n");
    sb.append("    workoutType: ").append(toIndentedString(workoutType)).append("\n");
    sb.append("    averageSpeed: ").append(toIndentedString(averageSpeed)).append("\n");
    sb.append("    maxSpeed: ").append(toIndentedString(maxSpeed)).append("\n");
    sb.append("    hasKudoed: ").append(toIndentedString(hasKudoed)).append("\n");
    sb.append("    gearId: ").append(toIndentedString(gearId)).append("\n");
    sb.append("    kilojoules: ").append(toIndentedString(kilojoules)).append("\n");
    sb.append("    averageWatts: ").append(toIndentedString(averageWatts)).append("\n");
    sb.append("    deviceWatts: ").append(toIndentedString(deviceWatts)).append("\n");
    sb.append("    maxWatts: ").append(toIndentedString(maxWatts)).append("\n");
    sb.append("    weightedAverageWatts: ").append(toIndentedString(weightedAverageWatts)).append("\n");
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

