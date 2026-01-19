package ada.tech.service;

import ada.tech.controller.request.ColorRequest;
import ada.tech.controller.response.ColorResponse;
import ada.tech.domain.entity.ColorEntity;
import ada.tech.domain.repository.ColorRepository;
import ada.tech.mapper.ColorMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ada.tech.mapper.ColorMapper.toEntity;
import static ada.tech.mapper.ColorMapper.toResponse;

@RequiredArgsConstructor
public class ColorService {

    private ColorRepository colorRepository;
    private Logger logger;

    public List<ColorResponse> findAll() {
        return colorRepository.findAll().stream().map(ColorMapper::toResponse).toList();
    }

    public ColorResponse findById(Long id) {
        return toResponse(colorRepository.findById(id));
    }

    @Transactional
    public ColorResponse save(ColorRequest request) {
        ColorEntity colorEntity = toEntity(request);

        colorRepository.persist(colorEntity);

        logger.info(colorEntity.toString());

        return toResponse(colorEntity);
    }

    public void update(Long id, ColorRequest request) {
        ColorEntity colorEntity = colorRepository.findById(id);

        if (!request.name().isBlank()) {
            colorEntity.setName(request.name());
        }

        if (!request.hexadecimal().isBlank()) {
            colorEntity.setHexadecimal(request.hexadecimal());
        }

        colorRepository.persist(colorEntity);
    }

    @Transactional
    public boolean delete(Long id) {
        return colorRepository.deleteById(id);
    }
}
