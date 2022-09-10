package shop.comuser.boardforremarkerble.DOMAIN.DTO.RESPONSE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Content;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentResponse {
    private String title;
    private String content;
    public void set(Content content){
        this.title = content.getTitle();
        this.content = content.getContent();
    }
}
