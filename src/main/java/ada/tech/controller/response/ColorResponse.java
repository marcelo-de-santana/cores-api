package ada.tech.controller.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ColorResponse {
    private Long id;
    private String name;
    private String hexadecimal;
}
