package com.toyland.product_service.entity;

import com.toyland.product_service.Enum.StatusProduct;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    String id;

    @Indexed(unique = true)
    String sku;// mã sản phẩm duy nhất

    @TextIndexed
    String name;//tên sản phẩm

    @Indexed(unique = true)
    String url;//url sản phẩm

    String content; //mô tả sản phẩm

    Long price;//giá sản phẩm

    Long originalPrice;//giá gốc sản phẩm

    @DBRef(lazy = true)
    Category category;

    @DBRef(lazy = true)
    Brand brand;

    List<String> Tags;

    @Builder.Default
    Boolean isLimitedEdition = false;//hàng giới hạn

    LocalDateTime releaseDate;//ngày phát hành

    @Indexed
    StatusProduct statusProduct = StatusProduct.AVAILABLE; // trạng thái sản phẩm(đang bán, ngừng bán, sắp ra mắt, hết hàng)

    @Builder.Default
    Boolean trending = false;//đánh dấu sản phẩm nội bật để hiện thị ở vị trí đặc biệt

    @Builder.Default
    Boolean newProduct = true;// đánh dấu là sản phẩm mới

    int viewsCount; //lượt xem

    List<String> image;

    @CreatedDate
    Date createdAt;

    @LastModifiedDate
    Date updatedAt;
}
