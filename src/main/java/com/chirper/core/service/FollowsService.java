package com.chirper.core.service;

import com.chirper.core.data.repository.FollowsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowsService {
    private final FollowsRepository followsRepository;

    public List<String> findForUser(String user) {
        return followsRepository.findByUser(user);
    }

    public String addFollow(String user, String toFollow) {
        return followsRepository.save(user, toFollow);
    }
}
