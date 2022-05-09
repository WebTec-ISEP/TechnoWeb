package org.techweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;
import org.techweb.entities.Image;
import org.techweb.entities.Message;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
