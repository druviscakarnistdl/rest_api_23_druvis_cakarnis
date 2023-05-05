package restApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Folder {
    @JsonProperty("id")
    private String folderId;

    @JsonProperty("name")
    private String folderName;

}
