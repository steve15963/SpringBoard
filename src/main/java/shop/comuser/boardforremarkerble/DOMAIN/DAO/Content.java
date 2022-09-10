package shop.comuser.boardforremarkerble.DOMAIN.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.ContentRequest;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Content.Value.ContentRequestStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @CreatedDate
    private LocalDateTime CreateAt;

    @ManyToOne
    private User user;

    @LastModifiedDate
    private LocalDateTime UpdateAt;
    private int views;
    private int thumbsUp;
    private int thumbsDown;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "content")
    private List<Comment> commentList;
    @ManyToOne
    private Board board;
    public void set(ContentRequest contentRequest,Board board,User user){
        this.title = contentRequest.getTitle();
        this.content = contentRequest.getContent();
        this.board = board;
        this.user = user;
        this.CreateAt = LocalDateTime.now();
        views = 0;
        thumbsUp = 0;
        thumbsDown = 0;
    }
    public void set(ContentRequest contentRequest){
        this.title = contentRequest.getTitle();
        this.content = contentRequest.getContent();
        this.UpdateAt = LocalDateTime.now();
    }
    public ContentRequestStatus equalsRequest(ContentRequest contentRequest){
        if(!this.title.equals(contentRequest.getTitle())) return ContentRequestStatus.NOT_EQUALS_TITLE;
        if(!this.content.equals(contentRequest.getContent())) return ContentRequestStatus.NOT_EQUALS_CONTENT;
        return ContentRequestStatus.OK;
    }
}
