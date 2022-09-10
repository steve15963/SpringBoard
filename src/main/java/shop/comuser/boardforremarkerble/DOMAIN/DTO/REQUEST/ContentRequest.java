package shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentRequest {
    private Long id;
    private String title;
    private String content;
}
