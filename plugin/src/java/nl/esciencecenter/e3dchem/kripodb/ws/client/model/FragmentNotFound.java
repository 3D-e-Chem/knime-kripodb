/*
 * Kripo API
 * API to interact with Kripo fragment, fingerprint and similarity data files.
 *
 * OpenAPI spec version: 2.2.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package nl.esciencecenter.e3dchem.kripodb.ws.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import nl.esciencecenter.e3dchem.kripodb.ws.client.model.Error;
import java.io.Serializable;

/**
 * FragmentNotFound
 */

public class FragmentNotFound implements Serializable {
  private static final long serialVersionUID = 1L;

  @SerializedName("status")
  private Integer status = null;

  @SerializedName("type")
  private String type = null;

  @SerializedName("detail")
  private String detail = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("identifier")
  private String identifier = null;

  public FragmentNotFound status(Integer status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(example = "null", required = true, value = "")
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public FragmentNotFound type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(example = "null", required = true, value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public FragmentNotFound detail(String detail) {
    this.detail = detail;
    return this;
  }

   /**
   * Get detail
   * @return detail
  **/
  @ApiModelProperty(example = "null", required = true, value = "")
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public FragmentNotFound title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(example = "null", required = true, value = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public FragmentNotFound identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

   /**
   * Identifier that could not be found
   * @return identifier
  **/
  @ApiModelProperty(example = "null", required = true, value = "Identifier that could not be found")
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FragmentNotFound fragmentNotFound = (FragmentNotFound) o;
    return Objects.equals(this.status, fragmentNotFound.status) &&
        Objects.equals(this.type, fragmentNotFound.type) &&
        Objects.equals(this.detail, fragmentNotFound.detail) &&
        Objects.equals(this.title, fragmentNotFound.title) &&
        Objects.equals(this.identifier, fragmentNotFound.identifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, type, detail, title, identifier);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FragmentNotFound {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    identifier: ").append(toIndentedString(identifier)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

