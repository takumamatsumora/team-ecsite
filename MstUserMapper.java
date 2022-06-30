package jp.co.internous.cony.model.mapper;





import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.internous.cony.model.domain.MstUser;


@Mapper
public interface MstUserMapper {
	
	
	MstUser findByUserNameAndPassword(
			@Param("userName") String userName,
			@Param("password") String password);

	//User情報をinsert
	int insert(MstUser mstUser);
	
	//ユーザー名チェック
	int findCountByUserName(String userName);
	
	//パスワードチェック
	int updatePassword(
			 @Param("userName")String userName,
	 		 @Param("password")String password);
}
