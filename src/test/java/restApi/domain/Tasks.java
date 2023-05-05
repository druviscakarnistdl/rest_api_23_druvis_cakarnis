package restApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tasks {
    @JsonProperty("id")
    private String taskId;

    @JsonProperty("name")
    private String taskName;
}
