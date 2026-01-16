package ada.tech.mapper;

import ada.tech.controller.request.ColorRequest;
import ada.tech.controller.response.ColorResponse;
import ada.tech.domain.entity.ColorEntity;

public class ColorMapper {
    public static ColorEntity toEntity(ColorRequest request) {
        if (request == null) {
            return null;
        }

        return ColorEntity.builder()
                .id(request.id())
                .name(request.name())
                .hexadecimal(request.hexadecimal())
                .build();
    }


    public static ColorResponse toResponse(ColorEntity entity) {
        if (entity == null) {
            return null;
        }

        return ColorResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .hexadecimal(entity.getHexadecimal())
                .build();
    }

}
