package common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class Response {
    @Schema(example="성공")
    String message;
    @Schema(example="result")
    Map<String,Object> result;
}
