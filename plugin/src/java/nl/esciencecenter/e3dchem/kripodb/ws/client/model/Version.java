/*
 * Kripo API
 * API to interact with Kripo fragment, fingerprint and similarity data files.
 *
 * OpenAPI spec version: 2.3.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package nl.esciencecenter.e3dchem.kripodb.ws.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.io.Serializable;

/**
 * Version response
 */
@ApiModel(description = "Version response")

public class Version implements Serializable {
  private static final long serialVersionUID = 1L;

  @SerializedName("version")
  private String version = null;

  public Version version(String version) {
    this.version = version;
    return this;
  }

   /**
   * The semantic version
   * @return version
  **/
  @ApiModelProperty(required = true, value = "The semantic version")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Version version = (Version) o;
    return Objects.equals(this.version, version.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Version {\n");
    
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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

