package com.work.board.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.board.member.dao.IMemberRepository;
import com.work.board.member.model.Member;
import com.work.board.member.service.IMemberService;

@Service
public class MemberService implements IMemberService {

	@Autowired
	IMemberRepository memberDao;
	
	@Override
	public void insertMember(Member member) {
		memberDao.insertMember(member);
	}

	@Override
<<<<<<< HEAD
	public int idCheck(String userid) {
		return memberDao.idCheck(userid);
	}
	
	@Override
	public Member selectMember(String userid) {
		return memberDao.selectMember(userid);
	}

	@Override
	public List<Member> selectAllMembers() {
		return memberDao.selectAllMembers();
	}

	@Override
	public void updateMember(Member member) {
		memberDao.updateMember(member);
//		memberDao.updateBoardName(member.getName());
=======
	public Member selectMember(String userid) {
		return memberDao.selectMember(userid);
	}

	@Override
	public List<Member> selectAllMembers() {
		return memberDao.selectAllMembers();
	}

	@Override
	public void updateMember(Member member) {
		memberDao.updateMember(member);
>>>>>>> refs/remotes/origin/master
	}

	@Override
	public void deleteMember(Member member) {
		memberDao.deleteMember(member);
	}

	@Override
	public String getPassword(String userid) {
		return memberDao.getPassword(userid);
	}

}
