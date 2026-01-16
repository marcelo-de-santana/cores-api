package ada.tech.controller.request;

import ada.tech.domain.entity.ColorEntity;

public record ColorRequest(Long id, String name, String hexadecimal) {
}
