package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.imageDto.request.ImageAddRequestDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.entity.Product;
import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.repository.ImageRepository;
import com.jalil_be_app.madang_app.repository.ProductRepository;
import com.jalil_be_app.madang_app.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.Value;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image add(ImageAddRequestDto imageAddRequestDto) {
        Image image = new Image();
        image.setImageName(imageAddRequestDto.getImageLink());
        image.setCategory(ImageCategory.USER);
        return imageRepository.save(image);
    }

//    @Override
//    public String uploadImage(MultipartFile imageFile) throws IOException {
//        Image imageToSave = Image.builder()
//                .imageLink(imageFile.getOriginalFilename())
//                .type(imageFile.getContentType())
//                .imageData(ImageUtils.compressImage(imageFile.getBytes()))
//                .build();
//        imageRepository.save(imageToSave);
//        return "file uploaded successfully : " + imageFile.getOriginalFilename();
//    }

    @Transactional
    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image();
        image.setImageName(fileName);
        image.setType(file.getContentType());
        image.setImageData(file.getBytes());
        image = imageRepository.save(image);

        String baseUrl = "madang-app.cloud";
//        String baseUrl = "localhost:8080";
        String imageLink = baseUrl + "/api/v1/image/get-image/" + image.getId();
        //set image link after save
        image.setImageLink(imageLink);

        return imageRepository.save(image);
    }

    @Override
    public Product uploadProductImage(UUID productId, MultipartFile file) throws IOException {
        Product existingProduct = productRepository.findById(productId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found")
        );

        Image image = imageRepository.save(uploadImage(file));
        existingProduct.setImage(image);
        return productRepository.save(existingProduct);
    }

//    @Override
//    public byte[] downloadImage(UUID imageId) {
//        return imageRepository.findById(imageId)
//                .map(image -> {
//                    try {
//                        return ImageUtils.decompressImage(image.getImageData());
//                    } catch (DataFormatException | IOException exception){
//                        throw new RuntimeException(
//                                String.format("Error downloading image. Image ID : %s, Image name : %s",
//                                        image.getId(), image.getImageLink()), exception);
//                    }
//                })
//                .orElseThrow(
//                        () -> new NoSuchElementException(
//                                String.format("Image with name '%s' not found", imageId)
//                        ));
//
//    }

    @Override
    public Image downloadImage(UUID imageId) {
        Image existingImage = imageRepository.findById(imageId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "image not found")
        );

        return existingImage;
    }
}
