package shop.comuser.boardforremarkerble.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Board;
import shop.comuser.boardforremarkerble.REPOSITORY.BoardRepository;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    public List<Board> getAllBoard(){
        return boardRepository.findAll();
    }

    public List<Board> getBoardContent(String boardName){
        return boardRepository.findAllByBoardNameOrderByIdDesc(boardName);
    }
}
