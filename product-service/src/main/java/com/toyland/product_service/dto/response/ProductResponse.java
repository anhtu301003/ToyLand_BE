package com.toyland.product_service.dto.response;

import com.toyland.product_service.Enum.StatusProduct;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    String sku;// mã sản phẩm duy nhất
    String name;//tên sản phẩm
    String url;//url sản phẩm
    String content; //mô tả sản phẩm
    Long price;//giá sản phẩm
    Long originalPrice;//giá gốc sản phẩm
    CategoryResponse category;
    BrandResponse brand;
    List<String> Tags;
    Boolean isLimitedEdition;//hàng giới hạn
    LocalDateTime releaseDate;//ngày phát hành
    StatusProduct statusProduct; // trạng thái sản phẩm(đang bán, ngừng bán, sắp ra mắt, hết hàng)
    Boolean trending;//đánh dấu sản phẩm nội bật để hiện thị ở vị trí đặc biệt
    Boolean newProduct;// đánh dấu là sản phẩm mới
    int viewsCount; //lượt xem
    List<String> image;
    Date createdAt;
    Date updatedAt;
}
