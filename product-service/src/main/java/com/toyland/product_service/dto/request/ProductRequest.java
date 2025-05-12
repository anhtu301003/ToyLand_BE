package com.toyland.product_service.dto.request;


import com.toyland.product_service.Enum.StatusProduct;
import com.toyland.product_service.entity.Attribute;
import com.toyland.product_service.entity.Brand;
import com.toyland.product_service.entity.Category;
import com.toyland.product_service.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String sku;// mã sản phẩm duy nhất
    String name;//tên sản phẩm

    String slug;//url sản phẩm

    String description; //mô tả sản phẩm
    String shortDescription;//mô tả ngắn của sản phẩm

    Long price;//giá sản phẩm
    Long originalPrice;//giá gốc sản phẩm

    CategoryRequest category;

    BrandRequest brand;

    List<String> Tags;

    Boolean isLimitedEdition;//hàng giới hạn
    Integer LimitedQuantity;// số lượng giới hạn
    Date releaseDate;//ngày phát hành
    StatusProduct statusProduct; // trạng thái sản phẩm(đang bán, ngừng bán, sắp ra mắt, hết hàng)

    boolean trending;//đánh dấu sản phẩm nội bật để hiện thị ở vị trí đặc biệt

    boolean newProduct;// đánh dấu là sản phẩm mới

    int viewsCount; //lượt xem

    List<String> image;
}
