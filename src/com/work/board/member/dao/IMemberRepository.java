package com.work.board.member.dao;

import java.util.List;

import com.work.board.member.model.Member;

public interface IMemberRepository {
	void insertMember(Member member) ;
<<<<<<< HEAD
	int idCheck(String userid);
	Member selectMember(String userid);
	List<Member> selectAllMembers();
	void updateMember(Member member);
	void deleteMember(Member member);
	String getPassword(String userid);
	String updateBoardName(String name);
=======
	Member selectMember(String userid);
	List<Member> selectAllMembers();
	void updateMember(Member member);
	void deleteMember(Member member);
	String getPassword(String userid);
>>>>>>> refs/remotes/origin/master
}
