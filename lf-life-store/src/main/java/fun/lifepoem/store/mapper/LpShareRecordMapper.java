package fun.lifepoem.store.mapper;

import fun.lifepoem.store.domain.LpShareRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 55971
 * @description 针对表【lp_share_record】的数据库操作Mapper
 * @createDate 2023-02-13 23:10:45
 * @Entity fun.lifepoem.store.domain.LpShareRecord
 */
@Repository
public interface LpShareRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(LpShareRecord record);

    int insertSelective(LpShareRecord record);

    LpShareRecord selectByPrimaryKey(Long id);

    List<LpShareRecord> selectByUserAndFile(@Param("userId") int userID, @Param("fileId") String fileId);

    int updateByPrimaryKeySelective(LpShareRecord record);

    int updateByPrimaryKey(LpShareRecord record);

}
