package ada.tech.service;

import ada.tech.controller.request.ColorRequest;
import ada.tech.domain.entity.ColorEntity;
import ada.tech.domain.repository.ColorRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ada.tech.mapper.ColorMapper.toEntity;

@RequiredArgsConstructor
public class ColorService {

    private ColorRepository colorRepository;
    private Logger logger;

    public List<ColorEntity> findAll() {
        return new ArrayList<>();
    }

    public ColorEntity findById(Long id) {
        return colorRepository.findById(id);
    }

    public void save(ColorRequest request) {
        logger.info(String.valueOf(request));

        colorRepository.persist(toEntity(request));

        return colorRepository.findByName();
    }
}
