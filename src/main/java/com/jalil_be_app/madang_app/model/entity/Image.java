package com.jalil_be_app.madang_app.model.entity;

import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.model.enums.ImageSize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "image_link")
    private String imageLink;
    private ImageCategory category;
    private ImageSize size;
}
