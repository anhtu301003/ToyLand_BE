package com.toyland.product_service.dto.request;


import com.toyland.product_service.Enum.StatusProduct;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String sku;// mã sản phẩm duy nhất
    String name;//tên sản phẩm
    String url;//url sản phẩm
    String content; //mô tả sản phẩm
    Long price;//giá sản phẩm
    Long originalPrice;//giá gốc sản phẩm
    CategoryRequest category;
    BrandRequest brand;
    List<String> Tags;
    Boolean isLimitedEdition;//hàng giới hạn
    LocalDateTime releaseDate;//ngày phát hành
    StatusProduct statusProduct; // trạng thái sản phẩm(đang bán, ngừng bán, sắp ra mắt, hết hàng)
    Boolean trending;//đánh dấu sản phẩm nội bật để hiện thị ở vị trí đặc biệt
    Boolean newProduct;// đánh dấu là sản phẩm mới
    int viewsCount; //lượt xem
    List<String> image;
}
