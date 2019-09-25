package com.work.board.member.service;

import java.util.List;

import com.work.board.member.model.Member;

public interface IMemberService {
<<<<<<< HEAD
	void insertMember(Member member);
	int idCheck(String userid);
	Member selectMember(String userid);
	List<Member> selectAllMembers();
	void updateMember(Member member);
	void deleteMember(Member member); 
=======
<<<<<<< HEAD
	void insertMember(Member member);
	int idCheck(String userid);
	Member selectMember(String userid);
	List<Member> selectAllMembers();
	void updateMember(Member member);
	void deleteMember(Member member); 
=======
	void insertMember(Member member) ;
	Member selectMember(String userid);
	List<Member> selectAllMembers();
	void updateMember(Member member);
	void deleteMember(Member member);
>>>>>>> refs/remotes/origin/master
>>>>>>> refs/remotes/origin/master
	String getPassword(String userid);
}
