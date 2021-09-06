package com.market.dao;

import com.market.domain.Member;

public interface MemberDao {

    /**
     * 根据memberId查找Member
     * @param id
     * @return
     */
    Member findById(String id);
}
