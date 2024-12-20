package com.jalil_be_app.madang_app.model.entity;
import org.hibernate.annotations.Type;

import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.model.enums.ImageSize;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_link")
    private String imageLink;

    @Enumerated(EnumType.STRING)
    private ImageCategory category;

    @Enumerated(EnumType.STRING)
    private ImageSize size;

    private String type;

    @Lob
    private byte[] imageData;

}
