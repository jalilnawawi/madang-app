package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.imageDto.request.ImageAddRequestDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.entity.Product;
import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.repository.ImageRepository;
import com.jalil_be_app.madang_app.repository.ProductRepository;
import com.jalil_be_app.madang_app.service.ImageService;
import com.jalil_be_app.madang_app.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.zip.DataFormatException;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image add(ImageAddRequestDto imageAddRequestDto) {
        Image image = new Image();
        image.setImageLink(imageAddRequestDto.getImageLink());
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

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image();
        image.setImageLink(fileName);
        image.setType(file.getContentType());
        image.setImageData(file.getBytes());

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
    public Image unduhImage(UUID imageId) {
        Image existingImage = imageRepository.findById(imageId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "image not found")
        );

        return existingImage;
    }
}
